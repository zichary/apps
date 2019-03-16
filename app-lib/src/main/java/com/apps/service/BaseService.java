package com.apps.service;

import java.util.List;
import java.util.Optional;

/**
 * 基礎介面
 *
 * @param <T>
 *         the type parameter
 * @author SimonYang
 * @date 2019 /2/10
 */
public interface BaseService<T, ID> {

    /**
     * 新增
     *
     * @param t
     *         T 操作物件
     * @param user
     *         String 操作者名稱
     * @param ip
     *         String 操作者ip
     * @return Optional<T>  optional
     */
    Optional<T> save(T t, String user, String ip);

    /**
     * 刪除(保留檔案)
     *
     * @param id
     *         String 操作物件id
     * @param user
     *         String 操作者名稱
     * @param ip
     *         String 操作者ip
     * @return Optional<T>  optional
     */
    Optional<T> remove(ID id, String user, String ip);

    /**
     * 刪除(不保留檔案)
     *
     * @param id
     *         String 紀錄ID
     */
    void delete(ID id);

    /**
     * 取得清單
     *
     * @return List<T>  list
     */
    List<T> findAllList();

    /**
     * 使用ID取得物件
     *
     * @param id
     *         String 物件ID
     * @return optional optional
     */
    Optional<T> findById(ID id);

    /**
     * 啟用/停用
     *
     * @param id
     *         ID 操作物件id
     * @param user
     *         String 操作者名稱
     * @param ip
     *         String 操作者ip
     * @return Optional<T>  optional
     */
    Optional<T> enable(ID id, String user, String ip);


}
