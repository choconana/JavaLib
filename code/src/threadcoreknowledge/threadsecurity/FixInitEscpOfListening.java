package threadcoreknowledge.threadsecurity;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/15 10:47
 */
public class FixInitEscpOfListening {

    int count;

    private EventListener eventListener;

    // 私有化构造函数
    private FixInitEscpOfListening(MySource source) {
        eventListener = new EventListener() {
            @Override
            public void onEvent(Event e) {
                System.out.println("\n我得到的数字是" + count);
            }
        };
        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    // 在工厂方法中完成初始化
    public static FixInitEscpOfListening getInstance(MySource source) {
        FixInitEscpOfListening saveListener = new FixInitEscpOfListening(source);
        // 完成所有的初始化工作才去注册监听器
        source.registerListener(saveListener.eventListener);
        return saveListener;
    }

    public static void main(String[] args) throws InterruptedException {
        FixInitEscpOfListening.MySource source = new FixInitEscpOfListening.MySource();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 短暂休眠等外部类先初始化
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                source.eventCome(new Event() {
                });
            }
        }).start();
//        Thread.sleep(11);
        FixInitEscpOfListening fixInitEscpOfListening = new FixInitEscpOfListening(source);
    }

    static class MySource {
        private EventListener listener;

        void registerListener(EventListener eventListener) {
            this.listener = eventListener;
        }

        void eventCome(Event e) {
            if (listener != null) {
                listener.onEvent(e);
            } else {
                System.out.println("还未初始化完毕");
            }
        }
    }

    interface EventListener {
        void onEvent(Event e);
    }

    interface Event {
    }
}
