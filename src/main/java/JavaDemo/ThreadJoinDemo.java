package JavaDemo;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 */
public class ThreadJoinDemo {
  private static final Logger LOGGER = LoggerFactory.getLogger(ThreadJoinDemo.class);

  public static void main(String[] args) throws Exception{
    Thread preThread = Thread.currentThread();

    for(int i=0; i<10; i++){
      Thread thread = new Thread(new Domino(preThread), String.valueOf(i));
      thread.start();

      preThread = thread;
    }

    LOGGER.info("sleep start at {}");
    TimeUnit.SECONDS.sleep(10);
    LOGGER.info("main thread {} terminate.", Thread.currentThread().getName());
  }

  //
  static class Domino implements Runnable{
    private Thread thread;

    public Domino(Thread thread){
      this.thread = thread;
    }

    @Override
    public void run() {
      try{
        thread.join();
      }catch (Exception ex){
        LOGGER.error("Exception happend. ex={}", ex);
      }

      LOGGER.info("{} terminate.", Thread.currentThread().getName());
    }
  }

}
