package com.apps.repository;

import com.apps.entity.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface User repository.
 *
 * @author SimonYang
 * @date 2019 /2/10
 */
@Repository
public interface UserRepository extends JpaRepository<UserDao, String> {

    /**
     * 用姓名查詢用戶
     *
     * @param name
     *         String 姓名
     * @return UserInfo optional
     */
    Optional<UserDao> findByName(String name);



}
