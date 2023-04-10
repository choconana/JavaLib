package com.example.wxpaydemo.entity;

import lombok.Data;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName Transfer.java
 * @Description 提现
 * @createTime 2022年04月09日 16:58:00
 */
@Data
public class Transfer {

    private String mch_appid;

    private String mchid;

    private String nonce_str;

    private String sign;

    private String partner_trade_no;

    private String openid;

    private String check_name;

    private int amount;

    private String desc;

}
