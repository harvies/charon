package io.github.harvies.charon.tests.base.jdk.clazz;

public abstract class Bird {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract int fly();
}
