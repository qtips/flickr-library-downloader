package no.qadeerkhan.flickr.integration;

import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.io.*;
import java.util.Scanner;

public class AccessToken {

    public static final String ACCESSTOKENFILENAME = new File(AccessToken.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent()+"/accesstoken.ser";

    public static OAuth1AccessToken findAccessToken(OAuth10aService service) {
        try {
            OAuth1AccessToken accessToken = findExistingToken();
            if (accessToken == null) {
                accessToken = generateAccessToken(service);
                saveToken(accessToken);
            }
            return accessToken;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static OAuth1AccessToken findExistingToken() throws IOException, ClassNotFoundException {
        File accessTokenFile = new File(ACCESSTOKENFILENAME);
        if (!accessTokenFile.exists())
            return null;
        FileInputStream fileIn = new FileInputStream(accessTokenFile);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        return (OAuth1AccessToken) in.readObject();
    }

    private static void saveToken(OAuth1AccessToken accessToken) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(ACCESSTOKENFILENAME);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(accessToken);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    private static OAuth1AccessToken generateAccessToken(OAuth10aService service) throws IOException {
        OAuth1RequestToken requestToken = service.getRequestToken();
        String redirectionURL = service.getAuthorizationUrl(requestToken);
        System.out.println("You must authorize this app with Flickr. Click on the following URL to generate verifyier code ");
        System.out.println(redirectionURL);

        System.out.println(" and write verifier here ");
        System.out.println(">>");
        Scanner in = new Scanner(System.in);
        final String oauthVerifier = in.nextLine();
        System.out.println("All set :)");

        return service.getAccessToken(requestToken, oauthVerifier);
    }
}
