package io.github.harvies.charon.util;

import net.dongliu.requests.Requests;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MavenUtilsTest {

    @Test
    public void getVersionList() {
        List<String> versionList = MavenUtils.getVersionList("org.mongodb", "mongo-java-driver");
        System.err.println(JsonUtils.toJSONString(versionList));
        assertTrue(versionList.size() > 0);
    }

    @Test
    void getJarFileUrl() {
        String jarFileUrl = MavenUtils.getJarFileUrl("org.mongodb", "mongodb-driver-core", "4.1.0");
        assertThat(Requests.head(jarFileUrl).send().statusCode(), is(200));
    }

    @Test
    void compareVersion() {
        assertTrue(MavenUtils.compareVersion("3.8.0", "3.10") < 0);
        assertTrue(MavenUtils.compareVersion("2.7.0-rc2", "2.7.0") < 0);
        assertEquals(0, MavenUtils.compareVersion("2.7.0", "2.7.0"));
    }
}