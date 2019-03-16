package com.apps.repository;

import com.apps.entity.AuthDao;
import com.apps.entity.UserDao;
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
public interface AuthRepository extends JpaRepository<AuthDao, String> {

    /**
     * 用角色代號查詢紀錄
     *
     * @param authority
     *         String 角色代號
     * @return AuthDao optional
     */
    Optional<AuthDao> findByAuthority(String authority);


}
