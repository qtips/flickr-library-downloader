package no.qadeerkhan.flickr.integration;

import com.github.scribejava.apis.FlickrApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.util.logging.Logger;

public class FlickrClientImpl implements FlickrClient {
    private static final Logger LOG = Logger.getLogger(FlickrClientImpl.class.getName());
    private OAuth10aService service;
    private OAuth1AccessToken accessToken;

    public FlickrClientImpl(String apiKey, String apiSecret) {
        service = new ServiceBuilder()
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                .build(FlickrApi.instance());

        accessToken = AccessToken.findAccessToken(service);

    }

    @Override
    public Response callFlickr(String url) {
        LOG.fine("Calling Flickr with URL "+url);
        final OAuthRequest request = new OAuthRequest(Verb.GET, url, service);
        service.signRequest(accessToken, request);
        return request.send();
    }

}
