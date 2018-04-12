package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    /**
     *
     * @return
     */
    public static String currentTime(){
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        return time;
    }
}
