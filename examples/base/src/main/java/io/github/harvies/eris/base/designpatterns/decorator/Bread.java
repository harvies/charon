package io.github.harvies.eris.base.designpatterns.decorator;

public class Bread extends Food {

    private Food basic_food;

    public Bread(Food basic_food) {
        this.basic_food = basic_food;
    }

    @Override
    public String make() {
        return basic_food.make()+"+面包";
    }
}
