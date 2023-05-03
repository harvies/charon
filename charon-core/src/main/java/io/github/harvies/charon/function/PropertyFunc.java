package io.github.harvies.charon.function;

@FunctionalInterface
public
interface PropertyFunc<T, R> extends java.io.Serializable {
    R apply(T t);
}