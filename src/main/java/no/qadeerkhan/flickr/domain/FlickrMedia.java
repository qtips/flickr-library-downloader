package no.qadeerkhan.flickr.domain;

public class FlickrMedia {

    public final String datetaken;
    public final String dateupload;
    int farm, server;
    public String id;
    public String secret;
    String title;
    public String originalsecret;
    public String originalformat;
    public String media;

    public FlickrMedia(String id, String secret, int server, int farm, String title, String originalsecret, String originalformat, String datetaken, String dateupload, String media) {
        this.id = id;
        this.secret = secret;
        this.server = server;
        this.farm = farm;
        this.title = title;
        this.originalsecret = originalsecret;
        this.originalformat = originalformat;
        this.datetaken = datetaken;
        this.dateupload = dateupload;
        this.media = media;
    }


}
