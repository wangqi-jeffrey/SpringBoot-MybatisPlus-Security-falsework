package com.jeffrey.context.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *  * Description:
 *  * @author 金泽强
 *  * @date 2020/08/26 2:03 下午
 *  
 */

@Getter
@AllArgsConstructor
public enum CCBSyncStatusEnum {

    DEFAULT(0, "未同步"),
    SUCCESS(1, "同步成功"),
    FAILURE(2, "同步失败");

    private int value;
    private String desc;
}
