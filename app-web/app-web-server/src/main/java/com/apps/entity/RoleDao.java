package com.apps.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * 角色
 *
 * @author SimonYang
 * @version 1.0.0
 * @date 2019-03-13
 */
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "ROLE")
@Component
public class RoleDao extends BaseDao implements GrantedAuthority {

    /** 角色名稱 */
    private String authority;
    /** 角色說明 */
    private String authDesc;

    protected RoleDao() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoleDao)) {
            return false;
        }
        RoleDao that = (RoleDao) o;
        return authority.equals(that.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authority);
    }
}
