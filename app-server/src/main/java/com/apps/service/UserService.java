package com.apps.service;

import com.apps.entity.UserDao;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * 用戶操作介面
 *
 * @author SimonYang
 * @date 2019/2/10
 */
public interface UserService extends BaseService<UserDao> {

    /**
     * 用姓名查詢用戶
     *
     * @param name
     *         String 姓名
     * @return Optional<UserDao>
     */
    Optional<UserDao> getUserByName(String name);

    /**
     * 獲取最新的用戶
     *
     * @return List<UserInfo>
     */
    List<UserDao> getCurrentUserList();

    /**
     * 獲取分頁的用戶
     *
     * @return Page<UserInfo>
     */
    Page<UserDao> getPageUserList();
}
