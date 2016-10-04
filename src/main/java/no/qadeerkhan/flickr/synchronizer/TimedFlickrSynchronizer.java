package no.qadeerkhan.flickr.synchronizer;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TimedFlickrSynchronizer implements Runnable {
    private static final Logger LOG = Logger.getLogger(TimedFlickrSynchronizer.class.getName());
    private final FlickrSynchronizer synchronizer;
    private final ScheduledExecutorService scheduledExecutorService;
    private final int periodMinutes;

    public TimedFlickrSynchronizer(FlickrSynchronizer synchronizer, int periodMinutes) {

        this.synchronizer = synchronizer;
        this.periodMinutes = periodMinutes;
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    }


    public void startScheduler() {
        scheduledExecutorService.scheduleAtFixedRate(this, 0, periodMinutes, TimeUnit.MINUTES);
    }

    @Override
    public void run() {

        try {
            Optional<LocalDateTime> lastSyncTimestamp = FlickrSynchronizer
                    .findLastSyncTimestamp();

            LOG.info(String.format(
                    "Executing sync - last sync date: %s",
                    lastSyncTimestamp.map(LocalDateTime::toString).orElse("first sync")));

            synchronizer.download();

            LOG.info(String.format("Finished sync - next sync will be: %s", LocalDateTime.now().plusMinutes(periodMinutes)));
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Exception from scheduler: " +e.getMessage(), e);
        }
    }
}
