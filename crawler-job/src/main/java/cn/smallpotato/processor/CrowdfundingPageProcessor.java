package cn.smallpotato.processor;

import cn.smallpotato.entity.Crowdfunding;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @author panjb
 */
public class CrowdfundingPageProcessor implements PageProcessor {

    private final Site site = Site.me();

    private int count = 1;

    @Override
    public void process(Page page) {
        List<Selectable> nodes = page.getHtml().css("div.pro_field > ul > li").nodes();
        if (nodes == null || nodes.isEmpty()) {
            Crowdfunding crowdfunding = parseDetailPage(page);
            page.putField("item", crowdfunding);
        } else {
            for (Selectable node : nodes) {
                String detailUrl = node.css("a", "href").toString();
                page.addTargetRequest(detailUrl);
            }
            count++;
            if (count > 49) {
                return;
            }
            page.addTargetRequest("https://zhongchou.modian.com/publishing/top_money/success/" + count);
        }
    }

    private Crowdfunding parseDetailPage(Page page) {
        Crowdfunding crowdfunding = new Crowdfunding();
        crowdfunding.setName(page.getHtml().css("div.short-cut > h3 > span", "text").toString());
        Selectable center = page.getHtml().css("div.center > div.center-top > div.col1.project-goal.success");
        String goalStr = center.css("span.goal-money", "text").toString();
        String goalMoney = goalStr.substring(goalStr.indexOf("¥") + 1).replace(",", "");
        crowdfunding.setGoal(Double.parseDouble(goalMoney));
        crowdfunding.setPercentage(center.css("span.percent", "text").toString());
        String people = page.getHtml().css("div.center > div.center-top > div.col3.support-people > h3 > span", "text").toString();
        crowdfunding.setPeople(Integer.parseInt(people));
        String completed = page.getHtml().css("div.center > div.center-top > div.col1.project-goal.success > h3 > span", "text").toString();
        crowdfunding.setCompleted(Double.parseDouble(completed.replace(",", "")));
        crowdfunding.setUpdates(Integer.parseInt(page.getHtml().css("li.pro-gengxin > span", "text").toString()));
        crowdfunding.setComments(Integer.parseInt(page.getHtml().css("span#common_comment_count", "text").toString()));
        crowdfunding.setLikes(Integer.parseInt(page.getHtml().css("div.nav-wrap-inner > ul.nav-right > li.atten > span", "text").toString()));
        return crowdfunding;
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new CrowdfundingPageProcessor())
                .addUrl("https://zhongchou.modian.com/publishing/top_money/success")
                .run();
    }
}
