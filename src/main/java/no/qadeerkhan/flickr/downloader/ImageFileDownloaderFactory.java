package no.qadeerkhan.flickr.downloader;

import no.qadeerkhan.flickr.domain.FlickrMedia;
import no.qadeerkhan.flickr.domain.SourceURLFactory;

public class ImageFileDownloaderFactory {
    private final String libraryPath;
    private final SourceURLFactory sourceURLFactory;

    public ImageFileDownloaderFactory(String libraryPath, SourceURLFactory sourceURLFactory) {
        this.libraryPath = libraryPath;
        this.sourceURLFactory = sourceURLFactory;
    }

    public ImageFileDownloader createImageFileDownloader(FlickrMedia photo) {
        return new ImageFileDownloader(new ImageFileName(libraryPath, photo), sourceURLFactory.create(photo));

    }
}
