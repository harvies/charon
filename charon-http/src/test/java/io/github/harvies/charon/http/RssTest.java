package io.github.harvies.charon.http;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import io.github.harvies.charon.util.StringUtils;
import io.github.harvies.charon.util.XmlUtils;
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
        @Cleanup InputStreamReader inputStreamReader = new InputStreamReader(Requests.get("https://tech.youzan.com/rss/").send().body());
        SyndFeed syndFeed = new SyndFeedInput().build(inputStreamReader);
        System.out.println(syndFeed.getEntries().get(0));
    }

    @SneakyThrows
    @Test
    void test() {
        SyndFeedInput syndFeedInput = new SyndFeedInput();
        SAXReader reader = new SAXReader();
        //https://blog.csdn.net/phoenix2121/article/details/6958176
        String text = Requests.get("https://ifeve.com/feed/").send().readToText();
        @Cleanup InputStream inputStream = IOUtils.toInputStream(XmlUtils.clean(text), Charset.defaultCharset());
        Document document = reader.read(inputStream);
        StringReader stringReader = new StringReader(document.asXML());
        SyndFeed syndFeed = syndFeedInput.build(stringReader);
        System.out.println(syndFeed.getEntries().get(0));
    }
}
