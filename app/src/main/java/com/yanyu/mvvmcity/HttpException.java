package com.yanyu.mvvmcity;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.HttpUrl;

/**
 * 网络请求错误
 */
public class HttpException extends IOException {
    private String message;
    private int code;
    private Throwable cause;

    public HttpException(int code, String message) {
        super("CODE:" + code + "  message:" + message);
        this.code = code;
        this.message = message;
    }

    public HttpException( String message,Throwable cause) {
        super( "message:" + message);
        this.message = message;
        this.cause = cause;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

}