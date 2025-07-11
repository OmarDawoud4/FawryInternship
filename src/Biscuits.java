import java.time.LocalDate;
// biscuits are expirable and shippable products
public class Biscuits extends Product implements Expirable, Shippable {
    private LocalDate expiryDate;
    private double weight;

    public Biscuits(String name, double price, int quantity, LocalDate expiryDate, double weight) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
        this.weight = weight;
    }
    @Override
    public boolean isExpired() {

        return LocalDate.now().isAfter(expiryDate);
    }
    @Override
    public String getName() {

        return name;
    }
    @Override
    public double getWeight() {

        return weight;
    }
}
