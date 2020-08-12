package io.github.harvies.eris.base.jdk.lang;

import org.junit.Test;

public class CharTest {

    /**
     * 9. char 型变量中能不能存储一个中文汉字，为什么？（2017-11-16-wl）
     * char 类型可以存储一个中文汉字，因为 Java 中使用的编码是 Unicode（不选择任何特定的编码，直接
     * 使用字符在字符集中的编号，这是统一的唯一方法），一个 char 类型占 2 个字节（16 比特），所以放一个中
     * 文是没问题的。
     * 补充：使用 Unicode 意味着字符在 JVM 内部和外部有不同的表现形式，在 JVM 内部都是 Unicode，当这个字符被
     * 从 JVM 内部转移到外部时（例如存入文件系统中），需要进行编码转换。所以 Java 中有字节流和字符流，以及在字
     * 符流和字节流之间进行转换的转换流，如 InputStreamReader 和 OutputStreamReader，这两个类是字节流和字符
     * 流之间的适配器类，承担了编码转换的任务；对于 C 程序员来说，要完成这样的编码转换恐怕要依赖于 union（联合
     * 体/共用体）共享内存的特征来实现了。
     */

    @Test
    public void test() {
        char c = '中';
        System.err.println(c);
    }
}
