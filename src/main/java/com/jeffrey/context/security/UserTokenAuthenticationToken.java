package com.jeffrey.context.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: UserTokenAuthenticationToken
 *
 * @author WQ
 * @date 2020/8/20 6:11 PM
 */
public class UserTokenAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = -6034356823375261423L;
    //~ Instance fields ================================================================================================
    private final Object principal;
    private Object credentials;
    private String accessToken;
    private Map<String, Object> userDetails = new HashMap<>();

    //~ Constructors ===================================================================================================

    /**
     * This constructor can be safely used by any code that wishes to create a
     * <code>JwtAuthenticationToken</code>, as the {@link
     * #isAuthenticated()} will return <code>false</code>.
     */
    public UserTokenAuthenticationToken(Object principal, String accessToken) {
        super(null);
        this.principal = principal;
        this.accessToken = accessToken;
        setAuthenticated(false);
    }


    /**
     * 用户密码登录验证通过
     * This constructor should only be used by <code>AuthenticationManager</code> or <code>AuthenticationProvider</code>
     * implementations that are satisfied with producing a trusted (i.e. {@link #isAuthenticated()} = <code>true</code>)
     * authentication token.
     *
     * @param principal
     * @param accessToken
     * @param authorities
     */
    public UserTokenAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities, String accessToken, Map<String, Object> userDetails) {
        super(authorities);
        this.principal = principal;
        this.accessToken = accessToken;
        this.userDetails = userDetails;
        super.setAuthenticated(true); // must use super, as we override
    }


    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Once created you cannot set this token to authenticated. Create a new instance using the constructor which takes a GrantedAuthority list will mark this as authenticated.");
        }

        super.setAuthenticated(false);
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Map<String, Object> getUserDetails() {
        return userDetails;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        credentials = null;
    }
}
