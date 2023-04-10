package com.example.javautil.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
public class IpUtil {

  private static final String UKNOWN = "unknown";
  private static final String X_FORWARDED_FOR = "x-forwarded-for";
  private static final String PROXY_CLIENT_IP = "Proxy-Client-IP";
  private static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
  private static final String LOCAL_IP = "127.0.0.1";
  private static final String LOCAL_IP_V6 = "0:0:0:0:0:0:0:1";

  /**
   * 获取客户端IP
   *
   * @param request
   * @return
   */
  public static String getClientIp(HttpServletRequest request) {

    String ipAddress = request.getHeader(X_FORWARDED_FOR);
    if (null == ipAddress || 0 == ipAddress.length() || UKNOWN.equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getHeader(PROXY_CLIENT_IP);
    }
    if (null == ipAddress || 0 == ipAddress.length() || UKNOWN.equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getHeader(WL_PROXY_CLIENT_IP);
    }
    if (null == ipAddress || 0 == ipAddress.length() || UKNOWN.equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getRemoteAddr();
      if (LOCAL_IP.equals(ipAddress) || LOCAL_IP_V6.equals(ipAddress)) {
        InetAddress inet = null;
        try {
          inet = InetAddress.getLocalHost();
          ipAddress = inet.getHostAddress();
        } catch (UnknownHostException e) {
          log.error(e.getMessage(), e);
        }
      }
    }
    //多个代理，取第一个IP为客户端真实IP，多个IP以‘,’分割
    String ipSeparate = ",";
    int ipLength = 15;
    if (null != ipAddress && ipLength < ipAddress.length()) {
      if (0 < ipAddress.indexOf(ipSeparate)) {
        ipAddress = ipAddress.substring(0, ipAddress.indexOf(ipSeparate));
      }
    }
    return ipAddress;
  }

}
