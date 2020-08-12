package io.github.harvies.charon.spider;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.HasKey;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.pipeline.JsonFilePageModelPipeline;
import us.codecraft.webmagic.scheduler.RedisPriorityScheduler;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 * @since 0.3.2
 */
@ToString
@Slf4j
@TargetUrl("https://github.com/[^/]+/[^/]+$")
@HelpUrl({"https://github.com/\\w+\\?tab=repositories", "https://github.com/\\w+", "https://github.com/explore/*"})
public class GithubRepo implements HasKey {

    private final static NumberFormat numberInstance = DecimalFormat.getNumberInstance();

    @Getter
    @Setter
    @ExtractBy(value = "//h1[@class='public']/strong/a/text()", notNull = true)
    private String name;

    @Getter
    @Setter
    @ExtractByUrl("https://github\\.com/(\\w+)/.*")
    private String author;

    @Getter
    @Setter
    @ExtractBy("//div[@id='readme']/tidyText()")
    private String readme;

    @Getter
    @Setter
    @ExtractBy(value = "//div[@class='repository-lang-stats']//li//span[@class='lang']/text()", multi = true)
    private List<String> language;

    @Setter
    @ExtractBy(value = "//a[@class='social-count js-social-count']/text()", notNull = true)
    private String starStr;

    public Long getStar() {
        Number number = null;
        try {
            number = numberInstance.parse(StringUtils.trim(starStr));
        } catch (Exception e) {
            log.warn("getStar error starStr:{} url:{}", starStr, url, e);
        }
        return number.longValue();
    }

    private Long star;

    @Setter
    @ExtractBy("//a[@class='social-count']/text()")
    private String forkStr;

    public Long getFork() {
        Number number = null;
        try {
            number = numberInstance.parse(StringUtils.trim(forkStr));
        } catch (Exception e) {
            log.warn("getFork error forkStr:{} url:{}", forkStr, url, e);
        }
        return number.longValue();
    }

    private Long fork;

    @Getter
    @Setter
    @ExtractByUrl
    private String url;

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(3000)
                , new JsonFilePageModelPipeline(),GithubRepo.class)
                .setScheduler(new RedisPriorityScheduler("localhost"))
                .addUrl("https://github.com/harvies/example").thread(5).run();
    }

    @Override
    public String key() {
        return author + ":" + name;
    }

}
