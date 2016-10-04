package no.qadeerkhan.flickr.domain;

import static no.qadeerkhan.flickr.domain.PhotoSize.DEFAULT_500_LONG;

public class PhotoSourceURL extends AbstractMediaSourceURL {

    private final FlickrMedia photo;

    // TODO: Should split into two photos
    public PhotoSourceURL(FlickrMedia photo) {
        this.photo = photo;
    }

    public String getUrlString() {
        return generateCommonURLPart(photo.originalsecret) +
                String.format("_o.%s", photo.originalformat);
    }

    public String getResizedSourceURL(PhotoSize size) {
        return generateCommonURLPart(photo.secret) +
                generateResizedPhotoURLPart(size);
    }

    private String generateCommonURLPart(String secret) {
        return String.format("https://farm%d.staticflickr.com/%d/%s_%s", photo.farm, photo.server, photo.id, secret);
    }

    private String generateResizedPhotoURLPart(PhotoSize size) {
        if (size.equals(DEFAULT_500_LONG))
            return ".jpg";
        return String.format("_%s.jpg", size.getFlickrSizeLetter());
    }
}
