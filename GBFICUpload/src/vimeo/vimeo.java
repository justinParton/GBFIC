package vimeo;

    
     
   // import com.kentcdodds.javahelper.helpers.PrinterHelper;
    import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.VimeoApi;
import org.scribe.exceptions.OAuthException;
import org.scribe.model.*;
import org.scribe.oauth.OAuthService;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

    @SuppressWarnings("unused")
    public class vimeo {
     
      private static OAuthService service;
      private static Token accessToken;
      private static String newline = System.getProperty("line.separator");
      private static int bufferSize = 1048576; // 1 MB = 1048576 bytes
      private String fileLocation;
      private String vTitle;
      private String vDescription;
      private String apiKey;
	  private String apiSecret;
      private String apitoken1;
      private String apitoken2;
      
      
      public vimeo(String file, String Title, String Descript,String vKey, String vSecret,String vtoken1,String vtoken2) {
    	 fileLocation = file;
     	 vTitle = Title;
     	 vDescription = Descript;
     	apiKey = vKey;
     	apiSecret = vSecret;
     	apitoken1 = vtoken1; 
     	apitoken2 = vtoken2;
     	Runtime rt = Runtime.getRuntime();
     	/*
     	try {
			Process pr = rt.exec("ffmpeg -i "+ fileLocation +" -f mp3 -ab 192000 -vn "+ fileLocation +"\\audio.mp3 -loglevel quiet");
		} catch (IOException e) {
			new vimPanel().textarea.append("error transcoding");
		}
     	*/
      }
	public String vimeoUpload(String[] args) throws Exception {
       
        String vimeoAPIURL = "http://vimeo.com/api/rest/v2";
        service = new ServiceBuilder().provider(VimeoApi.class).apiKey(apiKey).apiSecret(apiSecret).build();
        OAuthRequest request;
        Response response;
        
        accessToken = new Token(apitoken1, apitoken2); //Copy the new token you get here
     
        accessToken = checkToken(vimeoAPIURL, accessToken, service);
        if (accessToken == null) {
        	resultsPanel.textarea.append("No Access token was Inserted \n");
          return("Please Re-insert Access Token");
        }
     
        // Get Quota
        request = new OAuthRequest(Verb.GET, vimeoAPIURL);
        request.addQuerystringParameter("method", "vimeo.videos.upload.getQuota");
        signAndSendToVimeo(request, "getQuota", true);
     
        // Get Ticket
        request = new OAuthRequest(Verb.GET, vimeoAPIURL);
        request.addQuerystringParameter("method", "vimeo.videos.upload.getTicket");
        request.addQuerystringParameter("upload_method", "streaming");
        response = signAndSendToVimeo(request, "getTicket", true);
     
        // Get Endpoint and ticket ID
        resultsPanel.textarea.append("We're sending the video for upload!\n");
        Document doc = readXML(response.getBody());
        Element ticketElement = (Element) doc.getDocumentElement().getElementsByTagName("ticket").item(0);
        String endpoint = ticketElement.getAttribute("endpoint");
        String ticketId = ticketElement.getAttribute("id");
        
        // Setup File
        File testUp = new File(fileLocation);
        boolean sendVideo = sendVideo(endpoint, testUp);
        if (!sendVideo) {
        	resultsPanel.textarea.append("Cannot Connect to Vimeo, please Check Your Internet Connection.\n");
          throw new Exception("Can not connect to Vimeo.");
        }
     
        // Complete Upload
        request = new OAuthRequest(Verb.PUT, vimeoAPIURL);
        request.addQuerystringParameter("method", "vimeo.videos.upload.complete");
        request.addQuerystringParameter("filename", testUp.getName());
        request.addQuerystringParameter("ticket_id", ticketId);
        Response completeResponse = signAndSendToVimeo(request, "complete", true);
     
        //Set video info
        setVimeoVideoInfo(completeResponse, service, accessToken, vimeoAPIURL);
        resultsPanel.textarea.append("The Video has Been Successfully Submitted to Vimeo \n");
        return("The Video Has Been Successfully Submitted to Vimeo");
      }
     
      /**
       * Send the video data
       *
       * @return whether the video successfully sent
       */
      private static boolean sendVideo(String endpoint, File file) throws FileNotFoundException, IOException {
        // Setup File
        long contentLength = file.length();
        String contentLengthString = Long.toString(contentLength);
        @SuppressWarnings("resource")
		FileInputStream is = new FileInputStream(file);
        byte[] bytesPortion = new byte[bufferSize];
        int maxAttempts = 5; //This is the maximum attempts that will be given to resend data if the vimeo server doesn't have the right number of bytes for the given portion of the video
        long lastByteOnServer = 0;
        boolean first = false;
        while (is.read(bytesPortion, 0, bufferSize) != -1) {
          lastByteOnServer = prepareAndSendByteChunk(endpoint, contentLengthString, lastByteOnServer, bytesPortion, first, 0, maxAttempts);
          
          if (lastByteOnServer == -1) {
            return false;
          }
          first = true;
    //      getProgressBar().setValue(NumberHelper.getPercentFromTotal(byteNumber, getFileSize()));
        }
        return true;
      }
     
      /**
       * Prepares the given bytes to be sent to Vimeo
       *
       * @param endpoint
       * @param contentLengthString
       * @param lastByteOnServer
       * @param byteChunk
       * @param first
       * @param attempt
       * @param maxAttempts
       * @return number of bytes currently on the server
       * @throws FileNotFoundException
       * @throws IOException
       */
      private static long prepareAndSendByteChunk(String endpoint, String contentLengthString, long lastByteOnServer, byte[] byteChunk, boolean first, int attempt, int maxAttempts) throws FileNotFoundException, IOException {
        if (attempt > maxAttempts) {
          return -1;
        } else if (attempt > 0) {
          //System.out.println("Attempt number " + attempt + " for video " + "Test Video");
        	resultsPanel.textarea.append("Attempt number " + attempt + " for video " + "Test Video \n");
        }
        long totalBytesShouldBeOnServer = lastByteOnServer + byteChunk.length;
        String contentRange = lastByteOnServer + "-" + totalBytesShouldBeOnServer;
        long bytesOnServer = sendVideoBytes(endpoint, contentLengthString, "video/mp4", contentRange, byteChunk, first);
        if (bytesOnServer != totalBytesShouldBeOnServer) {
          //System.err.println(bytesOnServer + " (bytesOnServer)" + " != " + totalBytesShouldBeOnServer + " (totalBytesShouldBeOnServer)");
        	resultsPanel.textarea.append("Total Sent:" +bytesOnServer + "  Expected: " + totalBytesShouldBeOnServer + "\n");
          long remainingBytes = totalBytesShouldBeOnServer - bytesOnServer;
          int beginning = (int) (byteChunk.length - remainingBytes);
          int ending = (int) byteChunk.length;
          byte[] newByteChunk = Arrays.copyOfRange(byteChunk, beginning, ending);
          return prepareAndSendByteChunk(endpoint, contentLengthString, bytesOnServer, newByteChunk, first, attempt + 1, maxAttempts);
        } else {
          return bytesOnServer;
        }
      }
     
      /**
       * Sends the given bytes to the given endpoint
       *
       * @return the last byte on the server (from verifyUpload(endpoint))
       */
      private static long sendVideoBytes(String endpoint, String contentLength, String fileType, String contentRange, byte[] fileBytes, boolean addContentRange) throws FileNotFoundException, IOException {
        OAuthRequest request = new OAuthRequest(Verb.PUT, endpoint);
        request.addHeader("Content-Length", contentLength);
        request.addHeader("Content-Type", fileType);
        if (addContentRange) {
          request.addHeader("Content-Range", "bytes " + contentRange);
        }
        request.addPayload(fileBytes);
        Response response = signAndSendToVimeo(request, "sendVideo on " + "Test title", false);
        if (response.getCode() != 200 && !response.isSuccessful()) {
          return -1;
        }
        return verifyUpload(endpoint);
      }
     
      /**
       * Verifies the upload and returns whether it's successful
       *
       * @param endpoint to verify upload to
       * @return the last byte on the server
       */
      private static long verifyUpload(String endpoint) {
        // Verify the upload
        OAuthRequest request = new OAuthRequest(Verb.PUT, endpoint);
        request.addHeader("Content-Length", "0");
        request.addHeader("Content-Range", "bytes */*");
        Response response = signAndSendToVimeo(request, "verifyUpload to " + endpoint, true);
        if (response.getCode() != 308 || !response.isSuccessful()) {
          return -1;
        }
        String range = response.getHeader("Range");
        //range = "bytes=0-10485759"
        return Long.parseLong(range.substring(range.lastIndexOf("-") + 1)) + 1;
        //The + 1 at the end is because Vimeo gives you 0-whatever byte where 0 = the first byte
      }
     
      /**
      * Checks the token to make sure it's still valid. If not, it pops up a dialog asking the user to
      * authenticate.
      */
      private Token checkToken(String vimeoAPIURL, Token vimeoToken, OAuthService vimeoService) {
        if (vimeoToken == null) {
          vimeoToken = getNewToken(vimeoService);
        } else {
          OAuthRequest request = new OAuthRequest(Verb.GET, vimeoAPIURL);
          request.addQuerystringParameter("method", "vimeo.oauth.checkAccessToken");
          Response response = signAndSendToVimeo(request, "checkAccessToken", true);
          if (response.isSuccessful()
                  && (response.getCode() != 200 || response.getBody().contains("<err code=\"302\"")
                  || response.getBody().contains("<err code=\"401\""))) {
            vimeoToken = getNewToken(vimeoService);
          }
        }
        return vimeoToken;
      }
     
      /**
      * Gets authorization URL, pops up a dialog asking the user to authenticate with the url and the user
      * returns the authorization code
      *
      * @param service
      * @return
      */
      private Token getNewToken(OAuthService service) {
        // Obtain the Authorization URL
        Token requestToken = service.getRequestToken();
        String authorizationUrl = service.getAuthorizationUrl(requestToken);
        do {
          String code = JOptionPane.showInputDialog("The token for the account (whatever)" + newline
                  + "is either not set or is no longer valid." + newline
                  + "Please go to the URL below and authorize this application." + newline
                  + "Paste the code you're given on top of the URL here and click \'OK\'" + newline
                  + "(click the 'x' or input the letter 'q' to cancel." + newline
                  + "If you input an invalid code, I'll keep popping up).", authorizationUrl + "&permission=delete");
          if (code == null) {
            return null;
          }
          Verifier verifier = new Verifier(code);
          // Trade the Request Token and Verfier for the Access Token
          resultsPanel.textarea.append("Trading the Request Token for an Access Token...");
          resultsPanel.textarea.append("Trading the Request Token for an Access Token... \n");
          try {
            Token token = service.getAccessToken(requestToken, verifier);
            ////System.out.println(token); //Use this output to copy the token into your code so you don't have to do this over and over.
            return token;
          } catch (OAuthException ex) {
            int choice = JOptionPane.showConfirmDialog(null, "There was an OAuthException" + newline
                    + ex + newline
                    + "Would you like to try again?", "OAuthException", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.NO_OPTION) {
              break;
            }
          }
        } while (true);
        return null;
      }
     
      /**
       * Sets the video's meta-data
     * @return 
       */
      private void setVimeoVideoInfo(Response response, OAuthService service, Token token, String vimeoAPIURL) {
        OAuthRequest request;
        Document doc = readXML(response.getBody());
        org.w3c.dom.Element ticketElement = (org.w3c.dom.Element) doc.getDocumentElement().getElementsByTagName("ticket").item(0);
        String vimeoVideoId = ticketElement.getAttribute("video_id");
        //Set title, description, category, tags, private
        //Set Title
        request = new OAuthRequest(Verb.POST, vimeoAPIURL);
        request.addQuerystringParameter("method", "vimeo.videos.setTitle");
        request.addQuerystringParameter("title", vTitle);
        request.addQuerystringParameter("video_id", vimeoVideoId);
        signAndSendToVimeo(request, "setTitle", true);
     
        //Set description
        request = new OAuthRequest(Verb.POST, vimeoAPIURL);
        request.addQuerystringParameter("method", "vimeo.videos.setDescription");
        request.addQuerystringParameter("description", vDescription);
        request.addQuerystringParameter("video_id", vimeoVideoId);
        signAndSendToVimeo(request, "setDescription", true);
     
        List<String> videoTags = new ArrayList<String>();
        videoTags.add("GBFIC");
        videoTags.add(" ");
        videoTags.add(" ");
        videoTags.add(" ");
        videoTags.add(" ");
        videoTags.add(" ");
        videoTags.add(" ");
     
        //Create tags string
        String tags = "";
        for (String tag : videoTags) {
          tags += tag + ", ";
        }
        tags.replace(", , ", ", "); //if by chance there are empty tags.
     
        //Set Tags
        request = new OAuthRequest(Verb.POST, vimeoAPIURL);
        request.addQuerystringParameter("method", "vimeo.videos.addTags");
        request.addQuerystringParameter("tags", tags);
        request.addQuerystringParameter("video_id", vimeoVideoId);
        signAndSendToVimeo(request, "addTags", true);
     
        //Set Privacy
        request = new OAuthRequest(Verb.POST, vimeoAPIURL);
        request.addQuerystringParameter("method", "vimeo.videos.setPrivacy");
        request.addQuerystringParameter("privacy", (true) ? "nobody" : "anybody");
        request.addQuerystringParameter("video_id", vimeoVideoId);
        signAndSendToVimeo(request, "setPrivacy", true);
      }
     
      /**
      * Signs the request and sends it. Returns the response.
      *
      * @param request
      * @return response
      */
      public static Response signAndSendToVimeo(OAuthRequest request, String description, boolean printBody) throws org.scribe.exceptions.OAuthException {
        /*System.out.println(newline + newline
                + "Signing " + description + " request:"
                + ((printBody && !request.getBodyContents().isEmpty()) ? newline + "\tBody Contents:" + request.getBodyContents() : "")
                + ((!request.getHeaders().isEmpty()) ? newline + "\tHeaders: " + request.getHeaders() : ""));
        
          resultsPanel.textarea.append(newline + newline
                  + "Signing " + description + " request:"
                  + ((printBody && !request.getBodyContents().isEmpty()) ? newline + "\tBody Contents:" + request.getBodyContents() : "")
                  + ((!request.getHeaders().isEmpty()) ? newline + "\tHeaders: " + request.getHeaders() : "\n")); 
        */
    	  resultsPanel.textarea.append("sending..\n" );
        service.signRequest(accessToken, request);
        printRequest(request, description);
        Response response = request.send();
        printResponse(response, description, printBody);
        return response;
      }
     
      /**
       * Prints the given description, and the headers, verb, and complete URL of the request.
       *
       * @param request
       * @param description
       */
      private static void printRequest(OAuthRequest request, String description) {
    	 resultsPanel.textarea.append("\n");
    	 resultsPanel.textarea.append("Uploading Segment:\n" );
    	 // resultsPanel.textarea.append(description + " >>> Request\n");
    	 resultsPanel.textarea.append("Headers: " + request.getHeaders()+"\n");
    	 // resultsPanel.textarea.append("Verb: " + request.getVerb()+"\n");
    	 // resultsPanel.textarea.append("Complete URL: " + request.getCompleteUrl()+"\n");
      }
     
      /**
       * Prints the given description, and the code, headers, and body of the given response
       *
       * @param response
       * @param description
       */
      private static void printResponse(Response response, String description, boolean printBody) {
    	  resultsPanel.textarea.append("\n");
    	  resultsPanel.textarea.append("Uploading Segment:\n");
       // resultsPanel.textarea.append(description + " >>> Response \n");
       // resultsPanel.textarea.append("Code: " + response.getCode()+"\n");
       resultsPanel.textarea.append("Headers: " + response.getHeaders()+"\n");
        if (printBody) {
          //resultsPanel.textarea.append("Body: " + response.getBody()+"\n");
        }
      }
     
      /**
       * This method will Read the XML and act accordingly
       *
       * @param xmlString - the XML String
       * @return the list of elements within the XML
       */
      private static Document readXML(String xmlString) {
        Document doc = null;
        try {
          DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
          DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
          InputSource xmlStream = new InputSource();
          xmlStream.setCharacterStream(new StringReader(xmlString));
          doc = dBuilder.parse(xmlStream);
        } catch (Exception e) {
          e.printStackTrace();
        }
        return doc;
      }
    }
