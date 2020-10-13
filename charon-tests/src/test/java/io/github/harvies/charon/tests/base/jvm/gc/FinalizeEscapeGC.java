package io.github.harvies.charon.tests.base.jvm.gc;

/**
 * 通过finalize()方法在垃圾回收时自救
 * 一个对象finalize()方法最多只会被系统自动调用一次
 *
 * @author harvies
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.err.println("yes, i am still alive :)");
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void finalize() throws Throwable {
        /**
         * java8中此方法已被标记为弃用
         */
        super.finalize();
        System.err.println("finalize method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();
        /**
         * 对象第一次成功拯救自己
         */
        SAVE_HOOK = null;
        System.gc();
        //因为finalize方法优先级很低，所以暂停0.5秒以等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.err.println("no, i am dead :(");
        }
        //下面这段代码和上面的完全相同，但是自救却失败了
        SAVE_HOOK = null;
        System.gc();
        //因为finalize方法优先级很低，所以暂停0.5秒以等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.err.println("no, i am dead :(");
        }

    }
}
