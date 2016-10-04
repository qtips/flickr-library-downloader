package no.qadeerkhan.flickr.integration;

import com.github.scribejava.core.model.Response;

public interface FlickrClient {
    Response callFlickr(String url);

}
