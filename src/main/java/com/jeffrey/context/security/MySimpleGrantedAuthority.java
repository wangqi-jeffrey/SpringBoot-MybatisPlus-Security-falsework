package com.jeffrey.context.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * Description: 自定义简单实现{@link GrantedAuthority}，并提供无参构造方法
 *
 * @author WQ
 * @date 2020/08/25 9:25 PM
 */
@Data
public class MySimpleGrantedAuthority implements GrantedAuthority {

    private String role;

    public MySimpleGrantedAuthority() {
    }

    public MySimpleGrantedAuthority(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof MySimpleGrantedAuthority) {
            return role.equals(((MySimpleGrantedAuthority) obj).role);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.role.hashCode();
    }

    @Override
    public String toString() {
        return this.role;
    }
}