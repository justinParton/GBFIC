import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

public class uploadVideo {
	private boolean isDebug;
	private String fileLocation, url, vTitle;

	public uploadVideo(boolean isDebugOn, int bufferSize, String file,
			String siteUrl, String sTitle) {

		isDebug = isDebugOn;
		bufferSize = 2097152;// ;
		fileLocation = file;
		url = siteUrl;
		vTitle = sTitle;
	}

	public String uploadNow(String[] args) throws Exception {

		File testUp = new File(fileLocation);
		FileInputStream is = new FileInputStream(testUp);
		int bufferSize = 2097152;// ;
		long contentLength = testUp.length();
		consoleInfo(contentLength, bufferSize, isDebug);
		byte[] bytesPortion = new byte[bufferSize];
		int i = 1;

		consoleDetails("------File Contents-------------", isDebug);

		while (is.read(bytesPortion, 0, bufferSize) != -1) {
			byte[] newByteChunk = Arrays.copyOfRange(bytesPortion, 0,
					bufferSize);

			consoleDetails("File " + (i++) + ": " + newByteChunk, isDebug);

		}
		is.close();

		return null;

	}

	public void consoleInfo(long contentLength, int bufferSize,
			boolean isConsoleOn) {
		if (isConsoleOn) {
			System.out.println("--------Debug: Parameters--------");
			System.out.println("File Size: " + contentLength);
			System.out.println("File  loc: " + fileLocation);
			System.out.println("Buffer   : " + bufferSize);
			System.out.println("Url      : " + url);
			System.out.println("Sermon   : " + vTitle);
		}
	}

	public static void consoleDetails(String value, boolean isConsoleOn) {
		if (isConsoleOn) {
			System.out.println(value);
		}
	}
}
