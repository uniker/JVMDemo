package JavaDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.SleepUtils;
import utils.Utils;

import java.util.concurrent.TimeUnit;

public class InterruptedDemo {
    //
    static class SleepRunner implements Runnable{
        @Override
        public void run() {
            while(true){
                LOGGER.info("SleepThread sleep start at:{}", Utils.currentTime());
                SleepUtils.second(10);
                LOGGER.info("SleepThread sleep end at:{}", Utils.currentTime());
            }
        }
    }

    //
    static class BusyRunner implements Runnable{
        @Override
        public void run() {
            while(true){
                //
            }
        }
    }

    /**
     *
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(InterruptedDemo.class);
    public static void main(String[] args) throws Exception {
        //
        Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
        sleepThread.setDaemon(true);

        //
        Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
        busyThread.setDaemon(true);

        //
        sleepThread.start();
        busyThread.start();

        LOGGER.info("main thread sleep start @:{}." , Utils.currentTime());
        TimeUnit.SECONDS.sleep(5);
        LOGGER.info("main thread sleep end @:{}." , Utils.currentTime());
        sleepThread.interrupt();
        busyThread.interrupt();

        //
        LOGGER.info("SleepThread interrupted is:{}", sleepThread.isInterrupted());
        LOGGER.info("BusyThread interrupted is:{}", busyThread.isInterrupted());

        TimeUnit.SECONDS.sleep(2);
    }
}
