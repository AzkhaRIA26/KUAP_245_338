package kyotorestaurant;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private Map<MenuItem, Integer> items;

    public Order() {
        items = new HashMap<>();
    }

    public void addItem(MenuItem item, int quantity) {
        if (items.containsKey(item)) {
            items.put(item, items.get(item) + quantity);
        } else {
            items.put(item, quantity);
        }
    }

    public Map<MenuItem, Integer> getItems() {
        return items;
    }
}