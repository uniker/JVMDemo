package JVMDemo;

public class MemMalloc {
    private static final int _1MB = 1024 * 1024;

    /**
     * VMArgs: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * @param args
     */
    public static void main(String[] args) {
        testAllocation();
    }


    public static void testAllocation(){
        byte[] alloc1, alloc2, alloc3, alloc4;
        alloc1 = new byte[2*_1MB];
        alloc2 = new byte[2*_1MB];
        alloc3 = new byte[2*_1MB];
        alloc4 = new byte[4*_1MB];
    }
}
