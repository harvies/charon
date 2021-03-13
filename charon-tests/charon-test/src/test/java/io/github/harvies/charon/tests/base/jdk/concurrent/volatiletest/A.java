package io.github.harvies.charon.tests.base.jdk.concurrent.volatiletest;

public class A {
    public static void main(String[] args) {
        int i = 0;
        int a = ++i;
        System.err.println(a); //1
        System.err.println(i); //1
        int j = 0;
        int b = j++;
        System.err.println(b); //0
        System.err.println(j); //1

        for (int k = 0; k < 1; k++) {
            System.err.println("k"); //k
        }

        for (int k = 0; k < 1; ++k) {
            System.err.println("kk"); //
        }
    }
}
