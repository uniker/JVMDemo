package JavaDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.SleepUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WaitNotify {
    static Logger Logger = LoggerFactory.getLogger(WaitNotify.class);

    static boolean flag = true;
    static Object lock = new Object();

    static String time(){
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        return time;
    }
    // wait thread
    static class Wait implements Runnable{
        @Override
        public void run() {
            // 加锁，持有lock的Monitor
            synchronized (lock){
                // 当条件不满足时,继续wait,同时释放了lock的锁
                while (flag){
                    try{
                        Logger.info("{} flag is true. wait @ {}", Thread.currentThread(), time());
                        lock.wait();
                    }catch (InterruptedException e){
                        Logger.error("ERROR");
                    }
                }

                Logger.info("{} flag is false. running @ {}", Thread.currentThread(), time());
            }
        }
    }

    //
    static class Notify implements Runnable{
        @Override
        public void run() {
            // 加锁，持有lock的Monitor
            synchronized (lock){
                // 获取lock的锁，然后进行通知，通知时不会释放lock的锁
                // 直到当前线程释放了lock后，WaitingThread才能从wait方法中返回
                Logger.info("{} hold lock. notify @ {}" , Thread.currentThread(), time());
                lock.notifyAll();
                flag = false;
                SleepUtils.second(5);
            }

//            Logger.info("*******");

            // 再次加锁
            synchronized (lock){
                Logger.info("{} hold lock again. sleep @ {}" , Thread.currentThread(), time());
                SleepUtils.second(5);
            }

        }
    }

    public static void main(String[] args) {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();

        SleepUtils.second(1);

        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
    }
}
