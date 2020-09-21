package com.jeffrey.manager;

/**
 *  * Description: html转pdf
 *  * @author 金泽强
 *  * @date 2020/09/17 9:53 上午
 *  
 */

import com.jeffrey.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class CdocManager {

    @Value("${config.cdoc.service.url}")
    private String cdocUrl;

    public byte[] html2pdf(String url) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("url", url);
            return HttpUtil.postReceiveByte(cdocUrl.concat("/html2pdf/"), params, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
