//
// Клас що реалізує інтерфейс сховища на основі шаблону DAO
//
package dal;

import models.EntityContainerLibrary;
import models.EntityElementBook;

import java.util.List;

public class BookDAOImpl implements BookDAO {
    @Override
    public EntityContainerLibrary bookLibrary() {
        return data;
    }

    @Override
    public void addNewBook(EntityElementBook book) {
        data.setBookToList(book);
    }

    @Override
    public EntityElementBook getBook(String bookName) {
        return data.getBookFromList(bookName);
    }

    @Override
    public List<EntityElementBook> getLibrary() {
        return bookLibrary().getBookList();
    }

    @Override
    public void refactorBookName(String bookName, String newName) {
        EntityElementBook book = data.getBookFromList(bookName);
        book.setName(newName);
    }

    @Override
    public void refactorBookGenre(String bookName, String newGenre) {
        EntityElementBook book = data.getBookFromList(bookName);
        book.setGenre(newGenre);
    }

    @Override
    public void refactorBookCirculation(String bookName, int newCirculation) {
        EntityElementBook book = data.getBookFromList(bookName);
        book.setCirculation(newCirculation);
    }

    @Override
    public void deleteBook(String bookName) {
        data.deleteBookFromList(bookName);
    }

    private static EntityContainerLibrary data;

    public BookDAOImpl() {
        EntityContainerLibrary library = new EntityContainerLibrary();

        library.setBookToList(new EntityElementBook("TheEnchantedForest", "Fantasy", 65978));
        library.setBookToList(new EntityElementBook("CosmicChronicles", "SciFi", 41640));
        library.setBookToList(new EntityElementBook("TalesOfMysteryManor", "Mystery", 32865));
        library.setBookToList(new EntityElementBook("CulinaryCapers", "Adventure", 16379));
        library.setBookToList(new EntityElementBook("LoveInTheLavenderFields", "Romance", 18676));
        library.setBookToList(new EntityElementBook("AdventuresInTheAmazon", "Adventure", 48172));
        library.setBookToList(new EntityElementBook("Dragon'sDestiny", "Fantasy", 95896));
        library.setBookToList(new EntityElementBook("TimeTraveler'sOdyssey", "SciFi", 48992));
        library.setBookToList(new EntityElementBook("TheHauntedLighthouse", "Mystery", 79115));
        library.setBookToList(new EntityElementBook("Grandma'sSecretRecipes", "Fantasy", 13232));
        library.setBookToList(new EntityElementBook("ADanceWithDestiny", "Romance", 26718));
        library.setBookToList(new EntityElementBook("PiratesOfTheCaribbeanSea", "Adventure", 93891));

        data = library;
    }
}