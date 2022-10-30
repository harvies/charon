package io.github.harvies.charon.http;

import org.junit.jupiter.api.Test;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.example.BaiduBaikePageProcessor;

import java.util.ArrayList;
import java.util.List;

public class SpiderTest {

    @Test
    public void testBaiduBaikePageProcessor() {
        //single download
        Spider spider = Spider.create(new BaiduBaikePageProcessor()).thread(4);
        String urlTemplate = "http://baike.baidu.com/search/word?word=%s&pic=1&sug=1&enc=utf8";
        ResultItems resultItems = spider.get(String.format(urlTemplate, "张一鸣"));
        System.out.println(resultItems);

        //multidownload
        List<String> list = new ArrayList<>();
        list.add(String.format(urlTemplate, "马云"));
        list.add(String.format(urlTemplate, "李彦宏"));
        list.add(String.format(urlTemplate, "马化腾"));
        List<ResultItems> resultItemses = spider.getAll(list);
        for (ResultItems resultItemse : resultItemses) {
            System.out.println(resultItemse.getAll());
        }
        spider.close();
    }

    @Test
    public void testGithubRepoPageProcessor() {
        Spider.create(new GithubRepoPageProcessor())
                .addUrl("https://github.com/harvies")
                .addPipeline(new JsonFilePipeline())
                .addPipeline(new ConsolePipeline())
//                .setScheduler(new RedisPriorityScheduler("localhost"))
                .thread(5)
                .run();
//                .start();
    }
}
