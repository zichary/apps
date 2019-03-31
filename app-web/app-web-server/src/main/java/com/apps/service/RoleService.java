package com.apps.service;

import com.apps.entity.RoleDao;

import java.util.Optional;

/**
 * 角色介面
 *
 * @author SimonYang
 * @version 1.0.0
 * @date 2019 /2/10
 */
public interface RoleService extends BaseService<RoleDao, String> {

    /**
     * 用角色代號查詢紀錄
     *
     * @param authority
     *         String 角色代號
     * @return Optional<UserDao> user by name
     */
    Optional<RoleDao> findByAuthority(String authority);

}
