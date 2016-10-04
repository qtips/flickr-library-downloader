package no.qadeerkhan.flickr.downloader;

import no.qadeerkhan.flickr.domain.FlickrMedia;
import no.qadeerkhan.flickr.integration.FlickrService;

import java.io.IOException;
import java.util.List;

public class FullLibraryDownloader {

    private FlickrService service;
    private ImageDownloader imageDownloader;

    public FullLibraryDownloader(FlickrService service, ImageDownloader imageDownloader) {
        this.service = service;
        this.imageDownloader = imageDownloader;
    }

    public void download() throws IOException {
        List<FlickrMedia> allSources = fetchAllFlickrPhotos();
        imageDownloader.download(allSources);


    }

    private List<FlickrMedia> fetchAllFlickrPhotos() throws IOException {
        return service.findAllMedia();

    }

}
