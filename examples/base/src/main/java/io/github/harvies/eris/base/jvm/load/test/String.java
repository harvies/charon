package io.github.harvies.eris.base.jvm.load.test;

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
        int h = hash;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;
    }
}
