package no.qadeerkhan.flickr.domain;

public class VideoSourceURL extends AbstractMediaSourceURL {

    private final FlickrMedia video;
    private final String userId;

    public VideoSourceURL(FlickrMedia video, String userId) {

        this.video = video;
        this.userId = userId;
    }

    @Override
    public String getUrlString() {
        return String.format("https://www.flickr.com/photos/%s/%s/play/orig/%s/",userId, video.id, video.originalsecret);
    }

    @Override
    public String getResizedSourceURL(PhotoSize size) {
        throw new UnsupportedOperationException();
    }

}
