package com.wms.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.wms.utils.SxjgUrlImageConverter;
import lombok.Data;

import java.net.URL;

@Data
public class ExportData {

    @ExcelProperty("学号")
    private String no;

    @ExcelProperty("班级")
    private String storageName;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("年龄")
    private Integer age;

    @ExcelProperty("电话")
    private String phone;

    @ExcelProperty("性别")
    private String sex;

    @ExcelProperty("角色")
    private String role;

    @ExcelProperty("分数")
    private int score;

    @ExcelProperty("级别")
    private String type;

    @ExcelProperty("类型")
    private String level;

    @ExcelProperty("名称")
    private String reason;

    @ExcelProperty(value = {"佐证"},converter = SxjgUrlImageConverter.class)
    private URL img;
}
