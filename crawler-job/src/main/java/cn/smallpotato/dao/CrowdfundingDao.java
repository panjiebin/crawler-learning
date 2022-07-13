package cn.smallpotato.dao;

import cn.smallpotato.entity.Crowdfunding;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Panjb
 */
public interface CrowdfundingDao extends JpaRepository<Crowdfunding, Long> {
}
