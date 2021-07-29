package io.github.harvies.charon.tests.base.designpatterns.decorator;

public class Food {

    private String food_name;

    public Food() {
    }

    public Food(String food_name) {
        this.food_name = food_name;
    }

    public String make() {
        return food_name;
    };
}
