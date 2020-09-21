package com.jeffrey.dto.response;

import com.jeffrey.context.enums.ErrorCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;

/**
 * Description: 响应结果
 *
 * @author 滕国栋
 * @date 2020/08/18 上午 11:20
 */
@ApiModel
@AllArgsConstructor
public class ResponseDTO<T> {
    /**
     * 响应状态
     */
    @ApiModelProperty(value = "响应状态")
    private ResponseStatus status;

    /**
     * 响应结果
     */
    @ApiModelProperty(value = "响应结果")
    private T result;

    public ResponseDTO() {
        this.status = new ResponseStatus();
    }

    public ResponseDTO(T result) {
        this.status = new ResponseStatus();
        this.result = result;
    }

    public ResponseDTO(String code, String description) {
        this(code, description, null);
    }

    public ResponseDTO(String code, String description, T result) {
        this(new ResponseStatus(code, description), result);
    }

    /**
     * 返回成功的对象
     *
     * @return ResponseDTO
     */
    public static ResponseDTO success() {
        return new ResponseDTO();
    }

    /**
     * 返回成功的对象
     *
     * @param data
     * @return ResponseDTO
     */
    public static <T> ResponseDTO success(T data) {
        return new ResponseDTO(data);
    }

    /**
     * 返回失败对象
     *
     * @param error
     * @return ResponseDTO
     */
    public static ResponseDTO failure(ErrorCodeEnum error) {
        return new ResponseDTO(error.getErrorCode(), error.getErrorMsg());
    }

    /**
     * 返回失败对象
     *
     * @param error
     * @param data
     * @return ResponseDTO
     */
    public static ResponseDTO failure(ErrorCodeEnum error, Object data) {
        return new ResponseDTO(error.getErrorCode(), error.getErrorMsg(), data);
    }

    /**
     * 返回失败对象
     *
     * @param errorCode
     * @return ResponseDTO
     */
    public static ResponseDTO failure(String errorCode) {
        return new ResponseDTO(ErrorCodeEnum.code(errorCode), ErrorCodeEnum.msg(errorCode));
    }

    /**
     * 返回失败对象
     *
     * @param errorCode
     * @param errorMsg
     * @return ResponseDTO
     */
    public static ResponseDTO failure(String errorCode, String errorMsg) {
        return new ResponseDTO(errorCode, errorMsg);
    }

    /**
     * 返回失败对象
     *
     * @param errorCode
     * @param errorMsg
     * @return ResponseDTO
     */
    public static ResponseDTO failureWithMsg(String errorCode, String errorMsg) {
        return new ResponseDTO(ErrorCodeEnum.code(errorCode), errorMsg + ErrorCodeEnum.msg(errorCode));
    }

    /**
     * 返回失败对象
     *
     * @param error
     * @param errorMsg
     * @return ResponseDTO
     */
    public static ResponseDTO failureWithMsg(ErrorCodeEnum error, String errorMsg) {
        return new ResponseDTO(error.getErrorCode(), errorMsg + error.getErrorMsg());
    }

    /**
     * 处理返回对象
     *
     * @param flag
     * @param error
     * @param error
     * @return ResponseDTO
     */
    public static ResponseDTO handleResponseDTO(boolean flag, ErrorCodeEnum error) {
        return flag ? ResponseDTO.success(new Object()) : ResponseDTO.failure(error);
    }

    /**
     * 处理返回对象
     *
     * @param flag
     * @param error
     * @return ResponseDTO
     */
    public static ResponseDTO handleResponseDTO(int flag, ErrorCodeEnum error) {
        return handleResponseDTO(flag, new Object(), error);
    }

    /**
     * 处理返回对象
     *
     * @param flag
     * @param data
     * @param error
     * @return ResponseDTO
     */
    public static ResponseDTO handleResponseDTO(boolean flag, Object data, ErrorCodeEnum error) {
        return flag ? ResponseDTO.success(data) : ResponseDTO.failure(error);
    }

    /**
     * 处理返回对象
     *
     * @param flag
     * @param error
     * @return ResponseDTO
     */
    public static ResponseDTO handleResponseDTO(int flag, Object data, ErrorCodeEnum error) {
        return handleResponseDTO(flag > 0, data, error);
    }

    public boolean isSuccess() {
        return this.status.getCode().equals(ErrorCodeEnum.SUCCESS.getErrorCode());
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
