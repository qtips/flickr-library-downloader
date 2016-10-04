package no.qadeerkhan.flickr.learning;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import no.qadeerkhan.flickr.domain.FlickrMedia;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.List;

public class GsonTest {

    private static final String FLICKR_RAW_RESPONSE_EXAMPLE = "{\n" +
            "    \"stat\": \"ok\",\n" +
            "    \"photos\": {\n" +
            "        \"perpage\": 2,\n" +
            "        \"total\": 1000,\n" +
            "        \"pages\": 500,\n" +
            "        \"photo\": [\n" +
            "            {\n" +
            "                \"owner\": \"34455678@N06\",\n" +
            "                \"server\": \"8361\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"farm\": 9,\n" +
            "                \"id\": \"28856981884\",\n" +
            "                \"secret\": \"18e1e108cb\",\n" +
            "                \"title\": \"2015-10-16_LKG-Theater_248.jpg\",\n" +
            "                \"isfamily\": 0\n" +
            "            },\n" +
            "            {\n" +
            "                \"owner\": \"142274068@N02\",\n" +
            "                \"server\": \"8035\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"farm\": 9,\n" +
            "                \"id\": \"29372982742\",\n" +
            "                \"secret\": \"a184df9ff7\",\n" +
            "                \"title\": \"Le ciel devient fou  !   Photo prise avec le LG G3 \uD83D\uDC4D\uD83D\uDCF7   #skyporn  #skyline  #Oise  #belleOise  #eolienne  #ciel  #instasky  #cloudporn  #skies  #clouds  #nuages  #soleil  #sun  #goodnight  #bonnenuit  #travel  #picardie   #hautsdefrance  #\",\n" +
            "                \"isfamily\": 0\n" +
            "            }\n" +
            "        ],\n" +
            "        \"page\": 1\n" +
            "    }\n" +
            "}\n";

    @Test
    public void parseJsonWithGson() {
        JsonArray array = new JsonParser().parse(FLICKR_RAW_RESPONSE_EXAMPLE).getAsJsonObject().getAsJsonObject("photos").getAsJsonArray("photo");
        Gson gson = new Gson();
        Type type = new TypeToken<List<FlickrMedia>>() {}.getType();
        List<FlickrMedia> photo = gson.fromJson(array, type);

    }
}
