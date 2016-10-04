package no.qadeerkhan.flickr.downloader;

import no.qadeerkhan.flickr.domain.MediaSourceURL;
import no.qadeerkhan.flickr.domain.PhotoSize;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

public class ImageFileDownloaderTest {

    private ImageFileDownloader downloader;

    @Mock ImageFileName fileName;
    MediaSourceURL mediaUrl;

    @Before
    public void setup()     {
        MockitoAnnotations.initMocks(this);
        mediaUrl = new MediaSourceURL() {
            @Override
            public String getUrlString() {
                return "https://farm9.staticflickr.com/8238/29076837634_7208abbd6b_o.jpg";
            }

            @Override
            public String getResizedSourceURL(PhotoSize size) {
                return null;
            }

            @Override
            public HttpURLConnection openConnection() throws IOException {
                return (HttpURLConnection) new URL(getUrlString()).openConnection();
            }

            @Override
            public Optional<String> findMediaExtension() {
                return Optional.empty();
            }

            @Override
            public int getSize() {
                return 0;
            }
        };
        when(fileName.getFileName())
                .thenReturn("somefilename.jpg");

        downloader = new ImageFileDownloader(fileName, mediaUrl);

    }

    @Test
    public void testDownloadImage() throws Exception {
        downloader.downloadMedia();
        assertThat(downloader.hasFailed()).isFalse();
        assertThat(downloader.getFinalStatusMessage()).contains("completed");
    }
}