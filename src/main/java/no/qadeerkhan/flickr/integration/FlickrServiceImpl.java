package no.qadeerkhan.flickr.integration;

import com.github.scribejava.core.model.Response;
import no.qadeerkhan.flickr.domain.FlickrMedia;
import no.qadeerkhan.flickr.domain.FlickrResponse;
import no.qadeerkhan.flickr.mapper.FlickrPhotoMapper;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class FlickrServiceImpl implements FlickrService {

    public static final String FLICKR_PREFIX = "https://api.flickr.com/services/rest/?method=flickr.photos.search&user_id=";
    public static final String PART1 = "&sort=date-posted-desc&per_page=500";
    public static final String PART2 = "&extras=original_format,date_taken,media,date_upload&format=json&nojsoncallback=1";
    private final FlickrClient client;
    private final FlickrPhotoMapper flickrPhotoMapper;
    private String userId;
    private String extraFilters;

    public FlickrServiceImpl(FlickrClient client, FlickrPhotoMapper mapper, String userId, String extraFilters) {
        this.client = client;
        this.flickrPhotoMapper = mapper;
        this.userId = userId;
        this.extraFilters = extraFilters;
        // TODO convert url to map for better readibility
    }


    private List<FlickrMedia> findMedia(String url) {
        FlickrResponse response = getFirstSet(url);
        int totalPages = response.getTotalPages();
        ArrayList<FlickrMedia> allPhotos = new ArrayList<>(totalPages * 500);
        allPhotos.addAll(response.getPhotos());

        IntStream.rangeClosed(2, totalPages).parallel()
                .mapToObj(page -> getMediaFromPage(page, url))
                .forEach(allPhotos::addAll);
        return allPhotos;
    }

    @Override
    public List<FlickrMedia> findAllMedia() throws IOException {
        return findMedia(getSearchURL());
    }

    @Override
    public List<FlickrMedia> findMediaAfter(LocalDateTime time) {
        String url = getSearchURL() + "&min_upload_date=" + Timestamp.valueOf(time);
        return findMedia(url);
    }

    private String getSearchURL() {
        return FLICKR_PREFIX + userId + PART1 + PART2 + "&" + extraFilters;
    }

    private FlickrResponse getFirstSet(String firstURL) {
        Response firstResponse = client.callFlickr(firstURL);
        return flickrPhotoMapper.mapResponse(firstResponse.getStream());
    }

    private List<FlickrMedia> getMediaFromPage(int page, String url) {
        Response response = client.callFlickr(url + "&page=" + page);
        return flickrPhotoMapper.mapResponse(response.getStream()).getPhotos();
    }
}
