//
// Інтерфейс сховища на основі шаблону DAO
//
package dal;

import models.EntityContainerLibrary;
import models.EntityElementBook;

import java.util.List;

public interface BookDAO {
    EntityContainerLibrary bookLibrary();

    void addNewBook(EntityElementBook book);

    EntityElementBook getBook(String bookName);

    List<EntityElementBook> getLibrary();

    void refactorBookName(String bookName, String newName);

    void refactorBookGenre(String bookName, String newGenre);

    void refactorBookCirculation(String bookName, int newCirculation);

    void deleteBook(String bookName);
}