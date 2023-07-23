## 定时器

### 1.1 timer

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTask(), 0L, 2000);

    }

    private static class MyTask extends TimerTask {
        @Override
        public void run() {
            System.out.println(new Date());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
```

### 1.2 存在问题

- 单线程执行可能造成任务的堆积和丢失



## 二、线程池执行

### 2.1 线程池执行相关代码

```java
public class ThreadPoolSchedule {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        scheduledExecutorService.scheduleAtFixedRate(()->{
            try {
                System.out.println(new Date());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },0,1, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(()->{
            try {
                System.out.println(new Date());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },0,1, TimeUnit.SECONDS);
    }
}

```

### 2.2 存在问题

- 多个任务之间不会收到影响

- 相同任务执行，如果有一次执行超时，则会受到影响
