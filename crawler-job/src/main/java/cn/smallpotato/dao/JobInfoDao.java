package cn.smallpotato.dao;

import cn.smallpotato.entity.JobInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Panjb
 */
public interface JobInfoDao extends JpaRepository<JobInfo, Long> {
}
