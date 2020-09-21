package com.jeffrey.context.security;

import com.jeffrey.context.properties.JwtTokenProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Description: WebSecurityConfig
 *
 * @author WQ
 * @date 2020/8/20 6:04 PM
 */
@Configuration
@EnableWebSecurity
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启方法权限控制
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserTokenAuthenticationProvider userTokenAuthenticationProvider;
    @Autowired
    private UserAuthenticationFailureHandler userAuthenticationFailureHandler;
    @Autowired
    private JwtTokenProperties jwtTokenProperties;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 授权
        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().disable()
                .addFilterBefore(userTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);//token 验证拦截器

        http.headers().cacheControl();
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable(); //取消csrf
    }


    /**
     * token拦截器
     *
     * @throws Exception
     */
    private JwtAuthenticationFilter userTokenAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(authenticationManager(),
                jwtTokenProperties);
        filter.setContinueChainBeforeSuccessfulAuthentication(true);
        filter.setAuthenticationFailureHandler(userAuthenticationFailureHandler);
        return filter;
    }


    @Bean
    public UserAuthenticationFailureHandler authenticationFailureHandler() {
        return new UserAuthenticationFailureHandler();
    }

    @Bean
    public UserTokenAuthenticationProvider userTokenAuthenticationProvider() {
        return new UserTokenAuthenticationProvider();
    }

    /**
     * 自定义校验组件
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(userTokenAuthenticationProvider);
    }

}
