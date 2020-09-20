package io.github.harvies.charon.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.github.harvies.charon.model.artifact.MavenArtifact;
import net.dongliu.requests.Requests;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MavenUtils {
    private static final String REPO_ROOT_URL = "https://repo1.maven.org/maven2/";
    private static final String REPO_ROOT_MIRROR_URL = "https://maven.aliyun.com/nexus/content/groups/public/";

    public static List<String> getVersionList(String groupId, String artifactId) {
        String groupIdUrl = getGroupIdUrl(groupId);
        String metaDataUrl = REPO_ROOT_MIRROR_URL + groupIdUrl + "/" + artifactId + "/maven-metadata.xml";
        String readToText = Requests.get(metaDataUrl).send().readToText();
        Map<String, Object> map;
        try {
            map = XmlMapConverter.xmlToMap(readToText);
        } catch (Exception e) {
            return Collections.emptyList();
        }
        JSONObject jsonObject = JsonUtils.parseObject(JsonUtils.toJSONString(map));
        JSONArray jsonArray = jsonObject.getJSONObject("versioning").getJSONObject("versions").getJSONArray("version");
        return jsonArray.toJavaList(String.class);
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
     * @return 0代表相等，1代表左边大，-1代表右边大
     * Utils.compareVersion("1.0.358_20180820090554","1.0.358_20180820090553")=1
     */
    public static int compareVersion(String v1, String v2) {
        if (v1.equals(v2)) {
            return 0;
        }
        String[] version1Array = v1.split("[._]");
        String[] version2Array = v2.split("[._]");
        int index = 0;
        int minLen = Math.min(version1Array.length, version2Array.length);
        long diff = 0;

        while (index < minLen
                && (diff = Long.parseLong(version1Array[index])
                - Long.parseLong(version2Array[index])) == 0) {
            index++;
        }
        if (diff == 0) {
            for (int i = index; i < version1Array.length; i++) {
                if (Long.parseLong(version1Array[i]) > 0) {
                    return 1;
                }
            }

            for (int i = index; i < version2Array.length; i++) {
                if (Long.parseLong(version2Array[i]) > 0) {
                    return -1;
                }
            }
            return 0;
        } else {
            return diff > 0 ? 1 : -1;
        }
    }
}
