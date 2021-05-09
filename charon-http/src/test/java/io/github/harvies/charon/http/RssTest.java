package io.github.harvies.charon.http;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import lombok.Cleanup;
import lombok.SneakyThrows;
import net.dongliu.requests.Requests;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.Charset;

public class RssTest {

    @SneakyThrows
    @Test
    void badCase() {
        @Cleanup InputStreamReader inputStreamReader = new InputStreamReader(Requests.get("http://feed.cnblogs.com/blog/sitehome/rss").send().body());
        SyndFeed syndFeed = new SyndFeedInput().build(inputStreamReader);
        System.out.println(syndFeed.getEntries().get(0));
    }

    @SneakyThrows
    @Test
    void test() {
        SyndFeedInput syndFeedInput = new SyndFeedInput();
        SAXReader reader = new SAXReader();
        @Cleanup InputStream inputStream = IOUtils.toInputStream(Requests.get("http://feed.cnblogs.com/blog/sitehome/rss").send().readToText(), Charset.defaultCharset());
        Document document = reader.read(inputStream);
        StringReader stringReader = new StringReader(document.asXML());
        SyndFeed syndFeed = syndFeedInput.build(stringReader);
        System.out.println(syndFeed.getEntries().get(0));
    }
}
