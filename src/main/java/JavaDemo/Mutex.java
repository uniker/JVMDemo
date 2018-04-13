package JavaDemo;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * non-reentrant mutual exclusion lock class.
 *  0 标识未锁定状态
 *  1 标识锁定状态
 */
public class Mutex implements Lock, Serializable {
    private static final int STATE_UNLOCK = 0x00;
    private static final int STATE_LOCK = 0x01;

    // 静态内部类,自定义同步器
    private static class Sync extends AbstractQueuedSynchronizer {
        // 是否处于占用状态
        @Override
        protected boolean isHeldExclusively() {
            return getState() == STATE_LOCK;
        }

        // 当状态为0的时候获取锁
        @Override
        protected boolean tryAcquire(int acquires) {
            if (compareAndSetState(STATE_UNLOCK, STATE_LOCK)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }

            return false;
        }

        @Override
        protected boolean tryRelease(int realeases) {
            if (getState() == STATE_UNLOCK) {
                throw new IllegalMonitorStateException();
            }

            setExclusiveOwnerThread(null);
            setState(STATE_UNLOCK);
            return true;
        }

        // 返回一个Condition,每个condition都包含了一个condition队列
        Condition newCondition() {
            return new ConditionObject();
        }
    }

    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(STATE_LOCK);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(STATE_LOCK);
    }

    @Override
    public void unlock() {
        sync.release(STATE_LOCK);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(STATE_LOCK);
    }


    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(STATE_LOCK, unit.toNanos(time));
    }
}
