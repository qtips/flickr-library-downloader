package no.qadeerkhan.flickr.domain;

public class FlickrAccountDetails {

    private final String username;
    private final String apikey;
    private final String apisecret;

    public FlickrAccountDetails(String username, String apikey, String apisecret) {
        this.username = username;
        this.apikey = apikey;
        this.apisecret = apisecret;
    }

    public String getUsername() {
        return username;
    }

    public String getApikey() {
        return apikey;
    }

    public String getApisecret() {
        return apisecret;
    }
}
