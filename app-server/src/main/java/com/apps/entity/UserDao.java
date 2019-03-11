package com.apps.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用戶資訊
 *
 * @author SimonYang
 * @date 2019 /2/10
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "USER")
@Component
public class UserDao extends BaseDao {

    /** 姓名 */
    private String name;
    /** 工號 */
    private String jobNumber;

    /**
     * Instantiates a new User dao.
     *
     * @param other
     *         the other
     */
    public UserDao(UserDao other) {
        super(other);
        this.name = other.name;
        this.jobNumber = other.jobNumber;
    }

}
