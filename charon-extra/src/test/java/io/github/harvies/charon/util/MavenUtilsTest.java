package io.github.harvies.charon.util;

import io.github.harvies.charon.model.artifact.MavenArtifact;
import net.dongliu.requests.Requests;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MavenUtilsTest {

    private MavenArtifact oldMavenArtifact = new MavenArtifact()
            .setGroupId("org.mongodb")
            .setArtifactId("mongo-java-driver")
            .setVersion("3.6.0");

    private MavenArtifact currentMavenArtifact = new MavenArtifact()
            .setGroupId("org.mongodb")
            .setArtifactId("mongo-java-driver")
            .setVersion("3.7.0");

    private MavenArtifact newMavenArtifact = new MavenArtifact()
            .setGroupId("org.mongodb")
            .setArtifactId("mongo-java-driver")
            .setVersion("3.8.0");

    private List<MavenArtifact> newMavenArtifactList = Arrays.asList(newMavenArtifact, new MavenArtifact()
            .setGroupId("org.mongodb")
            .setArtifactId("mongodb-driver-sync")
            .setVersion("4.0.0"), new MavenArtifact()
            .setGroupId("org.mongodb")
            .setArtifactId("bson")
            .setVersion("4.0.0"));

    @Test
    void getAllClassNameList() {
        MavenArtifact mavenArtifact = new MavenArtifact()
                .setGroupId("org.mongodb")
                .setArtifactId("mongo-java-driver")
                .setVersion("3.8.0");
        List<String> allClassNameList = MavenUtils.getAllClassNameListByMavenArtifact(mavenArtifact);
        System.err.println(allClassNameList.size());
        allClassNameList.forEach(s -> System.err.println(s + "\r\n"));
    }

    @Test
    void getNewClassList() {
        List<String> newClassList = MavenUtils.getNewClassList(currentMavenArtifact, newMavenArtifactList);
        System.err.println(newClassList.size());
        newClassList.forEach(s -> System.err.println(s + "\r\n"));
    }

    @Test
    void getOldClassList() {
        List<String> oldClassList = MavenUtils.getOldClassList(currentMavenArtifact, newMavenArtifactList);
        System.err.println(oldClassList.size());
        oldClassList.forEach(s -> System.err.println(s + "\r\n"));
    }

    @Test
    void getNewOldClassList() {
        List<String> newClassList = MavenUtils.getNewClassList(oldMavenArtifact, currentMavenArtifact);
        List<String> oldClassList = MavenUtils.getOldClassList(currentMavenArtifact, newMavenArtifactList);
        newClassList.retainAll(oldClassList);
        System.err.println(newClassList.size());
        newClassList.forEach(s -> System.err.println(s + "\r\n"));
    }

    @Test
    void setArtifactInfo() {
        MavenArtifact mavenArtifact = new MavenArtifact()
                .setGroupId("org.mongodb")
                .setArtifactId("mongo-java-driver")
                .setAliasArtifactList(
                        Arrays.asList(
                                new MavenArtifact().setGroupId("org.mongodb").setArtifactId("mongodb-driver-sync"),
                                new MavenArtifact().setGroupId("org.mongodb").setArtifactId("mongodb-driver-core"),
                                new MavenArtifact().setGroupId("org.mongodb").setArtifactId("bson")
                        )
                );
        MavenUtils.setArtifactInfo("com.mongodb.client.MongoClientImpl", mavenArtifact);
        System.err.println(JsonUtils.toJSONString(mavenArtifact));
    }

    @Test
    void exists() {
        boolean exists = MavenUtils.exists("com.mongodb.client.MongoClientImpl", oldMavenArtifact);
        System.err.println(exists);
    }

    @Test
    public void getVersionList() {
        List<String> versionList = MavenUtils.getVersionList("org.mongodb", "mongo-java-driver");
        System.err.println(JsonUtils.toJSONString(versionList));
        assertTrue(versionList.size() > 0);
    }

    @Test
    public void getVersionList2() {
        List<String> versionList = MavenUtils.getVersionList("io.github.harvies.charon", "charon-core");
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

    @Test
    void getMethodList() throws IOException, ClassNotFoundException {
        List<Method> methodList = MavenUtils.getMethodList("org.apache.curator", "curator-recipes", "5.1.0", "org.apache.curator.framework.recipes.atomic.AtomicValue");
        for (Method method : methodList) {
            System.out.println(method.getReturnType().getName() + "|" + method.getName() + "|" + Arrays.asList(method.getParameters()));
        }
    }
}
