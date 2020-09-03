package io.github.harvies.charon.util;

import org.junit.jupiter.api.Test;

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
}