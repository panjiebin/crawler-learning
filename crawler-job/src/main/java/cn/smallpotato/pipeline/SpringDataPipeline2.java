package cn.smallpotato.pipeline;

import cn.smallpotato.entity.Crowdfunding;
import cn.smallpotato.entity.JobInfo;
import cn.smallpotato.service.CrowdfundingService;
import cn.smallpotato.service.JobInfoService;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author Panjb
 */
@Component
public class SpringDataPipeline2 implements Pipeline {

    private final CrowdfundingService crowdfundingService;

    public SpringDataPipeline2(CrowdfundingService crowdfundingService) {
        this.crowdfundingService = crowdfundingService;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        Crowdfunding item = resultItems.get("item");
        if (item != null) {
            this.crowdfundingService.save(item);
        }
    }
}
