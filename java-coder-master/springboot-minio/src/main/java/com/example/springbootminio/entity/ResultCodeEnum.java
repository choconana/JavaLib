package com.example.springbootminio.entity;

import lombok.Getter;

/**
 * @author 陈乐
 * @version 1.0.0
 * @ClassName ResultCodeEnum.java
 * @Description 响应码枚举类
 * @createTime 2022年02月08日 10:42:00
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(true, 200, "成功"),

    UNKNOWN_ERROR(false, 500, "未知错误"),

    PARAM_ERROR(false, 501, "参数错误"),
    ;

    // 响应是否成功
    private Boolean success;

    // 响应状态码
    private Integer code;

    // 响应信息
    private String message;

    ResultCodeEnum(boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

}
