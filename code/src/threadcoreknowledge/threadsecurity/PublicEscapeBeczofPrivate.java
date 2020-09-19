package threadcoreknowledge.threadsecurity;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 第三种线程安全问题：发布逸出
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/14 23:33
 */
public class PublicEscapeBeczofPrivate {

    private Map<String, String> states;

    public PublicEscapeBeczofPrivate() {
        states = new HashMap<>();
        states.put("1", "周一");
        states.put("2", "周二");
        states.put("3", "周三");
        states.put("4", "周四");
    }

    public Map<String, String> getStates() {
        return states;
    }

    // 用副本代替
    public Map<String, String> getStatesImproved() {
        return new HashMap<>(states);
    }

    public static void main(String[] args) {
        PublicEscapeBeczofPrivate publicEscape = new PublicEscapeBeczofPrivate();
//        Map<String, String> states = publicEscape.getStates();
        // 只new了一个，remove操作虽然不会影响本体，但会影响结果
        Map<String, String> states = publicEscape.getStatesImproved();
        // 每次都new了一个副本
        System.out.println(publicEscape.getStatesImproved().get("1"));
        publicEscape.getStatesImproved().remove("1");
        System.out.println(publicEscape.getStatesImproved().get("1"));
    }
}
