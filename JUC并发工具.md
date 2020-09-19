#  一、并发工具类纵览

## 1. 并发安全：

### 从底层原理分类

#### 互斥同步

资源在同一时间内，只能有一个线程独占

* synchronized、ReentrantLock等互斥同步锁
* 同步工具类：Collections.synchronizedList()、Vector等

#### 非互斥同步

多个线程可以同时占有资源

* atomic包、原子类

#### 结合互斥和非互斥同步

* 线程安全的并发容器：ConcurrentHashMap、CopyOnWriteArrayList、并发队列、ConcurrentSkipMap和ConcurrentSkipListSet

#### 无同步方案

* final关键字
* 线程封闭：ThreadLocal、栈封闭

### 从使用者角度分类

#### 避免共享变量

* 线程封闭：ThreadLocal、栈封闭

#### 共享变量，但是加以限制和处理

* 互斥同步
* final关键字

#### 使用工具类

* 线程安全的并发容器
* atomic包和原子类

## 2. 管理线程、提高效率

#### 线程池相关类

* Executor
* Executors
* ExecutorService
* 常见线程池

#### 能获取子线程的运行结果

* Callable

* Future

* FutureTask

  ......

## 3. 线程协作

* CountDownLatch

* CyclicBarrier

* Semaphore

* Condition

* Exchanger

* Phaser

  ......

---

# 二、线程池

**——治理线程的最大法宝**

## 1. 线程池的介绍

### 线程池的重要性

创建线程需要一定的开销

* 如果不使用线程池，每个任务都需要新开一个线程去处理，任务数量太多时，创建线程需要开销，销毁线程会给GC带来压力，而且线程的数量也有上限。
  * 问题一：反复创建线程开销大
  * 问题二：过多的线程会占用太多内存
* 解决以上两个问题的思路
  * 用少量线程——避免内存占用过多
  * 让这部分线程都保持工作，且可以反复执行任务——避免生命周期的损耗

#### 线程池的好处

1. 加快响应速度
2. 合理利用CPU和内存
3. 统一管理资源

#### 适用场景 

* 服务器接受到大量请求时
* 开发中，如果需要创建5个以上的线程

## 2. 创建和停止线程池

上山难下山容易

### 创建线程池

#### 1. 线程池构造函数的参数

![1587125511972](课件\JUC资料\图片\线程池构造函数的参数.png)

* **corePoolSize**：核心线程数，即使只有少量任务，也不会减少核心线程数
* **maxPoolSize**：规定了线程池的线程数量上限，来应对短时间内大量的任务
* **keepAliveTime**：如果线程池当前的线程数多于corePoolSize，那么如果**多余的线程**空闲时间超过keepAliveTime，它们就会被终止
* **threadFactory**：新的线程是由ThreadFactory创建的，默认使用Executor.defaultThreadFactory()，创建出来的线程都在同一个线程组，拥有同样的**NORM_PRIORITY优先级**并且都**不是守护线程**。如果自己指定ThreadFactory，那么就可以改变**线程名**、**线程组**、**优先级**、是否是**守护线程**等。
* **workQueue**：3种类型
  1. 直接交接：SynchronousQueue(1容量，需要增加maxPoolSize)；
  2. 无界队列：LinkedBlockingQueue(防止流量突增)，但是如果任务处理速度跟不上任务提交到线程池的速度，会造成内存浪费，甚至OOM异常；
  3. 有界队列：ArrayBlockingQueue

![1587126064435](课件\JUC资料\图片\线程池容量.png)

##### 添加线程规则

* 线程池初始化后，线程数为0。

1. 如果线程数**小于corePoolSize**，**即使其他工作线程处于空闲状态，也会创建一个新线程来运行新任务**；
2. 如果线程数**大于等于corePoolSize但少于maximumPoolSize**，则将新任务**放入队列**；
3. 如果**队列已满**，并且线程数**小于maxPoolSize**，则**创建新线程**来运行任务；
4. 如果**队列已满**，并且线程数**大于或等于maxPoolSize**，则**拒绝**该任务。(拒绝策略有哪些？)

![1587126516415](课件\JUC资料\图片\添加线程规则.png)

* 增加线程的判断顺序：corePoolSize -> workQueue -> maxPoolSize

##### 增减线程的特点

1. 设置corePoolSize和maxPoolSize相同，相当于创建了**固定大小**的线程池。
2. 线程池希望保持较少的线程数，并且只有在负载变得很大时才会增加。
3. 通过设置maximumPoolSize为很高的值(Integer.MAX_VALUE)，可以允许线程池容纳任意数量的并发任务。(**队列有界，线程无上限**)
4. 因为只有在队列填满时才创建多于corePoolSize的线程，所以如果使用无界队列(例如：LinkedBlockingQueue)，那么线程数就**不会超过corePoolSize**.(线程有限，队列无界)





#### 2. 手动还是自动创建线程池

* 手动创建更好，因为这样可以更加明确线程池的运行规则，避免资源耗尽的风险。

##### 自动创建线程池

