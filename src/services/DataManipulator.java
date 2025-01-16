//
// Клас який реалізує очікуваний функціонал – сценарії використання
//
package services;

import dal.BookDAOImpl;
import models.EntityElementBook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DataManipulator {
    public List<EntityElementBook> getAllBooksSortedByName(BookDAOImpl bookDAO) {
        List<EntityElementBook> daoLib = bookDAO.getLibrary();
        List<String> booksName = new ArrayList<>();

        for (EntityElementBook entityElementBook : daoLib) {
            booksName.add(entityElementBook.getName());
        }

        Collections.sort(booksName);

        List<EntityElementBook> sortedDaoLib = new ArrayList<>();
        for (String string : booksName) {
            for (EntityElementBook entityElementBook : daoLib) {
                if (Objects.equals(string, entityElementBook.getName())) {
                    sortedDaoLib.add(entityElementBook);
                }
            }
        }

        return sortedDaoLib;
    }

    public List<EntityElementBook> getAllBooksSortedByGenre(BookDAOImpl bookDAO) {
        List<EntityElementBook> daoLib = bookDAO.getLibrary();
        List<String> booksGenre = new ArrayList<>();

        for (EntityElementBook entityElementBook : daoLib) {
            booksGenre.add(entityElementBook.getGenre());
        }

        Collections.sort(booksGenre);

        List<EntityElementBook> sortedDaoLib = new ArrayList<>();
        for (String string : booksGenre) {
            for (EntityElementBook entityElementBook : daoLib) {
                if (Objects.equals(string, entityElementBook.getGenre()) && !sortedDaoLib.contains(entityElementBook)) {
                    sortedDaoLib.add(entityElementBook);
                }
            }
        }

        return sortedDaoLib;
    }

    public List<EntityElementBook> getAllBooksSortedByCirculation(BookDAOImpl bookDAO) {
        List<EntityElementBook> daoLib = bookDAO.getLibrary();
        List<Integer> booksCirculation = new ArrayList<>();

        for (EntityElementBook entityElementBook : daoLib) {
            booksCirculation.add(entityElementBook.getCirculation());
        }

        Collections.sort(booksCirculation);

        List<EntityElementBook> sortedDaoLib = new ArrayList<>();
        for (Integer integer : booksCirculation) {
            for (EntityElementBook entityElementBook : daoLib) {
                if (Objects.equals(integer, entityElementBook.getCirculation()) && !sortedDaoLib.contains(entityElementBook)) {
                    sortedDaoLib.add(entityElementBook);
                }
            }
        }

        return sortedDaoLib;
    }

    public List<EntityElementBook> getBooksCirculationAmountLessThenX(BookDAOImpl bookDAO, int x) {
        List<EntityElementBook> daoLib = bookDAO.getLibrary();
        List<EntityElementBook> booksAmountLessThenX = new ArrayList<>();

        for (EntityElementBook entityElementBook : daoLib) {
            if (entityElementBook.getCirculation() <= x) {
                booksAmountLessThenX.add(entityElementBook);
            }
        }

        BookDAOImpl newDaoForSort = new BookDAOImpl();
        newDaoForSort.bookLibrary().setBookList(booksAmountLessThenX);

        return getAllBooksSortedByCirculation(newDaoForSort);
    }

    public List<EntityElementBook> getBooksCirculationAmountMoreThenX(BookDAOImpl bookDAO, int x) {
        List<EntityElementBook> daoLib = bookDAO.getLibrary();
        List<EntityElementBook> booksAmountLessThenX = new ArrayList<>();

        for (EntityElementBook entityElementBook : daoLib) {
            if (entityElementBook.getCirculation() >= x) {
                booksAmountLessThenX.add(entityElementBook);
            }
        }

        BookDAOImpl newDaoForSort = new BookDAOImpl();
        newDaoForSort.bookLibrary().setBookList(booksAmountLessThenX);

        return getAllBooksSortedByCirculation(newDaoForSort);
    }

    public List<EntityElementBook> getBooksByGenre(BookDAOImpl bookDAO, String bookGenre) {
        List<EntityElementBook> daoLib = bookDAO.getLibrary();
        List<EntityElementBook> booksGenre = new ArrayList<>();


        for (EntityElementBook entityElementBook : daoLib) {
            if (Objects.equals(entityElementBook.getGenre(), bookGenre))
                booksGenre.add(entityElementBook);
        }

        return booksGenre;
    }

    public List<EntityElementBook> getBooksByGenreAndCirculationLessThenX(BookDAOImpl bookDAO, String bookGenre, int x) {
        List<EntityElementBook> genreAndCirculationLessThenX = getBooksCirculationAmountLessThenX(bookDAO, x);

        genreAndCirculationLessThenX.removeIf(entityElementBook -> !Objects.equals(entityElementBook.getGenre(), bookGenre));

        return genreAndCirculationLessThenX;
    }

    public List<EntityElementBook> getBooksByGenreAndCirculationMoreThenX(BookDAOImpl bookDAO, String bookGenre, int x) {
        List<EntityElementBook> genreAndCirculationMoreThenX = getBooksCirculationAmountMoreThenX(bookDAO, x);

        genreAndCirculationMoreThenX.removeIf(entityElementBook -> !Objects.equals(entityElementBook.getGenre(), bookGenre));

        return genreAndCirculationMoreThenX;
    }
}
