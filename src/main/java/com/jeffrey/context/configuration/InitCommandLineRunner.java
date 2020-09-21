package com.jeffrey.context.configuration;

import com.jeffrey.context.properties.JwtTokenProperties;
import com.jeffrey.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Description: 初始化配置信息
 *
 * @author WQ
 * @date 2020/9/16 4:08 PM
 */
@Order(Integer.MAX_VALUE)
@Component
public class InitCommandLineRunner implements CommandLineRunner {

    @Autowired
    private JwtTokenProperties jwtTokenProperties;

    public void run(String... args) throws Exception {
        // 加载配置信息
        JwtTokenUtil.setup(jwtTokenProperties.getAliasKey(), jwtTokenProperties.getPassword().toCharArray(),
                jwtTokenProperties.getKeystorePath());
    }

}
