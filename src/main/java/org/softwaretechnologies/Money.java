package org.softwaretechnologies;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import static java.lang.Integer.MAX_VALUE;

public class Money {
    private final MoneyType type;
    private final BigDecimal amount;

    public Money(MoneyType type, BigDecimal amount) {
        this.type = type;
        this.amount = amount;
    }

    /**
     * Money равны, если одинаковый тип валют и одинаковое число денег до 4 знака после запятой.
     * Округление по правилу: если >= 5, то в большую сторону, интаче - в меньшую
     * Пример округления:
     * BigDecimal scale = amount.setScale(4, RoundingMode.HALF_UP);
     *
     * @param o объект для сравнения
     * @return true - равно, false - иначе
     */
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true; // Проверка на идентичность
        }
        if (!(o instanceof Money)) // Проверка на класс
            return false;

        Money otherMoney = (Money) o;

        if(otherMoney.type != this.type) // Проверка на различие типов
            return false;

        if(otherMoney.amount == null && this.amount == null) // Проверка на null у двух объектов сразу
            return true;

        if (otherMoney.amount == null || this.amount == null) // Если один из amount = null, то не false
            return false;

        BigDecimal scale1 = this.getAmount().setScale(4, RoundingMode.HALF_UP);
        BigDecimal scale2 = otherMoney.getAmount().setScale(4, RoundingMode.HALF_UP);

        return scale1.compareTo(scale2) == 0;
    }

    /**
     * Формула:
     * (Если amount null 10000, иначе количество денег окрукленные до 4х знаков * 10000) + :
     * если USD , то 1
     * если EURO, то 2
     * если RUB, то 3
     * если KRONA, то 4
     * если null, то 5
     * Если amount округленный до 4х знаков * 10000 >= (Integer.MaxValue - 5), то хеш равен Integer.MaxValue
     * Округление по правилу: если >= 5, то в большую сторону, иначе - в меньшую
     * Пример округления:
     * BigDecimal scale = amount.setScale(4, RoundingMode.HALF_UP);
     *
     * @return хеш код по указанной формуле
     */
    @Override
    public int hashCode() {
        if (this.amount == null) return 10000;

        BigDecimal money = this.amount.setScale(4, RoundingMode.HALF_UP);
        money = money.multiply(BigDecimal.valueOf(10_000));

        if (this.type == null) {
            money = money.add(BigDecimal.valueOf(5));
        }
        else {
            money = switch (this.type) {
                case RUB -> money.add(BigDecimal.valueOf(3));
                case USD -> money.add(BigDecimal.valueOf(1));
                case EURO -> money.add(BigDecimal.valueOf(2));
                case KRONA -> money.add(BigDecimal.valueOf(4));
            };
        }

        if (money.compareTo(BigDecimal.valueOf(MAX_VALUE - 5)) >= 0) // Проверка на выход за MAX_VALUE
            return MAX_VALUE;

        return money.intValue();

    }

    /**
     * Верните строку в формате
     * Тип_ВАЛЮТЫ: количество.XXXX
     * Тип_валюты: USD, EURO, RUB или KRONA
     * количество.XXXX - округленный amount до 4х знаков.
     * Округление по правилу: если >= 5, то в большую сторону, интаче - в меньшую
     * BigDecimal scale = amount.setScale(4, RoundingMode.HALF_UP);
     * <p>
     * Если тип валюты null, то вернуть:
     * null: количество.XXXX
     * Если количество денег null, то вернуть:
     * Тип_ВАЛЮТЫ: null
     * Если и то и то null, то вернуть:
     * null: null
     *
     * @return приведение к строке по указанному формату.
     */
    @Override
    public String toString() {
        String typeString = "null";
        String num = "null";
        if (this.amount != null)
            num = this.amount.setScale(4, RoundingMode.HALF_UP).toString();

        if (this.type != null)
            typeString = this.type.toString();

        return typeString + ": " + num;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public MoneyType getType() {
        return type;
    }

    public static void main(String[] args) {
        Money money = new Money(MoneyType.EURO, BigDecimal.valueOf(10.00012));
        Money money1 = new Money(MoneyType.USD, BigDecimal.valueOf(10.5000));
        System.out.println(money1.toString());
        System.out.println(money1.hashCode());
        System.out.println(money.equals(money1));
    }
}

