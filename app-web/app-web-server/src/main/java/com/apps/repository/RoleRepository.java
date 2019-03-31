package com.apps.repository;

import com.apps.entity.RoleDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface Auth repository.
 *
 * @author SimonYang
 * @version 1.0.0
 * @date 2019 /2/10
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleDao, String> {

    /**
     * 用角色代號查詢紀錄
     *
     * @param authority
     *         String 角色代號
     * @return AuthDao optional
     */
    Optional<RoleDao> findByAuthority(String authority);


}
