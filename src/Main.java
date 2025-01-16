import dal.BookDAOImpl;
import representation.TextUserInterface;

public class Main {
    public static void main(String[] args) {
        System.out.println("\u001B[34mБаза даних книг \"Бібліотека\"\u001B[0m");

        TextUserInterface userInterface = new TextUserInterface();
        BookDAOImpl dataBaseLibrary = new BookDAOImpl();

        userInterface.mainMenu(dataBaseLibrary);
    }
}