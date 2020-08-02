package io.github.harvies.charon.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {

    @Test
    void getCurrentUserHomePath() {
        String currentUserHomePath = FileUtils.getCurrentUserHomePath();
        Assertions.assertNotNull(currentUserHomePath);
    }
}