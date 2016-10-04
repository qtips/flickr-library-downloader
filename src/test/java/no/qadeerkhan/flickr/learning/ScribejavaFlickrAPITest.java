package no.qadeerkhan.flickr.learning;

import com.github.scribejava.apis.FlickrApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import no.qadeerkhan.flickr.integration.AccessToken;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@Ignore("requires access token  (private) and api keys from flickr.properties")
public class ScribejavaFlickrAPITest {


    private static String apiKey;
    private static String apiSecret;
    private static String userId;

    @BeforeClass
    public static void setup() throws IOException {
        Properties properties = new Properties();
        try (FileInputStream in = new FileInputStream("flickr.properties")) {
            properties.load(in);
        }
        apiKey = properties.getProperty("api_key");
        apiSecret = properties.getProperty("api_secret");
        userId = properties.getProperty("userId");

    }

    @Test
    public void testCallFlickr() throws IOException {
        Response response = callPrivateFlickr("https://api.flickr.com/services/rest/?method=flickr.photos.search&user_id=" + userId + "&sort=date-posted-desc&per_page=500&format=json&nojsoncallback=1");
    }

    @Test
    public void callPublicAPI() throws IOException {
        String response = callPublicFlickr().getBody();
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        assertThat(jsonObject.get("stat").getAsString()).isEqualToIgnoringCase("ok");

    }


    private Response callPrivateFlickr(String url) throws IOException {
        OAuth10aService service = new ServiceBuilder()
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                .build(FlickrApi.instance());

        final OAuthRequest request = new OAuthRequest(Verb.GET, url, service);
        service.signRequest(AccessToken.findAccessToken(service), request);
        return request.send();
    }

    private Response callPublicFlickr() {
        OAuth10aService service = new ServiceBuilder()
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                .build(FlickrApi.instance());
        OAuthRequest oAuthRequest = new OAuthRequest(Verb.GET, "https://api.flickr.com/services/rest/?method=flickr.photos.getRecent&api_key=" + apiKey + "&format=json&nojsoncallback=1&per_page=2", service);

        return oAuthRequest.send();
    }

}
