package com.example.wxpaydemo.controller;


import com.example.wxpaydemo.entity.*;
import com.example.wxpaydemo.util.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.SSLContext;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName WXPayController.java
 * @Description 微信支付相关接口
 * @createTime 2022年04月09日 13:09:00
 */
@RestController
@RequestMapping(value = "/api/v1")
public class WXPayController {


    @RequestMapping("/wxpay/transfer")
    public Map transfer(HttpServletRequest request, Integer money) throws Exception {
        // 1.0 拼凑企业支付需要的参数
        String appid = Configure.getAppID(); // APP对应的微信的appid
        String mch_id = Configure.getMch_id(); // 商户号
        String nonce_str = RandomStringGenerator.getRandomStringByLength(32); // 生成随机数
        String partner_trade_no = new SnowflakeIdWorker(1, 2).nextId() + ""; // 生成商户订单号
        String openid = ""; // 收款用户openid
        String check_name = "NO_CHECK"; // 是否验证真实姓名呢
        // String re_user_name = "KOLO"; // 收款用户姓名(非必须)
        int amount = money * 100; // 企业付款金额，最少为100，单位为分
        String desc = "押金提现到账！"; // 企业付款操作说明信息。必填。
        // String spbill_create_ip = IpKit.getIpAddr(request); // 用户的ip地址

        // 2.0 封装参数
        Transfer transfer = new Transfer();
        transfer.setMch_appid(appid);
        transfer.setMchid(mch_id);
        transfer.setNonce_str(nonce_str);
        transfer.setPartner_trade_no(partner_trade_no);
        transfer.setOpenid(openid);
        transfer.setCheck_name(check_name);
        transfer.setAmount(amount);
        transfer.setDesc(desc);

        try {

            // 3.0 利用上面的参数，先去生成自己的签名
            String sign = Signature.getSign(transfer);

            // 4.0 将签名再放回map中，它也是一个参数
            transfer.setSign(sign);

            //解决XStream对出现双下划线的bug
            XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
            xStreamForRequestPostData.alias("xml", transfer.getClass());
            //将要提交给API的数据对象转换成XML格式数据Post给API
            String postDataXML = xStreamForRequestPostData.toXML(transfer);
            String s = doRefund(Configure.getUrl(), postDataXML);
            System.out.println(s);

            // 5.0 向微信发送请求转账请求
//            String result = HttpRequest.sendPost(Configure.getTransfers_url(), transfer);
//            System.out.println(result);
            XStream xStream = new XStream();
            xStream.alias("xml", OrderReturnInfo.class);

            OrderReturnInfo returnInfo = (OrderReturnInfo) xStream.fromXML(s);

            if ("SUCCESS".equals(returnInfo.getReturn_code()) && returnInfo.getReturn_code().equals(returnInfo.getResult_code())) {                // 付款成功
                return null;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 携带认证证书
    public String doRefund(String url, String data) throws Exception {
        //注意PKCS12证书 是从微信商户平台-》账户设置-》 API安全 中下载的
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        File file = new File(this.getClass().getClassLoader().getResource("apiclient_cert.p12").getFile());

        //指向你的证书的绝对路径，带着证书去访问
        FileInputStream instream = new FileInputStream(file);//P12文件目录
        try {
            //下载证书时的密码、默认密码是你的MCHID mch_id
            keyStore.load(instream, Configure.getMch_id().toCharArray());//这里写密码
        } finally {
            instream.close();
        }
        //下载证书时的密码、默认密码是你的MCHID mch_id
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, Configure.getMch_id().toCharArray())//这里也是写密码的
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[]{"TLSv1"},
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        try {
            HttpPost httpost = new HttpPost(url); // 设置响应头信息
            httpost.addHeader("Connection", "keep-alive");
            httpost.addHeader("Accept", "*/*");
            httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpost.addHeader("Host", "api.mch.weixin.qq.com");
            httpost.addHeader("X-Requested-With", "XMLHttpRequest");
            httpost.addHeader("Cache-Control", "max-age=0");
            httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
            httpost.setEntity(new StringEntity(data, "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(httpost);
            try {
                HttpEntity entity = response.getEntity();
                String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
                EntityUtils.consume(entity);
                return jsonStr;
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }


    /**
     * 下单
     */
    @RequestMapping(
            value = "/weixin/payment",
            method = RequestMethod.POST
    )
    public Map payment() {
        Map<String, Object> map = new HashMap<>();
        String money = "10";
        String title = "商品名字";

        try {
            OrderInfo order = new OrderInfo();
            order.setAppid(Configure.getAppID());
            order.setMch_id(Configure.getMch_id());
            order.setNonce_str(RandomStringGenerator.getRandomStringByLength(32));
            order.setBody(title);
            order.setOut_trade_no(RandomStringGenerator.getRandomStringByLength(32));
            order.setTotal_fee(Integer.parseInt(money));     // 该金钱其实10 是 0.1元
            order.setSpbill_create_ip("127.0.0.1");
            order.setNotify_url(Configure.getNotify_url());
            order.setTrade_type(Configure.getTrade_type());
            //这里直接使用当前用户的openid
            order.setOpenid("ohfYG5O2mdXZLoszDAWwqtodOZRM");
            order.setSign_type("MD5");
            //生成签名
            String sign = Signature.getSign(order);
            order.setSign(sign);

            String result = HttpRequest.sendPost(Configure.getUrl(), order);
            System.out.println(result);
            XStream xStream = new XStream();
            xStream.alias("xml", OrderReturnInfo.class);

            OrderReturnInfo returnInfo = (OrderReturnInfo) xStream.fromXML(result);
            // 二次签名
            if ("SUCCESS".equals(returnInfo.getReturn_code()) && returnInfo.getReturn_code().equals(returnInfo.getResult_code())) {
                SignInfo signInfo = new SignInfo();
                signInfo.setAppId(Configure.getAppID());
                long time = System.currentTimeMillis() / 1000;
                signInfo.setTimeStamp(String.valueOf(time));
                signInfo.setNonceStr(RandomStringGenerator.getRandomStringByLength(32));
                signInfo.setRepay_id("prepay_id=" + returnInfo.getPrepay_id());
                signInfo.setSignType("MD5");
                //生成签名
                String sign1 = Signature.getSign(signInfo);
                Map<String, String> payInfo = new HashMap<>();
                payInfo.put("timeStamp", signInfo.getTimeStamp());
                payInfo.put("nonceStr", signInfo.getNonceStr());
                payInfo.put("package", signInfo.getRepay_id());
                payInfo.put("signType", signInfo.getSignType());
                payInfo.put("paySign", sign1);
                map.put("status", 200);
                map.put("msg", "统一下单成功!");
                map.put("data", payInfo);

                // 此处可以写唤起支付前的业务逻辑

                // 业务逻辑结束 回传给小程序端唤起支付
                return map;
            }
            map.put("status", 500);
            map.put("msg", "统一下单失败!");
            map.put("data", null);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 微信小程序支付成功回调函数
     */
    @RequestMapping(value = "/weixin/callback")
    public void wxNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        //sb为微信返回的xml
        String notityXml = sb.toString();
        String resXml = "";
        System.out.println("接收到的报文：" + notityXml);

        Map map = PayUtil.doXMLParse(notityXml);

        String returnCode = (String) map.get("return_code");
        if ("SUCCESS".equals(returnCode)) {
            //验证签名是否正确
            Map<String, String> validParams = PayUtil.paraFilter(map);  //回调验签时需要去除sign和空值参数
            String validStr = PayUtil.createLinkString(validParams);//把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
            String sign = PayUtil.sign(validStr, Configure.getKey(), "utf-8").toUpperCase();//拼装生成服务器端验证的签名
            // 因为微信回调会有八次之多,所以当第一次回调成功了,那么我们就不再执行逻辑了

            //根据微信官网的介绍，此处不仅对回调的参数进行验签，还需要对返回的金额与系统订单的金额进行比对等
            if (sign.equals(map.get("sign"))) {
                /**此处添加自己的业务逻辑代码start**/
                // bla bla bla....
                /**此处添加自己的业务逻辑代码end**/
                //通知微信服务器已经支付成功
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            } else {
                System.out.println("微信支付回调失败!签名不一致");
            }
        } else {
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
        System.out.println(resXml);
        System.out.println("微信支付回调数据结束");

        BufferedOutputStream out = new BufferedOutputStream(
                response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
    }

    /**
     * 查询订单
     */
    @RequestMapping(
            value = "/weixin/orderQuery",
            method = RequestMethod.POST
    )
    public Map orderQuery() {
        Map<String, Object> map = new HashMap<>();

        try {
            OrderInfo order = new OrderInfo();
            order.setAppid(Configure.getAppID());
            order.setMch_id(Configure.getMch_id());
            order.setNonce_str(RandomStringGenerator.getRandomStringByLength(32));
            order.setOut_trade_no("wuyye9w8akwbq1nc60o4affwwqnxh9dn");
            //order.setSign_type("MD5");
            //生成签名
            String sign = Signature.getSign(order);
            order.setSign(sign);

            String result = HttpRequest.sendPost(Configure.getQuery_url(), order);
            System.out.println(result);
            XStream xStream = new XStream();
            xStream.alias("xml", QueryReturnInfo.class);

            QueryReturnInfo returnInfo = (QueryReturnInfo) xStream.fromXML(result);
            map.put("status", 500);
            map.put("msg", "统一下单失败!");
            map.put("data", returnInfo);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
