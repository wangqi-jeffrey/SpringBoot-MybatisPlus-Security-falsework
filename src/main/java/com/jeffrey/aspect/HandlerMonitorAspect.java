package com.jeffrey.aspect;

import com.jeffrey.context.enums.ErrorCodeEnum;
import com.jeffrey.context.exception.BaseException;
import com.jeffrey.dto.response.ResponseDTO;
import com.jeffrey.utils.LogUtil;
import com.jeffrey.utils.RequestUtil;
import com.jeffrey.utils.ResponseUtil;
import com.jeffrey.utils.StringUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Description: 监控器
 *
 * @author 滕国栋
 * @date 2020/08/17 下午 21:49
 */
@Aspect
@Order(1)
@Component
public class HandlerMonitorAspect {

    private static final Logger logger = LoggerFactory.getLogger(HandlerMonitorAspect.class);

    public static final String TRACE_ID = "TRACE_ID";

    @Pointcut("execution(public * com.jeffrey..controller..*.*(..))")
    public void handlerMonitor() {

    }

    @Around("handlerMonitor()")
    public Object processHandler(ProceedingJoinPoint pjp) {
        MDC.put(TRACE_ID, UUID.randomUUID().toString());
        //开始时间
        long startTime = System.currentTimeMillis();

        //返回结果
        Object result = null;
        HttpServletRequest request = null;

        boolean except = false;
        // 请求标识
        String queryId = StringUtil.EMPTY;
        String viewQueryId = StringUtil.EMPTY;

        try {
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            if (request == null) {
                throw new Exception("该方法没有HttpServletRequest参数.");
            }

            //打印请求参数
            logger.info(LogUtil.getRequestLog(request));

            try {
                //生成queryid
                String uuid = StringUtil.generateUUIDStr();
                queryId = String.format("[QueryID:%s]", uuid);
                //生成用于展示给用户的queryid
                viewQueryId = String.format("[信息码:%s]", uuid.substring(0, 6));
            } catch (Exception e) {
                logger.error(LogUtil.getCommLog(String.format("生成请求码异常:%s", ExceptionUtils.getStackTrace(e))));
            }

            result = pjp.proceed();
            if (result != null && result.getClass().isAssignableFrom(ResponseDTO.class)) {
                except = ((ResponseDTO) result).isSuccess();
            }
        } catch (Exception e) {
            // 判断是否为BaseException异常及其子异常
            if (BaseException.class.isAssignableFrom(e.getClass())) {
                BaseException baseException = (BaseException) e;
                result = ResponseDTO.failure(baseException.getErrorCode(), baseException.getDescription());
                logger.error(LogUtil.getCommLog(String.format("[%s:%s]业务异常%s:%s", request.getRequestURL().toString(),
                        RequestUtil.getRequest4String(request), queryId, ExceptionUtils.getStackTrace(e))));
            } else if (AccessDeniedException.class.isAssignableFrom(e.getClass())) {
                result = ResponseDTO.failure(ErrorCodeEnum._10006);
                logger.error(LogUtil.getCommLog(String.format("[%s:%s]权限校验失败%s:%s", request.getRequestURL().toString(),
                        RequestUtil.getRequest4String(request), queryId, ExceptionUtils.getStackTrace(e))));
            } else {
                result = ResponseDTO.failure(ErrorCodeEnum._10002);
                logger.error(LogUtil.getCommLog(String.format("[%s:%s]未知错误类型%s:%s", request.getRequestURL().toString(),
                        RequestUtil.getRequest4String(request), queryId, ExceptionUtils.getStackTrace(e))));
            }
        } catch (Throwable e) {
            result = ResponseDTO.failure(ErrorCodeEnum._10002);
            logger.error(LogUtil.getCommLog(String.format("[%s:%s]未知错误类型%s:%s", request.getRequestURL().toString(),
                    RequestUtil.getRequest4String(request), queryId, ExceptionUtils.getStackTrace(e))));
        }

        if (result != null && result.getClass().isAssignableFrom(ResponseDTO.class)) {
            String description = ((ResponseDTO) result).getStatus().getDescription();
            if (!except) {
                description = ((ResponseDTO) result).getStatus().getDescription() + StringUtil.SPACE + viewQueryId;
            }
            ((ResponseDTO) result).getStatus().setDescription(description);
        }

        //请求耗时
        long costTime = System.currentTimeMillis() - startTime;

        logger.info(LogUtil.getResponseLog(costTime, ResponseUtil.getResponseContent(result),
                request.getRequestURL().toString()));
        MDC.remove(TRACE_ID);

        return result;
    }
}
