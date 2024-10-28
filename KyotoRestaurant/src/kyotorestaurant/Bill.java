package kyotorestaurant;

import java.util.Map;

public class Bill {
    private Order order;

    public Bill(Order order) {
        this.order = order;
    }

    public void printBill() {
        double total = 0.0;
        System.out.println("===== Nota Pemesanan =====");
        for (Map.Entry<MenuItem, Integer> entry : order.getItems().entrySet()) {
            MenuItem item = entry.getKey();
            int quantity = entry.getValue();
            double itemTotal = item.getPrice() * quantity;
            total += itemTotal;
            System.out.println(item.getName() + " x" + quantity + " : " + itemTotal);
        }
        System.out.println("==========================");
        System.out.println("Total: " + total);
    }
}