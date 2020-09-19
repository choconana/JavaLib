package juc.concurrencytools.threadlocal;

/**
 * @Description:    演示ThreadLocal用法2：避免传递参数的麻烦
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/4/28 16:10
 */
public class ThreadLocalNormalUsage10 {
    public static void main(String[] args) {
        new Service1().process();
    }
}

class Service1 {
    public void process() {
        User user = new User("超哥");
        UserContextHolder.holder.set(user);
        ThreadSafeFormatter.dateFormatThreadLocal.get();
        new Service2().process();
    }
}

class Service2 {
    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println("Service2: " + user.name);
        // 清空之前保存的user
//        UserContextHolder.holder.remove();
        user = new User("王姐");
        UserContextHolder.holder.set(user);
        new Service3().process();
    }
}

class Service3 {
    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println("Service3: " + user.name);
        // 避免内存泄露
        UserContextHolder.holder.remove();
    }
}

class UserContextHolder {
    public static ThreadLocal<User> holder = new ThreadLocal<>();
}

class User {
    String name;

    public User(String name) {
        this.name = name;
    }
}