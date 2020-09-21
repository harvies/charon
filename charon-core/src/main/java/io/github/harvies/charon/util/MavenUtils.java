package io.github.harvies.charon.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import io.github.harvies.charon.model.artifact.MavenArtifact;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.artifact.versioning.ComparableVersion;

import java.util.List;
import java.util.Map;

@Slf4j
public class MavenUtils {
    private static final String REPO_ROOT_URL = "https://repo1.maven.org/maven2/";
    private static final String REPO_ROOT_MIRROR_URL = "https://maven.aliyun.com/nexus/content/groups/public/";

    public static MavenArtifact artifactMetadata(String groupId, String artifactId) {
        MavenArtifact mavenArtifact = new MavenArtifact().setGroupId(groupId).setArtifactId(artifactId);
        Preconditions.checkArgument(StringUtils.isNotBlank(groupId), "The parameter groupId cannot be black.");
        Preconditions.checkArgument(StringUtils.isNotBlank(artifactId), "The parameter artifactId cannot be black.");
        String groupIdUrl = getGroupIdUrl(groupId);
        String metaDataUrl = REPO_ROOT_MIRROR_URL + groupIdUrl + "/" + artifactId + "/maven-metadata.xml";
        String readToText = Requests.get(metaDataUrl).send().readToText();
        Map<String, Object> map;
        try {
            map = XmlMapConverter.xmlToMap(readToText);
        } catch (Exception e) {
            log.info("info error groupId:[{}] artifactId:[{}]", groupId, artifactId, e);
            return mavenArtifact;
        }
        mavenArtifact.setMetaDataUrl(REPO_ROOT_URL + groupIdUrl + "/" + artifactId + "/maven-metadata.xml");
        mavenArtifact.setMirrorMetaDataUrl(metaDataUrl);
        JSONObject jsonObject = JsonUtils.parseObject(JsonUtils.toJSONString(map));
        JSONObject versioning = jsonObject.getJSONObject("versioning");
        mavenArtifact.setLatest(jsonObject.getString("version"));
        mavenArtifact.setRelease(versioning.getString("release"));
        mavenArtifact.setLastUpdated(versioning.getString("lastUpdated"));
        JSONArray jsonArray = versioning.getJSONObject("versions").getJSONArray("version");
        List<String> strings = jsonArray.toJavaList(String.class);
        strings.sort(MavenUtils::compareVersion);
        mavenArtifact.setVersionList(strings);
        return mavenArtifact;
    }

    public static List<String> getVersionList(String groupId, String artifactId) {
        return artifactMetadata(groupId, artifactId).getVersionList();
    }

    private static String getGroupIdUrl(String groupId) {
        return StringUtils.replace(groupId, ".", "/");
    }

    public static String getJarFileUrl(MavenArtifact mavenArtifact) {
        return getJarFileUrl(mavenArtifact.getGroupId(), mavenArtifact.getArtifactId(), mavenArtifact.getVersion());
    }

    public static String getJarFileUrl(String groupId, String artifactId, String version) {
        return REPO_ROOT_MIRROR_URL + getGroupIdUrl(groupId) + "/" + artifactId + "/" + version + "/" + artifactId + "-" + version + ".jar";
    }

    /**
     * 版本号比较
     *
     * @param v1
     * @param v2
     * @return 0代表相等，x代表左边大，-x代表右边大
     * MavenUtils.compareVersion("2.7.0-rc2", "2.7.0")<0
     */
    public static int compareVersion(String v1, String v2) {
        return new ComparableVersion(v1).compareTo(new ComparableVersion(v2));
    }
}
