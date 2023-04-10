package com.example.javautil.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * dom4j解析xml
 */
public class Dom4jUtil {

    /**
     * get node element under root
     *
     * @param text
     * @param node
     * @return
     * @throws DocumentException
     */
    public static Element getBodyElement(String text, String node) throws DocumentException {
        Document doc = DocumentHelper.parseText(text);
        Element element = doc.getRootElement();
        Iterator iterator = element.elementIterator(node);
        return (Element) iterator.next();
    }

    /**
     * 获取跟目录的名字
     *
     * @param xml
     * @return
     * @throws DocumentException
     */
    public static String getRootElementName(String xml) throws DocumentException {
        Document doc = DocumentHelper.parseText(xml);
        Element element = doc.getRootElement();
        return element.getQName().getName();
    }

    public static String regex(String xml, String label) {
        String context = "";
        String rgex = "<" + label + ">(.*?)</" + label + ">";
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式  
        Matcher m = pattern.matcher(xml);
        xml = xml.replaceAll(rgex, "");
        return xml;
    }

}
