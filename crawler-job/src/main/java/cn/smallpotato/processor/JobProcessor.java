package cn.smallpotato.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @author Panjb
 */
public class JobProcessor implements PageProcessor {

    private final Site site = Site.me().setCharset("utf-8").setTimeOut(10*1000).setRetrySleepTime(3000).setRetryTimes(3);

    @Override
    public void process(Page page) {
        List<Selectable> jobList = page.getHtml().css("div.list a").nodes();
        if (jobList.isEmpty()) {
            // 详情页

        } else {
            // 列表页处理
            for (Selectable selectable : jobList) {
                // 获取详情页url
                String jobInfoUrl = selectable.css("a", "rel").toString();
                page.addTargetRequest(jobInfoUrl);
            }
            // 获取下一页url
            String nextPageUrl = page.getHtml().css("div.paging a.next").links().toString();
            page.addTargetRequest(nextPageUrl);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

}
