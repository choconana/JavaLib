package com.example.springtransactiondemo.exception;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName ParamInvalidException.java
 * @Description TODO
 * @createTime 2022年05月03日 19:39:00
 */
public class ParamInvalidException extends RuntimeException{

    public ParamInvalidException(String message) {
        super(message);
    }
}