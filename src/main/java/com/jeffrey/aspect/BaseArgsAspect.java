package com.jeffrey.aspect;

import com.jeffrey.context.HttpServletRequestContextHolder;
import com.jeffrey.context.constant.CommonConstant;
import com.jeffrey.dto.request.BaseRequestDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: 基础入参自动填充
 *
 * @author WQ
 * @date 2020/8/22 9:04 PM
 */
@Aspect
@Order(5)
@Component
public class BaseArgsAspect {

    @Pointcut("execution(public * com.jeffrey..controller..*.*(..))")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void processHandler(JoinPoint pjp) throws Throwable {
        for (Object arg : pjp.getArgs()) {
            if (arg instanceof BaseRequestDTO) {
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getRequest();
                BaseRequestDTO baseRequestDTO = (BaseRequestDTO) arg;
                BaseRequestDTO baseRequestAttributes = HttpServletRequestContextHolder.getBaseRequestAttributes(request);
                baseRequestDTO.setPlatform(baseRequestAttributes.getPlatform());
                baseRequestDTO.setVersion(baseRequestAttributes.getVersion());
                baseRequestDTO.setClientIp(baseRequestAttributes.getClientIp());
                baseRequestDTO.setUserInfoDTO(baseRequestAttributes.getUserInfoDTO());
                baseRequestDTO.setManagerAccountDTO(baseRequestAttributes.getManagerAccountDTO());
                request.setAttribute(CommonConstant.BASE_REQUEST_ATTR, baseRequestDTO);
                break;
            }
        }
    }
}
