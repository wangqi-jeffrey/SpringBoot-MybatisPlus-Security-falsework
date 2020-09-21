package com.jeffrey.utils;

import com.jeffrey.context.constant.CommonConstant;
import com.jeffrey.manager.CdocManager;
import com.jeffrey.manager.OssManager;
import com.jeffrey.manager.SpringContrextUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.output.FileWriterWithEncoding;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * Description: Freemarker工具类
 *
 * @author WQ
 * @date 2020/9/8 11:09 AM
 */
@Slf4j
public class FreemarkerUtil {

    /**
     * 渲染模板，返回html文档
     *
     * @param viewName
     * @param params
     * @return
     */
    public static String parseTpl(String viewName, Map<String, Object> params) {
        Configuration cfg = SpringContrextUtils.getBeanByClass(Configuration.class);
        String html = null;
        Template t = null;
        try {
            t = cfg.getTemplate(viewName + ".ftl");
            html = FreeMarkerTemplateUtils.processTemplateIntoString(t, params);
        } catch (IOException | TemplateException e) {
            log.error(LogUtil.getCommLog(e));
        }
        return html;
    }

    /**
     * 将模板渲染成pdf
     *
     * @param viewName
     * @param fileName
     * @param filePath
     * @param params
     * @return
     */
    public static String parseTplToPdf(String viewName, String fileName, String filePath, Map<String, Object> params) {
        String htmlUrl = parseTplToHtml(viewName, fileName, params);
        return htmlToPdf(htmlUrl, fileName);
    }

    /**
     * 将模板渲染成html
     *
     * @param viewName
     * @param fileName
     * @param params
     * @return
     */
    public static String parseTplToHtml(String viewName, String fileName, Map<String, Object> params) {
        Configuration cfg = SpringContrextUtils.getBeanByClass(Configuration.class);
        OssManager ossManager = SpringContrextUtils.getBeanByName("ossManager", OssManager.class);
        File file = new File("tmp/html/" + fileName + ".html");
        try {
            if (file.exists()) {
                file.delete();
            }
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (Exception e) {
            log.error(LogUtil.getCommLog(e));
        }

        try (Writer out = new FileWriterWithEncoding(file, CommonConstant.DEFAULT_CHARSET, true)) {
            Template template = cfg.getTemplate(viewName + ".ftl");
            template.process(params, out);
            return ossManager.putFile("html", fileName + ".html", file);
        } catch (Exception e) {
            log.error(LogUtil.getCommLog(e));
        } finally {
            file.delete();
        }
        return null;
    }

    /**
     * 将html转化成pdf
     *
     * @param htmlUrl
     * @param fileName
     * @return
     */
    public static String htmlToPdf(String htmlUrl, String fileName) {
        CdocManager cdocManager = SpringContrextUtils.getBeanByClass(CdocManager.class);
        OssManager ossManager = SpringContrextUtils.getBeanByName("ossManager", OssManager.class);
        byte[] bytes = cdocManager.html2pdf(htmlUrl);
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes)) {
            String pdfKey = ossManager.putObjectInputStream("pdf", fileName + ".pdf", inputStream);
            return ossManager.generateUrl(pdfKey);
        } catch (Exception e) {
            log.error(LogUtil.getCommLog(e));
        }
        return null;
    }

}
