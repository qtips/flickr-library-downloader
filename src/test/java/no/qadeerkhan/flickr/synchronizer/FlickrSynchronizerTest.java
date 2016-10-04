package no.qadeerkhan.flickr.synchronizer;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class FlickrSynchronizerTest {

    static final String lastsyncfileFullPath = new File(FlickrSynchronizer.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent() + "/lastsynctime.txt";
    @Test
    public void shouldFindLatestDate() throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(lastsyncfileFullPath))) {
            writer.write("2016-09-01T10:15:30");
        }


        LocalDateTime lastSyncTime = FlickrSynchronizer.findLastSyncTimestamp().get();
        assertThat(lastSyncTime).isEqualTo("2016-09-01T10:15:30");

//        boolean delete = Paths.get("lastsynctime.txt").toFile().delete();
//        assertThat(delete).isTrue();
    }



}