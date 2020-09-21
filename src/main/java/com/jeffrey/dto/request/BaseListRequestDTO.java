package com.jeffrey.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description: 列表查询公工入参
 *
 * @author TGD
 * @date 2020/08/24 上午 11:18
 */
@Data
public class BaseListRequestDTO extends BaseRequestDTO {
    /**
     * 页码
     */
    @ApiModelProperty(value = "页码")
    private Integer pageNum;

    /**
     * 分页大小
     */
    @ApiModelProperty(value = "分页大小")
    private Integer pageSize;
}
