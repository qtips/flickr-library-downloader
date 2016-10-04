package no.qadeerkhan.flickr;

import no.qadeerkhan.flickr.domain.FlickrAccountDetails;
import no.qadeerkhan.flickr.synchronizer.FlickrSynchronizer;
import no.qadeerkhan.flickr.synchronizer.TimedFlickrSynchronizer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {

        setupLogging();

        Properties properties = readPropertiesFromFile();

        String userId = properties.getProperty("userId");
        String downloadPath = properties.getProperty("destination");
        String extraFilters = properties.containsKey("extra") ? properties.getProperty("extra") : null;
        String apiKey = properties.getProperty("api_key");
        String apiSecret = properties.getProperty("api_secret");
        int downloadPeriod = Integer.valueOf(properties.getProperty("download_period"));

        FlickrSynchronizer flickrLibrarySynchronizer =
                FlickrSynchronizer.createFlickrLibrarySynchronizer(
                        new FlickrAccountDetails(userId, apiKey, apiSecret), downloadPath, extraFilters);

        TimedFlickrSynchronizer timedFlickrSynchronizer = new TimedFlickrSynchronizer(flickrLibrarySynchronizer, downloadPeriod);
        LOG.info("Starting scheduler...");
        timedFlickrSynchronizer.startScheduler();


    }

    private static Properties readPropertiesFromFile() throws IOException {
        String jarPath = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent();
        Properties properties = new Properties();
        try (FileInputStream in = new FileInputStream(jarPath+"/flickr.properties")) {
            properties.load(in);
        } catch (FileNotFoundException e) {
            LOG.severe("File name flickr.properties required in run directory. Exiting... ");
            System.exit(1);
        }
        return properties;
    }

    private static void setupLogging() {
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS %4$-6s %2$s %5$s%6$s%n");
    }

}
