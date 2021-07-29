package io.github.harvies.charon.spider;

import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author code4crafter@gmail.com <br>
 * @since 0.3.2
 */
public class GithubRepoPageProcessor implements PageProcessor {
    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Override
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
        page.putField("author", StringUtils.trim(page.getHtml().xpath("//a[@rel='author']/text()").toString()));
        page.putField("name", page.getHtml().xpath("//h1[@class='public']/strong/a/text()").toString());
        if (page.getResultItems().get("name") == null) {
            //skip this page
            page.setSkip(true);
        }
        page.putField("readme", page.getHtml().$(".Box-body").toString());
        page.putField("url", page.getUrl().toString());
        page.putField("watchNum", StringUtils.trim(page.getHtml().xpath("//a[contains(@aria-label,'watching ')]/text()").toString()));
        page.putField("starNum", StringUtils.trim(page.getHtml().xpath("//a[contains(@aria-label,'starred ')]/text()").toString()));
        page.putField("forkNum", StringUtils.trim(page.getHtml().xpath("//a[contains(@aria-label,'forked ')]/text()").toString()));
        page.putField("topics", page.getHtml().$(".list-topics-container a", "text").all());
        // 部分三：从页面发现后续的url地址来抓取
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-]+/[\\w\\-]+)").all());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider spider = Spider.create(new GithubRepoPageProcessor())
                .addPipeline(new ConsolePipeline())
                .addPipeline(new JsonFilePipeline())
                .addUrl("https://github.com/trending")
//                .setScheduler(new RedisPriorityScheduler("localhost"))
                .thread(5);
        spider.run();
    }
}
