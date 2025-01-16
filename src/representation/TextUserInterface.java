//
// Клас що реалізує текстовий користувацький інтерфейс
//
package representation;

import dal.BookDAOImpl;
import models.EntityElementBook;
import services.DataManipulator;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class TextUserInterface {
    public int mainMenu(BookDAOImpl dataBaseLibrary) {
        int enterNumb;

        enterNumb = firstOutput(dataBaseLibrary);

        switch (enterNumb) {
            case 1:
                choiceAddNewElement(dataBaseLibrary);
                break;
            case 2:
                choiceDeleteElement(dataBaseLibrary);
                break;
            case 3:
                choiceRefactorElement(dataBaseLibrary);
                break;
            case 4:
                choiceChangeReflection(dataBaseLibrary);
                break;
        }
        return 0;
    }

    private String deleteBrackets(List<EntityElementBook> bookList) {
        String finalBookList = bookList.toString();
        finalBookList.replaceAll("\\[", "").replaceAll("]", "");
        return finalBookList.substring(2);
    }

    private int firstOutput(BookDAOImpl dataBaseLibrary) {
        Scanner scn = new Scanner(System.in);
        int enterNumb;
        System.out.println("\u001B[34mНаявна база даних:\u001B[0m");
        System.out.println(deleteBrackets(dataBaseLibrary.getLibrary()));
        System.out.println("\u001B[34mОберіть дії з бази даних:\u001B[0m");
        System.out.println("\u001B[35m1.Додати елемент до бази даних\u001B[0m");
        System.out.println("\u001B[35m2.Видалити елемент з бази даних\u001B[0m");
        System.out.println("\u001B[35m3.Відредагувати елемент бази даних\u001B[0m");
        System.out.println("\u001B[35m4.Змінити відображення бази даних(сортування)\u001B[0m");
        System.out.print("\u001B[36mОберіть дію(введіть число від 1 до 4 та натисніть Enter): \u001B[0m");
        enterNumb = scn.nextInt();
        return enterNumb;
    }

    private void choiceAddNewElement(BookDAOImpl dataBaseLibrary) {
        String
                enterSymbol,
                enterName,
                enterGenre;
        int
                enterCirculation;
        Scanner scn = new Scanner(System.in);

        System.out.print("\u001B[36mВи обрали \"Додати новий елемент\", підтвердити?(y/n): \u001B[0m");
        enterSymbol = scn.next();

        if (Objects.equals(enterSymbol, "n") || Objects.equals(enterSymbol, "N")) {
            System.out.println();
            mainMenu(dataBaseLibrary);
        } else if (Objects.equals(enterSymbol, "y") || Objects.equals(enterSymbol, "Y")) {
            System.out.print("\u001B[36mВведіть назву книги(обов'язково)(без пробілів): \u001B[0m");
            enterName = scn.next();
            System.out.print("\u001B[36mВведіть жанр книги: \u001B[0m");
            enterGenre = scn.next();
            System.out.print("\u001B[36mВведіть тираж книги: \u001B[0m");
            enterCirculation = scn.nextInt();

            EntityElementBook newBook = new EntityElementBook(enterName, enterGenre, enterCirculation);
            dataBaseLibrary.addNewBook(newBook);
            System.out.println();
            mainMenu(dataBaseLibrary);
        } else {
            System.out.println("Не коректна відповідь");
            choiceAddNewElement(dataBaseLibrary);
        }
    }

    private void choiceDeleteElement(BookDAOImpl dataBaseLibrary) {
        String
                enterSymbol,
                enterName;
        List<EntityElementBook> bookList = dataBaseLibrary.getLibrary();
        Scanner scn = new Scanner(System.in);

        System.out.print("\u001B[36mВи обрали \"Видалити елемент\", підтвердити?(y/n): \u001B[0m");
        enterSymbol = scn.next();

        if (Objects.equals(enterSymbol, "n") || Objects.equals(enterSymbol, "N")) {
            System.out.println();
            mainMenu(dataBaseLibrary);
        } else if (Objects.equals(enterSymbol, "y") || Objects.equals(enterSymbol, "Y")) {
            System.out.print("\u001B[36mВведіть назву книги для видалення(обов'язково): \u001B[0m");
            enterName = scn.next();
            int a = 0;
            for (EntityElementBook entityElementBook : bookList) {
                if (Objects.equals(enterName, entityElementBook.getName())) {
                    a = 1;
                    break;
                }
            }
            if (a == 0) {
                System.out.println("Книги з такою назвою не існує");
                System.out.println();
                mainMenu(dataBaseLibrary);
            } else {
                for (EntityElementBook entityElementBook : bookList) {
                    if (Objects.equals(enterName, entityElementBook.getName())) {
                        dataBaseLibrary.deleteBook(entityElementBook.getName());
                        System.out.println();
                        mainMenu(dataBaseLibrary);
                    }
                }
            }
        } else {
            System.out.println("Не коректна відповідь");
            choiceDeleteElement(dataBaseLibrary);
        }
    }

    private void choiceRefactorElement(BookDAOImpl dataBaseLibrary) {
        String
                enterSymbol,
                enterName,
                enterNewName,
                enterNewGenre;
        int
                enterNumber,
                enterNewCirculation;
        Scanner scn = new Scanner(System.in);

        System.out.print("\u001B[36mВи обрали \"Відредагувати елемент\", підтвердити?(y/n): \u001B[0m");
        enterSymbol = scn.next();

        if (Objects.equals(enterSymbol, "n") || Objects.equals(enterSymbol, "N")) {
            System.out.println();
            mainMenu(dataBaseLibrary);
        } else if (Objects.equals(enterSymbol, "y") || Objects.equals(enterSymbol, "Y")) {
            System.out.print("\u001B[36mВведіть назву книги яку ви хочете відредагувати(обов'язково): \u001B[0m");
            enterName = scn.next();
            System.out.println("\u001B[34mОберіть який елемент ви хочете змінти:\u001B[0m");
            System.out.println("\u001B[35m1.Змінити назву\u001B[0m");
            System.out.println("\u001B[35m2.Змінити жанр\u001B[0m");
            System.out.println("\u001B[35m3.Змінити тираж\u001B[0m");
            System.out.print("\u001B[36mОберіть дію(введіть число від 1 до 3 та натисніть Enter): \u001B[0m");
            enterNumber = scn.nextInt();
            switch (enterNumber) {
                case 1:
                    System.out.print("\u001B[36mВведіть нову назву книги: \u001B[0m");
                    enterNewName = scn.next();
                    dataBaseLibrary.refactorBookName(enterName, enterNewName);
                    System.out.println();
                    mainMenu(dataBaseLibrary);
                    break;
                case 2:
                    System.out.print("\u001B[36mВведіть новий жанр книги: \u001B[0m");
                    enterNewGenre = scn.next();
                    dataBaseLibrary.refactorBookGenre(enterName, enterNewGenre);
                    System.out.println();
                    mainMenu(dataBaseLibrary);
                    break;
                case 3:
                    System.out.print("\u001B[36mВведіть нове значення тиражу книги: \u001B[0m");
                    enterNewCirculation = scn.nextInt();
                    dataBaseLibrary.refactorBookCirculation(enterName, enterNewCirculation);
                    System.out.println();
                    mainMenu(dataBaseLibrary);
                    break;
            }
        }
    }

    private void choiceChangeReflection(BookDAOImpl dataBaseLibrary) {
        int
                enterNumberMain,
                enterNumberSecond;
        DataManipulator manipulator = new DataManipulator();
        Scanner scn = new Scanner(System.in);

        enterNumberMain = ChangeReflectionMainText(dataBaseLibrary);

        switch (enterNumberMain) {
            case 1:
                System.out.println("\u001B[34mВи обрали \"Сортування\"\u001B[0m");
                System.out.println("\u001B[34mОберіть дію:\u001B[0m");
                System.out.println("\u001B[35m1.Відсортувати усі книги за ім'ям в алфавітному порядку\u001B[0m");
                System.out.println("\u001B[35m2.Відсортувати усі книги за жанром в алфавітному порядку\u001B[0m");
                System.out.println("\u001B[35m3.Відсортувати усі книги за тиражем у порядку зростання\u001B[0m");
                System.out.print("\u001B[36mОберіть дію(введіть число від 1 до 3 та натисніть Enter): \u001B[0m");
                enterNumberSecond = scn.nextInt();
                switch (enterNumberSecond) {
                    case 1:
                        String mainMenuBacker;
                        System.out.println(deleteBrackets(manipulator.getAllBooksSortedByName(dataBaseLibrary)));
                        System.out.print("\u001B[36mПовернутись до головного меню?(y/n): \u001B[0m");
                        mainMenuBacker = scn.next();
                        if (Objects.equals(mainMenuBacker, "y") || Objects.equals(mainMenuBacker, "Y")) {
                            System.out.println();
                            mainMenu(dataBaseLibrary);
                        } else
                            choiceChangeReflection(dataBaseLibrary);
                        break;
                    case 2:
                        System.out.println(deleteBrackets(manipulator.getAllBooksSortedByGenre(dataBaseLibrary)));
                        System.out.print("\u001B[36mПовернутись до головного меню?(y/n): \u001B[0m");
                        mainMenuBacker = scn.next();
                        if (Objects.equals(mainMenuBacker, "y") || Objects.equals(mainMenuBacker, "Y")) {
                            System.out.println();
                            mainMenu(dataBaseLibrary);
                        } else
                            choiceChangeReflection(dataBaseLibrary);
                        break;
                    case 3:
                        System.out.println(deleteBrackets(manipulator.getAllBooksSortedByCirculation(dataBaseLibrary)));
                        System.out.print("\u001B[36mПовернутись до головного меню?(y/n): \u001B[0m");
                        mainMenuBacker = scn.next();
                        if (Objects.equals(mainMenuBacker, "y") || Objects.equals(mainMenuBacker, "Y")) {
                            System.out.println();
                            mainMenu(dataBaseLibrary);
                        } else
                            choiceChangeReflection(dataBaseLibrary);
                        break;
                }
                break;
            case 2:
                System.out.println("\u001B[34mВи обрали \"Відображення за параметром\"\u001B[0m");
                System.out.println("\u001B[34mОберіть дію:\u001B[0m");
                System.out.println("\u001B[35m1.Відобразити усі книги що відповідають обраному жанру\u001B[0m");
                System.out.println("\u001B[35m2.Відобразити усі книги чий тираж більше обраного числа \u001B[0m");
                System.out.println("\u001B[35m3.Відобразити усі книги чий тираж менше обраного числа\u001B[0m");
                System.out.println("\u001B[35m4.Відобразити усі книги чий тираж більше обраного числа та відповідає обраному жанру\u001B[0m");
                System.out.println("\u001B[35m5.Відобразити усі книги чий тираж менше обраного числа та відповідає обраному жанру\u001B[0m");
                System.out.print("\u001B[36mОберіть дію(введіть число від 1 до 5 та натисніть Enter): \u001B[0m");
                enterNumberSecond = scn.nextInt();
                switch (enterNumberSecond) {
                    case 1:
                        String bookGenre;
                        System.out.print("\u001B[36mВведіть назву жанру: \u001B[0m");
                        bookGenre = scn.next();
                        System.out.println(deleteBrackets(manipulator.getBooksByGenre(dataBaseLibrary, bookGenre)));
                        System.out.print("\u001B[36mПовернутись до головного меню?(y/n): \u001B[0m");
                        bookGenre = scn.next();
                        if (Objects.equals(bookGenre, "y") || Objects.equals(bookGenre, "Y")) {
                            System.out.println();
                            mainMenu(dataBaseLibrary);
                        } else
                            choiceChangeReflection(dataBaseLibrary);
                        break;
                    case 2:
                        int bookCirculation;
                        System.out.print("\u001B[36mВведіть число: \u001B[0m");
                        bookCirculation = scn.nextInt();
                        System.out.println(deleteBrackets(manipulator.getBooksCirculationAmountMoreThenX(dataBaseLibrary, bookCirculation)));
                        System.out.print("\u001B[36mПовернутись до головного меню?(y/n): \u001B[0m");
                        bookGenre = scn.next();
                        if (Objects.equals(bookGenre, "y") || Objects.equals(bookGenre, "Y")) {
                            System.out.println();
                            mainMenu(dataBaseLibrary);
                        } else
                            choiceChangeReflection(dataBaseLibrary);
                        break;
                    case 3:
                        System.out.print("\u001B[36mВведіть число: \u001B[0m");
                        bookCirculation = scn.nextInt();
                        System.out.println(deleteBrackets(manipulator.getBooksCirculationAmountLessThenX(dataBaseLibrary, bookCirculation)));
                        System.out.print("\u001B[36mПовернутись до головного меню?(y/n): \u001B[0m");
                        bookGenre = scn.next();
                        if (Objects.equals(bookGenre, "y") || Objects.equals(bookGenre, "Y")) {
                            System.out.println();
                            mainMenu(dataBaseLibrary);
                        } else
                            choiceChangeReflection(dataBaseLibrary);
                        break;
                    case 4:
                        System.out.print("\u001B[36mВведіть число: \u001B[0m");
                        bookCirculation = scn.nextInt();
                        System.out.print("\u001B[36mВведіть назву жанру: \u001B[0m");
                        bookGenre = scn.next();
                        System.out.println(deleteBrackets(manipulator.getBooksByGenreAndCirculationMoreThenX(dataBaseLibrary, bookGenre, bookCirculation)));
                        System.out.print("\u001B[36mПовернутись до головного меню?(y/n): \u001B[0m");
                        bookGenre = scn.next();
                        if (Objects.equals(bookGenre, "y") || Objects.equals(bookGenre, "Y")) {
                            System.out.println();
                            mainMenu(dataBaseLibrary);
                        } else
                            choiceChangeReflection(dataBaseLibrary);
                        break;
                    case 5:
                        System.out.print("\u001B[36mВведіть число: \u001B[0m");
                        bookCirculation = scn.nextInt();
                        System.out.print("\u001B[36mВведіть назву жанру: \u001B[0m");
                        bookGenre = scn.next();
                        System.out.println(deleteBrackets(manipulator.getBooksByGenreAndCirculationLessThenX(dataBaseLibrary, bookGenre, bookCirculation)));
                        System.out.print("\u001B[36mПовернутись до головного меню?(y/n): \u001B[0m");
                        bookGenre = scn.next();
                        if (Objects.equals(bookGenre, "y") || Objects.equals(bookGenre, "Y")) {
                            System.out.println();
                            mainMenu(dataBaseLibrary);
                        } else
                            choiceChangeReflection(dataBaseLibrary);
                        break;
                }

                System.out.println();
                mainMenu(dataBaseLibrary);
                break;
        }
    }

    private int ChangeReflectionMainText(BookDAOImpl dataBaseLibrary) {
        int enterNumberMain;
        Scanner scn = new Scanner(System.in);

        System.out.println("\u001B[34mВи обрали \"Змінити відображення\"\u001B[0m");
        System.out.println("\u001B[34mОберіть дію:\u001B[0m");
        System.out.println("\u001B[35m1.Відсортувати усі книги за ім'ям, жанром чи тиражем в алфавітному порядку чи у порядку зростання\u001B[0m");
        System.out.println("\u001B[35m2.Відобразити усі книги за заданим параметром(жанром та/чи тиражем)\u001B[0m");
        System.out.print("\u001B[36mОберіть дію(введіть число 1 чи 2 та натисніть Enter): \u001B[0m");
        enterNumberMain = scn.nextInt();
        return enterNumberMain;
    }
}