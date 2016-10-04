package no.qadeerkhan.flickr.downloader;

import no.qadeerkhan.flickr.domain.FlickrMediaBuilder;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ImageFileNameTest {

    @Test
    public void verifyFileName() throws Exception {
        ImageFileName imageFileName = new ImageFileName("test", new FlickrMediaBuilder()
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
                .build());

        assertThat(imageFileName.getFileName())
                .isEqualTo("test/1471679349000_2016-08-14T16-08-49_id.originalformat");
    }
}