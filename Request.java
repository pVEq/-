package com.wms.entity;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class Request {

    private Integer id;

    private Integer status;

    private String message;
}
