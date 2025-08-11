/*

Software Design and Architecture - Tutorial 1
Java code made by Bralyn Loach-Perry (Student ID: 100867075)
                           CRN 45740

Java code creates examples using each of the main 5 principles by Martin to understand how they work

The Other classes and objects are located below the main class

*/

public class Main {
    public static void main(String[] args) {
        // 1. Single Responsibility Principle
        Book book = new Book("A Thousand Splendid Suns", "Khaled Hosseini");
        BookPrinter bookPrinter = new BookPrinter();
        bookPrinter.printBookDetails(book);

        // 2. Open-Closed Principle
        Payment payment = new CreditCardPayment();
        payment.makePayment(100.0);

        payment = new PayPalPayment();
        payment.makePayment(200.0);

        // 3. Liskov Substitution Principle
        Bird sparrow = new Sparrow();
        sparrow.fly();

        try {
            Bird ostrich = new Ostrich();
            ostrich.fly(); // Will throw an exception
        } catch (UnsupportedOperationException e) {
            System.out.println("Ostrich cannot fly.");
        }

        NonFlyingBird ostrichCorrect = new CorrectedOstrich();
        ostrichCorrect.fly(); // Now correctly shows that ostrich can't fly

        // 4. Interface Segregation Principle
        Restaurant dineIn = new DineInRestaurant();
        dineIn.prepareMeal();

        OnlineRestaurant online = new OnlineRestaurant();
        online.prepareMeal();
        online.deliverMeal();

        // 5. Dependency Inversion Principle
        DataReader fileReader = new FileReader();
        DataProcessor fileProcessor = new DataProcessor(fileReader);
        fileProcessor.process();

        DataReader dbReader = new DatabaseReader();
        DataProcessor dbProcessor = new DataProcessor(dbReader);
        dbProcessor.process();
    }
}

// 1. Single Responsibility Principle (SRP)
class Book {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}

// Class responsible for printing book details
class BookPrinter {
    public void printBookDetails(Book book) {
        System.out.println("Book: " + book.getTitle() + " by " + book.getAuthor());
    }
}

// 2. Open-Closed Principle (OCP)
abstract class Payment {
    public abstract void makePayment(double amount);
}

class CreditCardPayment extends Payment {
    @Override
    public void makePayment(double amount) {
        System.out.println("Payment of $" + amount + " made with Credit Card.");
    }
}

class PayPalPayment extends Payment {
    @Override
    public void makePayment(double amount) {
        System.out.println("Payment of $" + amount + " made with PayPal.");
    }
}

// 3. Liskov Substitution Principle (LSP)
class Bird {
    public void fly() {
        System.out.println("Flying...");
    }
}

class Sparrow extends Bird {
    // Sparrow can still fly, adhering to Liskov Substitution Principle
}

class Ostrich extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Ostriches cannot fly");
    }
}

// Correcting LSP
class NonFlyingBird extends Bird {
    @Override
    public void fly() {
        System.out.println("Cannot fly.");
    }
}

class CorrectedOstrich extends NonFlyingBird {
    // Now an ostrich does not violate LSP by correctly inheriting from NonFlyingBird
}

// 4. Interface Segregation Principle (ISP)
interface Restaurant {
    void prepareMeal();
}

interface DeliveryService {
    void deliverMeal();
}

class DineInRestaurant implements Restaurant {
    @Override
    public void prepareMeal() {
        System.out.println("Meal is prepared for dine-in.");
    }
}

class OnlineRestaurant implements Restaurant, DeliveryService {
    @Override
    public void prepareMeal() {
        System.out.println("Meal is prepared for delivery.");
    }

    @Override
    public void deliverMeal() {
        System.out.println("Meal is delivered.");
    }
}

// 5. Dependency Inversion Principle (DIP)
interface DataReader {
    String readData();
}

class FileReader implements DataReader {
    @Override
    public String readData() {
        return "Reading data from file...";
    }
}

class DatabaseReader implements DataReader {
    @Override
    public String readData() {
        return "Reading data from database...";
    }
}

class DataProcessor {
    private DataReader dataReader;

    public DataProcessor(DataReader dataReader) {
        this.dataReader = dataReader;
    }

    public void process() {
        System.out.println(dataReader.readData());
    }
}