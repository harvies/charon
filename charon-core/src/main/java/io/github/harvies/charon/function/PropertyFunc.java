package io.github.harvies.charon.function;

import java.io.Serializable;
import java.util.function.Function;

public interface PropertyFunc<T, R> extends Function<T, R>, Serializable {

}
