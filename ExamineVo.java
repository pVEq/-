package com.wms.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ExamineVo {

    private Integer id;

    private String no;

    private String storageName;

    private String name;

    private Integer age;

    private String sex;

    private String phone;

    private Integer score;


    private String type;

    private String level;

    private String reason;

    private String img;

    private Integer pageSize;

    private Integer pageNum;

    private Integer storageId;


}
