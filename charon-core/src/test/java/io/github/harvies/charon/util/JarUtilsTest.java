package io.github.harvies.charon.util;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

class JarUtilsTest {

    @Test
    void getJarNameList() throws IOException {
        List<String> jarName = JarUtils.getClassNameListByJarFile(new File(FileUtils.getCurrentUserHomePath() + "/.m2/repository/io/github/harvies/charon/charon-core/0.0.5-SNAPSHOT/charon-core-0.0.5-20200912.070906-3.jar"));
        jarName.forEach(s -> System.err.println(s + "\r\n"));
    }
}