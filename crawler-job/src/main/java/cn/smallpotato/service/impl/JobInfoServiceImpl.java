package cn.smallpotato.service.impl;

import cn.smallpotato.dao.JobInfoDao;
import cn.smallpotato.entity.JobInfo;
import cn.smallpotato.service.JobInfoService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Panjb
 */
@Service
public class JobInfoServiceImpl implements JobInfoService {

    private final JobInfoDao jobInfoDao;

    public JobInfoServiceImpl(JobInfoDao jobInfoDao) {
        this.jobInfoDao = jobInfoDao;
    }

    @Override
    public void save(JobInfo jobInfo) {
        JobInfo param = new JobInfo();
        param.setUrl(jobInfo.getUrl());
        param.setTime(jobInfo.getTime());
        List<JobInfo> info = findJobInfo(param);
        if (info.isEmpty()) {
            this.jobInfoDao.saveAndFlush(jobInfo);
        }
    }

    @Override
    public List<JobInfo> findJobInfo(JobInfo jobInfo) {
        return this.jobInfoDao.findAll(Example.of(jobInfo));
    }
}
