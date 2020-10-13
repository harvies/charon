package io.github.harvies.charon.tests.base.bytecode;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class Test {
    public static void main(String[] args) throws Exception {
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("io.github.harvies.eris.base.bytecode.Hello");
        CtMethod m = cc.getDeclaredMethod("say");
        m.insertBefore("{ System.out.println(\"Hello.say():\"); }");
        Class<Hello> c = (Class<Hello>) cc.toClass();
        Hello h = c.getDeclaredConstructor().newInstance();
        h.say();
    }
}