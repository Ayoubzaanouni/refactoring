package re.forestier.edu.rpg.Inventory;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private List<String> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(String item) {
        items.add(item);
    }

    public boolean contains(String item) {
        return items.contains(item);
    }

    public List<String> getItems() {
        return new ArrayList<>(items); // Return a copy to prevent external modification
    }

    public void clear() {
        items.clear();
    }

    // is empty
    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void display() {
        System.out.println("Inventory: " + items);
    }

    // overide toString
    @Override
    public String toString() {
        String inv = "Inventory: ";
        for (String item : items) {
            inv += item + ", ";
        }
        return inv;
        
    }
}
