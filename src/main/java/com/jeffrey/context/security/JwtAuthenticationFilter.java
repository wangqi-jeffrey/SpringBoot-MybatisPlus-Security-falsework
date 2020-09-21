package com.jeffrey.context.security;

import com.jeffrey.context.enums.ErrorCodeEnum;
import com.jeffrey.context.exception.JwtAuthenticationException;
import com.jeffrey.context.properties.JwtTokenProperties;
import com.jeffrey.dto.response.ResponseDTO;
import com.jeffrey.utils.JwtTokenUtil;
import com.jeffrey.utils.json.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Description: JwtAuthenticationFilter
 *
 * @author WQ
 * @date 2020/8/20 6:15 PM
 */
@Slf4j
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProperties jwtTokenProperties;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenProperties jwtTokenProperties) {
        super(new AntPathRequestMatcher(jwtTokenProperties.getTokenPathMatcher()));
        this.authenticationManager = authenticationManager;
        this.jwtTokenProperties = jwtTokenProperties;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        log.info("http request url： {}", request.getRequestURL());
        // chain.doFilter(req, res);
        if (match(request, freeLoginUrls)) {
            chain.doFilter(req, res);
            return;
        }
        super.doFilter(req, res, chain);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String token = request.getHeader(jwtTokenProperties.getTokenHeader());
        if (StringUtils.isEmpty(token)) {
            throw new JwtAuthenticationException(ErrorCodeEnum._10015);
        }

        String subject = JwtTokenUtil.getSubject(token);
        UserTokenAuthenticationToken authRequest = new UserTokenAuthenticationToken(subject, token);
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
        return this.authenticationManager.authenticate(authRequest);
    }

    private void printWriter(HttpServletResponse httpResponse, ResponseDTO responseDTO) throws IOException {
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        PrintWriter out = httpResponse.getWriter();
        out.write(GsonUtil.toJson(responseDTO));
        out.flush();
        out.close();
    }

    public static boolean match(HttpServletRequest request, String[] freeLoginUrls) {
        // 忽略白名单
        AntPathRequestMatcher matcher;
        for (String whiteUrl : freeLoginUrls) {
            matcher = new AntPathRequestMatcher(whiteUrl);
            if (matcher.matches(request)) {
                return true;
            }
        }
        return false;
    }

    private String[] freeLoginUrls = {
            "/",
            "/error",
            "/actuator/**",
            "/favicon.ico",
            "/swagger/**",
            "/swagger-resources/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/v2/api-docs/**",
            "/manager-account/oauth/**",
            "/oss/bank-branch/**",
            "/oss/landlord-info/**",
            "/user/oauth/ticket/**",
            "/payment/callback",
            "/ccb/**",
            "/**.html"
    };
}
