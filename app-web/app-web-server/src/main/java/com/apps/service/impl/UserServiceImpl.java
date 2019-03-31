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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 用戶操作
 *
 * @author SimonYang
 * @version 1.0.0
 * @date 2019 /2/10
 */
@Slf4j
@Service("userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends BaseServiceImpl<UserDao, String> implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        super(repository);
        this.repository = repository;
    }


    @Override
    public Optional<UserDao> findByUserName(String name) {
        Optional<UserDao> optional;
        try {
            optional = repository.findByUsername(name);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
        return optional;
    }

    @Override
    public UserDao loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDao> optional = this.findByUserName(username);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new UsernameNotFoundException("UserName " + username + " not found");
        }
    }

    @Override
    public List<UserDao> getCurrentUserList() {
        Sort sort = new Sort(Sort.Direction.DESC, "createDate");
        return repository.findAll(sort);
    }

    @Override
    public Page<UserDao> getPageUserList() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "createTime"));
        return repository.findAll(pageable);
    }
}