import model.Cart;
import model.Order;
import model.Product;
import model.User;
import  Custom_Exceptions.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OrderApp {
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;
    private static List<Product> products = new ArrayList<>();
    private static Cart cart = new Cart();

    public static void main(String[] args) {
        seedProducts();
        login();
        mainMenu();
    }

    private static void seedProducts() {
        products.add(new Product(1, "Laptop", 999.99));
        products.add(new Product(2, "Phone", 499.99));
        products.add(new Product(3, "Headphones", 79.99));
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        // Simple authentication (fixed user for demo)
        if ("admin".equals(username) && "password".equals(password)) {
            currentUser = new User(username, password);
            System.out.println("Login successful!");
            System.out.print("\007"); // Sound alert
        } else {
            System.out.println("Invalid credentials. Try again.");
            login();
        }
    }

    private static void mainMenu() {
        while (true) {
            System.out.println("\n1. List Products\n2. Add to Cart\n3. View Cart\n4. Remove from Cart\n5. Place Order\n6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> listProducts();
                case 2 -> addToCart();
                case 3 -> viewCart();
                case 4 -> removeFromCart();
                case 5 -> placeOrder();
                case 6 -> System.exit(0);
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void listProducts() {
        System.out.println("\nAvailable Products:");
        for (Product p : products) {
            System.out.printf("ID: %d | Name: %s | Price: $%.2f\n", p.getId(), p.getName(), p.getPrice());
        }
    }

    private static void addToCart() {
        try {
            System.out.print("Enter product ID to add to cart: ");
            int id = scanner.nextInt();
            Product product = products.stream().filter(p -> p.getId() == id).findFirst().orElseThrow(() -> new ProductNotFoundException("Product not found!"));
            cart.addProduct(product);
            System.out.println("Added " + product.getName() + " to cart.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid product ID.");
            scanner.next();
        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void viewCart() {
        System.out.println("\nYour Cart:");
        for (Product p : cart.getProducts()) {
            System.out.printf("ID: %d | Name: %s | Price: $%.2f\n", p.getId(), p.getName(), p.getPrice());
        }
    }

    private static void removeFromCart() {
        try {
            System.out.print("Enter product ID to remove: ");
            int id = scanner.nextInt();
            cart.removeProduct(id);
            System.out.println("Product removed from cart.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid product ID.");
            scanner.next();
        }
    }

    private static void placeOrder() {
        if (cart.getProducts().isEmpty()) {
            System.out.println("Your cart is empty. Add products before placing an order.");
            return;
        }
        Order order = new Order(cart.getProducts());
        order.displayOrderDetails();
        System.out.print("\007"); // Sound alert
    }
}
