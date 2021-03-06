package com.animals.animalsdemo.domain.response.merchants;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PetsDetailRespDTO {
    private Integer id;

    private String code;

    private String name;

    private String animalName;

    private String detail;

    private BigDecimal price;

    private Double age;

    private Integer sex;

    private String imageUrlId;

    private String videoUrlId;

    private String createBy;

    private String createName;

    private Date createDate;

    private String updateBy;

    private String updateName;

    private Date updateDate;

    private String imageMainUrlId;

    private Integer version;
    private Integer isDel;
}
