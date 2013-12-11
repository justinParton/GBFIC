GBFIC
=====

Uploads a video to Vimeo using the supplied keys and configuration options.


GBFICUpload is a package capable of uploading large mp4 files to vimeo through a java interface. 

The interface has three main functionalities worth highlighting:

h3 1. Ease of Use
   The Java Interface is broken into three panels:
    h5 Main Panel: 
    Handles video specific metadata inputs (title, Author,etc). upload execution is also performed from this page. Also when the upload begins, the progress bar shows a friendly progressions as the video uploads.The progress bar is built to recieve file chunk verifications that vimeo sends back to the client after each chunk upload. So in essence, the progress bar shows how much data is confirmed to be uploaded.
    
    h5 Configuration Panel: 
    Vimeo requires two sets of authentication to allow uploads to a specific account, as such, this page facilitates the storage and placement of the keys into the workflow of the interface. Also included, are the default directory (Usefull if you like automation and always have the file being uploaded in the same place with the same filename), and the Archive directory: if you dont input anything, it will skip the archiving process all together

    Results Panel: output from the upload process will be displayed on this page. 
      
h3 2. Advanced functions
  
h3 3. Error Handling
