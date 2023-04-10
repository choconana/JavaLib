package com.example.javautil.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * xml helper
 */
public class XmlUtil {

    /**
     * JAXBContext 缓存
     */
    private static Map<String, JAXBContext> contexCache = new ConcurrentHashMap<>();

    public static <T> T fromXml(String xmlString, Class<T> clazz) {

        if (StringUtils.isEmpty(xmlString)) {
            return null;
        }
        try {
            String key = Md5Util.MD5Encode(clazz.getName(), "UTF-8");
            JAXBContext context;
            //md5 加密失败，不缓存
            if (StringUtils.isEmpty(key)) {
                context = JAXBContext.newInstance(clazz);
            } else {
                context = contexCache.get(key);
                if (null == context) {
                    context = JAXBContext.newInstance(clazz);
                    contexCache.put(key, context);
                }
            }
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader reader = new StringReader(xmlString);
            return (T) unmarshaller.unmarshal(reader);
        } catch (Exception e) {
            LoggerClient.error(String.format("parse xml error;xml:{%s},exception:{%s}", xmlString, e.toString()));
        }
        return null;
    }

    /**
     * 含有子类
     *
     * @param xmlString
     * @param clazz     父类
     * @param subClass  子类
     * @param <T>
     * @return
     */
    public static <T> T fromXml(String xmlString, Class<T> clazz, Class subClass) {

        if (StringUtils.isEmpty(xmlString)) {
            return null;
        }
        try {
            String key = Md5Util.MD5Encode(String.format("%s%s", clazz.getName(), subClass.getName()), "UTF-8");
            JAXBContext context;
            //md5 加密失败，不缓存
            if (StringUtils.isEmpty(key)) {
                context = JAXBContext.newInstance(clazz, subClass);
            } else {
                context = contexCache.get(key);
                if (null == context) {
                    context = JAXBContext.newInstance(clazz, subClass);
                    contexCache.put(key, context);
                }
            }
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader reader = new StringReader(xmlString);
            return (T) unmarshaller.unmarshal(reader);
        } catch (Exception e) {
            LoggerClient.error(String.format("parse xml error;xml:{%s},exception:{%s}", xmlString, e.toString()));
        }
        return null;
    }

    /**
     * @param object       对象
     * @param fragmentFlag 是否省略xml头信息
     * @return 返回xmlStr
     */
    public static String objectToXml(Object object, Boolean fragmentFlag) {

        try {
            StringWriter writer = new StringWriter();
            if (!fragmentFlag) {
                writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            }
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller marshal = context.createMarshaller();
            marshal.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");// 编码格式,默认为utf-8
            marshal.setProperty(Marshaller.JAXB_FRAGMENT, true);// 是否省略xml头信息
            marshal.marshal(object, writer);
            return new String(writer.getBuffer());
        } catch (Exception e) {
            LoggerClient.error(String.format("convert to xml error;object:{%s},exception:{%s}",
                    JSON.toJSONString(object), e.toString()));
            return null;
        }

    }

    /**
     * @param element
     * @param json
     */
    public static void dom4j2Json(Element element, JSONObject json) {
        //如果是属性
        for (Object o : element.attributes()) {
            Attribute attr = (Attribute) o;
            if (!isEmpty(attr.getValue())) {
                json.put("@" + attr.getName(), attr.getValue());
            }
        }
        List<Element> chdEl = element.elements();
        if (chdEl.isEmpty() && !isEmpty(element.getText())) {//如果没有子元素,只有一个值
            json.put(element.getName(), element.getText());
        }

        for (Element e : chdEl) {//有子元素
            if (!e.elements().isEmpty()) {//子元素也有子元素
                JSONObject chdjson = new JSONObject();
                dom4j2Json(e, chdjson);
                Object o = json.get(e.getName());
                if (o != null) {
                    JSONArray jsona = null;
                    if (o instanceof JSONObject) {//如果此元素已存在,则转为jsonArray
                        JSONObject jsono = (JSONObject) o;
                        json.remove(e.getName());
                        jsona = new JSONArray();
                        jsona.add(jsono);
                        jsona.add(chdjson);
                    }
                    if (o instanceof JSONArray) {
                        jsona = (JSONArray) o;
                        jsona.add(chdjson);
                    }
                    json.put(e.getName(), jsona);
                } else {
                    if (!chdjson.isEmpty()) {
                        json.put(e.getName(), chdjson);
                    }
                }


            } else {//子元素没有子元素
                for (Object o : element.attributes()) {
                    Attribute attr = (Attribute) o;
                    if (!isEmpty(attr.getValue())) {
                        json.put("@" + attr.getName(), attr.getValue());
                    }
                }
                if (!e.getText().isEmpty()) {
                    json.put(e.getName(), e.getText());
                }
            }
        }
    }

    public static boolean isEmpty(String str) {

        if (str == null || str.trim().isEmpty() || "null".equals(str)) {
            return true;
        }
        return false;
    }


    /**
     * 读取指定XML节点值
     *
     * @param originXml
     * @param elementPath
     * @return
     */
    public static String dom4jGetElement(String originXml, String elementPath) {
        SAXReader reader = null;
        Element root = null;
        Document document = null;
        String returnXml = "";

        try {
            reader = new SAXReader();
            document = reader.read(new ByteArrayInputStream(originXml.getBytes("UTF-8")));
            root = document.getRootElement();
            Node node = root.selectSingleNode(elementPath);
            returnXml = node.asXML();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return returnXml;


    }


    public static JSONObject xmltoJson(String xml) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Document document = DocumentHelper.parseText(xml);
        //获取根节点元素对象
        Element root = document.getRootElement();
        iterateNodes(root, jsonObject);
        return jsonObject;
    }

    /**
     * 遍历元素
     *
     * @param node 元素
     * @param json 将元素遍历完成之后放的JSON对象
     */
    @SuppressWarnings("unchecked")
    public static void iterateNodes(Element node, JSONObject json) {
        //获取当前元素的名称
        String nodeName = node.getName();
        //判断已遍历的JSON中是否已经有了该元素的名称
        if (json.containsKey(nodeName)) {
            //该元素在同级下有多个
            Object Object = json.get(nodeName);
            JSONArray array = null;
            if (Object instanceof JSONArray) {
                array = (JSONArray) Object;
            } else {
                array = new JSONArray();
                array.add(Object);
            }
            //获取该元素下所有子元素
            List<Element> listElement = node.elements();
            if (listElement.isEmpty()) {
                //该元素无子元素，获取元素的值
                String nodeValue = node.getTextTrim();
                array.add(nodeValue);
                json.put(nodeName, array);
                return;
            }
            //有子元素
            JSONObject newJson = new JSONObject();
            //遍历所有子元素
            for (Element e : listElement) {
                //递归
                iterateNodes(e, newJson);
            }
            array.add(newJson);
            json.put(nodeName, array);
            return;
        }
        //该元素同级下第一次遍历
        //获取该元素下所有子元素
        List<Element> listElement = node.elements();
        if (listElement.isEmpty()) {
            //该元素无子元素，获取元素的值
            String nodeValue = node.getTextTrim();
            json.put(nodeName, nodeValue);
            return;
        }
        //有子节点，新建一个JSONObject来存储该节点下子节点的值
        JSONObject object = new JSONObject();
        //遍历所有一级子节点
        for (Element e : listElement) {
            //递归
            iterateNodes(e, object);
        }
        json.put(nodeName, object);
        return;
    }


}
