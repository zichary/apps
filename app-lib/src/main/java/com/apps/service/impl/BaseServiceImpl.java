package com.apps.service.impl;

import com.apps.entity.BaseDao;
import com.apps.service.BaseService;
import com.apps.util.BaseMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author SimonYang
 * @version 1.0.0
 * @date 2019-03-15
 */
@Slf4j
public class BaseServiceImpl<T extends BaseDao, ID> implements BaseService<T, ID> {

    private JpaRepository<T, ID> repository;

    public BaseServiceImpl(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public Optional<T> save(T t, String user, String ip) {
        Optional<T> optional = Optional.empty();
        try {
            t.saveData(user, ip);
            optional = Optional.of(repository.save(t));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return optional;
    }

    @Override
    public void delete(ID id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }


    @Override
    public Optional<T> remove(ID id, String user, String ip) {
        Optional<T> optional = Optional.empty();
        try {
            Optional<T> daoOptional = repository.findById(id);
            if (daoOptional.isPresent()) {
                T userDao = daoOptional.get();
                userDao.dead(user, ip);
                optional = Optional.of(repository.save(userDao));
            } else {
                log.error(BaseMsg.DATA_NOT_FOUND);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return optional;
    }


    @Override
    public List<T> findAllList() {
        List<T> list = null;
        try {
            list = repository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.empty();
    }

    @Override
    public Optional<T> enable(ID id, String user, String ip) {
        Optional<T> optional = Optional.empty();
        try {
            Optional<T> daoOptional = repository.findById(id);
            if (daoOptional.isPresent()) {
                T dao = daoOptional.get();
                dao.enable(user, ip);
                optional = Optional.of(repository.save(dao));
            } else {
                log.error(BaseMsg.DATA_NOT_FOUND);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return optional;
    }
}
