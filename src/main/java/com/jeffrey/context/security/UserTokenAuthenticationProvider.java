package com.jeffrey.context.security;

import com.jeffrey.context.enums.ErrorCodeEnum;
import com.jeffrey.context.exception.JwtAuthenticationException;
import com.jeffrey.utils.BeanMapUtils;
import com.jeffrey.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.List;

/**
 * Description: UserTokenAuthenticationProvider
 *
 * @author WQ
 * @date 2020/8/20 6:11 PM
 */
@Slf4j
public class UserTokenAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserTokenAuthenticationToken authenticationToken = (UserTokenAuthenticationToken) authentication;

        String USERIDENTITY = "UNKNOWN";
        try {
            String accessToken = authenticationToken.getAccessToken();
            Claims claims = JwtTokenUtil.getTokenBody(accessToken);
            USERIDENTITY = claims.getSubject();
            if (USERIDENTITY == null || USERIDENTITY.equals("UNKNOWN")) {
                throw new JwtAuthenticationException(ErrorCodeEnum._10023);
            }
            UserTokenAuthenticationToken userTokenAuthenticationToken =
                    new UserTokenAuthenticationToken(USERIDENTITY, getAuthorityFromToken(claims), accessToken, claims);
            // 赋值授权，用于其他请求获取用户
            SecurityContextHolder.getContext().setAuthentication(userTokenAuthenticationToken);
            return userTokenAuthenticationToken;
        } catch (JwtAuthenticationException e) {
            throw e;
        } catch (ExpiredJwtException e) {
            throw new JwtAuthenticationException(ErrorCodeEnum._10018);
        } catch (UnsupportedJwtException e) {
            throw new JwtAuthenticationException(ErrorCodeEnum._10019, USERIDENTITY);
        } catch (MalformedJwtException e) {
            throw new JwtAuthenticationException(ErrorCodeEnum._10020, USERIDENTITY);
        } catch (SignatureException e) {
            throw new JwtAuthenticationException(ErrorCodeEnum._10021, USERIDENTITY);
        } catch (IllegalArgumentException e) {
            throw new JwtAuthenticationException(ErrorCodeEnum._10022, USERIDENTITY);
        } catch (Exception e) {
            log.error("解析登录token失败，{}", ExceptionUtils.getStackTrace(e));
            throw new JwtAuthenticationException(ErrorCodeEnum._10017, USERIDENTITY);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UserTokenAuthenticationToken.class);
    }

    @SuppressWarnings("unchecked")
    private Collection<? extends GrantedAuthority> getAuthorityFromToken(Claims claims) throws IllegalAccessException, InstantiationException {
        List authorities = claims.get("authorities", List.class);
        List<MySimpleGrantedAuthority> authoritySet = BeanMapUtils.mapsToObjects(authorities, MySimpleGrantedAuthority.class);
        return authoritySet;
    }
}
