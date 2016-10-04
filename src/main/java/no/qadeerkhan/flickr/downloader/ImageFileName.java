package no.qadeerkhan.flickr.downloader;

import no.qadeerkhan.flickr.domain.FlickrMedia;

public class ImageFileName {

    private final String fileName;
    private String extension;

    public ImageFileName(String libraryPath, FlickrMedia photo) {
        this.fileName = libraryPath + "/" + String.format("%s_%s_%s",
                Long.valueOf(photo.dateupload) * 1000,
                photo.datetaken.replace(":", "-").replace(" ", "T"),
                photo.id);
        this.extension = photo.originalformat;
    }

    public String getFileName() {
        return fileName+"."+extension;
    }


    public void setExtension(String extension) {
        this.extension = extension;
    }
}

// TODO: Flickr video funker ikke for  29820518245 !!??!
