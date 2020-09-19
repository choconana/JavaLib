package threadcoreknowledge.threadsecurity;

/**
 * @Description: 观察者模式
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/14 23:50
 */
public class InitEscapeBeczofListening {

    int count;

    public InitEscapeBeczofListening(MySource source) {
        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Event e) {
                System.out.println("\n我得到的数字是" + count);
            }
        });
        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    public static void main(String[] args) throws InterruptedException {
        MySource source = new MySource();
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
        InitEscapeBeczofListening initEscapeBeczofListening = new InitEscapeBeczofListening(source);
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
