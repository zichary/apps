package com.apps.service;

import com.apps.entity.UserDao;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

/**
 * 用戶操作介面
 *
 * @author SimonYang
 * @version 1.0.0
 * @date 2019 /2/10
 */
public interface UserService extends BaseService<UserDao, String>, UserDetailsService {

    /**
     * 用姓名查詢用戶
     *
     * @param username
     *         String 姓名
     * @return Optional<UserDao> user by name
     */
    Optional<UserDao> findByUserName(String username);

    /**
     * 獲取最新的用戶
     *
     * @return List<UserInfo> current user list
     */
    List<UserDao> getCurrentUserList();

    /**
     * 獲取分頁的用戶
     *
     * @return Page<UserInfo> page user list
     */
    Page<UserDao> getPageUserList();
}
