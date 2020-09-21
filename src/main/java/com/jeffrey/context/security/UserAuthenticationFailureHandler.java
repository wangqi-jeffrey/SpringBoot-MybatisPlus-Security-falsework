package com.jeffrey.context.security;

import com.jeffrey.context.enums.ErrorCodeEnum;
import com.jeffrey.context.exception.JwtAuthenticationException;
import com.jeffrey.dto.response.ResponseDTO;
import com.jeffrey.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: AuthenticationFailureHandler
 *
 * @author WQ
 * @date 2020/8/20 6:07 PM
 */
@Slf4j
public class UserAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        log.error("authentication happend a error: {}", exception.getMessage());
        if (exception instanceof JwtAuthenticationException) {
            JwtAuthenticationException except = (JwtAuthenticationException) exception;
            log.error("authentication happend a error, current user identity is: {}", except.getUserIdentity());
            ErrorCodeEnum errorCodeEnum = except.getErrorCodeEnum();
            ResponseUtil.writeObj(response, ResponseDTO.failure(errorCodeEnum));
            return;
        }

        ResponseUtil.writeObj(response, ResponseDTO.failure(ErrorCodeEnum._10016));
    }
}
