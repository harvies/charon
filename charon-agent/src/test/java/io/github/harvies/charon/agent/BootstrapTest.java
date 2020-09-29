package io.github.harvies.charon.agent;

import org.junit.jupiter.api.Test;

import static io.github.harvies.charon.agent.ClassA.test1;
import static io.github.harvies.charon.agent.ClassB.test2;

public class BootstrapTest {
    @Test
    public void test() {
        System.out.println("starting");
        test1();
        test2();
        System.out.println("started");
    }
}
