package io.github.harvies.charon.http;

import com.google.common.collect.Lists;
import io.github.harvies.charon.util.StringUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author harvies
 */
@Slf4j
public class NameTest {

    private static final Proxy http = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved("router.kikera.top", 7890));

    @Test
    void test() {
        for (int i = 1; i <= 100; i++) {
            List<Name> wiki = wiki(i);
            for (Name name : wiki) {
                if (StringUtils.isNotBlank(name.getEnglishName())) {
//                System.out.println(name.getChineseName() + "," + name.getEnglishName() + "," + name.getDesc());
                    System.out.println(name.getEnglishName());
                }
            }
        }

    }

    private List<Name> wiki(final int pageIndex) {
        return wiki(1000 * (pageIndex - 1) + 1, 1000 * pageIndex);
    }

    private List<Name> wiki(final int offset, final int limit) {
//        log.info("wiki offset:[{}] limit:[{}]", offset, limit);
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("accept-language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7");
        String url = "https://zh.m.wikipedia.org/wiki/%E5%B0%8F%E8%A1%8C%E6%98%9F%E5%88%97%E8%A1%A8/" + offset + "-" + limit + "";
        String text = Requests.get(url).proxy(http).headers(headerMap).timeout(30000).send().readToText();
        Elements elements = Jsoup.parse(text).select("#mf-section-0 > table:nth-child(4) > tbody > tr > td:nth-child(1)");
        List<Name> list = Lists.newArrayListWithCapacity(elements.size());
        elements.forEach(element -> {
            Name name = new Name();
            name.setDesc(element.text());
            list.add(name);
        });
        for (int i = 0; i < 1000; i++) {
            Name name = list.get(i);
            int left = name.getDesc().indexOf("（");
            int right = name.getDesc().indexOf("）");
            if (left != -1 && right != -1) {
                name.setChineseName(StringUtils.substring(name.getDesc(), ("小行星" + (offset + i)).length(), left));
                name.setEnglishName(StringUtils.substring(name.getDesc(), left + 1, right));
            } else {
                name.setEnglishName(StringUtils.substring(name.getDesc(), ("小行星" + (offset + i)).length(), name.getDesc().length()));
            }
        }
        return list;
    }

    @Data
    public static class Name {
        private String chineseName;
        private String englishName;
        private String desc;
    }
}
