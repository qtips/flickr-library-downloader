package no.qadeerkhan.flickr.integration;

import no.qadeerkhan.flickr.domain.FlickrMedia;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface FlickrService {

    List<FlickrMedia> findAllMedia() throws IOException;

    List<FlickrMedia> findMediaAfter(LocalDateTime time);

}
