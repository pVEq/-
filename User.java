package com.wms.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.beans.Transient;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wms
 * @since 2022-07-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String uid;

    @ApiModelProperty(value = "账号")
    @ExcelProperty("账号")
    private String no;

    // 身份证
    @ExcelProperty("身份证")
    private String idCard;

    @ApiModelProperty(value = "名字")
    @ExcelProperty("名字")
    private String name;

    @ApiModelProperty(value = "密码")
    @ExcelProperty("密码")
    private String password;

    private String avatar;

    @ExcelProperty("年龄")
    private Integer age;

    @ApiModelProperty(value = "性别")
    @ExcelProperty("性别")
    private Integer sex;

    @ApiModelProperty(value = "电话")
    @ExcelProperty("电话")
    private String phone;

    @ApiModelProperty(value = "角色 0超级管理员，1管理员，2普通账号")
    @ExcelProperty("角色Id")
    private Integer roleId;

    @ApiModelProperty(value = "是否有效，Y有效，其他无效")
    @TableField("isValid")
    private String isvalid;

    private int score;

    @ExcelProperty("班级Id")
    private Integer storageId;

    @TableField(exist = false)
    private String storageName;

    @TableField(exist = false)
    private Integer sort;

    @TableField(exist = false)
    private String performance;

    @TableField(exist = false)
    private String ranking;
}
