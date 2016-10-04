package no.qadeerkhan.flickr.downloader;

import no.qadeerkhan.flickr.domain.FlickrMedia;

import java.io.IOException;
import java.util.List;

public interface ImageDownloader {
    void download(List<FlickrMedia> allSources) throws IOException;
}
