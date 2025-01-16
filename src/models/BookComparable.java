//
// Інтерфейс Comparable
//
package models;

public interface BookComparable {
    // Методи для порівняння двох книг

    // Метод для визначення чи не є тираж книги більше за тираж обраної книги
    boolean isBookCirculationMoreThenOtherBook(EntityElementBook book);

    // Метод для визначення чи не є тираж книги менше за тираж обраної книги
    boolean isBookCirculationLessThenOtherBook(EntityElementBook book);

    // Метод для визначення чи не дорінює тираж книги тиражу обраної книги
    boolean isBookCirculationEqualsOtherBook(EntityElementBook book);
}
