package io.github.harvies.charon.util.load.test;

/**
 * @author harvies
 */
public class String {
    static {
        System.err.println("load my String");
    }

    private char value[];

    private int hash;

    public String() {
        this.value = "".toCharArray();
    }

    public String(java.lang.String original) {
        this.value = original.toCharArray();
    }

    public String(char[] value) {
        this.value = value;
    }

    @Override
    public java.lang.String toString() {
        return java.lang.String.valueOf(value);
    }

    @Override
    public int hashCode() {
        return 999;
    }
}
