package SimDbConnPool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPoolDemo {
    static ConnectionPool pool = new ConnectionPool(10);

    // 保证所有ConnectionRunner 能够同时开始
    static CountDownLatch start = new CountDownLatch(1);

    // main线程将会等待所有ConnectionRunner结束后才能继续执行
    static CountDownLatch end;

    public static void main(String[] args) throws Exception {
        // 线程数量，可以修改线程数量进行观察
        int threadCount = 10;
        end = new CountDownLatch(threadCount);


    }


    static class ConnectionRunner implements Runnable{
        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public ConnectionRunner(int c, AtomicInteger g, AtomicInteger notG){
            count = c;
            got = g;
            notGot = notG;
        }

        @Override
        public void run() {

        }
    }
}
