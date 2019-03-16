package com.apps.service;

import com.apps.entity.AuthDao;
import com.apps.entity.UserDao;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

/**
 * 角色介面
 *
 * @author SimonYang
 * @version 1.0.0
 * @date 2019 /2/10
 */
public interface AuthService extends BaseService<AuthDao, String> {

    /**
     * 用角色代號查詢紀錄
     *
     * @param authority
     *         String 角色代號
     * @return Optional<UserDao> user by name
     */
    Optional<AuthDao> findByAuthority(String authority);

}
