package no.qadeerkhan.flickr.photosourceURL;

import no.qadeerkhan.flickr.domain.FlickrMedia;
import no.qadeerkhan.flickr.domain.PhotoSize;
import no.qadeerkhan.flickr.domain.PhotoSourceURL;
import no.qadeerkhan.flickr.mapper.FlickrPhotoMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static no.qadeerkhan.flickr.FlickrJsonResponseConstants.FLICKR_RESPONSE_DEFAULT_SIZE;
import static no.qadeerkhan.flickr.FlickrJsonResponseConstants.FLICKR_RESPONSE_ORIGINAL_FORMAT;
import static no.qadeerkhan.flickr.domain.PhotoSize.DEFAULT_500_LONG;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PhotoSourceURLTest {

    private FlickrPhotoMapper mapper;

    @Before
    public void setup() {
        mapper = new FlickrPhotoMapper();
    }

    @Test
    public void shouldMapDefaultPhotoFormat() throws UnsupportedEncodingException {
        List<FlickrMedia> mappedSources = getMappedPhotos(FLICKR_RESPONSE_DEFAULT_SIZE);
        assertThat(mappedSources)
                .hasSize(2)
                .extracting(flickrPhoto -> new PhotoSourceURL(flickrPhoto)
                        .getResizedSourceURL(DEFAULT_500_LONG))
                .containsExactly(
                        "https://farm9.staticflickr.com/8361/28856981884_18e1e108cb.jpg",
                        "https://farm9.staticflickr.com/8035/29372982742_a184df9ff7.jpg"
                );
    }

    @Test
    public void shouldMapOriginalPhotoFormat() throws UnsupportedEncodingException {
        List<FlickrMedia> mappedSources = getMappedPhotos(FLICKR_RESPONSE_ORIGINAL_FORMAT);
        assertThat(mappedSources)
                .hasSize(2)
                .extracting(flickrPhoto -> new PhotoSourceURL(flickrPhoto)
                        .getUrlString())
                .containsExactly(
                        "https://farm9.staticflickr.com/8162/29412354040_ac81607218_o.jpg",
                        "https://farm9.staticflickr.com/8238/29076837634_7208abbd6b_o.jpg"
                );
    }

    @Test
    public void shouldMapResizedPhotoFormat() throws UnsupportedEncodingException {
        List<FlickrMedia> mappedSources = getMappedPhotos(FLICKR_RESPONSE_ORIGINAL_FORMAT);

        assertThat(mappedSources).hasSize(2);

        PhotoSourceURL urlSource1 = new PhotoSourceURL(mappedSources.get(0));
        PhotoSourceURL urlSource2 = new PhotoSourceURL(mappedSources.get(1));

        assertThat(urlSource1.getResizedSourceURL(PhotoSize.SMALL_75x75))
                .isEqualTo("https://farm9.staticflickr.com/8162/29412354040_248962d7dd_s.jpg");
        assertThat(urlSource1.getResizedSourceURL(PhotoSize.THUMBNAIL_100_LONGISE))
                .isEqualTo("https://farm9.staticflickr.com/8162/29412354040_248962d7dd_t.jpg");
        assertThat(urlSource2.getResizedSourceURL(PhotoSize.LARGE_1600_LONG))
                .isEqualTo("https://farm9.staticflickr.com/8238/29076837634_044d390c50_h.jpg");
    }

    private List<FlickrMedia> getMappedPhotos(String flickrResponseDefaultSize) throws UnsupportedEncodingException {
        return mapper.mapResponse(inputStream(flickrResponseDefaultSize)).getPhotos();
    }

    private InputStream inputStream(String flickrResponseDefaultSize) throws UnsupportedEncodingException {
        return new ByteArrayInputStream(flickrResponseDefaultSize.getBytes("UTF-8"));
    }
}