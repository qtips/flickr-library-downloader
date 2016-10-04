package no.qadeerkhan.flickr.domain;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

public abstract class AbstractMediaSourceURL implements MediaSourceURL {
    private HttpURLConnection httpURLConnection;
    private int size;

    @Override
    public abstract String getUrlString();

    @Override
    public abstract String getResizedSourceURL(PhotoSize size);

    @Override
    public HttpURLConnection openConnection() throws IOException {
        httpURLConnection = (HttpURLConnection) new URL(getUrlString()).openConnection();
        return httpURLConnection;
    }

    @Override
    public Optional<String> findMediaExtension() {
        return  Optional.ofNullable(httpURLConnection.getHeaderField("Content-Disposition"))
                .map(contentDisposition -> {
                    String[] splitOnEquals = contentDisposition.split("=");
                    String[] splitOnDot = splitOnEquals[splitOnEquals.length - 1].split("\\.");
                    return splitOnDot[splitOnDot.length - 1];
                });

    }

    @Override
    public int getSize(){
        if (size == 0)
            size = Integer.parseInt(httpURLConnection.getHeaderField("Content-Length"));
        return size;
    }
}
