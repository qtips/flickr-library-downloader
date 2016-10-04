package no.qadeerkhan.flickr.domain;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Optional;

public interface MediaSourceURL {
    String getUrlString();
    String getResizedSourceURL(PhotoSize size);

    HttpURLConnection openConnection() throws IOException;

    Optional<String> findMediaExtension();

    int getSize();
}
