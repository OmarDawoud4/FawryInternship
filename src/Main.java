import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // create some products
        Cheese cheese = new Cheese("Cheese", 100, 5, LocalDate.of(2025, 8, 1), 0.2);
        Biscuits biscuits = new Biscuits(" Biscuits", 150, 3, LocalDate.of(2025, 7, 30), 0.7);
        TV tv = new TV("TV", 2000, 2, 5.0);
        ScratchCard card = new ScratchCard("ScratchCard", 50, 10);
        // create a customer
        Customer customer = new Customer("Omar", 1000);


        //cart behaviour
        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(card, 1);


        CheckoutService.checkout(customer, cart);
    }
}
