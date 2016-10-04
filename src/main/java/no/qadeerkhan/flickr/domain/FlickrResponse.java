package no.qadeerkhan.flickr.domain;

import java.util.List;

public class FlickrResponse {
    private FlickrPhotos photos;

    public class FlickrPhotos {
        private int page, pages, perpage, total;
        private List<FlickrMedia> photo;
    }


    public int getTotalPages()  {
        return photos.pages;
    }

    public List<FlickrMedia> getPhotos() {
        return photos.photo;
    }

}
