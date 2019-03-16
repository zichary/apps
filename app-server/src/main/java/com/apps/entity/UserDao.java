package com.apps.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * 用戶資訊
 *
 * @author SimonYang
 * @date 2019 /2/10
 * @version 1.0.0
 */
@Getter
@Setter
@Entity
@Table(name = "USER")
@NoArgsConstructor
@Component
public class UserDao extends BaseDao implements UserDetails {

    /** 帳號 */
    private String username;
    /** 密碼(已加密) */
    private String password;
    /** 姓名 */
    private String name;
    /** 工號 */
    private String jobNumber;
    /** 暱稱 */
    private String nikName;
    /** 帳戶未過期 */
    private boolean accountNonExpired = false;
    /** 帳戶未鎖定 */
    private boolean accountNonLocked = false;
    /** 憑證未過期 */
    private boolean credentialsNonExpired = false;
    /** 啟用 */
    private boolean enabled = false;
    /** 角色清單 */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private Set<AuthDao> authorities;

    public UserDao(UserDao other) {
        super(other);
        this.username = other.username;
        this.password = other.password;
        this.name = other.name;
        this.jobNumber = other.jobNumber;
        this.nikName = other.nikName;
        this.accountNonExpired = other.accountNonExpired;
        this.accountNonLocked = other.accountNonLocked;
        this.credentialsNonExpired = other.credentialsNonExpired;
        this.enabled = other.enabled;
        this.authorities = other.authorities;
    }
}
