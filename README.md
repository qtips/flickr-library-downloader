# Flickr Library Downloader

Flickr Library Downloader is a tool that downloads all your pictures and videoes from Flickr, periodically checking for new content.

## Requirements
* Java 8
* Flickr Account
* Flickr API key and secret - Get yours [here](https://www.flickr.com/services/apps/create/apply)


## Usage

1. Download [this](https://github.com/qtips/flickr-library-downloader/releases/download/0.1.0/flickr-library-downloader-0.1.0.jar) jar together with configuration file ```flickr.properties```. Warning! there are some known issues with this version - see [release](https://github.com/qtips/flickr-library-downloader/releases/tag/0.1.0) for details. 
2. Edit the configuration file filling in your details: 

    ```javascript
    download_period: [period to check for new content - in minutes ]
    api_key: [your api key]
    api_secret: [your api secret]
    userId: [your user ID]
    destination: [destination folder to put the image files]
    ```
3. Run the application by:
`java -jar flickr-library-downloader.jar`
4. For the first run you will be asked to give permission for downloading media. Follow the instructions from the output. This will create a `accesstoken.ser` file which contains your access token.
5. The application will start downloading all content to your chosen destination from the configuration file. See output for details
6. In case of error with some files, you will see it in the output.

### Tips
To save the output in a file, run the command as follows (in Unix environments)
 ```
 java -jar flickr-library-downloader.jar > output.log 2>&1
 ```

 To provide extra filters to Flick API use property `extra` e.g.
 ```
 extra: &media=video
 ```
 will only download videoes. See Flickr API documentation for [flickr.photos.search](https://www.flickr.com/services/api/explore/flickr.photos.search) for details on the different parameters.

To keep track of whats been downloaded, the application creates and uses a file `lastsynctime.txt` which simply contains a timestamp. This timestamp is used to find files uploaded after this time. You can alter this timestamp to re-download stuff or to start downloading from a specific time.
