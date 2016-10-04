package no.qadeerkhan.flickr.domain;

public class SourceURLFactory {

    private String userid;

    public SourceURLFactory(String userid) {
        this.userid = userid;
    }

    public MediaSourceURL create(FlickrMedia media) {
        if (media.media.equals("photo")) return new PhotoSourceURL(media);
        return new VideoSourceURL(media, userid);
    }
}
