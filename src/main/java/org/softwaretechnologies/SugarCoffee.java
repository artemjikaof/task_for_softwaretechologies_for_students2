package org.softwaretechnologies;

public class SugarCoffee implements CoffeeInterface {
    private final CoffeeInterface coffee;
    private static final int SUGAR_COST = 20;

    public SugarCoffee(CoffeeInterface coffee) {
        this.coffee = coffee;
    }

    @Override
    public int getCost() {
        return coffee.getCost() + SUGAR_COST; // Добавить стоимость сахара
    }

    @Override
    public String description() {
        return coffee.description() + " + sugar"; // Добавить к описанию
    }
}