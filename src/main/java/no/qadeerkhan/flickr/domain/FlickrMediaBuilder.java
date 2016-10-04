package no.qadeerkhan.flickr.domain;

public class FlickrMediaBuilder {
    private String id;
    private String secret;
    private int server;
    private int farm;
    private String title;
    private String originalsecret;
    private String originalformat;
    private String datetaken;
    private String dateupload;
    private String media;

    public FlickrMediaBuilder id(String id) {
        this.id = id;
        return this;
    }

    public FlickrMediaBuilder secret(String secret) {
        this.secret = secret;
        return this;
    }

    public FlickrMediaBuilder server(int server) {
        this.server = server;
        return this;
    }

    public FlickrMediaBuilder farm(int farm) {
        this.farm = farm;
        return this;
    }

    public FlickrMediaBuilder title(String title) {
        this.title = title;
        return this;
    }

    public FlickrMediaBuilder originalsecret(String originalsecret) {
        this.originalsecret = originalsecret;
        return this;
    }

    public FlickrMediaBuilder originalformat(String originalformat) {
        this.originalformat = originalformat;
        return this;
    }

    public FlickrMediaBuilder datetaken(String datetaken) {
        this.datetaken = datetaken;
        return this;
    }

    public FlickrMediaBuilder dateupload(String dateupload) {
        this.dateupload = dateupload;
        return this;
    }

    public FlickrMediaBuilder media(String media) {
        this.media = media;
        return this;
    }

    public FlickrMedia build() {
        return new FlickrMedia(id, secret, server, farm, title, originalsecret, originalformat, datetaken, dateupload, media);
    }
}