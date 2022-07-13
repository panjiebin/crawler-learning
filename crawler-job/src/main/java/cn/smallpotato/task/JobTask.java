package cn.smallpotato.task;

import cn.smallpotato.pipeline.SpringDataPipeline2;
import cn.smallpotato.processor.CrowdfundingPageProcessor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

/**
 * @author Panjb
 */
@Component
public class JobTask {

    private final SpringDataPipeline2 dataPipeline2;

    public JobTask(SpringDataPipeline2 dataPipeline2) {
        this.dataPipeline2 = dataPipeline2;
    }


    @Scheduled(initialDelay = 1000, fixedDelay = 3600 * 1000)
    public void execute() {
        Spider.create(new CrowdfundingPageProcessor())
                .addUrl("https://zhongchou.modian.com/publishing/top_money/success")
                .addPipeline(this.dataPipeline2)
                .thread(5)
                .run();
    }
}
