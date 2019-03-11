package com.apps.service.impl;

import com.apps.entity.UserDao;
import com.apps.repository.UserRepository;
import com.apps.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 用戶操作
 *
 * @author SimonYang
 * @date 2019 /2/10
 */
@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDao> getAllList() {
        List<UserDao> userList = null;
        try {
            userList = userRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return userList;
    }

    @Override
    public Optional<UserDao> save(UserDao userDao, String user, String ip) {
        Optional<UserDao> optional = Optional.empty();
        try {
            userDao.saveData(user, ip);
            optional = Optional.of(userRepository.save(userDao));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return optional;
    }

    @Override
    public Optional<UserDao> remove(String id, String user, String ip) {
        Optional<UserDao> optional = Optional.empty();
        try {
            Optional<UserDao> userDaoOptional = userRepository.findById(id);
            if (userDaoOptional.isPresent()) {
                UserDao userDao = userDaoOptional.get();
                userDao.dead(user, ip);
                optional = Optional.of(userRepository.save(userDao));
            }else{
                log.error("無此紀錄");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return optional;
    }

    @Override
    public Optional<UserDao> enable(String id, String user, String ip) {
        Optional<UserDao> optional = Optional.empty();
        try {
            Optional<UserDao> userDaoOptional = userRepository.findById(id);
            if (userDaoOptional.isPresent()) {
                UserDao userDao = userDaoOptional.get();
                userDao.enable(user, ip);
                optional = Optional.of(userRepository.save(userDao));
            }else{
                log.error("無此紀錄");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return optional;
    }

    @Override
    public void delete(String id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public Optional<UserDao> getUserByName(String name) {
        Optional<UserDao> optional = Optional.empty();
        try {
            optional = userRepository.findByName(name);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return optional;
    }

    @Override
    public List<UserDao> getCurrentUserList() {
        Sort sort = new Sort(Sort.Direction.DESC, "createDate");
        return userRepository.findAll(sort);
    }

    @Override
    public Page<UserDao> getPageUserList() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "createTime"));
        return userRepository.findAll(pageable);
    }
}