* **newFixedThreadPool**：带有无界队列的缺点(OOM异常)

  ~~~java
  public static ExecutorService newFixedThreadPool(int nThreads) {
      return new ThreadPoolExecutor(nThreads, nThreads, 0L, 		        	TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
  }
  ~~~

* **newSingleThreadExecutor**：带有无界队列的缺点(OOM异常)

* **newCachedThreadPool**：线程数量无上限，也会出现处理任务过多的情况，导致OOM；

  * 可缓存线程池
  * 无界线程池，具有自动回收多余线程的功能

  ~~~java
  public static ExecutorService newCachedThreadPool() {
  	return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, 		       TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
      }
  ~~~

  ![1587130563676](课件\JUC资料\图片\newCachedThreadPool模型.png)

* **newScheduledThreadPool**
  
  * 支持定时以及周期性任务执行

##### 正确创建线程池的方法

* **根据不同的业务场景手动创建线程池**：内存、线程名、任务被拒绝时如何记录日志等。



#### 3. 线程池里的线程数量设定多少合适？

* **CPU密集型**(加密、计算hash等)：最佳线程数为CPU核心数的1-2倍左右；
* **耗时IO型**(读写数据、文件、网络读写等)：最佳线程数一般会大于CPU核心数很多倍，以JVM线程监控显示繁忙情况为依据，保证线程空闲可以衔接上，参考Brain Goetz推荐的计算方法：**线程数=CPU核心数*(1+平均等待时间(读写操作)/平均工作时间)**



### 停止线程池

#### 1. shutdown

等到线程池内的任务执行完毕再停止，而且拒绝新任务的执行。

* isShutDown：判断线程池是否进入停止状态
* isTerminated：判断线程池是否已经停止

* awaitTermination：判断指定时间内线程池是否会停止
  * 时间内线程未执行完，返回false
  * 时间内线程执行完，返回true
  * 被打断，抛出Interrupted异常

#### 2. shutdownNow

* 正在执行的线程会收到Interrupt信号，抛出Interrupted异常
* 返回值是List<Runnable>
* 队列里等待的任务直接返回

## 3. 常见线程池的特点和用法

### ① FixedThreadPool

![1587131838492](课件\JUC资料\图片\FixedThreadPool模型.png)

### ② CachedThreadPool

![1587131922000](D:\java学习\并发和多线程\课件\JUC资料\图片\CachedThreadPool模型.png)

### ③ ScheduledThreadPool

* 支持定时以及周期性任务执行的线程池

### ④ SingleThreadExecutor

* **单线程**的线程池：它只会用唯一的工作线程来执行任务
* 原理**和FixedThreadPool一样**，但此时的线程数量被设置为1

### 4种线程池的构造函数的参数

![1587132599004](D:\java学习\并发和多线程\课件\JUC资料\图片\常用线程池的构造函数参数.png)

### 阻塞队列分析

#### 1. LinkedBlockingQueue

FixedThreadPool和SingleThreadExecutor的线程数量是固定的，为了不超过corePoolSize，就需要使用无界队列。

#### 2. SynchronousQueue

CachedThreadPool不需要使用队列做存储

#### 3. DelayedWorkQueue

ScheduledThreadPool需要对任务执行时间自定义

### ⑤ WorkStealingPool(JDK1.8加入)

* 适用于可以产生子任务的任务(递归场景)，比如树的遍历(树里包含树)、矩阵里包含矩阵

* 窃取，线程间会互相帮助执行(并行执行)

* 注意：

  * 为了提高执行效率，任务最好不加锁；
  * 不保证执行顺序

  





## 4. 解决任务太多问题

### 拒绝时机

1. 当Executor关闭时，提交新任务会被拒绝。
2. 以及当Executor对最大线程和工作队列容量使用有线边界并且以及饱和时

### 4种拒绝策略

#### 1. AbortPolicy

直接抛出异常RejectedExecutionException

#### 2. DiscardPolicy

默默地丢弃任务

#### 3. DiscardOldestPolicy

丢弃队列中等待时机最长的任务，腾出空间存放刚提交的新任务

#### 4. CallerRunsPolicy

让提交任务的线程去执行，也算是减少该线程提交任务的频率



## 5. 钩子方法

* 在每个任务执行前后加入日志、统计等

* 代码：`juc.threadpool.PauseableThreadPool`

## 6. 实现原理、源码分析

### 线程池组成部分

1. 线程池管理器
2. 工作线程
3. 任务队列：用的是线程安全的BlockingQueue
4. 任务接口(Task)

### Executor家族

* 线程池、ThreadPoolExecutor、ExecutorService、Executor、Executors等和线程池相关的类，它们之间是什么关系？
  * Executor中只有`execute()`一个方法
  * ExecutorService继承自Executor，并增加**多个操作线程池的方法**
  * Executors是线程池工具类，里面包含**多种创建线程池的方法**

![1587285706533](课件\JUC资料\图片\线程池相关类.png)

### 线程池实现任务复用的原理

* 相同线程执行不同任务

#### 源码——ThreadPoolExecutor.execute()

* 在`runWorker()`中利用无限循环来获取任务，只要任务存在循环就不会停止，线程没被终止就可以不断地获取任务

~~~java
public void execute(Runnable command) {
	...
    // 1.
    int c = ctl.get();// ctl记录了线程池当前状态，和现存的线程数
	if (workerCountOf(c) < corePoolSize) {
            if (addWorker(command, true))
                return;
            c = ctl.get();
    }
    ...
}

private boolean addWorker(Runnable firstTask, boolean core) {
    ...
    try {
        // 2.
        w = new Worker(firstTask);
        ...
    }
    ...
}

private final class Worker
        extends AbstractQueuedSynchronizer
        implements Runnable {
        ...
        // 3.
        public void run() {
            runWorker(this);
        }
        ...
}

final void runWorker(Worker w) {
		Thread wt = Thread.currentThread();
        Runnable task = w.firstTask;
        w.firstTask = null;
        w.unlock(); // allow interrupts
        ...
        try {
       	 	// 4. 无限循环实现线程复用
            while (task != null || (task = getTask()) != null) {
                w.lock();
                ...
                try {
                    ...
                        task.run();
                    ...
                }
        } 
        ...
}
~~~



### 线程池状态(5种)

1. RUNNING：接受新任务并处理排队任务。
2. SHUTDOWN：不接受新任务，但处理排队任务。
3. STOP：不接受新任务，也不处理排队任务，并中断正在进行的任务。
4. TIDYING：等所有任务都已终止，workerCount为零时，线程会转换到TIDYING状态，并将运行terminate()钩子方法。
5. TERMINATED：terminate()运行完成。



## 7. 使用线程池的注意点

* 避免任务堆积
* 避免线程数过度增加
* 排查线程泄露：线程执行完毕却不能被回收，通常是任务逻辑存在问题，任务一直结束不了，线程就无法终止。

# 三、ThreadLocal

## 两大使用场景

### 典型场景1

* 每个线程需要一个**独享的对象**(通常是工具类，如SimpleDateFormat和Random)
* 因为这些工具类是**线程不安全**的，多线程使用时容易出错，就需要用到ThreadLocal**为每个线程都制作一个独享的对象**，而且线程之间都是不同的实例，所以互不影响。
* 强调每个线程内有自己的实例副本，不共享
* **比喻**：教程只有一本，一起做笔记有线程安全问题。复印后就没有问题。
* 用**SimpleDateFormat**的**进化**之路来说明ThreadLocal的好处
  1. 只有少量线程使用该类，直接新建线程
  2. 有30个线程使用该类，用for循环创建线程
  3. 大量线程使用该类时，用ThreadPool，否则消耗内存太多
     * 但是每一个线程都会创建该类的实例，导致过多的实例，消耗不必要的资源
  4. 对3进行改进，用静态变量消除重复创建而带来的资源消耗问题
     * 但是由于该类是线程不安全的，会出现重复日期的问题
     * 因为所有线程都共用这一个类，从而导致线程安全问题
  5. 对4进行改进，在使用该类的一个方法上加上类锁
     * 线程需要排队，效率低
  6. 更好的解决方案是使用ThreadLocal，给每个线程分配自己的对象，保证了线程安全，高效利用内存

### 典型场景2

* 每个线程内需要**保存全局变量**(例如在拦截器中获取用户信息)，可以让不同方法内直接使用，**避免参数传递**的麻烦

![ThreadLocal场景2实例](课件\JUC资料\图片\ThreadLocal场景2实例.png)

* 在此基础上进行改进，使用UserMap集中user参数

![1588054578500](课件\JUC资料\图片\ThreadLocal场景2实例改进1.png)

* 当多线程同时工作是，需要**保证线程安全**，可以用synchronized，也可以用ConcurrentHashMap，但无论用什么，都会对**性能**有所影响

![1588055139501](课件\JUC资料\图片\ThreadLocal场景2实例问题1.png)

* 更好的办法是使用ThreadLocal，这样无需synchronized，可以在不影响性能的情况下，也**无需层层传递参数**，就可达到保存当前线程对应的用户信息的目的

![1588064648081](课件\JUC资料\图片\ThreadLocal场景2改进2.png)

#### 方法

* 用ThreadLocal保存一些业务内容(用户权限信息、从用户系统获取到的用户名、userID等)
* 这些信息在**同一个线程内相同**，但是**不同的线程**使用的业务内容是**不相同**的
* **在线程生命周期内**，都通过这个静态ThreadLocal实例的get()取得自己set过的那个对象，避免了将这个对象(例如user对象)作为参数传递的麻烦
* 强调的是同一个请求内(同一个线程内)不同方法间的共享
* 不需要重写initialValue()，但是必须手动调用set()
* 多个线程不会共享同一个对象，具有线程安全



### 总结

#### ThreadLocal的两个作用

1. 让某个需要用到的对象在**线程间隔离**（每个线程都有自己的独立的对象）
2. 在任何方法中都可以**轻松获取**到对象

* 回顾前面的两个场景，对应到这两个作用

* 根据**共享对象的生成时机**不同，选择initialValue或set来保存对象

##### 场景一：initialValue

* 在ThreadLocal**第一次get**的时候把对象给初始化出来，对象的初始化时机可以**由我们控制**。

##### 场景二：set

* 如果需要保存到ThreadLocal里的对象的生成时机不由我们随意控制，例如拦截器生成的用户信息，用ThreadLocal.set直接放到我们的ThreadLocal中去，以便后续使用。

#### 好处

* **线程安全**
* **不需要加锁**，提高执行**效率**
* 更高效地**利用内存**、**节省开销**：不用为每个任务都新建一个对象
* **免去传参**的繁琐：无论是场景一的工具类，还是场景二的用户名，都可以在任何地方直接通过ThreadLocal拿到，再也不需要每次都传同样的参数。这使得代码耦合度更低、更优雅。



## 原理、源码分析

### ThreadLocal结构

* 搞清楚Thread、ThreadLocal以及ThreadLocalMap三者之间的关系
* 每个Thread对象中都持有一个ThreadLocalMap成员变量

![1588068391729](课件\JUC资料\图片\ThreadLocal结构图.png)

* 一个Thread用一个ThreadLocalMap保存多个ThreadLocal，因为可能会用到很多不同的ThreadLocal对象。

### 主要方法介绍

#### initialValue()

* `T initialValue()`：初始化

1. 该方法会返回当前线程对应的“初始值”，这是一个**延迟加载**的方法，只有在**调用get**的时候，才会触发；

> ```java
>     public T get() {
>         Thread t = Thread.currentThread();
>         ThreadLocalMap map = getMap(t);
>         // 如果先set一个值，就会执行下面if语句，就不再执行initialValue()
>         if (map != null) {
>             ThreadLocalMap.Entry e = map.getEntry(this);
>             if (e != null) {
>                 @SuppressWarnings("unchecked")
>                 T result = (T)e.value;
>                 return result;
>             }
>         }
>         
>         /** 1. */
>         return setInitialValue();
>     }
> 
>     private T setInitialValue() {
>         /** 2. */
>         T value = initialValue();
>         
>         Thread t = Thread.currentThread();
>         ThreadLocalMap map = getMap(t);
>         if (map != null)
>             map.set(this, value);
>         else
>             createMap(t, value);
>         return value;
>     }
> 
> 	/** 3. */
>     protected T initialValue() {
>         return null;
>     }
> ```

2. 当线程**第一次使用get方法**访问变量时，将调用此方法，除非线程**先调用了set方法**，在这种情况下，不会为线程调用initialValue方法；

* 用initialValue()和set()方法赋值，正对应了ThreadLocal的两种典型用法

3. 通常，每个线程最多调用一次此方法，但如果已经调用了remove()后，再调用get()，则可以再次调用此方法；
4. 如果不重写本方法，这个方法会返回null。所以在场景一中最好重写initialValue()，以便在后续使用中可以初始化副本对象；

#### set()

* `void set(T t)`：为这个线程设置一个新值；

```java
public void set(T value) {
    Thread t = Thread.currentThread();
    ThreadLocalMap map = getMap(t);
    if (map != null)
        map.set(this, value);
    else
        createMap(t, value);
}
```

#### get()

* `T get()`：得到这个线程对应的value。如果第一次调用ThreadLocal的方法时get()（没有设置过值），则会调用initialize来得到这个值；
* get方法时先取出当前线程的**ThreadLocalMap**，然后调用**map.getEntry**方法，把本ThreadLocal的引用作为参数传入，取出map中属于本ThreadLocal的value；
* 注意：这个map以及map中的key和value都是**保存在Thread中**的，而不是保存在ThreadLocal中；

```java
public T get() {
    Thread t = Thread.currentThread();
    ThreadLocalMap map = getMap(t);
    if (map != null) {
        ThreadLocalMap.Entry e = map.getEntry(this);
        if (e != null) {
            @SuppressWarnings("unchecked")
            T result = (T)e.value;
            return result;
        }
    }
    return setInitialValue();
}
```

#### remove()

* `void remove()`：删除对应这个线程的设置的值

## ThreadLocalMap类

* ThreadLocalMap类，也就是Thread.threadLocals

~~~java
    == Thread.java ==
    ThreadLocal.ThreadLocalMap threadLocals = null;
~~~

* 它是Thread类里的**成员变量**，里面最重要的是一个键值对数组**Entry[] table**，可以认为是一个map，键值对：
  * 键：这个ThreadLocal
  * 值：实际需要的成员变量，比如user或者simpleDateFormat对象

### 冲突处理

* HashMap：**拉链法**和**红黑树**

![Java8 HashMap结构](D:\java学习\并发和多线程\课件\JUC资料\图片\Java8 HashMap结构.png)

* ThreadLocalMap采用的是**线性探测法**，也就是如果发生冲突，就继续找下一个空位置

### 两个场景殊途同归

* 通过源码分析可以看出，setInitialValue和直接set最后都是利用**map.set()**方法来设置值。也就是说，最后都会对应到ThreadLocalMap的一个Entry，只不过设置值的方式不同



## ThreadLocal注意点

### 内存泄露

#### 什么是内存泄漏?

* 某个对象不再有用，但是占用的内存却不能被回收；

#### ThreadLocal可能导致内存泄露的情况

* **弱引用的特点**：如果这个对象**只**被弱引用关联（没有任何强引用关联，比如普通的赋值），那么这个对象就可以被回收；所以弱引用不会阻止GC，因此这个弱引用的机制；

##### Key的泄露

* ThreadLocalMap中的Entry继承自**WeakReference**，是**弱引用**；

##### Value的泄露

* ThreadLocalMap的每个Entry都是一个**对key的弱引用**，同时，每个Entry都包含了一个**对value的强引用**；
* 正常情况下，当线程终止，保存在ThreadLocal里的value会被垃圾回收，因为没有任何强引用了；
* 但是，如果线程不终止（比如线程需要保持很久），那么**ThreadLocal外部强引用被置为null**，key会被gc的时候回收掉时，此时无法通过为null的key去访问对应的value，value就不能被回收，但此时还存在这个调用链：`Thread -> ThreadLocalMap -> Entry(key为null) -> value`；

![ThreadLocal内存泄漏](assets\ThreadLocal内存泄露.png)

* 因为value和Thread之间还存在这个强引用链路，所以**导致value无法回收**，就可能会出现OOM；
* JDK已经考虑到了这个问题，所以在set、remove、rehash方法中会**扫描key为null的Entry，并把对应的value设置为nul**l，这样value对象就可以被回收；
* 但是如果一个ThreadLocal不被使用，那么实际上set、remove、rehash方法也不会被调用，如果同时线程又不停止，那么调用链就一直存在，就会导致了value的内存泄露；

##### 如何避免内存泄露（阿里规约）

* 主动调用remove方法，就会删除对应的Entry对象，可以避免内存泄露，所以使用完ThreadLocal后，应该调用remove方法；
* 如果使用拦截器方法获取到信息，就同样应该用拦截器方法在线程请求退出之前，拦住并将之前保存的信息remove；

### 空指针异常

* 在进行get()之前，必须先set()，否则可能会报空指针异常？

  这是由于ThreadLocal的**泛型(Long)**与获取get()的**声明的类型(long)**不匹配导致的（比如：装箱拆箱；

### 共享对象

* 如果在每个线程中ThreadLocal.**set()进去**的东西**本来就是多线程共享**的同一个对象，比如static对象，那么多个线程的ThreadLocal.get()取得的还是这个共享对象本身，还是存在并发访问的问题；(**尽量不要set一些静态变量**)

### 其他

* 如果可以不使用ThreadLocal就能解决问题，那么不要强行使用
  * 例如在任务数很少的时候，在局部变量中可以新建对象就可以解决问题；
* 优先使用框架的支持，而不是自己创造
  * 例如在Spring中，如果可以使用RequestContextHolder，那么就不需要自己维护ThreadLocal，因为自己可能会忘记调用remove()方法等，从而造成内存泄露

## 实际应用场景——在Spring中的实例分析

* DateTimeContextHolder类，看到里面用了ThreadLocal
* 每次HTTP请求都对应一个线程，线程之间相互隔离，这就是ThreadLocal的典型应用场景



# 四、锁

1. Lock接口
2. 锁的分类
3. 乐观锁和悲观锁
4. 可重入锁和非可重入锁，以ReentrantLock为例（重点）
5. 公平锁和非公平锁
6. 共享锁和排它锁：以ReentrantReadWriteLock读写锁为例（重点）
7. 自旋锁和阻塞锁
8. 可中断锁：可响应中断的锁
9. 锁优化

## 1. Lock接口

### 1. 简介、地位、作用

* 锁是一种工具，用于控制对**共享资源**的访问；
* Lock和synchronized都是最常见的锁，都可以达到**线程安全**的目的，Lock并不是用来代替synchronized的，而是当使用synchronized不合适或者不足以满足要求的时候，来提供**高级功能**的；
* Lock接口最常见的实现类是**ReentrantLock**；
* 通常情况下，Lock只允许一个线程来访问这个共享资源。不给过有的时候，一些特殊的实现也可以允许并发访问，比如ReadWriteLock里面的**ReadLock**；

### 2. 为什么synchronized不够用？为什么需要Lock？

#### 为什么synchronized不够用？

1. **效率低**：锁的释放情况少、试图获得锁时**不能设定超时**、不能中断一个正在试图获得锁的线程;
2. **不够灵活**(读写锁更灵活)：加锁和释放的时机单一，每个锁仅有单一的条件(某个对象)，可能是不够的的；
3. 无法知道是否**成功获取到锁**；

### 3. 主要方法介绍

* 四个获取锁的方法：lock()、tryLock()、tryLock(long time, TimeUnit unit)、lockInterruptibly()

#### lock()

* 最普通的获取锁，如果锁已被其他线程获取，则进行等待；
* 不会像synchronized一样在**异常时自动释放锁**；
* 因此最佳实践是：**在finally中释放锁**，以保证发生异常时锁一定被释放；
* lock()方法不能被中断，这会带来很大的隐患：一旦**陷入死锁**，lock()就会陷入永久等待；

#### tryLock()

* tryLock()用来尝试获取锁，如果当前锁没有被其他线程占用，则获取成功，返回true，否则返回false，代表获取锁失败；
* 可以根据是否能获取到锁来**决定后续程序的行为**；
* 该方法会立即返回，即便在拿不到锁时不会一直在那等；

#### tryLock(long time, TimeUnit unit)

* 会在一定时间内去获取锁，超时就放弃；

#### lockInterruptibly()

* 相当于`tryLock(long time, TimeUnit unit)`把**超时时间设置为无限**。在等待锁的过程中，线程可以被中断；

### 4. 可见性保证

* 可见性
* happens-before
* Lock的加解锁和synchronized有同样的内存语义，也就是说，下一个线程**加锁后可以看到所有前一个线程解锁前发生的所有操作**

![1589374326476](课件\JUC资料\图片\Lock可见性.png)

## 2. 锁的分类

* 这些分类，是从各种不同角度出发的，它们并**不是互斥**的，也就是多个类型可以**并存**：有可能一个锁，同时属于两种类型，比如ReentrantLock既是**互斥锁**，又是**可重入锁**；

![1589375274656](课件\JUC资料\图片\锁的分类.png)

## 3. 乐观锁和悲观锁

**乐观锁就是非互斥同步锁，悲观锁就是互斥同步锁**

### 1. 介绍

#### ① 为什么会诞生非互斥同步锁

* 互斥同步锁的劣势：
  1. 阻塞和唤醒带来的**性能**劣势
  2. **永久阻塞**：如果持有锁的线程被永久阻塞，比如遇到了无限循环、死锁等活跃性问题，那么等待该线程释放锁的那几个悲催的线程，将永远也得不到执行；
  3. **优先级反转**：当低优先级线程持有锁的时候，高优先级线程需要等低优先级线程释放锁

#### ② 悲观锁

* 锁住同步资源，确保资源万无一失
* Java中悲观锁的实现就是synchronized和Lock相关类
* 流程：

![1589377285928](课件\JUC资料\图片\悲观锁流程1.png)

![1589377329511](D:\java学习\并发和多线程\课件\JUC资料\图片\悲观锁流程2.png)

#### ③乐观锁

* 认为自己在处理操作的时候不会有其他线程来干扰，所以并**不会锁住被操作对象**；
* 在更新的时候，去对比在修改的期间数据有没有被其他人改变过：如果没被修改过，就说嘛只有自己在操作，就正常去修改数据；
* 如果数据和最开始的状态不一样了，就说明在这段时间内数据被其他线程修改过，就不能继续刚才的更新数据过程，可以选择放弃、报错、重试等策略；
* 乐观锁的实现一般都是利用**CAS算法**来实现的；
  * CAS核心思想：在数据的一个原子操作内，把数据对比并且交换；

![1589381125784](D:\java学习\并发和多线程\课件\JUC资料\图片\乐观锁流程1.png)

![1589381165726](课件\JUC资料\图片\乐观锁流程2.png)

![1589381193523](课件\JUC资料\图片\乐观锁流程3.png)

![1589381229877](课件\JUC资料\图片\乐观锁流程4.png)

![1589381292587](课件\JUC资料\图片\乐观锁流程5.png)



### 2. 典型例子

* 悲观锁：synchronized和Lock接口

* 乐观锁：原子类、并发容器等

* Git：Git就是乐观锁的典型例子，当往远端仓库push的时候，git会检查远端仓库的版本是不是领先于现在的版本，如果远程仓库的版本号和本地不一样，就表示其他人修改了远端代码，这次提交就失败；如果一致，就可以提交到远端仓库；

* 数据库

  * select for update就是悲观锁
  * 用version控制数据库就是乐观锁

  ![1589382091625](课件\JUC资料\图片\乐观锁例子.png)



### 3. 开销对比

* 悲观锁的原始开销要高于乐观锁，但是一劳永逸，临界区斥锁时间就算越来越长，也不会对互斥锁的开销造成影响；
* 乐观锁在一开始就布好了局，虽然乐观锁一开始的开销比悲观锁小，但是如果自旋时间很长或者不停重试，那么**消耗的资源也会越来越多**；

### 4. 使用场景

* 悲观锁：适合**并发写入多**的情况，适用于临界区持续时间比较长的情况，悲观锁可以避免大量的无用自旋等消耗，典型情况：
  1. 临界区有**IO操作**；
  2. 临界区**代码复杂**或者循环里量大；
  3. 临界区竞争**非常激烈**；
* 乐观锁：适合并发写入少，大部分是读取的场景，不加锁的能让读取性能大幅提高

## 4. 可重入锁和非可重入锁

以ReentrantLock为例

### 使用案例

* 普通用法1：预定电影院座位
* 普通用法2：打印字符串

### 可重入性质

* 可重入：**同一线程**的**外层函数**获得锁之后，**内层函数**可以直接再次获取该锁。可重入锁又称递归锁，同一个线程可以多次获取同一把锁，有ReentrantLock、synchronized；
* 不可重入：

* 好处：
  * 避免死锁：第一个方法获取锁后，执行第二个方法需要获取同样的锁，可重入性就能保证第一个方法无需释放锁，第二个方法就能进入；否则第二个方法必须等到第一个方法释放锁后才能进入，而没执行完第二个方法，第一个方法就不能释放锁的情况下，就会发生死锁。
  * 提升封装性

### 源码对比

* 可重入锁ReenrantLock以及非可重入锁ThreadPoolExecutor的Worker类
* ReenrantLock上锁过程的**核心是AQS**

![1589455315046](课件\JUC资料\图片\可重入源码对比.png)

### ReenrantLock其他方法介绍

* isHeldByCurrentThread：可以看出锁是否被当前线程持有；
* getQueueLength：可以返回当前正在等待这把锁的队列有多长；

一般这两个方法时开发和调试时候使用，上线后用到的不多



## 5. 公平锁和非公平锁

### 1. 什么是公平和非公平

* 公平：指的是按照线程请求的顺序来分配锁
* 非公平：指的是不完全按照请求的顺序，在合适的时机，可以插队
* 什么是合适的时机？
* 火车票被插队的例子



### 2. 为什么要有非公平锁

* 是为了提高效率，避免**唤醒带来的空档期**

### 3.公平与不公平的情况(ReentrantLock)

**ReentrantLock锁默认的策略是非公平的**

* 如果在创建ReentrantLock对象时，参数填写true，那么这就是个公平锁；
* 假设线程1234是按顺序调用lock()的：
  * 公平：线程1234依次获取并释放锁
  * 不公平：线程1释放锁后，在线程2被唤醒并获取锁之间的空档期，线程5恰好去执行lock()

### 4. 代码案例：演示公平和非公平

`juc.lock.reentrantlock.FairLock`

### 5. 特例

* tryLock()不遵守设定的公平规则，自带插队属性
* 例如，当有线程执行tryLock()的时候，一旦有线程释放了锁，那么这个正在tryLock的线程就能获取到锁，即使在它前面有等待的线程

### 6. 优缺点 

![1589461083115](课件\JUC资料\图片\公平与非公平对比.png)

### 7. 源码分析

![1589461184144](课件\JUC资料\图片\公平与非公平锁源码.png)



## 6. 共享锁和排它锁

以`ReentrantReadWriteLock`读写锁为例

### 1. 什么是共享锁和排它锁

* 排它锁，又称独占锁、独享锁：`synchronized`等；
* 共享锁，又称为**读锁**。获得共享锁之后，可以查看但**无法修改和删除数据**，其他线程此时也可以获取到共享锁，也可以查看但无法修改和删除数据；

* 共享锁和排它锁的典型是读写锁`ReentrantReadWriteLock`，其中读锁是共享锁，写锁是独享锁;

### 2. 读写锁的作用

* 在没有读写锁之前，使用ReentrantLock，虽然能够保证线程安全，但是也浪费了一定的资源：多个读操作同时进行，并不存在线程安全问题；
* 在读的地方使用读锁，在写的地方使用写锁，灵活控制，如果没有写锁的情况下，读是无阻塞的，提高了程序的执行效率；

### 3. 读写锁的规则

1. 多个线程**只申请读锁**，都可以申请到；
2. 如果有一个线程已经**占用了读锁**，则此时其他线程如果要**申请写锁**，则申请写锁的线程会一直**等待释放读锁**；
3. 如果有一个线程已经**占用了写锁**，则此时其他线程如果**申请写锁或者读锁**，则申请的线程会一直**等待释放写锁**；

* 总结：要么是一个或多个线程同时有读锁，要么是一个线程有写锁，但是**两者不会同时出现**(要么多读，要么一写)；可以理解为，**读写锁只是一把锁，但可以通过两种方式锁定**；

### 4. ReentrantReadWriteLock具体用法

* 电影院升级

![1589469226129](课件\JUC资料\图片\读锁案例.png)

![1589469264659](课件\JUC资料\图片\写锁案例.png)



### 5. 读锁和写锁的交互方式

* 选择规则：有线程获取读锁，等待队列中既有获取读锁又有获取写锁的线程，获取写锁的线程需要等读锁被释放，那么读锁还未释放的时候，后面的读锁是否能够插队？
* 读线程插队
* 升降级：读锁和写锁并不是平级的，在写锁还未释放的情况下是否能获取读锁(降级)，反之(升级)是否亦可？
* ReentrantReadWriteLock**不允许读锁插队，允许降级，不允许升级**；

#### 读锁插队策略

* 公平：自然都需要排队，不允许插队；

* 非公平：假设线程2和线程4正在同时读取，线程3想要写入，但还拿不到锁，于是进入等待队列，线程5不在队列里，现在过来想要读取，此时有两种策略：

  1. 读可以插队：效率高，但容易造成写锁饥饿

  ![1589471056259](课件\JUC资料\图片\读锁插队策略1.png)

  2. 读不可以插队：避免饥饿

* 策略的选择取决于具体锁的实现，ReentrantReadWriteLock考虑整体情况，**选择实现策略2**；

* 更准确的说，非公平锁的插队策略：

  * **写锁可以随时插队**，因为它严格的获取锁的条件使得它不可能插队成功；
  * 读锁仅在等待队列**头结点不是想获取写锁的线程**的时候可以插队；

##### 源码分析

* 公平锁

~~~java
	static final class FairSync extends Sync {
        private static final long serialVersionUID = -2274990926593161451L;
        // 返回true则需要排队
        final boolean writerShouldBlock() {
            return hasQueuedPredecessors(); 
        }
        final boolean readerShouldBlock() {
            return hasQueuedPredecessors();
        }
    }
~~~

* 非公平锁

~~~java
	static final class NonfairSync extends Sync {
        private static final long serialVersionUID = -8159625535654395037L;
        final boolean writerShouldBlock() {
            return false; // writers can always barge
        }
        final boolean readerShouldBlock() {
            /* As a heuristic to avoid "indefinite writer starvation",
             * block if the thread that momentarily appears to be head
             * of queue, if one exists, is a waiting writer.  This is
             * only a probabilistic effect since a new reader will not
             * block if there is a waiting writer behind other enabled
             * readers that have not yet drained from the queue.
             */
            return apparentlyFirstQueuedIsExclusive();
        }
    }
~~~



##### 代码演示

1. 读不插队
2. 读实际上可以插队

#### 锁的升降级

##### 为什么需要升降级

* 因为写入需求而获取写锁，之后又有读取的需求，如果要**先释放写锁，可能不知道什么时候才能获取读锁**，所以出现写锁降级为读锁的需求；
* 读锁升级为写锁，需要等所有读锁被释放掉；

##### 代码演示

* 支持锁的降级，不支持升级：`juc.lock.readwrite.Upgrading`;
* 实际锁降级的例子：在锁降级成功后，也就是持有写锁的时候同时申请并获得了读锁后，此时直接释放写锁，但是不释放读锁，这样就可以提高锁的利用效率，下面这段代码演示了在更新缓存的时候，如何利用锁的降级功能。

~~~java
public class CacheData {
    
    Object data;
    volatile boolean cacheValid;
    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    
    void processCachedData() {
        // 最开始是读取
        rwl.readLock().lock();
        if (!cacheValid) {
            // 发现缓存失效，那么就需要写入了，所以现在需要获取写锁。
            // 由于锁不支持升级，所有在获取写锁之前，必须首先释放读锁。
            rwl.readLock().unlock();
            rwl.writeLock().lock();
            try {
                // 这里需要再次判断数据的有效性，因为在我们释放读锁和获取写锁的空档期，可能有其他线程修改了数据
                if (!cacheValid) {
                    data = new Object();
                    cacheValid = true;
                }
                // 在不释放写锁的情况下，直接获取读锁，这就是读写锁的降级
                rwl.readLock().lock();
            } finally {
                // 释放了写锁，但是依然持有读锁，这样一来，就可以多个线程同时读取数据，提高整体效率
                rwl.writeLock().unlock();
            }
        }
        try {
            System.out.println(data);
        } finally {
            // 最后释放读锁
            rwl.readLock().unlock();
        }
    }
}
~~~



### 6. 总结

1. ReentrantReadWriteLock实现了ReadWriteLock接口，最主要的有两个方法：readLock()和writeLock()用来获取读锁和写锁
2. 锁申请和释放策略(**要么多读，要么一写**)
   1. 多个线程只申请读锁，都可以申请到；
   2. 如果有一个线程已经占用了读锁，则此时其他线程如果要申请写锁，则申请写锁的线程会一直等待释放读锁；
   3. 如果有一个线程已经占用了写锁，则此时其他线程如果申请写锁或者读锁，则申请的线程会一直等待释放写锁；
   4. 要么是一个或多个线程同时有读锁，要么是一个线程有写锁，但是两者不会同时出现
3. 插队策略：为了防止饥饿，读锁不能插队；
4. 升降级策略：只能降级，不能升级；
5. 适用场合：ReentrantReadWriteLock适用于读多写少的情况，合理适用可以进一步提高并发效率；

## 7. 自旋锁和阻塞锁

### 1. 概念

* **阻塞或唤醒**一个java线程需要操作系统**切换CPU状态**来完成，这种状态转换需要**耗费处理器时间**，这些时间可能比用户代码执行的时间还要长；在许多场景中，同步资源的锁定时间很短，为了一小段时间去切换线程，线程**挂起和恢复**现场的花费可能让系统得不偿失；
* 如果物理机器有多个处理器，能够让两个或以上的线程同时并行执行，就可以让后面请求锁的线程**不放弃CPU**的执行时间，看看持有锁的线程是否很快就会释放锁；
* 自旋锁：为了让当前线程“稍等一下”，需要让当前线程进行自旋，如果在自旋完成后前面锁定同步资源的线程已经释放了锁，那么当前线程就可以不必阻塞而是直接获取同步资源，从而**避免切换线程带来的开销**；

### 2. 缺点

* 在自旋的过程中，一直消耗CPU，虽然自旋锁的起始开销低于悲观锁，但随着自旋时间的增长，开销也线性增长，如果锁被占用的时间很长，那么自旋的线程只会白白浪费处理器资源；

### 3. 原理和源码分析

* 在java1.5版本及以上的并发框架JUC的atomic包下的类基本都是自旋锁的实现；
* AtomicInteger的实现：**自旋锁的实现原理是CAS**，AtomicInteger中调用unsafe进行自增操作的源码中的do-while循环就是一个自旋操作，如果修改过程中遇到其他线程竞争导致没修改成功，就在while里死循环，直至修改成功；
* 自己实现一个自旋锁`juc.lock.spinlock.SpinLock`

### 4. 适用场景

* 自旋锁一般用于多核的服务器，在**并发度不是特别高**的情况下，比阻塞锁的效率高；
* 自旋锁也适用于临界区比较短小的情况，否则如果**临界区很大**(线程一旦拿到锁，很久以后才会释放)，那也是不合适的

## 8. 可中断锁

* 可中断锁：如果某一线程A正在执行锁中的代码，另一线程B正在等待获取该锁，可能由于等待时间过长就不想再等了，而是先去处理其他事情，此时可以中断等待获取锁的过程。
* 在Java中，synchronized就不是可中断锁，而Lock是可中断锁，因为tryLock(time)和lockInterruptibly都能响应中断。



## 9. 锁的优化

### JVM对锁的优化

* 自旋锁和自适应
  * 自适应：自旋锁在获取锁到一定次数时还未获取到，就会转为阻塞锁；
* 锁消除：对于一些外部访问不到的私有内容，JVM可以检查出来，将锁消除；
* 锁粗化：对于一些重复获取锁释放锁并且可以合并在一起执行的被锁住的代码块，可以扩大锁的范围，减少频繁获取锁释放锁带来的开销；

### 编写代码优化锁

1. 缩小同步代码块，最小也要包括基本的原子操作；
2. 尽量不要锁住方法，因为方法可能会被扩展；
3. 减少请求锁的次数；
4. 避免人为制造大量使用锁的场景；
5. 所终尽量不要在包含锁，否则容易产生死锁；
6. 选择合适的锁类型或合适的工具类；



# 五、原子类

## 1. 什么是原子类，有什么用？

* 不可分割
* 一个操作是不可中断的，即便是多线程的情况下也可以保证
* `java.util.concurrent.atomic`

* 原子类的作用和锁类似，是为了保证并发情况下**线程安全**。不过原子类相比于锁，有一定的**优势**：
  * **粒度更细**：原子变量可以把竞争范围缩小到变量级别，这是最细粒度的情况，通常锁的粒度都要大于原子变量的粒度；
  * **效率更高**：除了**高度竞争**的情况，使用原子类的效率会比使用锁的效率更高；



## 2. 6类原子类纵览

![1589705306298](课件\JUC资料\图片\6类原子纵览.png)



## 3. Atomic*基本类型原子类，已AtomicInteger为例

### AtomicInetger：整型原子类

#### 常用方法

* `public final int get()`：获取当前的值；
* `public final int getAndSet(int newValue)`：获取当前值，并设置新的值；
* `public final int getAndIncrement()`：获取当前的值，并自增；
* `public final int getAndDecrement()`：获取当前的值，并自减；
* `public final int getAndAdd(int delta)`：获取当前的值，并加上预期的值；
* `boolean compareAndSet(int expect, int update)`：如果输入的数值等于预期值，则以原子方式将该值设置为输入值(update)；



### AtomicLong：长整型原子类



### AtomicBoolean：布尔型原子类





## 4.Atomic*Array数组类型原子类





## 5.Atomic*Reference引用类型原子类

* `AtomicReference`：AtomicReference类的作用，**和AtomicInteger并没有本质却别**，AtomicInteger可以让一个整数保证原子性，而AtomicReference可以**让一个对象保证原子性**，当然，AtomicReference的功能明显比AtomicInteger多，因为一个对象里可以**包含很多属性**。用法和AtomicInteger类似。



## 6. 把普通变量升级为原子类

**适用于类已经定义好，不方便更改，想对里面的变量进行原子操作的情况**

* 用**AtomicIntegerFieldUpdater**升级原有的变量
* 使用场景：**偶尔**需要一个原子get-set操作
* 用法，代码演示`juc.atomic.AtomicIntegerFieldUpdateDemo`；
* 可见范围；
* 不支持被static修饰的变量；





## 7. **Adder累加器**

* 是Java8引入的，相对是比较新的一个类

* 高并发下`LongAdder`比`AtomicLong`**效率高**，不过本质是**空间换时间**；

* 好处：竞争激烈的时候，`LongAdder`把不同线程对应到不同的Cell上进行修改，降低了冲突的概率，是**多段锁**的理念，提高了并发性；

* 代码演示`juc.atomic.AtomicLongDemo`，`juc.atomic.LongAdderDemo`

  * 这里演示多线程情况下`AtomicLong`的性能，有16个线程对同一个`AtomicLong`累加
  * `AtomicLong`的弊端：由于竞争很激烈，**每一次加法，都要flush和refresh**，导致很耗费资源；

  ![1589815165805](课件\JUC资料\图片\AtomicLong的弊端.png)

### `LongAdder`带来的改进和原理：

#### 改进

* 在内部，这个`LongAdder`的实现原理和`AtomicLong`有所不同，`AtomicLong`的原理是：每一次加法都需要进行同步，所以在高并发的时候会导致冲突比较多，也就降低的效率；
* 而`LongAdder`，**每个线程都会有自己的一个计数器**，仅用来在自己线程内计数(thread-1中的ctr'和thread-2中的ctr'')，它们之间并不存在竞争关系，所以**在加和的过程中，根本不需要同步机制**，也不需要flush和refresh，也没有一个公共的counter来给所有线程统一计数，这样一来就**不会受到其他线程的计数器干扰**，等计算结束后再汇总；

####  原理

* `LongAdder`引入了分段累加的概念，内部有一个base变量和一个Cell[]数组共同参与计数：
  * base变量：竞争不激烈是=时，直接累加到该变量上；
  * Cell[]数组：竞争激烈时，各个线程分散累加到自己的槽Cell[i]中；
* `sum()`源码分析
  * 由于该方法没有加锁，也可能存在一些问题，返回的结果不是非常精确

~~~java
    public long sum() {
        Cell[] as = cells; Cell a;
        long sum = base;
        if (as != null) {
            for (int i = 0; i < as.length; ++i) {
                if ((a = as[i]) != null)
                    sum += a.value;
            }
        }
        return sum;
    }
~~~



![1589815356742](课件\JUC资料\图片\LongAdder的原理.png)

### 对比AtomicLong和LongAdder

* 在低争用的情况下，`AtomicLong`和`LongAdder`这两个类具有相似的特征。但在竞争激烈的情况下，`LongAdder`的预期吞吐量要高得多，但要消耗更多的空间；
* `LongAdder`适合的场景是统计求和技术的场景，而且它基本只提供了add方法，而`AtomicLong`还具有cas方法；

## 8. Accumulator累加器

* Accumulator和Adder非常相似，Accumulator就是一个更通用版本的Adder；
* 代码演示`juc.atomic.LongAccumulatorDemo`；
* 使用场景：大量的并行计算，而且对计算顺序没有要求



# 六、CAS原理

## 1. 什么是CAS

* 是一种**实现线程安全的算法**，同时也是一种**CPU指令**，比如compareAndSwap
* 思想：操作前V值被认为**应该是A，如果是的话就把它改成B，如果不是A(说明被别人修改过了)，那就不再去修改**，避免多人同时修改导致出错；

* CAS有三个操作数：内存值V、预期值A、要修改的值B，当且仅当预期值A和内存值V相同时，才将内存值修改为B，否则什么都不做。最后返回现在的V值；
* CAS利用了一些CPU的特殊指令，这些指令由CPU保证了原子性，一条指令就能做多件事而不会出现线程安全问题；

## 2. 案例演示

* CAS的等价代码：

~~~java
public class SimulatedCAS {
    private volatile int value;

    // 对应一条CPU原子级别的指令
    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }

}
~~~

* 两个线程竞争，其中一个落败：`juc.cas.TwoThreadsCompetition`；

## 3. 应用场景

* 乐观锁
* 并发容器
* 原子类



## 4. 分析在java中是如何利用CAS实现原子操作的？

**以AtomicInteger为例**

* AtomicInteger加载Unsafe工具，用来直接操作内存数据
* 用volatile修饰value字段，保证可见性
* 分析getAndAddInt方法

![1589879275851](课件\JUC资料\图片\CAS源码分析1.png)

![1589879473836](课件\JUC资料\图片\CAS源码分析2.png)

* Unsafe类中的compareAndSwapInt方法
  * 方法中先想办法拿到变量value在内存中的地址；
  * 通过`Atomic::cmpxchg(x, addr, e)`实现原子性的比较和替换，其中参数x是即将更新的值，参数e是原内存的值。至此，最终完成了CAS的全过程；

## 5. 缺点

* ABA问题：CAS中的**compare仅仅比较值是否相等**，而在检查的过程中存在多线程访问，值可能出现A -> B -> A的过程，最终值是相等的，但**中间是否发生改变其实是不清楚的**；但是可以通过附加**版本号**来标记变化过程；

* 自旋时间过长：CAS存在死循环，如果竞争过于激烈，会导致自旋时间过长，从而消耗很多资源



# 七、以不变应万变

## 1. 什么是不变形(Immutable)

* 如果对象在**被创建后，状态(引用、字段、成员变量等)都不能被修改**，那么它就是不可变的

* 具有不变性的对象(只要存在能够被修改的地方就不具备不变性)**一定是线程安全的**，就不需要对其采取任何额外的安全措施，也能保证线程安全；



## 2. final的作用

* 早期版本
  * 锁定
  * 效率：早期的java实现版本中，会将final方法转为内嵌调用
* 目前版本
  * **类防止被继承、方法防止被重写、变量防止被修改**
  * 天生是**线程安全**的，而不需要额外的同步开销
  * 目前性能上没有多大优势，主要用在语义上

## 3. 3种用法

### final修饰变量

* 含义：被final修饰的变量，意味着**值不能被修改**。如果变量是**对象**，那么对象的**引用不能变**，但是对象**自身的内容依然可以变化**；
* 3种变量：
  * `final instance variable`（类中的final属性）
  * `final static variable`（类中的static final属性）
  * `final local variable`（**方法**中的final变量）
* 属性被声明为final后，该变量则**只能被赋值一次**。且一旦被赋值，final的变量就**不能再被改变**，无论如何也不会变；

#### 赋值时机

* `final instance variable`（类中的final属性）

  1. 在声明变量的**等号右边**直接赋值；
  2. 在**构造函数**中赋值；
  3. 在类的**初始代码块**中赋值(不常用)；

  * 如果不采用第一种赋值方法，那么就必须在第2、3中挑一个来赋值，而不能不赋值，这是final语法所规定的；

* `final static variable`（类中的static final属性）

  1. 在声明变量的**等号右边**直接赋值；
  2. 在**static初始代码块**中赋值，但不能用普通的初始代码块赋值

* `final local variable`（**方法**中的final变量）

  * 和前面两种不同，由于这里的变量是在方法里的，所有没有构造函数，也不存在初始代码块；
  * `final local variable`不规定赋值时机，只要求在**使用前必须赋值**，这和方法中的非final变量的要求是一样的；

* 为什么要规定赋值时机？

  如果初始化不赋值，**后续赋值，就是从null变为要赋的值**，这就违反final不变得原则了；

### final修饰方法

* final不能修饰**构造方法**
* 被final修饰的方法**不能被重写**：final修饰的方法不允许被子类重写，重名的方法也不允许有；
* 引申：**static方法不能被重写**：子类可以有与父类重名的静态方法，但不是重写父类的方法；

### final修饰类

* **不可被继承**
* 典型的例子就是**String类**



## 4. 注意点

* final修饰对象/类的时候，只是**对象的引用不可变**，而对象**本身的属性是可以变化**的；
* final使用原则：良好的编程习惯，告诉其他开发人员，这些被final修饰的是不想被改变的；
* 所有的byte、short、char型的值将被提升为int型，而**被final修饰的变量不会自动改变类型**；



## 5. 不变性和final的关系

* 不变性并不意味着，简单地用final修饰就是不可变的

  * 对于**基本数据类型**，被final修饰后确实具有不变性
  * 但是对于**对象类型**，需要该对象保证自身被创建后，状态永远不会变才可以

* 如何利用final实现对象不可变

  * 把所有属性都声明为final？

    并不是，如果有的成员变量为对象类型，仍不一定具备不可变性;

  * 一个属性是对象类型的不可变对象的例子：`juc.immutable.ImmutableDemo`;

### 对象不可变的条件

* 对象**创建后**，其状态就**不能被修改**
* **所有属性都是final**修饰的
* 对象创建过程中**没有发生逸出**（逸出后，可能会被其他线程拿到并且修改）

### 不可变——栈封闭

**把变量写在线程内部**

* 在方法里新建的局部变量，实际上是**存储在每个线程私有的栈空间**，而**每个栈所占的空间是不能被其他线程所访问到的**，所以不会有线程安全问题。这就是著名的**“栈封闭”**技术，是**“线程封闭”**技术的一种情况。
* 代码演示：`juc.immutable.StackConfinement`

## 6. 面试题——真假美猴王

~~~java
public class FinalStringDemo1 {

    public static void main(String[] args) {
        String a = "wukong2";
        final String b = "wukong";
        final String f = getWukong();
        String c = "wukong";
        String d = b + 2;
        String e = d + 2;
        String g = f + 2;
        System.out.println((a == d));
        System.out.println((a == e));
        System.out.println((a == g));
        System.out.println((e == g));
    }
    
    private static String getWukong() {
        return "wukong";
    }
}

// 打印结果
true
false
false
false
~~~

* 一旦把b加了final修饰之后，编译期间就知道b的准确值，而且b永远不会变化，所以后期编译器会把b当做编译时期的常量来使用，用到b的时候就直接访问这个常量而不需要在编译的时候再确定；d用到b的时候就发现是“wukong”了，就能直接计算出结果，然后发现存在“wukong2”，对于d就没必要再新建一个对象，就直接指向该地址(与a指向的地址一致)；
* 而c没有被final修饰，所以编译器在使用c的时候，也不会提前知道c具体的值，所以e的值要在运行的时候才能确定，而在运行时确定的e，会在堆上生成“wukong2”，所以最终e指向的是堆中的，而a和d指向的是常量池中的；
* 而对于f，由于是方法返回的值，所以编译器无法确定被final修饰的f的值，g也是在运行的时候生成的；

# 八、并发容器

## 1. 并发容器概览

* **`ConcurrentHashMap`**：线程安全的`HashMap`；
* **`CopyOnWriteArrayList`**：线程安全的List；
* **`BlockingQueue`**：这是一个接口，表示阻塞队列，非常适合用于作为**数据共享的通道**；
* `ConcurrentLinkedQueue`：高效的非阻塞并发队列，使用链表实现。可以看做一个线程安全的`LinkedList`；
* `ConcurrentSkipListMap`：是一个Map，使用跳表的数据结构进行快速查找；

## 2. 集合类的历史

* Vector和Hashtable：线程安全、但性能不好
  * Vector可以看做是一个线程安全的AarryList，Hashtable可以看做是一个线程安全的HashMap；
  * 其中许多方法被Synchronized修饰，因此在并发场景中性能不是很好；

* AarryList和HashMap这两个类虽然不是线程安全的，但是可以用

  `Collections.synchronizedList(new AarryList<E>())`和

  `Collections.synchronizedMap(new HashMap<K, V>())`使之变成线程安全；

  * 分析源码：用同步代码块，依然性能不高

  ```java
  public E get(int index) {
      synchronized (mutex) {return list.get(index);}
  }
  ```

* `ConcurrentHashMap`和`CopyOnWriteArrayList`取代同步的HashMap和同步的ArrayList
  * 绝大多数并发情况下，`ConcurrentHashMap`和`CopyOnWriteArrayList`的性能更好；
  * 如果一个List经常被修改的情况下，`Collections.synchronizedList(new AarryList<E>())`的性能会优于`CopyOnWriteArrayList`，因为`CopyOnWriteArrayList`适合**读多写少**的场景，每次写入都会赋值完整的列表；
  * `ConcurrentHashMap`几乎在所有的情况下都比`Collections.synchronizedMap(new HashMap<K, V>())`的性能更好；

## 3. `ConcurrentHashMap`(重点)

### 1）磨刀不误砍柴工：Map简介

* **`HashMap`**：根据键的hashcode存储，大多数时候可以算出hashcode值，所以可以直接定位到要找的位置，因此访问速度比较快。**允许键(key)值(value)为null**，**线程非安全**；
* `Hashtable`：历史遗留类，功能与`HashMap`类似，但不允许键(key)值(value)为null，**线程安全**;
* `LinkedHashMap`：`HashMap`的子类，**保存了插入的顺序**，遍历的顺序跟插入的顺序一致；
* `TreeMap`：**根据键排序**，默认升序，遍历的结果是排过序的；

**以上四种要求key是不可变对象，即对象创建后，hash值不会再改变**

![1590320351808](课件\JUC资料\图片\Map接口.png)

* 代码演示常用方法

### 2）为什么需要`ConcurrentHashMap`

* 为什么HashMap是线程不安全的？
  * 同时put碰撞导致数据丢失：多个线程计算出来的hash值一样的话，这些相同的key会放在同一个位置，最终除了一个其他都会丢失；
  * 同时put扩容导致数据丢失：多线程put发现都需要扩容，最终只有一个扩容后的数组被保留下来；
  * **死循环造成的CPU100%**
    * 彩蛋：调试技巧——如何修改JDK版本，从8到7
    * 彩蛋：调试技巧——**多线程配合**，模拟真实场景
    * `HashMap`在高并发下的死循环(仅在JDK7及以前存在)
    * 代码演示
    * 原理：多线程同时扩容的时候，会造成链表的死循环(你指向我，我指向你)

### 3）HashMap的分析

* JDK1.7

![1590329272883](课件\JUC资料\图片\JDK1.7HashMap结构1.png)

![1590329419780](课件\JUC资料\图片\JDK1.7HashMap结构2.png)

* JDK1.8

![1590329483775](课件\JUC资料\图片\JDK1.8HashMap结构1.png)

![1590329563910](课件\JUC资料\图片\JDK1.8HashMap结构2.png)

* 红黑树
  * 对**二叉查找树BST**的一种**平衡**策略，左孩子<父结点<右孩子，(logN) vs O(N)；
  * 会自动平衡，防止极端不平衡从而**影响查找效率**的情况发生；
  * 每个结点要么是红色，要么是黑色，但根结点永远是黑色的；
  * **红色结点不能连续(**也即是，红色结点的孩子和父亲都不能是红色)；
  * 从任一结点到其子树中每个叶子结点的路径都包含**相同数量的黑色结点**；
  * 所有的叶结点都是黑色的
* `HashMap`并发的特点：
  1. 非线程安全；
  2. 迭代时不允许修改内容；
  3. 只读的并发是安全的；
  4. 如果一定要把HashMap用在并发环境，用`Collections.synchronizedMap(new HashMap<K, V>())`



### 4）JDK1.7的`ConcurrentHashMap`实现和分析

![1590330287758](课件\JUC资料\图片\JDK1.7ConcurrentHashMap结构.png)

![1590330373752](课件\JUC资料\图片\JDK1.7ConcurrentHashMap结构2.png)

* java7中的`ConcurrentHashMap`最外层是多个segment，每个segment的底层数据结构与`HashMap`类似，仍然是数组和链表组成的**拉链法**；
* 每个segment**独立上`ReentrantLock`锁**，每个segment之间互不影响，提高了并发效率；
* `ConcurrentHashMap`默认有16个segments，所有最多可以**同时支持16个线程并发写**(操作分别分布在不同的segment上)。这个默认值可以在初始化的时候设置为其他值，但是一旦**初始化以后，是不可以扩容**的；



### 5）JDK1.8的`ConcurrentHashMap`实现和源码分析

* 简介：代码量从JDK1.7的1000多行变为6000多行，在结构上与原来的segment方式大有区别，首先结构设计上不在采用segment方式，而是采用**Node**，而且保证线程安全的方式改为CAS和synchronized；
* 结构图：当某个Node链表大于某个阈值(默认8)时且满足容量条件(默认64)，将链表转为红黑树;

![1590330879105](课件\JUC资料\图片\JDK1.8ConcurrentHashMap结构图.png)

#### 源码分析

* put：

  1. 判断key value不为空
  2. 计算hash值
  3. 根据对应位置结点的类型来赋值，或者helpTransfer，或者增长链表，或者给红黑树增加结点；
  4. 检查满足阈值就“红黑树化”；
  5. 返回oldVal；

  ```java
  public V put(K key, V value) {
      return putVal(key, value, false);
  }
  
  /** Implementation for put and putIfAbsent */
  final V putVal(K key, V value, boolean onlyIfAbsent) {
      if (key == null || value == null) throw new NullPointerException();
      int hash = spread(key.hashCode());
      int binCount = 0;
      for (Node<K,V>[] tab = table;;) {
          Node<K,V> f; int n, i, fh;
          if (tab == null || (n = tab.length) == 0)
              tab = initTable();
          else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {
              if (casTabAt(tab, i, null,
                           new Node<K,V>(hash, key, value, null)))
                  break;               // no lock when adding to empty bin
          }
          else if ((fh = f.hash) == MOVED)
              tab = helpTransfer(tab, f);
          else {
              V oldVal = null;
              synchronized (f) {
                  if (tabAt(tab, i) == f) {
                      if (fh >= 0) {
                          binCount = 1;
                          for (Node<K,V> e = f;; ++binCount) {
                              K ek;
                              if (e.hash == hash &&
                                  ((ek = e.key) == key ||
                                   (ek != null && key.equals(ek)))) {
                                  oldVal = e.val;
                                  if (!onlyIfAbsent)
                                      e.val = value;
                                  break;
                              }
                              Node<K,V> pred = e;
                              if ((e = e.next) == null) {
                                  pred.next = new Node<K,V>(hash, key,
                                                            value, null);
                                  break;
                              }
                          }
                      }
                      else if (f instanceof TreeBin) {
                          Node<K,V> p;
                          binCount = 2;
                          if ((p = ((TreeBin<K,V>)f).putTreeVal(hash, key,
                                                         value)) != null) {
                              oldVal = p.val;
                              if (!onlyIfAbsent)
                                  p.val = value;
                          }
                      }
                  }
              }
              if (binCount != 0) {
                  if (binCount >= TREEIFY_THRESHOLD)
                      treeifyBin(tab, i);
                  if (oldVal != null)
                      return oldVal;
                  break;
              }
          }
      }
      addCount(1L, binCount);
      return null;
  }
  ```

* get

  1. 计算hash值；
  2. 找到对应的位置，根据情况进行：
  3. 直接取值；
  4. 遍历红黑树取值；
  5. 遍历链表取值；
  6. 返回找到的结果；

  ```java
  public V get(Object key) {
      Node<K,V>[] tab; Node<K,V> e, p; int n, eh; K ek;
      int h = spread(key.hashCode());
      if ((tab = table) != null && (n = tab.length) > 0 &&
          (e = tabAt(tab, (n - 1) & h)) != null) {
          // 第一个结点
          if ((eh = e.hash) == h) {
              if ((ek = e.key) == key || (ek != null && key.equals(ek)))
                  return e.val;
          }
         // 红黑树
          else if (eh < 0)
              return (p = e.find(h, key)) != null ? p.val : null;
          // 链表
          while ((e = e.next) != null) {
              if (e.hash == h &&
                  ((ek = e.key) == key || (ek != null && key.equals(ek))))
                  return e.val;
          }
      }
      return null;
  }
  ```



### 6）对比JDK1.7和1.8的优缺点

* 数据结构不同：**并发度的提升**，升级后，从原来默认的16个独立的segment变为每个独立的Node；

* Hash碰撞：升级后，从原来拉链法变为拉链法中链表达到某个阈值会转变为红黑树，**提高查找效率**；

* 保证并发安全：原来是继承自`ReentrantLock`的segment， 升级后，采用CAS和synchronized

* 查询复杂度：由链表的O(N)提升为红黑树O(logN)；

* 为什么超过8要转为红黑树？

  因为根据泊松分布计算出链表长度超过8的概率千万分之一，当超过8的极端情况下，转为红黑树能够保证查询效率。

### 7）组合操作：`ConcurrentHashMap`也不是线程安全

* `ConcurrentHashMap`能够保证同时get或者put操作的线程安全，但是get和put组合使用会引发线程安全问题；

  * 代码演示：`juc.collections.concurrenthashmap.OptionsNotSafe`；

* 组合操作：

  * 可以用`replace(key, oldVal, newVal)`代替put()；
  * `putIfAbsent`，等价代码：

  ~~~java
  if(!map.containsKey(key))
  	return map.put(key, value);
  else {
  	return map.get(key);
  }
  ~~~



## 4. `CopyOnWriteArrayList`

### 1）诞生的历史和原因

* 用来代替`Vector`和`SynchronizedList`，就和ConcurrentHashMap代替SynchronizedMap的原因一样；
* `Vector`和`SynchronizedList`的锁的粒度太大，**并发效率相对比较低**，并且**迭代时无法编辑**；
* Copy-On-Write并发容器还包括`CopyOnWriteArraySet`，用来代替同步Set；

### 2）适用场景

* **读操作可以尽可能地快**，而**写操作即使慢一些**也没有太大关系；
* **读多写少**：黑名单，每日更新；监听器：迭代操作远多于修改操作；

### 3）读写规则

* 回顾读写锁：读读共享、其他都互斥(写写互斥、读写互斥、写读互斥)
* 读写锁规则的**升级**：读取是完全不用加锁的，并且**写入也不会阻塞读取操作**。只有**写入和写入**之间需要进行同步等待；

### 4）实现原理

* CopyOnWrite的含义：写入的时候，先复制一份到新的内存中进行写入，然后再将指针指向写入后的副本，原本就会被回收；

* “不可变”原理：写入的时候会新建新的容器，对于旧的容器则是不可变的，是线程安全的，读取是没什么问题；

* 迭代的时候进行修改不会报错，即使读取的内容已经过时；

  * 代码演示：`juc.collections.copyonwrite.CopyOnWriteArrayListDemo2`；

  * `ArrayList`迭代时修改报错原因：

    ```java
    final void checkForComodification() {
        if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
    }
    ```

### 5）缺点

* **数据一致性**问题：CopyOnWrite容器只能保证数据的**最终一致性**，不能保证数据的实时一致性。所以如果希望写入的数据能马上读到，就不要使用CopyOnWrite容器；
* **内存占用**问题：因为CopyOnWrite的写是复制机制，所以在进行写操作的时候，内存里会同时驻扎两个对象的内存；

### 6）源码分析

* add

```java
public boolean add(E e) {
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
        Object[] elements = getArray();
        int len = elements.length;
        Object[] newElements = Arrays.copyOf(elements, len + 1);
        newElements[len] = e;
        setArray(newElements);
        return true;
    } finally {
        lock.unlock();
    }
}
```

* get

```java
public E get(int index) {
    return get(getArray(), index);
}

private E get(Object[] a, int index) {
    return (E) a[index];
}

/**
 * Gets the array.  Non-private so as to also be accessible
 * from CopyOnWriteArraySet class.
 */
final Object[] getArray() {
    return array;
}
```



## 5.并发队列Queue(**阻塞队列**、非阻塞队列)

### 1）为什么要使用队列

* 用队列可以在**线程间**传递数据：生产者消费者模式、银行转账；
* 考虑锁等线程安全问题的重任从“使用者”转移到“队列”上；

### 2）并发队列简介

* `Queue`
* `BlockingQueue`：队列为空时，取操作被阻塞；队列满时，写操作被阻塞

### 3）各并发队列关系图

![1590409404078](课件\JUC资料\图片\队列关系图.png)

### 4）阻塞队列BlockingQueue

#### a. 什么是阻塞队列

* 阻塞队列是具有**阻塞**功能的队列，所以它首先是一个队列，其次是具有阻塞功能；
* 通常，阻塞队列的一端是给生产者放数据用，另一端给消费者拿数据用。阻塞队列是**线程安全**的，所以生产者和消费者都可以是多线程的；

![1590409976196](课件\JUC资料\图片\生产者消费者模式.png)

* 阻塞功能：最有特色的两个带有阻塞功能的方法：

  * `take()`：获取并移除队列的头结点，一旦如果执行take的时候，**队列里无数据，则阻塞**，直到队列里有数据；

  ![1590410416646](课件\JUC资料\图片\阻塞队列take方法.png)

  * `put()`：插入元素。但是如果队列已满，那么就无法继续插入，则阻塞，直到队列里有了空闲空间；

  ![1590410471287](课件\JUC资料\图片\阻塞队列put方法.png)

* **是否有界**(容量有多大)：这是一个非常重要的属性，无界队列意味着里面可以容纳非常多(`Integer.MAX_VALUE`，约为2的31次，可以近似认为是无限容量)；
* 阻塞队列和**线程池**的关系：阻塞队列是线程池的重要组成部分；

#### b. 主要方法介绍

* put, take：会阻塞住
* add, remove, element：
  * add, remove分别对应put, take，但是如果取不出来或者放不进去，会抛出异常；
  * element是返回队列头元素，如果为空会抛出异常；
* offer, poll, peek：
  * offer向队列添加元素，如果队列已满，则返回false，表示添加失败；
  * poll取出队列中的元素并将其**删除**，如果队列为空，则返回null；
  * peek与poll功能一样，但是peek取出元素并不会将其删除；

#### c. `ArrayBlockingQueue`

* 有界

* 指定容量

* **公平**：可以指定是否需要保证公平，如果指定保证公平，那么等待时间最长的线程会被优先处理，不过这会带来一定的性能损耗；

* 使用案例：`juc/collections/queue/ArrayBlockingQueueDemo.java`，有10个面试者，一共只有一个面试官，大厅里有3个位置供面试者休息，每个人的面试时间是10秒，模拟所有人面试的场景；

* 源码

  * put

  ```java
  public void put(E e) throws InterruptedException {
      checkNotNull(e);
      final ReentrantLock lock = this.lock;
      lock.lockInterruptibly();
      try {
          while (count == items.length)
              notFull.await();
          enqueue(e);
      } finally {
          lock.unlock();
      }
  }
  ```

#### d. `LinkedBlockingQueue`

* 无界，容量`Integer.MAX_VALUE`
* 内部结构：Node、两把锁
* 分析put方法：

```java
public void put(E e) throws InterruptedException {
    if (e == null) throw new NullPointerException();
    // Note: convention in all put/take/etc is to preset local var
    // holding count negative to indicate failure unless set.
    int c = -1;
    Node<E> node = new Node<E>(e);
    final ReentrantLock putLock = this.putLock;
    final AtomicInteger count = this.count;
    putLock.lockInterruptibly();
    try {
        /*
         * Note that count is used in wait guard even though it is
         * not protected by lock. This works because count can
         * only decrease at this point (all other puts are shut
         * out by lock), and we (or some other waiting put) are
         * signalled if it ever changes from capacity. Similarly
         * for all other uses of count in other wait guards.
         */
        while (count.get() == capacity) {
            notFull.await();
        }
        enqueue(node);
        // 返回的旧值
        c = count.getAndIncrement();
        // c + 1为当前容量
        if (c + 1 < capacity)
            notFull.signal();
    } finally {
        putLock.unlock();
    }
    if (c == 0)
        signalNotEmpty();
}
```

#### e. `PriorityBlockingQueue`

* 支持优先级
* **自然顺序**（不是先进先出）
* 无界队列：当容量不够时会进行扩容
* `PriorityQueue`的线程安全版本，用到`ReentrantLock`，为空时take可能会阻塞

#### f. `SynchronousQueue`

* 容量为0
* 需要注意的是， `SynchronousQueue`的容量不是1而是0，因为 `SynchronousQueue`不需要去持有元素，它所做的就是直接传递；
* 效率很高
*  `SynchronousQueue`没有peek等函数，因为peek的含义是取出头结点，但是 `SynchronousQueue`的容量为0，所以连头结点都没有，也就没有peek方法。同理，没有iterate相关方法；
* 是一个极好的用来直接传递的并发数据结构；
*  `SynchronousQueue`是线程池`Executors.newCachedThreadPool()`使用的阻塞队列；

#### g. `DelayQueue`

* 延迟队列，根据延迟时间排序；
* 元素需要实现Delayed接口，规定排序规则；
* 无界队列；

### 5）非阻塞队列

* JUC中的非阻塞队列只有`ConcurrentLinkedQueue`这一种，顾名思义`ConcurrentLinkedQueue`是使用链表作为其数据结构的，使用CAS非阻塞算法来实现线程安全(不具备阻塞功能)，适合用在对性能要求较高的并发场景。用的相对比较少；
* 看源码的offer方法的CAS思想，内有`p.casNext()`，用了`UNSAFE.compareAndSwapObject()`;

### 6）如何选择适合自己的队列

* 边界
* 空间
* 吞入量：`LinkedBlockingQueue`一般会优于`ArrayBlockingQueue`，因为`LinkedBlockingQueue`中锁的粒度更细，有两把锁`takeLock`和`putLock`；



## 并发容器总结

* JUC包提供的容器，分为3类：Concurrent* 、CopyOnWrite* 、Blocking*；
* Concurrent* 的特点是大部分通过CAS实现并发，而CopyOnWrite* 则是通过复制一份原数据来实现的，Blocking* 通过AQS实现的；



# 九、控制并发流程

## 1. 什么是控制并发流程？

* 控制并发流程的工具类，作用就是帮助程序员更容易的让线程之间合作；
* 让线程之间相互配合，来满足业务逻辑；
* 比如让线程A等待线程B执行完毕后再执行等合作策略；

![1591352239433](课件\JUC资料\图片\控制并发流程工具类.png)

## 2. `CountDownLatch`倒计时门闩

* 倒数门闩
* 例子：购物拼团，等到人数满了再团购；过山车，人满发车；
* 流程：倒数结束之前，一直处于等待状态，直到倒计时结束了，此线程才继续工作；

### 主要方法

* `CountDownLatch(int count)`：仅有一个构造函数，参数count为需要倒数的数值；
* `await()`：调用`await()`方法的线程会被挂起，它会等待直到count值为0才继续执行；
* `countDown()`：将count值减1，直到为0时，等待的线程会被唤起；

![1591360607733](课件\JUC资料\图片\CountDownLatch主要方法执行.png)

### 两个典型用法

* 用法一：`flowcontrol.countdownlatch.CountDownLatchDemo1`，**一**个线程**等**待**多**个线程都执行完毕**，再继续自己的工作。

* 用法二：`flowcontrol.countdownlatch.CountDownLatchDemo2`，**多**个线程**等**待某**一**个线程的信号，同时开始执行。

  例如：多个运动员等待起跑信号；压测，需要事件同时执行，达到高并发的目的；

* 综合以上两种用法：`flowcontrol.countdownlatch.CountDownLatchDemo3`，多个运动员等待起跑信号，起跑后，工作人员在终点计时，等待最后一名运动员到达终点后，宣布本次比赛结束；

### 注意点

* 扩展用法：多个线程等待多个线程执行完成后，在同时执行；
* `CountDownLatch`是**不能够重用**的，如果需要重新计数，可以考虑使用`CyclicBarrier`或者创建新的`CountDownLatch`实例；



## 3. `Semaphore`信号量

* 用来**限制**或管理数量**有限的资源**的使用的情况；
* 作用：维护一个“许可证”的计数，线程可以“获取”许可证，那信号量剩余的许可证就减一， 线程也可以“释放”一个许可证，那信号量剩余的许可证就加一，当信号量所拥有的许可证数量为0，那么下一个还想要获取许可证的线程，就需要等待，直到有另外的线程释放了许可证；

![1591366329252](课件\JUC资料\图片\Semaphore信号量.png)

### 应用实例

![1591366719731](课件\JUC资料\图片\Semaphore应用实例.png)

### 用法

* 使用流程：
  1. 初始化`Semaphore`并指定许可证的数量；
  2. 在需要被现在的代码前加`acquire()`或者`acquireUniterruptibly()`方法；
  3. 在任务执行结束后，调用`release()`来释放许可证；
* 代码演示：`flowcontrol.semaphore.SemaphoreDemo`；
* 特殊用法：`acquire(n)`和`release(n)`，n为一次获取许可证的数量；
  * TaskA会调用**很消耗资源**的method1()，而TaskB调用的是不太消耗资源的method2()，假设一共有5个许可证。那么就可以要求TaskA获取5个许可证才能执行，而TaskB只需要获取到一个许可证就能执行，这样就避免了A和B同时运行的情况，根据自己的需求合理分配资源；
  * **注意点**：获取和释放的许可证数量必须一致，否则少释放的许可证一直在某个线程中，会导致许可证数量不够用，造成程序卡死；



### 主要方法

* `Semaphore(int premits, boolean fair)`：这里可以设置是否使用公平策略，如果传入true，那么`Semaphore`会把之前等待的线程放到FIFO的队列里，以便于当有了新的许可证，可以分发给之前等待最长时间的线程；
* `acquire()`：获取“许可证”时，可以响应中断；
* `acquireUninterruptibly()`：不可响应中断；
* `tryAcquire()`：看看现在有没有空闲的许可证，如果有就去获取，否则，去做别的事，过一会再来查看许可证的空闲情况；
* `tryAcquire(timeout)`：多了一个超时时间，在timeout时间内获取不到许可证，就去做别的事；
* `release()`：用完归还许可证

### 注意点

1. **获取**和**释放**的许可证数量必须**一致**，否则比如每次获取2个但是只释放1个甚至不放，随着时间的推移，到最后，许可证数量不够用，会导致程序卡死。（虽然信号量类并不对释放和获取的数量做规定，但是这是一种编程规范，否则容易出错）；
2. 注意在初始化`Semaphore`的时候设置公平性，一般设置为true会更合理；信号量尤其适用于速度慢的地方，容易造成任务堆积，如果为非公平，后面的任务可能会插队，而造成饥饿现象；
3. 并不是必须由获取许可证的线程来释放那个许可证，事实上，获取和释放许可证对线程并无要求，也许是**A获取了，然后由B释放**，只要逻辑合理即可；
4. 除了控制临界区最多同时有N个线程访问外，另一个作用是可以实现“条件等待”，例如线程1需要在线程2完成准备工作后才能开始工作，那么就线程1`aquire()`，而线程2完成任务后`release()`，这样就相当于轻量级的`CountDownLatch`；

## 4. `Condition`接口（条件对象）

### 作用

* 当线程1需要等待某个条件的时候，他就去执行`condition.await()`方法，一旦执行了`await()`方法，线程就会进入阻塞状态；
* 然后通常会有另外一个线程，假设是线程2，去执行对应的条件，直到这个条件达成的时候，线程2就会去执行`condition.signal()`方法，这时JVM就会从被阻塞的线程里找，找到那些等待该condition的线程，当线程1就会受到可执行信号的时候，他的线程状态就会变成Runnable可执行状态；![1591428820501](课件\JUC资料\图片\Condition执行流程.png)

* `signalAll()`和`signal()`区别
  * `signalAll()`会唤起所有的正在等待的线程；
  * `signal()`是公平的，只会唤起等待时间最长的线程；

### 代码演示

* 普通示例：`flowcontrol.condition.ConditionDemo1`；
* 用`Condition`实现生产者消费者模式：`flowcontrol.condition.ConditionDemo2`；

### 注意点

* 如果说Lock用来代替`synchronized`，那么`Condition`就是用来代替相对应的`Object.wait/notify`的，所以在用法和性质上，几乎都一样；
* await方法会自动释放持有的Lock锁，和`Object.wait`一样，不需要自己手动先释放锁；
* 调用await的时候，必须持有锁，否则会抛出异常，和`Object.wait`一样；



## 5. `CyclicBarrier`循环栅栏

* `CyclicBarrier`循环栅栏和`CountDownLatch`很类似，都能阻塞一组线程；
* 当有大量线程相互配合，分别计算不同任务，并且需要最后统一汇总的时候，就可以使用`CyclicBarrier`。`CyclicBarrier`可以构造一个集结点，当某一个线程执行完毕，它就会到集结点等待，直到所有线程都到了集结点，那么该栅栏就被撤销，所有线程在统一出发，继续执行剩下的任务；
* 例子：3个人约定明天中午在学习碰面，都到齐后，一起讨论下学期的计划；
* 代码演示：`flowcontrol.cyclicBarrier.CyclicBarrierDemo`；

* `CyclicBarrier`循环栅栏和`CountDownLatch`的**区别**
  1. 作用不同：`CyclicBarrier`要等固定数量的线程都到达了栅栏位置才能继续执行，而`CountDownLatch`只需等待数字到0，也就是说，**`CountDownLatch`用于事件，`CyclicBarrier`用于线程**；
  2. 可重用性不同：`CountDownLatch`在倒数到0并触发门闩打开后，就不能再次使用了，除非新建新的实例；而`CyclicBarrier`可以重复使用；
  3. `CyclicBarrier`在can构造函数中传入了一个满足条件便能立即执行的Runnable；



# 十、 AQS(并发灵魂)

## 1. 学习AQS的思路

* 学习AQS的目的主要是想理解原理、提高技术，以及应对面试；
* 先从应用层面理解为什么需要如何使用它，然后再看看java代码的设计者是如何使用它，并了解它的应用场景；
* 最后再去分析它的结构；

## 2. 为什么需要AQS？

* 锁和协作类有**共同点**：闸门
  * `ReentrantLock`和`Sempaphore`
    * 都只允许一定量的线程通过，前者用`lock()`，后者用`acquire()`;
    * 都可以尝试去获取锁或者“许可证”，前者用`tryLock()`，后者用`tryAcquire()`;
  * 事实上，不仅是`ReentrantLock`和`Sempaphore`，包括`CountDownLatch`、`ReentrantReadWriteLock`都有这样**类似的“协作”**(或者叫“同步”)功能，其实，它们底层都用了一个**共同的基类，就是AQS**

* 因为上面的那些协作类，它们有很多工作都是类似的，所有可以提取一个工具类，对于JUC中的一些类就可以屏蔽很多细节，只关注自己的“业务逻辑”就可以了；

* `Semaphore`、`ReentrantLock`和`CountDownLatch`内部有一个Sync类，它继承自AQS；

### AQS的比喻

* 比喻：群面、单面
  * `Semaphore`：一个人面试完了后，后一个人才能进来继续面试；
  * `CountDownLatch`：群面，等待10人到齐；
* 安排面试者就坐、叫号、先来后到等HR的工作就是AQS做的工作，线程就好比面试者；
* 面试官不会去关心两个面试者是不是号码相同冲突了，也不想去管面试者需要一个地方坐着休息，这些都交给HR去做；
* `Semaphore`、`ReentrantLock`和`CountDownLatch`这些同步工具类，要做的就只是自己的“要人规则”。比如：“出一个，进一个”，“凑齐10人，一起面试”；剩下的**招呼面试者的活就交给AQS来做**；

### 如果没有AQS

* 就需要每个协作工具自己实现：
  * 同步状态的原子性管理
  * 线程的阻塞与接触阻塞
  * 队列的管理：线程进来进入阻塞状态，需要排队
* 在并发场景下，自己正确且高效实现这些内容，是相当有难度的，所以我们用AQS来帮我们把这些脏活累活搞定，自己只关注业务逻辑就够了；



## 3. AQS的作用

* AQS是一个用于构建锁、同步器、协作工具类的工具类(框架)。它解决了许多细节上的问题，比如等待线程用先进先出的队列操作，以及制定一些标准来判断线程是否应该等待，还有处理多个地方的竞争问题、降低上下文开销、提高吞吐量；
* 一句话总结：有了AQS后，构建线程协作类就容易多了；

## 4. AQS的重要性、地位

* `AbstractQueuedSynchronizer`是`Doug Lea`写的，从JDK1.5加入的一个**基于FIFO等待队列实现的一个用于实现同步器的基础框架**，其实现类有以下这些：

![1593599277037](课件\JUC资料\图片\AQS的实现类.png)



## 5. AQS内部原理解析

* AQS最核心的三要素：
  * **state**
  * 控制线程抢锁和配合的FIFO队列
  * 期望协作工具类去实现的**获取/释放**等重要方法

### 1) state状态

* 这里的state的具体含义，会根据具体实现类的不同而不同：
  * 在`Semaphore`里，它表示**剩余的许可证的数量**；
  * 在`CountDownLatch`里，它表示**还需要倒数的数量**；
  * 在`ReentrantLock`中，它表示锁的占有情况，包括**可重入计数**，当state为0的时候，标识该Lock不被任何线程所占有；
* state是`volatile`修饰的，会被并发地修改，所以所有修改state的方法都需要保证线程安全，比如`getState`、`setState`以及`compareAndSetState`操作来读取和更新这个状态。这些方法都依赖于j.u.c.atomic包的支持；

### 2）控制线程抢锁和配合的FIFO队列

* 这个队列用来**存放“等待的线程”**，AQS就是“排队管理器”，当多个线程争用同一把锁时，必须有排队机制将那些没能拿到锁的线程串在一起。当锁被释放时，锁管理器就会挑选一个合适的线程来占有这个刚刚释放的锁；
* 实现：AQS会维护一个等待的线程队列，把**线程都放到这个队列里**；
* 这是一个双向形式的队列；

### 3）期望协作工具类去实现的获取/释放等重要方法

* 这里的获取和释放方法，是利用AQS的协作工具类里最重要的方法，是由协作类自己去实现的，并且**含义各不相同**；

#### 1> 获取方法

* 获取操作会依赖state变量，经常会阻塞（比如获取不到锁的时候）
* 在`ReentrantLock`中state不为0时，表示有线程持有锁，其他线程会被阻塞；
* 在`Semaphore`中，获取就是`acquire()`方法，作用就是**获取一个许可证**；
* 而在`CountDownLatch`里，获取就是`await()`方法，作用就是**“等待，直到倒数结束”**；

#### 2> 释放方法

* 释放操作不会阻塞
* 在`Semaphore`中，释放就是`release()`方法，作用是**释放一个许可证**；
* 在`CountDownLatch`里，释放就是`countDown()`方法，作用是**“倒数1个数”**；

#### 3> 需要重写`tryAcquire()`和`tryRelease()`等方法



## 6. 应用实例、源码解析

### 1）AQS的用法

* 第一步：写一个类，想好协作的逻辑，实现**获取/释放方法**；
* 第二步：内部写一个**Sync类继承`AbstractQueuedSynchronizer`**；
* 第三步：根据是否独占来**重写`tryAcquire/tryRelease`或`tryAcquireShared(int acquires)/tryReleaseShared(int releases)`等方法**，在之前写的获取/释放方法中调用AQS的`acquire/release`或者Shared方法；

### 2）AQS在`CountDownLatch`的应用

* 内部类Sync继承AQS
* 构造函数

~~~java
== CountDownLatch.java ==
	public CountDownLatch(int count) {
        if (count < 0) throw new IllegalArgumentException("count < 0");
        this.sync = new Sync(count);
    }
	Sync(int count) {
        setState(count);
    }

== AbstractQueuedSynchronizer.java ==
	protected final void setState(int newState) {
        state = newState;
    }
~~~

* `getCount`

~~~java
== CountDownLatch.java ==
	public long getCount() {
        return sync.getCount();
    }
    int getCount() {
        return getState();
    }

== AbstractQueuedSynchronizer.java ==
    protected final int getState() {
        return state;
    }
~~~

* `countDown`

~~~java
== CountDownLatch.java ==   
	public void countDown() {
        sync.releaseShared(1);
    }


    protected boolean tryReleaseShared(int releases) {
        // Decrement count; signal when transition to zero
        for (;;) {
            int c = getState();
            if (c == 0)
                return false;
            int nextc = c-1;
            if (compareAndSetState(c, nextc))
                return nextc == 0;
        }
    }

== AbstractQueuedSynchronizer.java ==
    public final boolean releaseShared(int arg) {
        if (tryReleaseShared(arg)) {
            // 把之前等待的线程全部唤醒
            doReleaseShared();
            return true;
        }
        return false;
    }

~~~



* `await`

~~~java
== CountDownLatch.java ==   
    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }
    
== AbstractQueuedSynchronizer.java ==
    public final void acquireSharedInterruptibly(int arg)
            throws InterruptedException {
        if (Thread.interrupted())
            throw new InterruptedException();
        if (tryAcquireShared(arg) < 0)
            // 线程进入等待队列，并且被阻塞
            doAcquireSharedInterruptibly(arg);
    }

== CountDownLatch.java ==   
	protected int tryAcquireShared(int acquires) {
        return (getState() == 0) ? 1 : -1;
    }


=======================================================================
    
== CountDownLatch.java ==   
	public boolean await(long timeout, TimeUnit unit)
        throws InterruptedException {
        return sync.tryAcquireSharedNanos(1, unit.toNanos(timeout));
    }
~~~

#### AQS在`CountDownLatch`中的总结

* 调用`CountDownLatch`的`await`方法时，便会尝试获取“共享锁”，不过**一开始是获取不到锁的，于是线程被阻塞**；
* 而“共享锁”可获取到的条件，就是**“锁计数器”**的值为0；
* 而“锁计数器”的初始值为count，每当一个线程调用该`CountDownLatch`对象的`countDown()`方法时，才将“锁计数器”减1；
* count个线程调用`countDown()`之后，“锁计数器”才为0，而前面提到的等待获取共享锁的线程才能继续运行；



### 3）AQS在`Semaphore`的应用

* 在`Semaphore`中，**state**表示许可证的剩余数量；
* 看`tryAcquire`方法，判断`nonfairTryAcquireShared(acquires)`大于等于0的话，代表成功
* 这里会先检查剩余许可证数量够不够这次需要的，用减法来计算，如果直接不够，那就返回负数，表示失败，如果够了，就用自旋加`compareAndSetState`来改变state状态，直到改变成功就返回正数；或者是期间如果被其他人修改了导致剩余数量不够了，会返回负数代表获取失败；

~~~java
== Semaphore.java ==
	public void acquire(int permits) throws InterruptedException {
        if (permits < 0) throw new IllegalArgumentException();
        sync.acquireSharedInterruptibly(permits);
    }

	protected int tryAcquireShared(int acquires) {
        return nonfairTryAcquireShared(acquires);
    }

    final int nonfairTryAcquireShared(int acquires) {
        for (;;) {
            int available = getState();
            int remaining = available - acquires;
            if (remaining < 0 ||
                compareAndSetState(available, remaining))
                return remaining;
        }
    }

== AbstractQueuedSynchronizer.java ==
	public final void acquireSharedInterruptibly(int arg)
            throws InterruptedException {
        if (Thread.interrupted())
            throw new InterruptedException();
        if (tryAcquireShared(arg) < 0)
            doAcquireSharedInterruptibly(arg);
    }
~~~



### 4）AQS在`ReentrantLock`的应用

* 解锁

~~~java
== ReentrantLock.java ==
	public void unlock() {
        sync.release(1);
    }

    protected final boolean tryRelease(int releases) {
        // 这里的state表示重入的次数
        int c = getState() - releases;
        // 判断当前线程是不是持有锁的线程
        if (Thread.currentThread() != getExclusiveOwnerThread())
            throw new IllegalMonitorStateException();
        boolean free = false;
        if (c == 0) {
            free = true;
            setExclusiveOwnerThread(null);
        }
        setState(c);
        return free;
    }

== AbstractQueuedSynchronizer.java ==
    public final boolean release(int arg) {
        if (tryRelease(arg)) {
            Node h = head;
            if (h != null && h.waitStatus != 0)
                // 将后面的节点唤醒
                unparkSuccessor(h);
            return true;
        }
        return false;
    }


~~~

* 加锁

~~~java
== ReentrantLock.java ==
	public void lock() {
        sync.lock();
    }

 	final void lock() {
        if (compareAndSetState(0, 1))
            setExclusiveOwnerThread(Thread.currentThread());
        else
            acquire(1);
    }

== AbstractQueuedSynchronizer.java ==
	public final void acquire(int arg) {
        if (!tryAcquire(arg) &&
            acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
            selfInterrupt();
    }
~~~



## 7. 利用AQS实现一个自己的Latch门闩





# 十一、Future和Callable

## 1. Runnable的缺陷

1. 不能返回一个**返回值**

2. 不能抛出checked Exception(代码演示)

   `future.RunnableCantThrowException`，只能用try/catch；

* `Runnable`为什么设计成这样？

  因为大部分执行Runnable中的run方法的代码不是自己编写的，从而抛出的异常也不是自己来处理，所以正确的方法就是在run方法内捕获异常；

## 2. Callable接口

* 类似于`Runnable`，被其他线程执行的任务

* 实现call方法

* 有返回值

* 源码

  ~~~java
  public interface Callable<V> {
      /**
       * Computes a result, or throws an exception if unable to do so.
       *
       * @return computed result
       * @throws Exception if unable to compute a result
       */
      V call() throws Exception;
  }
  ~~~

  

## 3. Future类

### 1）作用

​        遇到耗时的方法不可能一直等待方法执行完毕返回结果，因此用子线程去执行，想要取消或者获取结果的操作都可以用Future类

### 2）`Callable`和`Future`的关系

* 可以用**`Future.get`**来获取`Callable`接口返回的执行结果，还可以通过**`Future.isDone()`**来判断任务是否已经执行完了，以及取消这个任务，限时获取任务的结果等；
* 在`call()`未执行完毕之前，调用`get()`的线程(假定此时是主线程)会被**阻塞**，知道`call()`方法返回了结果后，此时`future.get()`才会得到该结果，然后主线程才会切换到runnable状态；
* 所以`Future`是一个存储器，它存储了`call()`这个任务的结果，而这个任务的执行时间是无法提前确定的，因为这完全取决于`call()`方法执行的情况；

### 3）主要方法（5个）

#### 1> `get()`：获取结果

get方法的行为取决于`Callable`任务的状态，只有以下这5中情况：

1. 任务正常完成：get方法会立刻返回结果；
2. 任务**尚未完成**(任务还没开始或进行中)：get将**阻塞**并直到任务完成；
3. 任务执行过程中抛出Exception：get方法会抛出`ExecutionException`：这里的抛出异常，是`call()`执行时产生的那个异常，看到这个异常类型是`java.util.concurrent.ExecutionException`。不论`call()`执行时抛出的异常类型是什么，最后get方法抛出的异常类型都是`ExecutionException`；
4. 任务被取消：get方法抛出`CancellationException`；
5. 任务超时：get方法有一个重载方法，是传入一个延迟时间的，如果时间到了还没有获得结果，get方法就会抛出`TimeoutException`；

#### 2> `get(long timeout, TimeUnit unit)`

* 超时的需求很常见，比如：携程订票在规定时间内请求多个航空公司的接口；
* 如果`call()`在规定时间内完成了任务，那么就会正常获取到返回值；而如果在指定时间内没有计算出结果，那么就会抛出`TimeoutException`；
* 超时不会去，**任务需取消**；

#### 3> `cancel(boolean b)`方法

- 取消任务的执行；

1. 如果这个任务**还没有开始执行**，那么这种情况最简单，任务会被正常的取消，**未来也不会被执行**，方法返回true；
2. 如果任务**已完成**，或者**已取消**，那么`cancel()`方法会执行失败，方法返回false；
3. 如果这个任务**已经开始执行**，那么这个取消方法将不会直接取消该任务，而是会根据自己填的参数`mayInterruptIfRunning`做判断：如果参数为false，任务则不会收到中断信号，会继续执行下去；为true时，任务会收到中断信号，有人想要停止这个任务；

* `cancel(true)`适用于：
  1. 任务能够处理interrupt;
* `cancel(false)`仅用于避免启动尚未启动的任务，适用于：
  1. 未能处理interrupt的任务；
  2. 不清楚任务是否支持取消；
  3. 需要等待已经开始的任务执行完成；

#### 4> `isDone()`：判断线程是否执行完毕

* 线程执行失败、中断等会返回true，该判断只能知道是否执行完毕，不管是否执行成功；

#### 5> `isCancel()`：判断任务是否被取消



## 4. 用法

### 1）线程池的submit方法返回Future对象，6个代码演示

![1595913541636](课件\JUC资料\图片\线程池的submit方法返回Future对象.png)

* 首先，我们要给线程池提交我们的任务，提交时线程池会立刻返回一个空的Future容器。当线程的任务一旦执行完毕，也就是可以获取结果的时候，线程池便会把该结果填入到之前给我们的那个Future中去(而不是创建新的Future)，此时便可以从该Future中获得任务执行的结果；
* **注意：**`submit()`中捕获的异常并不会直接抛出，而是返回给`FutureTask.get()`，如果没有获取执行子线程的结果，出现的**异常就会被吞掉**；
* 代码演示
  * get基本用法`future.OneFuture`;
  * `Callable`的`Lambda`表达式形式`future.OneFutureLambda`;
  * 多个任务，用Future数组来获取结果`future.MultiFutures`;
  * 任务执行过程中抛出Exception和isDone展示`future.GetException`;
  * 获取任务超时`future.Timeout`;
  * 任务取消`future.Timeout`;

### 2）用`FutureTask`来创建`Future`

* 用`FutureTask`来获取`Future`和任务的结果；
* `FutureTask`是一种包装器，可以把`Callable`转化成`Future`和`Runnable`，它**同时实现二者的接口**，所以它既可以作为`Runnable`被线程执行，又可以作为`Future`得到`Callable`的返回值；

![1596116108529](课件\JUC资料\图片\FutureTask继承关系.png)

* 把`Callable`实例当做参数，生成`FutureTask`的对象，然后把这个对象当作一个`Runnable`对象，用线程池或另起线程去执行这个`Runnable`对象，最后通过`FutureTask`获取刚才执行的结果；
* 代码演示：`future.FutureTaskDemo`;

## 5. `Future`的注意点

1. 当for循环**批量获取**future的结果时，容易发生一部分线程很慢的情况，get方法调用时应使用timeout限制，或者用**`CompletableFuture`**：某个子任务先完成就可以先获取该任务结果；
   * 如下图所示，后面的任务执行速度快，但是在for循环中，`Future.get()`最先获取task1执行结果，由于task1还未完成，就会被阻塞，直到task1完成，才能继续获取后面的任务结果；

![1596117003016](课件\JUC资料\图片\FutureTask批量获取future结果注意点.png)

2. `Future`的**生命周期不能后退**；

   生命周期只能前进，不能后退。就和线程池的生命周期一样，一旦完全完成了任务，它就永久停在了“已完成”的状态，不能重头再来；

# 实战：高性能缓存

* 从0开始迭代，手把手一步步设计并实现
* 缓存是实际生产中非常常用的工具，用了缓存以后，可以避免重复计算，提高吞吐量
* 最初级的缓存可以用一个Map来实现，不过一个功能完备、性能强劲的缓存，需要考虑的点很多，从最简单的`HashMap`入手，一步步提高缓存的性能

## 实战

### 优化

1. 最简单的缓存——`HashMap`：
   * 代码：`cache.SimplestCache`

   * 缺点：不具备线程安全；
2. 简单的线程安全缓存，在存数据的地方使用`synchronized关键字`：

   * 代码：`cache.SimplestSyncCache`

   * 缺点：
     1. 性能差，就是因为数据量大，并发高才考虑缓存；
     2. 代码复用性差，不应当侵入具体业务代码里，不应当在每个类里写一遍，而且一旦缓存方法需要修改，那么每一个相应的缓存方法都要去修改，违反**开闭原则**；

**tip：**给`HashMap`加`final`关键字，增强安全性

​		属性被声明为`final`后，该变量只能被赋值一次，且一旦被赋值，final的变量就不能再被改变；`HashMap`内容会被改变，但其**引用**不会被改变



3. 代码重构——用**装饰者模式**：

   假设`ExpensiveFunction`类是耗时计算的实现类，实现了`Computable`接口，但是其**本身不具备缓存功能**，也不需要考虑缓存的事情；

   * 代码：`cache.DecratorCache`；

   * 缺点：性能差，多线程同时计算时，需要等待一个线程计算完毕，严重时，性能甚至比不用缓存更差；

4. 性能优化——缩小锁的粒度：
   
* 虽然把存入缓存的语句加上`synchronized`关键字，但并不意味着就是线程安全的，还需要考虑到**同时读写**等情况；
  
5. 用并发集合做缓存——`ConcurrentHashMap`：

   * 代码：`cache.ConcurrentCache`；

   * 缺点：在计算完成前，另一个要求计算相同值的请求到来，会导致计算两遍，这和缓存想避免多次计算的初衷恰恰相反；

   ![1598842359311](课件\JUC资料\图片\并发集合作缓存的缺陷.png)

6. 避免重复计算——`Future`和`Callable`：

   * 代码：`cache.FutureCache`；
   * 缺点：依然存在重复计算的可能；

   ![1598846121551](课件\JUC资料\图片\Future重复计算的原因.png)

7. 完全避免重复计算——加上原子操作**`putIfAbsent`**：
   
   * 代码：`cache.AtomicFutureCache`；

### 扩展

1. 正确的异常处理逻辑——各司其职

   * 代码：`cache.AtomicFutureCache`；

   * 其中的3中异常之所以用不同的catch块捕获，是因为它们的处理逻辑是不同的：
     * `CancellationException`和` InterruptedException`是人为取消的，那么就应该立即终止任务；
     * 但是如果是`ExecutionException`,且明确知道多试几次就可以得到答案，那么异常处理逻辑应该是重试，尝试多次知道获取正确的结果；
     * 在这里，加上`while(true)`来保证计算出错不会影响其他逻辑，然后如果是计算错误，就进入下一个循环，重新计算，直到计算成功；如果是人为取消，那么就抛出异常然后结束运行；

2. 考虑“缓存污染”问题

   * 代码：`cache.AtomicFutureCache`；

   * 计算失败则移除`Future`，增加健壮性。因为`putIfAbsent`只有在空值的时候才会放值，否则不会重复往里面添加，因此如果不去清除之前出错的值，就一直存在下去

3. 缓存过期功能
   * 为每个结果指定**过期时间**，并定期扫描过期元素；

