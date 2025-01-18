package re.forestier.edu.rpg.Inventory;

import java.util.ArrayList;
import java.util.List;

public class ItemList {
    private static final List<Item> items = new ArrayList<>();

    static {
        items.add(new Item("Lookout Ring", "Prevents surprise attacks", 1, 100));
        items.add(new Item("Scroll of Stupidity", "INT-2 when applied to an enemy", 2, 50));
        items.add(new Item("Draupnir", "Increases XP gained by 100%", 3, 500));
        items.add(new Item("Magic Charm", "Magic +10 for 5 rounds", 2, 200));
        items.add(new Item("Rune Staff of Curse", "May burn your enemies... Or yourself. Who knows?", 2, 300));
        items.add(new Item("Combat Edge", "Well, that's an edge", 5, 150));
        items.add(new Item("Holy Elixir", "Recover your HP", 3, 100));
    }

    public static Item getItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    public static List<Item> getItems() {
        return new ArrayList<>(items); // Return a copy to prevent external modification
    }
}
