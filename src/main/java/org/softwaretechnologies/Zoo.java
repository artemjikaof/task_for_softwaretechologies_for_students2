package org.softwaretechnologies;

import org.softwaretechnologies.animals.Animal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Zoo {
    private final List<Animal> animalList = new ArrayList<>();

    public void addAnimal(Animal animal) {
        animalList.add(animal);
    }

    /**
     * Метод должен возвращать список звуков животных.
     * Звуки животных должны быть отсортированы по имени животного. Пример:
     * Животные:
     *
     * Корова: Яша
     * Кошка: Дуся
     * Собака: Жучка
     * Корова: Абракадабра
     * Собака: Шарик
     * Кошка: Мурзик
     * Собака: Бобик
     *
     * Возвращаемый список звуков: moo, woof, meow, woof, meow, woof, moo
     *
     * @return Звуки животных, в алфавитном порядке имени животного.
     */
    public List<String> soundAllAnimalsSortByName() {
        List<String> sounds = new ArrayList<>();

        // Сортировка animalList по имени животного

        animalList.sort(Comparator.comparing(Animal::getName));
        animalList.forEach(animal -> sounds.add(animal.sound()));



        return sounds;
    }
}
