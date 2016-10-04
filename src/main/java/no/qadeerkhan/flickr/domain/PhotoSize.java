package no.qadeerkhan.flickr.domain;

public enum PhotoSize {
    SMALL_75x75("s"),
    LARGE_150x150("q"),
    THUMBNAIL_100_LONGISE("t"),
    SMALL_240_LONG("m"),
    SMALL_320_LONG("n"),
    MEDIUM_640_LONG("z"),
    MEDIUM_800_LONG("c"),
    LARGE_1024_LONG("b"),
    LARGE_1600_LONG("h"),
    LARGE_2048_LONG("k"),
    DEFAULT_500_LONG("");

    private String flickrSizeLetter;

    PhotoSize(String flickrSizeLetter) {
        this.flickrSizeLetter = flickrSizeLetter;
    }

    public String getFlickrSizeLetter() {
        return flickrSizeLetter;
    }
}
