package com.apps.service.impl;

import com.apps.entity.RoleDao;
import com.apps.entity.UserDao;
import com.apps.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author SimonYang
 * @date 2019-02-23
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    @Transactional
    public void save() {
        UserDao dao = new UserDao();
        dao.setUsername("admin");
        dao.setPassword("admin");
        dao.setName("測試員");
        dao.setJobNumber("00001");
        dao.setNikName(dao.getName());
        Set<RoleDao> authorities = new HashSet<>();
        authorities.add(new RoleDao("ADMIN","管理員"));
        dao.setAuthorities(authorities);
        Optional<UserDao> newDao = userService.save(dao, "Test", "127.0.0.1");
        log.info(newDao.toString());
    }

    @Test
    public void delete() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void findAllList() {
        List<UserDao> list = userService.findAllList();
        log.info("資料長度：{}", list.size());
    }

    @Test
    public void findById() {
        Optional<UserDao> dao = userService.findById("1111");
        if (dao.isPresent()) {
            log.info("find data!");
        } else {
            log.info("data not found!");
        }
    }

    @Test
    public void enable() {
    }

    @Test
    public void findByUserName() {
    }

    @Test
    public void loadUserByUsername() {
    }

    @Test
    public void getCurrentUserList() {
    }

    @Test
    public void getPageUserList() {
    }
}