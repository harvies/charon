package io.github.harvies.charon.http;

import lombok.SneakyThrows;
import net.dongliu.requests.Requests;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.Charset;

public class XMLTest {

    @Test
    @SneakyThrows
    public void test() {
        SAXReader reader = new SAXReader();
        InputStream inputStream = IOUtils.toInputStream(Requests.get("http://feed.cnblogs.com/blog/sitehome/rss").send().readToText(), Charset.defaultCharset());
        Document document = reader.read(inputStream);
        System.out.println(document.asXML());
    }
}
