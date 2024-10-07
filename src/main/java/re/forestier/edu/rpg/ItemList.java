package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.List;

public class ItemList {
    private static final List<Item> items = new ArrayList<>();

    static {
        items.add(new Item("Lookout Ring", "Prevents surprise attacks"));
        items.add(new Item("Scroll of Stupidity", "INT-2 when applied to an enemy"));
        items.add(new Item("Draupnir", "Increases XP gained by 100%"));
        items.add(new Item("Magic Charm", "Magic +10 for 5 rounds"));
        items.add(new Item("Rune Staff of Curse", "May burn your enemies... Or yourself. Who knows?"));
        items.add(new Item("Combat Edge", "Well, that's an edge"));
        items.add(new Item("Holy Elixir", "Recover your HP"));
    }

    public static List<Item> getItems() {
        return new ArrayList<>(items); // Return a copy to prevent external modification
    }
}
