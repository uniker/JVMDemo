package JVMDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FinalizeEscapeGC {
    private static Logger LOGGER  = LoggerFactory.getLogger(FinalizeEscapeGC.class);

    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive(){
        //LOGGER.info("Yes, i'm still alive:)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();

        //LOGGER.info("finalize() called!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Throwable {
        SAVE_HOOK = new FinalizeEscapeGC();

        //
        SAVE_HOOK = null;
        System.gc();

        //
        Thread.sleep(500);
        if(SAVE_HOOK!=null){
            SAVE_HOOK.isAlive();
        }else{
            //LOGGER.info("No, i'm dead:(");
        }


        /**
         *
         */
        //
        SAVE_HOOK = null;
        System.gc();

        //
        Thread.sleep(500);
        if(SAVE_HOOK!=null){
            SAVE_HOOK.isAlive();
        }else{
            //LOGGER.info("No, i'm dead:(");
        }
    }
}
