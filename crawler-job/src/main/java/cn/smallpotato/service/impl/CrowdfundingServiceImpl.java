package cn.smallpotato.service.impl;

import cn.smallpotato.dao.CrowdfundingDao;
import cn.smallpotato.entity.Crowdfunding;
import cn.smallpotato.service.CrowdfundingService;
import org.springframework.stereotype.Service;

/**
 * @author Panjb
 */
@Service
public class CrowdfundingServiceImpl implements CrowdfundingService {

    private final CrowdfundingDao crowdfundingDao;

    public CrowdfundingServiceImpl(CrowdfundingDao crowdfundingDao) {
        this.crowdfundingDao = crowdfundingDao;
    }

    @Override
    public void save(Crowdfunding crowdfunding) {
        this.crowdfundingDao.save(crowdfunding);
    }
}
