package org.softwaretechnologies;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoffeeTest {
    @Test
    void costCofeWithMilk() {
        CoffeeInterface coffee = new MilkCoffee(new Coffee());
        assertEquals(coffee.getCost(), 60);
    }

    @Test
    void costCofeWithMilkSugar() {
        CoffeeInterface coffee = new SugarCoffee(new MilkCoffee(new Coffee()));
        assertEquals(coffee.getCost(), 80);

    }

    @Test
    void descriptionTest() {
        CoffeeInterface allCoffee = new SugarCoffee(new MilkCoffee(new Coffee()));
        assertEquals(allCoffee.description(), "only coffee + milk + sugar");

        CoffeeInterface baseCoffee = new Coffee();
        assertEquals(baseCoffee.description(), "only coffee");
    }
}