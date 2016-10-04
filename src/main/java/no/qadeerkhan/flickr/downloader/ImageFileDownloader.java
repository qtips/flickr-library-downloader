package no.qadeerkhan.flickr.downloader;

import no.qadeerkhan.flickr.domain.MediaSourceURL;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.time.Duration;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageFileDownloader {
    private static final Logger LOG = Logger.getLogger(ImageFileDownloader.class.getName());
    private final ImageFileName name;
    private final MediaSourceURL mediaSourceURL;
    private boolean hasFailed;
    private String status;

    public ImageFileDownloader(ImageFileName name, MediaSourceURL url) {
        this.name = name;
        this.mediaSourceURL = url;
        this.status = "not started";
        this.hasFailed = false;
    }

    public void downloadMedia() {

        try {
            Instant start = Instant.now();

            HttpURLConnection connection = mediaSourceURL.openConnection();

            if (connection.getResponseCode() > 400) {
                this.status = String.format("Something went wrong: %s %s",
                        connection.getResponseCode(), connection.getResponseMessage());
                this.hasFailed = true;
                return;
            }

            mediaSourceURL.findMediaExtension()
                    .ifPresent(name::setExtension);

            this.status = "started...";
            double size = mediaSourceURL.getSize() / 1000000.0;
            LOG.info(String.format("Downloading media %.2fMB %s from %s",
                    size,
                    name.getFileName(),
                    mediaSourceURL.getUrlString()));


            ReadableByteChannel rbc = Channels.newChannel(connection.getInputStream());
            FileOutputStream fos = new FileOutputStream(name.getFileName());
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

            Duration duration = Duration.between(start, Instant.now());
            LOG.info(String.format("Finished downloading %.2fMB in %s %s ",
                    size,
                    duration,
                    name.getFileName()));
            this.status = String.format("completed in %s", duration);
        } catch (IOException e) {
            this.status = "Errormessage: "+e.getMessage();
            hasFailed = true;
            LOG.log(Level.FINE, e.getMessage(), e);
        }
    }

    public boolean hasFailed() {
        return hasFailed;
    }

    public String getFinalStatusMessage() {
        return String.format("url: %s name: %s status: %s", mediaSourceURL.getUrlString(), name.getFileName(), status);
    }
}
