import java.util.ArrayList;
import java.util.List;

public class CheckoutService {
    private static final double SHIPPING_FEE = 30.0;

    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            throw new RuntimeException("Cart is empty.");
        }

        double subtotal = 0;
        List<Shippable> toShip = new ArrayList<>();
        // looping through cart items
        for (CartItem item : cart.getItems()) {
            Product p = item.product;
            // is product expired ?
            if (p instanceof Expirable && ((Expirable) p).isExpired()) {
                throw new RuntimeException(p.getName() + " is expired");
            }
            // is quantity available ?
            if (p.getQuantity() < item.quantity) {
                throw new RuntimeException(p.getName() + " is out of stock");
            }

            subtotal += item.getTotalPrice();

            if (p instanceof Shippable) {
                for (int i = 0; i < item.quantity; i++) {
                    toShip.add((Shippable) p);
                }
            }
        }

        double shippingFee = toShip.isEmpty() ? 0 : SHIPPING_FEE;
        double total = subtotal + shippingFee;

        if (customer.getBalance() < total) {
            throw new RuntimeException("Insufficient balance.");
        }
        // reduce product quantity
        for (CartItem item : cart.getItems()) {
            item.product.reduceQuantity(item.quantity);
        }

        customer.pay(total);
        // receipt
        if (!toShip.isEmpty()) {
            ShippingService.ship(toShip);
        }
        System.out.println("-----------------------");
        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.println(item.quantity + "x " + item.product.getName() + " " + item.getTotalPrice());
        }
        System.out.println("----------------------");
        System.out.println("Subtotal " + subtotal);
        System.out.println("Shipping " + shippingFee);
        System.out.println("Amount " + total);
        System.out.println("Remaining Balance: " + customer.getBalance());
    }
}
