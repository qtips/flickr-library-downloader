package no.qadeerkhan.flickr.downloader;

import no.qadeerkhan.flickr.domain.FlickrMedia;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ImageDownloaderImpl implements ImageDownloader {

    private static final Logger LOG = Logger.getLogger(ImageDownloaderImpl.class.getName());

    ImageFileDownloaderFactory imageFileDownloaderFactory;


    public ImageDownloaderImpl(ImageFileDownloaderFactory imageFileDownloaderFactory) {
        this.imageFileDownloaderFactory = imageFileDownloaderFactory;
    }

    @Override
    public void download(List<FlickrMedia> allSources) throws IOException {
        Instant start = Instant.now();
        if (allSources.size() > 0) {
            LOG.info(String.format("Starting download of %s files from Flickr", allSources.size()));
            List<ImageFileDownloader> failedItems = allSources.stream()
                    .parallel()
                    .map((photo) -> imageFileDownloaderFactory.createImageFileDownloader(photo))
                    .peek(ImageFileDownloader::downloadMedia)
                    .filter(ImageFileDownloader::hasFailed)
                    .collect(Collectors.toList());

            LOG.info(String.format("Finished TOTAL download in %s", Duration.between(start, Instant.now())));

            if (failedItems.size() > 0)
                logFailures(failedItems);
        } else LOG.info("No new items");


    }

    private void logFailures(List<ImageFileDownloader> failedItems) {
        LOG.info(failedItems.size() + " ITEMS FAILED - please download them manually, or change the lastsync.txt time. Reasons:");
        failedItems.forEach(imageFileDownloader -> LOG.info("ERROR:" + imageFileDownloader.getFinalStatusMessage()));
    }

}
