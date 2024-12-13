package org.softwaretechnologies;

public class Coffee implements CoffeeInterface {
    private static final int BASE_COST = 50;
    private static final String DESCRIPTION = "only coffee";

    @Override
    public int getCost() {
        return BASE_COST;
    }

    @Override
    public String description() {
        return DESCRIPTION;
    }
}