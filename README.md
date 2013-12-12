GBFIC
=====

Uploads a video to Vimeo using the supplied keys and configuration options.GBFICUpload is a package capable of uploading large mp4 files to vimeo through a java interface. The interface has three main functionalities worth highlighting:

1. Usage
-----------------

   The Interface is divided into three panels:
   
   1. Main Panel 
    >Handles video specific metadata inputs (title, author,etc).Upload execution is also performed from this page.Also when the upload begins, the progress bar shows a friendly progressions as the video uploads.The progress bar is built to recieve file chunk verifications that vimeo sends back to the client after each chunk upload. So in essence, the progress bar shows how much data is confirmed to be uploaded.
    
   2. Configuration Panel 
   >Vimeo requires two sets of authentication to allow uploads to a specific account, as such, this page facilitates the storage and placement of the keys into the workflow of the interface. Also included, are the default directory (Usefull if you like automation and always have the file being uploaded in the same place with the same filename), and the Archive directory: if you dont input anything, it will skip the archiving process all together

   3. Results Panel
    >Output from the upload process will be displayed on this page. 
      
h3 2. Advanced functions
------------------------
   
   1. Timer: There is a timer built into the application that auto submits after a designated time period. The reason for this was that i often have systems automated because im not always in the location they are ( I live 90 miles from the company headquarters). Settings for this will be found on the Configuration Panel
   2. Archiving: When enabled, MP4 files (also mp3 if enabled, see below) will be stored in the desired directory, in a folder with the current date. 
   3. MPEG Layer 3: WHEN a user enables archiving, they have the ability to auto create an mp3 that is store in the archive directory. Creation occurs before before upload, but after date folder creation
   5. Vimeo Upload: obviously i had to put in that the vimeo upload is an advanced function. it uses http stream upload instead of posting, its just better in the long run especially since v3 of the api will not have POST implementation (rumor).
   
h3 3. Error Handling
--------------------
