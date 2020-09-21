package com.jeffrey.aspect;

import com.jeffrey.context.annotation.EnumField;
import com.jeffrey.context.annotation.MoreThan;
import com.jeffrey.context.annotation.ScriptValid;
import com.jeffrey.context.annotation.VerifyObject;
import com.jeffrey.context.enums.ErrorCodeEnum;
import com.jeffrey.context.exception.BaseException;
import com.jeffrey.context.exception.LackParameterException;
import com.jeffrey.dto.response.ResponseDTO;
import com.jeffrey.utils.BeanUtil;
import com.jeffrey.utils.DateUtil;
import com.jeffrey.utils.LogUtil;
import com.jeffrey.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

/**
 * Description: 入参校验AOP
 * ClassName: ParamValidationAop
 * date: 2018年11月11日
 *
 * @author YGC
 * @since JDK 1.8
 */
@Slf4j
@Aspect
@Order(3)
@Component
public class ParamValidationAspect {

    @Pointcut("execution(public * com.jeffrey.controller..*.*(..))")
    public void paramValidation() {

    }

    @Around("paramValidation()")
    public ResponseDTO handle(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                log.info(LogUtil.getCommLog(String.format("param:%s", arg)));
                if (arg == null)
                    continue;
                // 获取需要校验的类
                List<Class<?>> clazzs = BeanUtil.getSuperClasses(arg.getClass());

                // 分别校验各个类中加有注解的字段
                if (CollectionUtils.isNotEmpty(clazzs)) {
                    for (int i = 0; i < clazzs.size(); i++) {
                        verify(arg, clazzs.get(i));
                    }
                }
            }
        }
        return (ResponseDTO) pjp.proceed();
    }

    /**
     * 获取属性对用的getter
     *
     * @param fieldName
     * @param clazz
     * @return
     * @throws NoSuchMethodException
     */
    private Method getMethodByFieldName(String fieldName, Class<?> clazz) throws NoSuchMethodException {
        String charAtFirst = fieldName.substring(0, 1);
        String methodName = "get" + fieldName.replaceFirst(charAtFirst, charAtFirst.toUpperCase());
        return clazz.getDeclaredMethod(methodName);
    }

    /**
     * 校验
     *
     * @param arg
     * @param clazz
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws NoSuchFieldException
     */
    private void verify(Object arg, Class<?> clazz)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            verifyNotNull(arg, field, clazz);
            verifyObject(arg, field, clazz);
            verifyNotBlank(arg, field, clazz);
            verifyNotEmpty(arg, field, clazz);
            verifySize(arg, field, clazz);
            verifyNumberRange(arg, field, clazz);
            verifyEnum(arg, field, clazz);
            verifyComparison(arg, field, clazz);
            verifyPattern(arg, field, clazz);
        }
        Annotation[] typeAnnotations = clazz.getAnnotations();
        for (Annotation annotation : typeAnnotations) {
            verifyScript(annotation, arg, clazz);
        }
    }

    /**
     * 对象非空校验
     *
     * @param arg
     * @param clazz
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    private void verifyNotNull(Object arg, Field field, Class<?> clazz)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        // 非空校验
        if (field.isAnnotationPresent(NotNull.class)) {
            Method method = getMethodByFieldName(field.getName(), clazz);
            Object obj = method.invoke(arg);
            if (obj == null) {
                NotNull notNull = field.getAnnotation(NotNull.class);
                if (StringUtil.isNotBlank(notNull.message())) {
                    throw new BaseException(ErrorCodeEnum._10004.getErrorCode(), "请输入" + notNull.message());
                }
            }
        }
    }

    /**
     * 自定义脚本校验
     *
     * @param clazz
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    private void verifyScript(Annotation annotation, Object arg, Class<?> clazz)
            throws NoSuchMethodException {
        if (annotation instanceof ScriptValid.List) {
            ScriptValid[] list = ((ScriptValid.List) annotation).value();
            for (ScriptValid valid : list) {
                Method method = clazz.getMethod(valid.script());
                try {
                    Object obj = method.invoke(arg);
                } catch (Exception e) {
                    throw new BaseException(ErrorCodeEnum._10004.getErrorCode(), e.getCause().getMessage());
                }
            }
        }
    }

    /**
     * 如果对象非空校验
     *
     * @param arg
     * @param clazz
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    private void verifyObject(Object arg, Field field, Class<?> clazz)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {
        // 如果对象非空校验
        if (field.isAnnotationPresent(VerifyObject.class)) {
            Class<?> type = field.getType();
            field.setAccessible(true);

            if (field.get(arg) != null) {
                verify(field.get(arg), type);
            }
        }
    }

    /**
     * 字符串非空校验
     *
     * @param arg
     * @param clazz
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    private void verifyNotBlank(Object arg, Field field, Class<?> clazz)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        // 非空校验
        if (field.isAnnotationPresent(NotBlank.class)) {
            Method method = getMethodByFieldName(field.getName(), clazz);
            Object obj = method.invoke(arg);
            if ((obj == null) || (obj instanceof String && StringUtil.isBlank((String) obj))) {
                NotBlank notBlank = field.getAnnotation(NotBlank.class);
                if (StringUtil.isNotBlank(notBlank.message())) {
                    throw new BaseException(ErrorCodeEnum._10004.getErrorCode(), notBlank.message());
                }
                throw new LackParameterException("请输入" + field.getName());
            }
        }
    }

    /**
     * 对象内容非空校验
     *
     * @param arg
     * @param clazz
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    private void verifyNotEmpty(Object arg, Field field, Class<?> clazz)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {
        // 非空校验
        if (field.isAnnotationPresent(NotEmpty.class)) {
            Method method = getMethodByFieldName(field.getName(), clazz);
            Object obj = method.invoke(arg);
            if (obj != null && obj instanceof List) {
                List<?> list = (List<?>) obj;
                if (CollectionUtils.isNotEmpty(list)) {
                    for (Object element : list) {
                        List<Class<?>> classes = BeanUtil.getSuperClasses(element.getClass());
                        for (Class<?> aClass : classes) {
                            verify(element, aClass);
                        }
                    }
                }
            }
        }
    }

    /**
     * 长度校验
     *
     * @param arg
     * @param clazz
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void verifySize(Object arg, Field field, Class<?> clazz)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (field.isAnnotationPresent(Size.class)) {
            Method method = getMethodByFieldName(field.getName(), clazz);
            Size size = field.getAnnotation(Size.class);
            int max = size.max();
            int min = size.min();
            Object obj = method.invoke(arg);
            if (obj != null) {
                if (obj instanceof String) {
                    String value = (String) obj;
                    if (value.length() > max) {
                        throw new BaseException(ErrorCodeEnum._10004.getErrorCode(),
                                StringUtil.isNotBlank(size.message()) ? size.message() : size.message() + "超出最长" + max + "字符长度限制");
                    }
                    if (value.trim().length() < min) {
                        throw new BaseException(ErrorCodeEnum._10004.getErrorCode(),
                                StringUtil.isNotBlank(size.message()) ? size.message() : size.message() + "小于最小" + min + "字符长度限制");
                    }
                }
                if (obj instanceof List) {
                    List<?> list = (List<?>) obj;
                    if (list.size() > max) {
                        throw new BaseException(ErrorCodeEnum._10004.getErrorCode(),
                                StringUtil.isNotBlank(size.message()) ? size.message() : size.message() + "超出最长" + max + "长度限制");
                    }

                    if (list.size() < min) {
                        throw new BaseException(ErrorCodeEnum._10004.getErrorCode(),
                                StringUtil.isNotBlank(size.message()) ? size.message() : size.message() + "小于最小" + min + "长度限制");
                    }
                }
                if (obj instanceof String[]) {
                    String[] array = (String[]) obj;
                    if (array.length > max) {
                        throw new BaseException(ErrorCodeEnum._10004.getErrorCode(),
                                StringUtil.isNotBlank(size.message()) ? size.message() : size.message() + "超出最多" + max + "张限制");
                    }

                    if (array.length < min) {
                        throw new BaseException(ErrorCodeEnum._10004.getErrorCode(),
                                StringUtil.isNotBlank(size.message()) ? size.message() : size.message() + "小于最少" + min + "张限制");
                    }
                }

                if (obj instanceof Long[]) {
                    Long[] array = (Long[]) obj;
                    if (array.length > max) {
                        throw new BaseException(ErrorCodeEnum._10004.getErrorCode(),
                                StringUtil.isNotBlank(size.message()) ? size.message() : size.message() + "超出最多" + max + "个限制");
                    }

                    if (array.length < min) {
                        throw new BaseException(ErrorCodeEnum._10004.getErrorCode(),
                                StringUtil.isNotBlank(size.message()) ? size.message() : size.message() + "小于最少" + min + "个限制");
                    }
                }
            }
        }
    }

    /**
     * 数值范围校验
     *
     * @param arg
     * @param clazz
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void verifyNumberRange(Object arg, Field field, Class<?> clazz)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (field.isAnnotationPresent(Max.class)) {
            Method method = getMethodByFieldName(field.getName(), clazz);
            Max max = field.getAnnotation(Max.class);
            long maxValue = max.value();
            Number value = (Number) method.invoke(arg);
            if (value != null && value.doubleValue() > maxValue) {
                throw new BaseException(ErrorCodeEnum._10004.getErrorCode(), max.message() + "大于" + maxValue);
            }
        }
        if (field.isAnnotationPresent(Min.class)) {
            Method method = getMethodByFieldName(field.getName(), clazz);
            Min min = field.getAnnotation(Min.class);
            long minValue = min.value();
            Number value = (Number) method.invoke(arg);
            if (value != null && value.doubleValue() < minValue) {
                throw new BaseException(ErrorCodeEnum._10004.getErrorCode(), min.message() + "小于" + minValue);
            }
        }
    }

    /**
     * 枚举值校验
     *
     * @param arg
     * @param clazz
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void verifyEnum(Object arg, Field field, Class<?> clazz)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (field.isAnnotationPresent(EnumField.class)) {
            Method method = getMethodByFieldName(field.getName(), clazz);
            EnumField enumField = field.getAnnotation(EnumField.class);
            String range = enumField.range();
            if (StringUtil.isNotBlank(range)) {
                Number value = (Number) method.invoke(arg);
                String[] values = range.split(",");
                List<String> valueList = Arrays.asList(values);
                if (value != null && !valueList.contains(value.toString())) {
                    throw new BaseException(ErrorCodeEnum._10004.getErrorCode(), field.getName() + "参数不合法");
                }
            }
        }
    }

    /**
     * 大小值比较校验,只能比较数值类型和日期类型
     *
     * @param arg
     * @param clazz
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void verifyComparison(Object arg, Field field, Class<?> clazz)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        if (field.isAnnotationPresent(MoreThan.class)) {
            Method method = getMethodByFieldName(field.getName(), clazz);
            MoreThan target = field.getAnnotation(MoreThan.class);
            String targetFieldName = target.target();
            if (StringUtil.isNotBlank(targetFieldName)) {
                Class<?> type = field.getType();
                if (Number.class.isAssignableFrom(type)) {
                    Number num = (Number) method.invoke(arg);
                    Field targetField = clazz.getDeclaredField(targetFieldName);
                    targetField.setAccessible(true);
                    Number targetNum = (Number) targetField.get(arg);
                    if (num != null && targetNum != null && num.doubleValue() <= targetNum.doubleValue()) {
                        throw new BaseException(ErrorCodeEnum._10004.getErrorCode(),
                                target.message() + "必须大于" + target.targetMessage());
                    }
                }
                if (Date.class.isAssignableFrom(type)) {
                    Date date = (Date) method.invoke(arg);
                    Field targetField = clazz.getDeclaredField(targetFieldName);
                    targetField.setAccessible(true);
                    Date targetDate = (Date) targetField.get(arg);
                    if (date != null && targetDate != null
                            && (DateUtil.afterInDay(targetDate, date) || DateUtil.isSameDay(date, targetDate))) {
                        throw new BaseException(ErrorCodeEnum._10004.getErrorCode(),
                                target.message() + "必须晚于" + target.targetMessage());
                    }
                }
                if (String.class.isAssignableFrom(type)) {
                    String num = (String) method.invoke(arg);
                    Field targetField = clazz.getDeclaredField(targetFieldName);
                    targetField.setAccessible(true);
                    String targetNum = (String) targetField.get(arg);
                    if (num != null && targetNum != null) {
                        Double d1, d2;
                        try {
                            d1 = Double.valueOf(num);
                        } catch (NumberFormatException e) {
                            log.info(LogUtil.getCommLog(String.format("@MoreThan不支持该数据：%s", num)));
                            return;
                        }

                        try {
                            d2 = Double.valueOf(targetNum);
                        } catch (NumberFormatException e) {
                            log.info(LogUtil.getCommLog(String.format("@MoreThan不支持该数据：%s", targetNum)));
                            return;
                        }

                        if (d1 <= d2) {
                            throw new BaseException(ErrorCodeEnum._10004.getErrorCode(),
                                    target.message() + "必须大于" + target.targetMessage());
                        }
                    }
                }
            }
        }
    }

    /**
     * 正则校验
     *
     * @param arg
     * @param clazz
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void verifyPattern(Object arg, Field field, Class<?> clazz)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (field.isAnnotationPresent(Pattern.class)) {
            Method method = getMethodByFieldName(field.getName(), clazz);
            Object obj = method.invoke(arg);
            String str = (String) obj;
            if (StringUtil.isNotEmpty(str)) {
                if (String.class.isAssignableFrom(obj.getClass())) {
                    Pattern pattern = field.getAnnotation(Pattern.class);
                    String regexp = pattern.regexp();
                    java.util.regex.Pattern p = java.util.regex.Pattern.compile(regexp);
                    Matcher m = p.matcher(str);
                    boolean isMatch = m.matches();
                    if (!isMatch) {
                        throw new BaseException(ErrorCodeEnum._10004.getErrorCode(), pattern.message());
                    }
                }
            }

        }
    }
}
