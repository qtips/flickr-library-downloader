package no.qadeerkhan.flickr;

import no.qadeerkhan.flickr.integration.FlickrClient;
import no.qadeerkhan.flickr.integration.FlickrClientImpl;
import org.junit.Ignore;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.Properties;

public class FlickrClientTest {

    private FlickrClient client;


    @Test
    @Ignore
    public void shouldFixSecurity() throws Exception {
        Properties properties = new Properties();
        try (FileInputStream in = new FileInputStream("flickr.properties")) {
            properties.load(in);
        }
        client = new FlickrClientImpl(properties.getProperty("api_key"), properties.getProperty("api_secret"));
        client.callFlickr("https://api.flickr.com/services/rest/?method=flickr.photos.getSizes&photo_id=23466483873&format=rest");
    }


}
