package com.jeffrey.utils;

import com.jeffrey.utils.json.GsonUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Description: 响应处理工具类
 *
 * @author 滕国栋
 * @date 2020/08/17 下午 21:49
 */
public class ResponseUtil {

    private static final Log logger = LogFactory.getLog(ResponseUtil.class);

    /**
     * 根据用户端接受的文件格式，生成对应的格式
     *
     * @param result
     * @return
     */
    public static String getResponseContent(Object result) {
        // 获取客户端能够接收的数据格式
        String content = GsonUtil.toJson(result);
        return content;
    }

    public static void writeObj(HttpServletResponse response, Object value) throws IOException {
        writer(response, GsonUtil.toJson(value));
    }

    /**
     * 向客户端输出内容
     *
     * @param response
     * @param content
     */
    public static void writer(HttpServletResponse response, String content) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(content);
            out.flush();
        } catch (IOException e) {
            logger.error(LogUtil.getCommLog(String.format("响应客户端出错:%s", e.getMessage())));
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    logger.error(LogUtil.getCommLog(String.format("关闭输出流出错:%s", e.getMessage())));
                }
            }
        }
    }

    /**
     * 向客户端输出图片
     *
     * @param response
     * @param bufferedImage
     */
    public static void writer(HttpServletResponse response, BufferedImage bufferedImage) {
        response.setContentType("image/jpeg");
        response.setDateHeader("expries", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        ServletOutputStream outPutStream = null;
        try {
            outPutStream = response.getOutputStream();
            ImageIO.write(bufferedImage, "jpg", outPutStream);
            outPutStream.flush();
        } catch (IOException e) {
            logger.error(LogUtil.getCommLog(String.format("响应客户端出错:%s", e.getMessage())));
        } finally {
            if (outPutStream != null) {
                try {
                    outPutStream.close();
                } catch (Exception e) {
                    logger.error(LogUtil.getCommLog(String.format("关闭输出流出错:%s", e.getMessage())));
                }
            }
        }
    }

}
