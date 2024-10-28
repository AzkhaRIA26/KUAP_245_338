package kyotorestaurant;

public class Main {
    public static void main(String[] args) {
        // Implementasi utama aplikasi
        MenuItem sushi = new MenuItem("Sushi", 50.0);
        MenuItem ramen = new MenuItem("Ramen", 30.0);
        MenuItem tempura = new MenuItem("Tempura", 40.0);

        Order order = new Order();
        order.addItem(sushi, 2);
        order.addItem(ramen, 1);
        order.addItem(tempura, 3);

        Bill bill = new Bill(order);
        bill.printBill();
    }
}