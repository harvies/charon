package io.github.harvies.eris.base.jdk.clazz;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class ClassTest {
    @Test
    public void testClassNameAArr() {
        ClassNameA[] arr = {};
        //获取jvm里的类名
        String name = arr.getClass().getName();
        //Canonical:规范,获取更容易理解类名，和getName的主要区别在于数组上
        String canonicalName = arr.getClass().getCanonicalName();
        //获取类的简单名称
        String simpleName = arr.getClass().getSimpleName();
        //获取类型名称
        String typeName = arr.getClass().getTypeName();

        log.info("name:{}", name);
        log.info("canonicalName:{}", canonicalName);
        log.info("simpleName:{}", simpleName);
        log.info("typeName:{}", typeName);

        /**
         * 17:42:02.208 [main][i.g.h.eris.base.jdk.clazz.ClassTest] INFO  20 - name:[Lio.github.harvies.eris.base.jdk.clazz.ClassNameA;
         * 17:42:02.212 [main][i.g.h.eris.base.jdk.clazz.ClassTest] INFO  21 - canonicalName:io.github.harvies.eris.base.jdk.clazz.ClassNameA[]
         * 17:42:02.212 [main][i.g.h.eris.base.jdk.clazz.ClassTest] INFO  22 - simpleName:ClassNameA[]
         * 17:42:02.212 [main][i.g.h.eris.base.jdk.clazz.ClassTest] INFO  23 - typeName:io.github.harvies.eris.base.jdk.clazz.ClassNameA[]
         */
    }
}
