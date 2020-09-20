package io.github.harvies.charon.util;

import io.github.harvies.charon.model.artifact.MavenArtifact;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class ClassUtilsTest {

    private MavenArtifact oldMavenArtifact = new MavenArtifact()
            .setGroupId("org.mongodb")
            .setArtifactId("mongo-java-driver")
            .setVersion("3.7.0");

    private MavenArtifact currentMavenArtifact = new MavenArtifact()
            .setGroupId("org.mongodb")
            .setArtifactId("mongo-java-driver")
            .setVersion("3.8.0");

    private MavenArtifact newMavenArtifact = new MavenArtifact()
            .setGroupId("org.mongodb")
            .setArtifactId("mongodb-driver-core")
            .setVersion("4.0.0");

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
                .setArtifactId("mongo-drive-driver")
                .setVersion("3.8.0");
        List<String> allClassNameList = ClassUtils.getAllClassNameListByMavenArtifact(mavenArtifact);
        System.err.println(allClassNameList.size());
        allClassNameList.forEach(s -> System.err.println(s + "\r\n"));
    }

    @Test
    void getNewClassList() {
        List<String> newClassList = ClassUtils.getNewClassList(currentMavenArtifact, newMavenArtifactList);
        System.err.println(newClassList.size());
        newClassList.forEach(s -> System.err.println(s + "\r\n"));
    }

    @Test
    void getOldClassList() {
        List<String> oldClassList = ClassUtils.getOldClassList(currentMavenArtifact, newMavenArtifactList);
        System.err.println(oldClassList.size());
        oldClassList.forEach(s -> System.err.println(s + "\r\n"));
    }

    @Test
    void getNewOldClassList() {
        List<String> newClassList = ClassUtils.getNewClassList(oldMavenArtifact, currentMavenArtifact);
        List<String> oldClassList = ClassUtils.getOldClassList(currentMavenArtifact, newMavenArtifactList);
        newClassList.retainAll(oldClassList);
        System.err.println(newClassList.size());
        newClassList.forEach(s -> System.err.println(s + "\r\n"));
    }

    @Test
    void setArtifactInfo() {
        MavenArtifact mavenArtifact = new MavenArtifact()
                .setGroupId("org.mongodb")
                .setArtifactId("mongodb-java-drive")
                .setAliasArtifactList(
                        Arrays.asList(
                                new MavenArtifact().setGroupId("org.mongodb").setArtifactId("mongodb-driver-sync"),
                                new MavenArtifact().setGroupId("org.mongodb").setArtifactId("mongodb-driver-core"),
                                new MavenArtifact().setGroupId("org.mongodb").setArtifactId("bson")
                        )
                );
        ClassUtils.setArtifactInfo("com.mongodb.operation.AggregateOperationImpl", mavenArtifact);
        System.err.println(JsonUtils.toJSONString(mavenArtifact));
    }

    @Test
    void exists() {
        boolean exists = ClassUtils.exists("com.mongodb.operation.AggregateOperationImpl", currentMavenArtifact);
        System.err.println(exists);
    }
}