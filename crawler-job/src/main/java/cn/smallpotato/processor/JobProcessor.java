package cn.smallpotato.processor;

import cn.smallpotato.entity.JobInfo;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.io.IOException;
import java.util.List;

/**
 * @author Panjb
 */
public class JobProcessor implements PageProcessor {

    private final Site site = Site.me().setCharset("utf-8").setTimeOut(10*1000).setRetrySleepTime(3000)
            .setRetryTimes(3)
            .addHeader("Cookie", "guid=14fb003b77303f5c8f5b91d841461521; _uab_collina=165656984935669502356714; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2214fb003b77303f5c8f5b91d841461521%22%2C%22first_id%22%3A%22181b3422f1a11a-036169d767bc03a-26021a51-1049088-181b3422f1b72a%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTgxYjM0MjJmMWExMWEtMDM2MTY5ZDc2N2JjMDNhLTI2MDIxYTUxLTEwNDkwODgtMTgxYjM0MjJmMWI3MmEiLCIkaWRlbnRpdHlfbG9naW5faWQiOiIxNGZiMDAzYjc3MzAzZjVjOGY1YjkxZDg0MTQ2MTUyMSJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%2214fb003b77303f5c8f5b91d841461521%22%7D%2C%22%24device_id%22%3A%22181b3422f1a11a-036169d767bc03a-26021a51-1049088-181b3422f1b72a%22%7D; privacy=1656574485; slife=lastvisit%3D110300%26%7C%26; nsearch=jobarea%3D%26%7C%26ord_field%3D%26%7C%26recentSearch0%3D%26%7C%26recentSearch1%3D%26%7C%26recentSearch2%3D%26%7C%26recentSearch3%3D%26%7C%26recentSearch4%3D%26%7C%26collapse_expansion%3D; search=jobarea%7E%60110300%7C%21ord_field%7E%600%7C%21recentSearch0%7E%60110300%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA00%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FAjava%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21; partner=webmeta; m_search=areacode%3D110300%26%7C%26keyword%3DJava; msearchhistory=110300%2CJava%2C%2C%2C%2C%7C%7C110300%2Cjava%2C%2C%2C%2C%7C%7C110300%2C%2C%2C%2C%2C; SECKEY_ABVK=Ay1Hv2c2Q8HRMcMC4wKrFHFyFEgmRu2Pb4wZTTxbLF0%3D; BMAP_SECKEY=-h3X2ASF-pylsaytqRobvO5OodOwHX6tTVxBGy3QlTd94dJ3dvowOL3VjZP0Cbo_aX2xkRQxsr1-e7b-SEAX-ikuBh6UxGybjLSN5h2Z69are3dF4DJ2o5Ozi6_sW8cSA_Xogfc1660wNz8J0MKa3T2kDWvcPpJO5lxHkvroJ6FNy5-C8iQCEzmZee54CNnn; ssxmod_itna=eqIxcDnGDtd4yDGxBP8Q5DkQM5Yv544YeQ+vLxD/KQIDnqD=GFDK40EEOK3DO+SOYR+5feb0WIQfzmOxd43acramfTS=x0aDbqGkeRF4iiyDCeDIDWeDiDG4Gml4GtDpxG=Dj0FUZMUxi3Dbh=Di4D+7uQDmqG0DDU7B4G2D7t9RB59tmLPLkRgYB0xKG=DjLbD/RGWwG65apcY9MiroaiaQqGynPGuULt/l8bDCg6/0Tijiipz32vPI0DAK7xNiY+A32+4SEojEGDNQiAQn04Q0Amd+DDAKY49vGDxD; ssxmod_itna2=eqIxcDnGDtd4yDGxBP8Q5DkQM5Yv544YeQ+vxG9bZRKDBkgOx7p+QSqDOYK7zxn+x8Dcrkri4eaSo7=G3see5nwxZjmIn+e5A=BwjYxKIkTh1VbtZOjC5NYbS2Bmvo7lgGTX=NiX6rmrk=nsFxeaz=K4SaphvEm1xOmhwrEr0rwCO3vKBEOtvgavYlOvyOACSAwEg08fgerLCzBAc4S7rzArbRpCi+Mjx2A+MoyFhxAT9AKF13MsfbTEDgQ=+9O63ECr3=YzqOnOSm=fqQeTT+PtAfqwrEUFc+u90WW7okUb3Oib5TwgQ4wSHrKRiQ5F7WMl4tSqw7mA0Ry/u/fpHW4CQ+BIeNbTNlep7eb4oDsxpK4bl48m+3L2Yo4tborigdWbcUIovgHjO=UqwD+dLor4qb/Ozi+=oSd3nrsh3wEiVqIRo8Uw3k3pWfCf4b98isfaWEYs2Fa+aoR7y88PqOPT3D07R4DLxD2maeYHeYGT5isPD===")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36");

    @Override
    public void process(Page page) {
        List<Selectable> search = page.getHtml().css("div.search.search_s").nodes();
        if (search.isEmpty()) {
            // 详情页
            try {
                this.saveJobInfo(page);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            // 列表页处理
            List<Selectable> jobList = page.getHtml().css("div.list a").nodes();
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

    private void saveJobInfo(Page page) throws IOException {
        JobInfo jobInfo = new JobInfo();
        jobInfo.setJobName(page.getHtml().css("div.jbox p.jname", "text").toString());
        jobInfo.setSalary(page.getHtml().css("div.jbox p.sal", "text").toString());
        jobInfo.setTime(page.getHtml().css("div.jbox span.date", "text").toString());
        jobInfo.setCompanyName(page.getHtml().css("div.c_info.j_company div.info h3", "text").toString());
        String addr = page.getHtml().css("a.m_address span", "text").toString();
        if (StringUtils.isNotBlank(addr)) {
            String[] split = addr.split(":");
            if (split.length == 2) {
                jobInfo.setJobAddr(split[1].trim());
            }
        } else {
            System.out.println("addr = " + addr);
        }

        List<Selectable> nodes = page.getHtml().css("div.c_aox article p").nodes();
        StringBuilder sb = new StringBuilder();
        for (Selectable node : nodes) {
            sb.append(node.css("p", "text").toString());
        }
        jobInfo.setJobInfo(sb.toString());
        jobInfo.setUrl(page.getUrl().toString());
        page.putField("jobInfo", jobInfo);
    }

}
