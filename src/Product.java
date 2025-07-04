public abstract class Product {
    protected String name;
    protected double price;
    protected int quantity;

    public Product(String name, double price, int quantity)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    //no setters to protect product data
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price; }
    public int getQuantity() { return quantity;
    }

    // how and when values change when product is purchased
    public void reduceQuantity(int amount) {

        this.quantity -= amount;
    }
}
