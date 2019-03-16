package com.apps.service.impl;

import com.apps.entity.UserDao;
import com.apps.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author: SimonYang
 * @Date: 2019-02-23
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    public void getAllList() {
        List<UserDao> list = userService.findAllList();
        log.info("資料長度：{}", list.size());
    }
}