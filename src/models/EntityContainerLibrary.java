//
// Сутність-контейнер "Бібліотека"
//
package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EntityContainerLibrary {
    // params
    private List<EntityElementBook> bookList;

    // constructor
    public EntityContainerLibrary() {
        this.bookList = new ArrayList<EntityElementBook>();
    }

    // get set
    public void setBookList(List<EntityElementBook> bookList) {
        this.bookList = bookList;
    }

    public void setBookToList(EntityElementBook book) {
        int a = 0;
        for (EntityElementBook entityElementBook : bookList) {
            if (Objects.equals(book.getName(), entityElementBook.getName())) {
                a = 1;
                System.out.println("\u001B[31mІм'я зайняте\u001B[0m");
                break;
            }
        }
        if (a != 1) {
            this.bookList.add(book);
        }
    }

    public List<EntityElementBook> getBookList() {
        return bookList;
    }

    public EntityElementBook getBookFromList(String bookName) {
        EntityElementBook a = null;
        for (EntityElementBook entityElementBook : this.bookList) {
            if (Objects.equals(entityElementBook.getName(), bookName))
                a = entityElementBook;
        }
        return a;
    }

    // methods
    public void deleteBookFromList(String bookName) {
        this.bookList.removeIf(entityElementBook -> Objects.equals(entityElementBook.getName(), bookName));
    }


}