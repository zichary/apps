package com.apps.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@Entity
@Table(name = "AUTH")
@Component
public class AuthDao extends BaseDao implements GrantedAuthority {

    /** 角色名稱 */
    private String authority;
    /** 角色說明 */
    private String authDesc;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AuthDao)) {
            return false;
        }
        AuthDao that = (AuthDao) o;
        return authority.equals(that.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authority);
    }
}
