package JavaDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class MultiThread {
    private static final Logger LOGGER = LoggerFactory.getLogger(MultiThread.class);

    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        // [1] main                     // main线程，用户程序入口
        // [2] Reference Handler        // 清除reference的线程
        // [3] Finalizer                // 调用对象finalize方法的线程
        // [4] Signal Dispatcher        // 分发处理发送给JVM信号的线程
        // [5] Attach Listener
        // [6] Monitor Ctrl-Break
        for(ThreadInfo threadInfo:threadInfos){
            LOGGER.info("[{}] {}", threadInfo.getThreadId(), threadInfo.getThreadName());
        }
    }
}
