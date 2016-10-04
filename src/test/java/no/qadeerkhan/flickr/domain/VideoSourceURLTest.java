package no.qadeerkhan.flickr.domain;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class VideoSourceURLTest {

    @Test
    public void shouldGenerateVideoURL() {
        FlickrMedia video = new FlickrMediaBuilder().id("my_id").secret("secret").server(1).farm(2).title("title").originalsecret("my_originalsecret").originalformat("originalformat").datetaken("2016-08-14 16:08:49").dateupload("1471679349").media("media").build();
        VideoSourceURL videoSourceURL = new VideoSourceURL(video, "my_userid");
        assertThat(videoSourceURL.getUrlString()).isEqualTo(
                "https://www.flickr.com/photos/my_userid/my_id/play/orig/my_originalsecret/"
        );
    }

}