package io.github.harvies.charon.tests.base.datastructure.list;

/**
 * Vector(数组实现、线程同步)
 * <p>
 * Vector 与 ArrayList 一样,也是通过数组实现的,不同的是它支持线程的同步,即某一时刻只有一
 * 个线程能够写 Vector,避免多线程同时写而引起的不一致性,但实现同步需要很高的花费,因此,
 * 访问它比访问 ArrayList 慢。
 *
 * @author harvies
 */
public class VectorTest {
}
