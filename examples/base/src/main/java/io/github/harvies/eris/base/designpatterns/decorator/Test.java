package io.github.harvies.eris.base.designpatterns.decorator;

/**
 * https://mp.weixin.qq.com/s/JIKmq_Rit_gah8o9OgHgLA
 * @author harvies
 */
public class Test {
    public static void main(String[] args) {
        Food food = new Bread(new Vegetable(new Cream(new Food("香肠"))));
        System.out.println(food.make());
    }
}
