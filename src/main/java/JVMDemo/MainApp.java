package JVMDemo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * A Camel Application
 */
public class MainApp {
    public static Logger logger = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) {
        logger.info("+++");

        /**
         *
         */
//         simulateHeapOOM();


        /**
         *
         */
//        MainApp app = new MainApp();
//        try{
//            app.JavaVMStackSOF();
//        }catch (Throwable e){
//            logger.info("stackLength={}", app.stackLength);
//            throw e;
//        }

        /**
         *
         */
        simulateGcTest();

        logger.info("---");
    }

    /**
     * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
     */
    private static void simulateHeapOOM(){
        List<MainApp> result = new ArrayList<>();

        while (true){
            result.add(new MainApp());
        }
    }


    private static void simulateGcTest(){
        System.gc();
    }

    /**
     * VMArgs: -Xss128k
     */
    private int stackLength = 1;
    private void JavaVMStackSOF(){
        stackLength++;
        JavaVMStackSOF();
    }
}

