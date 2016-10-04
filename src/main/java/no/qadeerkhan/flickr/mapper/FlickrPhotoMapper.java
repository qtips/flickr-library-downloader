package no.qadeerkhan.flickr.mapper;

import com.google.gson.Gson;
import no.qadeerkhan.flickr.domain.FlickrResponse;

import java.io.InputStream;
import java.io.InputStreamReader;

public class FlickrPhotoMapper {

    private final Gson gson = new Gson();

    public FlickrResponse mapResponse(InputStream jsonResponse) {
        return gson.fromJson(new InputStreamReader(jsonResponse), FlickrResponse.class);
    }

}
