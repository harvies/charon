package io.github.harvies.charon.util;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class XmlMapConverterTest {

    @Test
    void xmlToMap() throws Exception {
        Map<String, Object> stringObjectMap = XmlMapConverter.xmlToMap("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<metadata>\n" +
                "    <groupId>org.mongodb</groupId>\n" +
                "    <artifactId>mongodb-driver</artifactId>\n" +
                "    <versioning>\n" +
                "        <latest>3.12.7</latest>\n" +
                "        <release>3.12.7</release>\n" +
                "        <versions>\n" +
                "            <version>3.0.0-beta1</version>\n" +
                "            <version>3.0.0-beta2</version>\n" +
                "            <version>3.0.0-beta3</version>\n" +
                "            <version>3.0.0-rc0</version>\n" +
                "            <version>3.0.0-rc1</version>\n" +
                "            <version>3.0.0</version>\n" +
                "            <version>3.0.1</version>\n" +
                "            <version>3.0.2</version>\n" +
                "            <version>3.0.3</version>\n" +
                "            <version>3.0.4</version>\n" +
                "            <version>3.1.0-rc0</version>\n" +
                "            <version>3.1.0</version>\n" +
                "            <version>3.1.1</version>\n" +
                "            <version>3.2.0-rc0</version>\n" +
                "            <version>3.2.0</version>\n" +
                "            <version>3.2.1</version>\n" +
                "            <version>3.2.2</version>\n" +
                "            <version>3.3.0</version>\n" +
                "            <version>3.4.0-beta1</version>\n" +
                "            <version>3.4.0-rc1</version>\n" +
                "            <version>3.4.0</version>\n" +
                "            <version>3.4.1</version>\n" +
                "            <version>3.4.2</version>\n" +
                "            <version>3.4.3</version>\n" +
                "            <version>3.5.0-alpha1</version>\n" +
                "            <version>3.5.0</version>\n" +
                "            <version>3.6.0-beta1</version>\n" +
                "            <version>3.6.0-beta2</version>\n" +
                "            <version>3.6.0-rc0</version>\n" +
                "            <version>3.6.0-rc1</version>\n" +
                "            <version>3.6.0</version>\n" +
                "            <version>3.6.1</version>\n" +
                "            <version>3.6.2</version>\n" +
                "            <version>3.6.3</version>\n" +
                "            <version>3.6.4</version>\n" +
                "            <version>3.7.0-rc0</version>\n" +
                "            <version>3.7.0</version>\n" +
                "            <version>3.7.1</version>\n" +
                "            <version>3.8.0-beta1</version>\n" +
                "            <version>3.8.0-beta2</version>\n" +
                "            <version>3.8.0-beta3</version>\n" +
                "            <version>3.8.0</version>\n" +
                "            <version>3.8.1</version>\n" +
                "            <version>3.8.2</version>\n" +
                "            <version>3.9.0-rc0</version>\n" +
                "            <version>3.9.0</version>\n" +
                "            <version>3.9.1</version>\n" +
                "            <version>3.10.0</version>\n" +
                "            <version>3.10.1</version>\n" +
                "            <version>3.10.2</version>\n" +
                "            <version>3.11.0-beta1</version>\n" +
                "            <version>3.11.0-beta2</version>\n" +
                "            <version>3.11.0-beta3</version>\n" +
                "            <version>3.11.0-beta4</version>\n" +
                "            <version>3.11.0-rc0</version>\n" +
                "            <version>3.11.0</version>\n" +
                "            <version>3.11.1</version>\n" +
                "            <version>3.11.2</version>\n" +
                "            <version>3.12.0</version>\n" +
                "            <version>3.12.1</version>\n" +
                "            <version>3.12.2</version>\n" +
                "            <version>3.12.3</version>\n" +
                "            <version>3.12.4</version>\n" +
                "            <version>3.12.5</version>\n" +
                "            <version>3.12.6</version>\n" +
                "            <version>3.12.7</version>\n" +
                "        </versions>\n" +
                "        <lastUpdated>20200804194820</lastUpdated>\n" +
                "    </versioning>\n" +
                "</metadata>\n");
        String jsonString = JsonUtils.toJSONString(stringObjectMap);
        int size = JsonUtils.parseObject(jsonString).getJSONObject("versioning").getJSONObject("versions").getJSONArray("version").size();
        assertThat(size, is(66));
    }
}