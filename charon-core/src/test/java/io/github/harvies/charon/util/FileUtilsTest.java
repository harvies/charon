package io.github.harvies.charon.util;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FileUtilsTest {

    @Test
    void getCurrentUserHomePath() {
        String currentUserHomePath = FileUtils.getCurrentUserHomePath();
        assertNotNull(currentUserHomePath);
    }

    @Test
    void getSeparator() {
        String separator = FileUtils.getSeparator();
        assertNotNull(separator);
    }

    @Test
    void getPathSeparator() {
        String pathSeparator = FileUtils.getPathSeparator();
        assertNotNull(pathSeparator);
    }

    @Test
    void getTmpDir() {
        String tmpDir = FileUtils.getTmpDir();
        assertNotNull(tmpDir);
    }

    @Test
    void getDirectoryPath() {
        String directoryPath = FileUtils.getDirectoryPath("/org/mongodb/mongo-java-driver/3.8.0/mongo-java-driver-3.8.0.jar");
        assertThat(directoryPath, is("/org/mongodb/mongo-java-driver/3.8.0"));
    }
}