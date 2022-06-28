package cn.smallpotato.demo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 解析Flink官网首页目录树
 *
 * @author panjb
 */
public class FlinkPageProcessor implements PageProcessor {

    private final Site site = Site.me();

    @Override
    public void process(Page page) {
        page.putField("list", page.getHtml().xpath("//div[@id=bs-example-navbar-collapse-1]/ul/li").all());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new FlinkPageProcessor())
                .addUrl("https://flink.apache.org/")
                .run();
    }
}
