package com.example.wxpaydemo.util;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName Configure.java
 * @Description 微信支付配置类
 * @createTime 2022年04月09日 12:56:00
 */
public class Configure {

    //小程序ID
    private static String appID = "";

    // 小程序的secret
    private static String secret = "";

    //商户号
    private static String mch_id = "";

    // 商户支付秘钥
    private static String key = "";

    // 回调通知地址
    private static String notify_url = "http://localhost:8080/api/v1/weixin/callback";

    //交易类型
    private static  String trade_type = "JSAPI";

    //统一下单API接口链接
    private static String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    //查询订单API接口链接
    private static String query_url = "https://api.mch.weixin.qq.com/pay/orderquery";

    public static String getKey() {
        return key;
    }

    public static void setKey(String key) {
        Configure.key = key;
    }

    public static String getAppID() {
        return appID;
    }

    public static void setAppID(String appID) {
        Configure.appID = appID;
    }

    public static String getMch_id() {
        return mch_id;
    }

    public static void setMch_id(String mch_id) {
        Configure.mch_id = mch_id;
    }

    public static String getSecret() {
        return secret;
    }

    public static void setSecret(String secret) {
        Configure.secret = secret;
    }

    public static String getNotify_url() {
        return notify_url;
    }

    public static void setNotify_url(String notify_url) {
        Configure.notify_url = notify_url;
    }

    public static String getTrade_type() {
        return trade_type;
    }

    public static void setTrade_type(String trade_type) {
        Configure.trade_type = trade_type;
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        Configure.url = url;
    }

    public static String getQuery_url() {
        return query_url;
    }

    public static void setQuery_url(String query_url) {
        Configure.query_url = query_url;
    }
}
