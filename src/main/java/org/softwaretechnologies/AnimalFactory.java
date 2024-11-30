package org.softwaretechnologies;

import org.softwaretechnologies.animals.*;

public class AnimalFactory {

    /**
     * Метод для создания животных по типу
     * @param name - имя животного
     * @param type - тип животного
     * @return животное, соответствующее каждому из типов.
     */


    public static Animal createAnimal(String name, AnimalType type) {
        /* TODO в зависимости от type создайте и верните нужное животное */
        return switch (type)
        {
            case CAT -> new Cat(name);
            case DOG -> new Dog(name);
            case COW -> new Cow(name);
            default -> throw new IllegalArgumentException("Unknown animal type: " + type);
        };
    }
}
