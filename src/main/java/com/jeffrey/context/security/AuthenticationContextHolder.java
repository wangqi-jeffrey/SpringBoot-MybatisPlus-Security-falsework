package com.jeffrey.context.security;

import com.jeffrey.context.enums.ErrorCodeEnum;
import com.jeffrey.context.exception.JwtAuthenticationException;
import com.jeffrey.dto.ManagerAccountDTO;
import com.jeffrey.dto.UserInfoDTO;
import com.jeffrey.utils.BeanMapUtils;
import com.jeffrey.utils.JwtTokenUtil;
import com.jeffrey.utils.LogUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Description: 获取上下文认证信息
 *
 * @author WQ
 * @date 2020/08/20 7:55 PM
 */
@Slf4j
public class AuthenticationContextHolder {

    /**
     * 获取B端管理员信息
     *
     * @return
     */
    public static ManagerAccountDTO getManagerAccount() {
        return getUserDetails(getUserAuthentication(), ManagerAccountDTO.class);
    }

    public static ManagerAccountDTO getManagerAccount(String accessToken) {
        Claims claims = JwtTokenUtil.getTokenBody(accessToken);
        try {
            return BeanMapUtils.mapToBean(claims, ManagerAccountDTO.class);
        } catch (Exception e) {
            throw new JwtAuthenticationException(ErrorCodeEnum._10024);
        }
    }

    /**
     * 获取C端租客信息
     *
     * @return
     */
    public static UserInfoDTO getUserInfo() {
        return getUserDetails(getUserAuthentication(), UserInfoDTO.class);
    }

    public static <T> T getUserDetails(UserTokenAuthenticationToken userAuthentication, Class<T> clazz) {
        try {
            return BeanMapUtils.mapToBean(userAuthentication.getUserDetails(), clazz);
        } catch (Exception e) {
            log.error(LogUtil.getCommLog(e));
            throw new JwtAuthenticationException(ErrorCodeEnum._10024);
        }
    }

    public static UserTokenAuthenticationToken getUserAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            log.error(LogUtil.getCommLog("获取Claims信息失败，authentication为空"));
            throw new JwtAuthenticationException(ErrorCodeEnum._10024);
        }

        if (authentication instanceof UserTokenAuthenticationToken) {
            return (UserTokenAuthenticationToken) authentication;
        }
        return null;
    }


}