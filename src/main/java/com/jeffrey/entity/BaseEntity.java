package com.jeffrey.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jeffrey.utils.AnalyzeUtil;
import lombok.Data;

import java.util.Date;

/**
 * Description:实体基础信息
 *
 * @author YGC
 * @date 2020/08/19 19:01
 */
@Data
public class BaseEntity extends AnalyzeUtil.Analyzable {

    /**
     * 主键id
     */
    @TableId(value = "f_id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建时间
     */
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "f_update_time", update = "now()", updateStrategy = FieldStrategy.IGNORED, fill = FieldFill.INSERT)
    private Date updateTime;

    /**
     * 是否删除：0-否 1-是
     */
    @TableField(value = "f_is_delete")
    private Integer isDelete;
}
