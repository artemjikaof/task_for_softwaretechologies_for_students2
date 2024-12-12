package org.softwaretechnologies;

public class MilkCoffee implements CoffeeInterface {
    private final CoffeeInterface coffee;
    private static final int MILK_COST = 10;

    // Конструктор, принимающий CofeInterface
    public MilkCoffee(CoffeeInterface coffee) {
        this.coffee = coffee;
    }

    @Override
    public int getCost() {
        return coffee.getCost() + MILK_COST;
    }

    @Override
    public String description() {
        return coffee.description() + " + milk";
    }
}