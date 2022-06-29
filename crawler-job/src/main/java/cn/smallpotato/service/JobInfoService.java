package cn.smallpotato.service;

import cn.smallpotato.entity.JobInfo;

import java.util.List;

/**
 * @author Panjb
 */
public interface JobInfoService {

    void save(JobInfo jobInfo);


    List<JobInfo> findJobInfo(JobInfo jobInfo);
}
