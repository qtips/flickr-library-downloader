package no.qadeerkhan.flickr.synchronizer;

import no.qadeerkhan.flickr.domain.FlickrAccountDetails;
import no.qadeerkhan.flickr.domain.FlickrMedia;
import no.qadeerkhan.flickr.domain.SourceURLFactory;
import no.qadeerkhan.flickr.downloader.ImageDownloader;
import no.qadeerkhan.flickr.downloader.ImageDownloaderImpl;
import no.qadeerkhan.flickr.downloader.ImageFileDownloaderFactory;
import no.qadeerkhan.flickr.integration.FlickrClientImpl;
import no.qadeerkhan.flickr.integration.FlickrService;
import no.qadeerkhan.flickr.integration.FlickrServiceImpl;
import no.qadeerkhan.flickr.mapper.FlickrPhotoMapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class FlickrSynchronizer {

    static final String lastsyncfileFullPath = new File(FlickrSynchronizer.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent() + "/lastsynctime.txt";
    private final FlickrService service;
    private final ImageDownloader downloader;

    public FlickrSynchronizer(FlickrService service, ImageDownloader downloader) {

        this.service = service;
        this.downloader = downloader;
    }

    public static FlickrSynchronizer createFlickrLibrarySynchronizer(FlickrAccountDetails userAccount, String downloadPath, String extraFilters) throws IOException {
       return new FlickrSynchronizer(
                new FlickrServiceImpl(
                        new FlickrClientImpl(userAccount.getApikey(), userAccount.getApisecret()),
                        new FlickrPhotoMapper(),
                        userAccount.getUsername(), extraFilters),
                new ImageDownloaderImpl(
                        new ImageFileDownloaderFactory(
                                downloadPath,
                                new SourceURLFactory(userAccount.getUsername()))));
    }

    public void download() throws IOException {
        List<FlickrMedia> flickrMedias = findLastSyncTimestamp()
                .map(service::findMediaAfter)
                .orElse(service.findAllMedia());
        downloader.download(flickrMedias);
        setNewLastSyncTimestamp();
    }

    private static void setNewLastSyncTimestamp() throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(lastsyncfileFullPath))) {
            writer.write(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }
    }

    public static Optional<LocalDateTime> findLastSyncTimestamp() {
        try (Stream<String> fileStream = Files.lines(Paths.get(
                lastsyncfileFullPath
        ))) {
            return fileStream.findFirst()
                    .map(date -> LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        } catch (IOException e) {
            return Optional.empty();
        }
    }


}
