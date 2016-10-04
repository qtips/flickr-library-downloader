package no.qadeerkhan.flickr;

import no.qadeerkhan.flickr.domain.FlickrMedia;
import no.qadeerkhan.flickr.domain.FlickrMediaBuilder;

public class TestUtils {

    public static FlickrMedia createSimpleFlickrMedia() {
        return new FlickrMediaBuilder()
                .id("id")
                .secret("secret")
                .server(1)
                .farm(2)
                .title("title")
                .originalsecret("originalsecret")
                .originalformat("originalformat")
                .datetaken("2016-08-14 16:08:49")
                .dateupload("1471679349")
                .media("media")
                .build();
    }
}
