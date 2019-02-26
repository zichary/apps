package com.apps.service.base;

import java.util.List;
import java.util.Optional;

/**
 * 基礎介面
 *
 * @author SimonYang
 * @date 2019/2/10
 */
public interface BaseService<T> {

    /**
     * 取得清單
     *
     * @return List<T>
     */
    List<T> getAllList();

    /**
     * 新增
     *
     * @param t
     *         T 操作物件
     * @param user
     *         String 用戶
     * @param ip
     *         String ip
     * @return Optional<T>
     */
    Optional<T> save(T t, String user, String ip);

    /**
     * 刪除(保留檔案)
     *
     * @param id
     *         String 操作物件id
     * @param user
     *         String 用戶
     * @param ip
     *         String ip
     * @return Optional<T>
     */
    Optional<T> remove(String id, String user, String ip);

    /**
     * 啟用/停用
     *
     * @param id
     *         String 操作物件id
     * @param user
     *         String 用戶
     * @param ip
     *         String ip
     * @return Optional<T>
     */
    Optional<T> enable(String id, String user, String ip);

    /**
     * 刪除(不保留檔案)
     *
     * @param id
     *         String ID
     */
    void delete(String id);
}
