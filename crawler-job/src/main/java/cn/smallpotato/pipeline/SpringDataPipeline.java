package cn.smallpotato.pipeline;

import cn.smallpotato.entity.JobInfo;
import cn.smallpotato.service.JobInfoService;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author Panjb
 */
@Component
public class SpringDataPipeline implements Pipeline {

    private final JobInfoService jobInfoService;

    public SpringDataPipeline(JobInfoService jobInfoService) {
        this.jobInfoService = jobInfoService;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        JobInfo jobInfo = resultItems.get("jobInfo");
        if (jobInfo != null) {
            this.jobInfoService.save(jobInfo);
        }
    }
}
