//
// Сутність-елемент "Книга"
//
package models;

import java.util.Objects;

public class EntityElementBook implements BookComparable {
    // Параметри "Назва", "Жанр" та "Тираж" відповідно
    private String name;
    private String genre;
    private int circulation;

    // Конструктори класу сутності-елемента
    public EntityElementBook(String name, String genre, int circulation) {
        this.name = name;
        this.genre = genre;
        this.circulation = circulation;
    }

    // get та set методи класу сутності-елемента
    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public int getCirculation() {
        return circulation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setCirculation(int circulation) {
        this.circulation = circulation;
    }

    // Перевизначені методи toString, equals та hashCode
    public String toString() {
        return "\nBook{" + "Назва=\"" + "\u001B[31m" + name + "\u001B[0m" + "\", Жанр=\"" + "\u001B[32m" + genre + "\u001B[0m" + "\", Тираж=\"" + "\u001B[33m" + circulation + "\u001B[0m" + "\"}";
    }

    public boolean equals(EntityElementBook book) {
        return this.hashCode() == book.hashCode();
    }

    public int hashCode() {
        return Objects.hash(name);
    }

    // Реалізація інтерфейсу Comparable(BookComparable)
    @Override
    public boolean isBookCirculationMoreThenOtherBook(EntityElementBook book) {
        return this.circulation > book.circulation;
    }

    @Override
    public boolean isBookCirculationLessThenOtherBook(EntityElementBook book) {
        return this.circulation < book.circulation;
    }

    @Override
    public boolean isBookCirculationEqualsOtherBook(EntityElementBook book) {
        return this.circulation == book.circulation;
    }

    // Компаратор
    public int comparator(EntityElementBook book) {
        int compareResult = this.circulation - book.circulation;
        if (compareResult < 0) {
            compareResult = -1;
        } else if (compareResult == 0) {
            compareResult = 0;
        } else
            compareResult = 1;

        return compareResult;
    }
}