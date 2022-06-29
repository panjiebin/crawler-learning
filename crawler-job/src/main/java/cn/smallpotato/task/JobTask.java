package cn.smallpotato.task;

import cn.smallpotato.pipeline.SpringDataPipeline;
import cn.smallpotato.processor.JobProcessor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

/**
 * @author Panjb
 */
@Component
public class JobTask {

    private final SpringDataPipeline dataPipeline;

    public JobTask(SpringDataPipeline dataPipeline) {
        this.dataPipeline = dataPipeline;
    }


    @Scheduled(initialDelay = 1000, fixedDelay = 3600 * 1000)
    public void execute() {
        String url = "https://msearch.51job.com/job_list.php?keyword=Java&keywordtype=&funtype=&indtype=&jobarea=110300&workyear=&jobterm=&cotype=&issuedate=&degree=&saltype=&cosize=&lonlat=&radius=&landmark=&wxjobid=&filttertype=&pageno=1";
        Spider.create(new JobProcessor())
                .addUrl(url)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(1000000)))
                .addPipeline(this.dataPipeline)
                .thread(4)
                .run();
    }
}
