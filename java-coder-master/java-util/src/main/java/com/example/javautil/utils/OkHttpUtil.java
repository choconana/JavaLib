package com.example.javautil.utils;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * okhttp utils
 *
 * @author jinlv
 */
@Slf4j
public class OkHttpUtil {

    private final static int READ_TIMEOUT = 15;

    private final static int CONNECT_TIMEOUT = 60;

    private final static int WRITE_TIMEOUT = 60;

    private static OkHttpClient client = new OkHttpClient.Builder()
            //读取超时
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            //连接超时
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            //写入超时
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            //自定义连接池最大空闲连接数和等待时间大小，否则默认最大5个空闲连接
            .connectionPool(new ConnectionPool(32, 5, TimeUnit.MINUTES))
            .build();

    /**
     * get for string
     *
     * @param url
     * @param param
     * @return
     */
    public static Result doGet(String url, String param) {

        StringBuilder sb = new StringBuilder(url);
        if (!StringUtils.isEmpty(param)) {
            if (url.indexOf("?") == -1) {
                sb.append("?");
            } else {
                sb.append("&");
            }
            sb.append(param);
        }
        Request request = new Request.Builder().url(sb.toString()).get().build();
        return getResult(request);
    }

    /**
     * get for list
     *
     * @param url
     * @param params
     * @return
     */
    public static Result doGet(String url, List<String> params) {

        StringBuilder sb = new StringBuilder(url);
        if (!CollectionUtils.isEmpty(params)) {
            if (url.indexOf("?") == -1) {
                sb.append("?");
            } else {
                sb.append("&");
            }
            params.forEach(param -> sb.append(param).append("&"));
        }
        Request request = new Request.Builder().url(sb.toString()).get().build();
        return getResult(request);
    }

    /**
     * get result
     *
     * @param request
     * @return
     */
    private static Result getResult(Request request) {

        try {
            Call call = client.newCall(request);
            Response response = call.execute();
            //response返回null，默认服务器异常500
            if (null == response) {
                return Result.builder().code(500).message("服务未返回").build();
            }
            //response un success
            if (!response.isSuccessful()) {
                log.warn("response failure;code:{},message:{}", response.code(), response.message());
                return Result.builder().code(response.code()).message(response.message()).build();
            }
            return Result.builder()
                    .code(response.code())
                    .message(response.message())
                    .data(null == response.body() ? "" : response.body().string()).build();
        } catch (IOException e) {
            log.error("http call error:", e);
            return Result.builder().code(500).message("服务调用异常").build();
        }
    }

    /**
     * post json
     *
     * @param url
     * @param json
     */
    public static Result doPost(String url, String json) {
        //MediaType  设置Content-Type 标头中包含的媒体类型值
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8")
                , json);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return getResult(request);
    }

    /**
     * form commit
     *
     * @param url
     * @param formData
     * @return
     */
    public static Result doPost(String url, Map<String, String> formData) {

        FormBody.Builder builder = new FormBody.Builder();
        if (!CollectionUtils.isEmpty(formData)) {
            for (Map.Entry<String, String> header : formData.entrySet()) {
                builder.add(header.getKey(), header.getValue());
            }
        }
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return getResult(request);
    }


    /**
     * form commit
     *
     * @param url
     * @return
     */
    public static Result formPost(String url, Map<String, String> params) {

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        if (!CollectionUtils.isEmpty(params)) {
            for (Map.Entry<String, String> param : params.entrySet()) {
                builder.addFormDataPart(param.getKey(), param.getValue());
            }

        }
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return getResult(request);
    }

    /**
     * @param url     请求地址
     * @param headers
     * @param json
     * @return
     */
    public static Result doPost(String url, Map<String, String> headers, String json) {
        //先设置headers：
        Request.Builder builder = addHeaders(headers);
        //设置json参数
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8")
                , json);
        Request request = builder
                .url(url)
                .post(requestBody)
                .build();
        return getResult(request);

    }


    @Data
    @Builder
    public static class Result {
        private int code;
        private String message;
        private String data;
    }


    /**
     * 添加headers
     *
     * @param headers
     * @return
     */
    private static Request.Builder addHeaders(Map<String, String> headers) {

        Request.Builder builder = new Request.Builder();
        if (CollectionUtils.isEmpty(headers)) {
            return builder;
        }
        for (Map.Entry<String, String> header : headers.entrySet()) {
            builder.addHeader(header.getKey(), header.getValue());
        }
        return builder;
    }
}
