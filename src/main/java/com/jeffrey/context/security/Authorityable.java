package com.jeffrey.context.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Description: 实现用户角色设置
 *
 * @author WQ
 * @date 2020/08/22 6:02 PM
 */
public interface Authorityable {

    Collection<? extends GrantedAuthority> obtainRoles();
}