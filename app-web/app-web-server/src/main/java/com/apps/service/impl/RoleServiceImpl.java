package com.apps.service.impl;

import com.apps.entity.RoleDao;
import com.apps.repository.RoleRepository;
import com.apps.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 用戶操作
 *
 * @author SimonYang
 * @version 1.0.0
 * @date 2019 /2/10
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends BaseServiceImpl<RoleDao, String> implements RoleService {

    private final RoleRepository repository;

    @Autowired
    public RoleServiceImpl(RoleRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Optional<RoleDao> findByAuthority(String authority) {
        return repository.findByAuthority(authority);
    }
}