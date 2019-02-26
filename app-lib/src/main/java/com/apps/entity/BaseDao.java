package com.apps.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 基礎資訊
 *
 * @author SimonYang
 * @date 2019/2/10
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseDao {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(length = 36, updatable = false, nullable = false)
    private String id;
    /** 啟用 */
    private boolean action = true;
    /** 刪除 */
    private boolean alive = true;
    /** 建立日期 */
    private LocalDateTime createDate;
    /** 建立人 */
    private String createUser;
    /** 建立IP */
    private String createAddress;
    /** 修改日期 */
    private LocalDateTime updateDate;
    /** 修改人 */
    private String updateUser;
    /** 修改IP */
    private String updateAddress;

    public BaseDao(BaseDao other) {
        this.id = other.id;
        this.action = other.action;
        this.alive = other.alive;
        this.createDate = other.createDate;
        this.createUser = other.createUser;
        this.createAddress = other.createAddress;
        this.updateDate = other.updateDate;
        this.updateUser = other.updateUser;
        this.updateAddress = other.updateAddress;
    }

    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * 保存資料前紀錄時間
     *
     * @param user
     *         String 操作者工號
     * @param ip
     *         String 操作者IP
     */
    public void saveData(String user, String ip) {
        if (isAction() && isAlive()) {
            LocalDateTime date = LocalDateTime.now();
            if (isNew()) {
                setCreateDate(date);
                setCreateUser(user);
                setCreateAddress(ip);
            }
            setUpdateDate(date);
            setUpdateUser(user);
            setUpdateAddress(ip);
        }
    }

    /**
     * 變更啟用狀態(可還原)
     *
     * @param user
     *         String 操作者工號
     * @param ip
     *         String 操作者IP
     */
    public void enable(String user, String ip) {
        if (isAlive()) {
            setUpdateDate(LocalDateTime.now());
            setUpdateUser(user);
            setUpdateAddress(ip);
            setAction(!isAction());
        }
    }

    /**
     * 變更刪除狀態(不可還原)
     *
     * @param user
     *         String 操作者工號
     * @param ip
     *         String 操作者IP
     */
    public void dead(String user, String ip) {
        if (isAlive()) {
            setUpdateDate(LocalDateTime.now());
            setUpdateUser(user);
            setUpdateAddress(ip);
            setAction(false);
            setAlive(false);
        }
    }
}
