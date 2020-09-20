package io.github.harvies.charon.util;

import com.google.common.collect.Lists;
import io.github.harvies.charon.model.artifact.MavenArtifact;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class ClassUtils {

    /**
     * 某版本的包中是否存在某类
     *
     * @param className
     * @param mavenArtifact
     * @return
     */
    public static boolean exists(String className, MavenArtifact mavenArtifact) {
        return getAllClassNameListByMavenArtifact(mavenArtifact).contains(className);
    }

    //com.mongodb.LazyDBObject
    public static void setArtifactInfo(String className, MavenArtifact mavenArtifact) {
        ArrayList<MavenArtifact> aliasArtifactList = new ArrayList<>(mavenArtifact.getAliasArtifactList());
        aliasArtifactList.add(0, mavenArtifact);
        for (MavenArtifact artifact : aliasArtifactList) {
            List<String> versionList = MavenUtils.getVersionList(artifact.getGroupId(), artifact.getArtifactId());
            for (String version : versionList) {
                try {
                    List<String> allClassNameListByMavenArtifact = getAllClassNameListByMavenArtifact(artifact.getGroupId(), artifact.getArtifactId(), version);
                    if (mavenArtifact.getFirstAddedArtifact() == null && allClassNameListByMavenArtifact.contains(className)) {
                        MavenArtifact firstAddedArtifact = new MavenArtifact()
                                .setGroupId(artifact.getGroupId())
                                .setArtifactId(artifact.getArtifactId())
                                .setVersion(version)
                                .setVersionList(versionList);
                        mavenArtifact.setFirstAddedArtifact(firstAddedArtifact);
                    }
                    boolean remove = mavenArtifact.getFirstAddedArtifact() != null
                            && !allClassNameListByMavenArtifact.contains(className)
                            && MavenUtils.compareVersion(version, mavenArtifact.getFirstAddedArtifact().getVersion()) > 0;
                    if (remove) {
                        MavenArtifact firstRemovedArtifact = new MavenArtifact()
                                .setGroupId(artifact.getGroupId())
                                .setArtifactId(artifact.getArtifactId())
                                .setVersion(version)
                                .setVersionList(versionList);
                        mavenArtifact.setFirstRemovedArtifact(firstRemovedArtifact);
                    }
                    if (mavenArtifact.getFirstAddedArtifact() != null && mavenArtifact.getFirstRemovedArtifact() != null) {
                        break;
                    }
                } catch (Exception e) {
                    log.warn("set setArtifactInfo error mavenArtifact:[{}] version:[{}]", mavenArtifact, version);
                }
            }
        }
        aliasArtifactList.remove(mavenArtifact);
    }

    public static List<String> getNewClassList(MavenArtifact oldArtifact, List<MavenArtifact> newArtifactList) {
        return getNewClassList(Collections.singletonList(oldArtifact), newArtifactList);
    }

    public static List<String> getNewClassList(List<MavenArtifact> oldArtifactList, List<MavenArtifact> newArtifactList) {
        List<String> allOldClassNameListByMavenArtifact = Lists.newArrayList();
        List<String> allNewClassNameListByMavenArtifact = Lists.newArrayList();
        for (MavenArtifact oldArtifact : oldArtifactList) {
            allOldClassNameListByMavenArtifact.addAll(getAllClassNameListByMavenArtifact(oldArtifact));
        }
        for (MavenArtifact newArtifact : newArtifactList) {
            allNewClassNameListByMavenArtifact.addAll(getAllClassNameListByMavenArtifact(newArtifact));
        }
        allNewClassNameListByMavenArtifact.removeAll(allOldClassNameListByMavenArtifact);
        return allNewClassNameListByMavenArtifact;
    }

    public static List<String> getNewClassList(MavenArtifact oldArtifact, MavenArtifact newArtifact) {
        return getNewClassList(Collections.singletonList(oldArtifact), Collections.singletonList(newArtifact));
    }

    public static List<String> getOldClassList(List<MavenArtifact> oldArtifactList, List<MavenArtifact> newArtifactList) {
        return getNewClassList(newArtifactList, oldArtifactList);
    }

    public static List<String> getOldClassList(MavenArtifact oldArtifact, MavenArtifact newArtifact) {
        return getNewClassList(newArtifact, oldArtifact);
    }

    public static List<String> getOldClassList(MavenArtifact oldArtifact, List<MavenArtifact> newArtifactList) {
        return getNewClassList(newArtifactList, Collections.singletonList(oldArtifact));
    }

    public static List<String> getAllClassNameListByMavenArtifact(MavenArtifact mavenArtifact) {
        return getAllClassNameListByMavenArtifact(mavenArtifact.getGroupId(), mavenArtifact.getArtifactId(), mavenArtifact.getVersion());
    }

    public static List<String> getAllClassNameListByMavenArtifact(String groupId, String artifactId, String version) {
        String jarFileUrl = MavenUtils.getJarFileUrl(groupId, artifactId, version);
        File file = new File(FileUtils.getTmpDir() + RandomUtils.uuid());
        try {
            byte[] bytes = Requests.get(jarFileUrl).send().readToBytes();
            FileUtils.writeByteArrayToFile(file, bytes);
            return JarUtils.getClassNameListByJarFile(file);
        } catch (IOException e) {
            log.info("getAllClassNameList error groupId:[{}] artifactId:[{}] version:[{}]", groupId, artifactId, version);
        } finally {
            file.delete();
        }
        return Collections.emptyList();
    }
}
