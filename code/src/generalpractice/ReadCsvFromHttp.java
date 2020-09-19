package generalpractice;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/8/19 10:23
 */

public class ReadCsvFromHttp {
    public static void main(String[] args) {
        test();
    }

    // HttpURLConnection方式
    public static void test() {
        String SUBMIT_METHOD_GET = "GET";  // 一定要是大写，否则请求无效

        String urlStr = "http://120.92.86.192:5060/extract/getEnzymeCSV";  // 请求http地址
//        String param = "2020";  // 请求参数

        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;  // 返回结果字符串
        try {
            // 创建远程url连接对象
            URL url = new URL(urlStr);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：GET
            connection.setRequestMethod(SUBMIT_METHOD_GET);
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，请求成功后获取输入流
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                }
                result = sbf.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            connection.disconnect();  // 关闭远程连接
        }
        System.out.println("Successfully：" + result);
    }
}