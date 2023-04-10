package com.example.wxpaydemo.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName SignInfo.java
 * @Description 签名实体类
 * @createTime 2022年04月09日 13:04:00
 */
public class SignInfo {

    private String appId;//小程序ID

    private String timeStamp;//时间戳

    private String nonceStr;//随机串

    @XStreamAlias("package")
    private String repay_id;

    private String signType;//签名方式

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getRepay_id() {
        return repay_id;
    }

    public void setRepay_id(String repay_id) {
        this.repay_id = repay_id;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }
}
