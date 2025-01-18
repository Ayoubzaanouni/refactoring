package re.forestier.edu.rpg.PlayerTypes;
import java.util.HashMap;

import re.forestier.edu.rpg.Level;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.Inventory.Inventory;
import re.forestier.edu.rpg.Inventory.Item;
import re.forestier.edu.rpg.Inventory.ItemList;

public class Player {
    public String playerName;
    public String avatarName;
    public Integer money;
    public Integer poidsMax = 7;
    public Integer poidsActuel = 0;

    public String avatarClass;

    public int level = 1;
    public int healthPoints;
    public int currentHealthPoints;

    protected int xp;
    
    public Inventory inventory;
    public HashMap<Integer, Abilities> map_abilities;

    public Abilities abilities;

    public Player(String playerName, String avatarName, String avatarClass, int money, Inventory inventory) {
        
        if (!GameClasses.isValidClass(avatarClass)) {
            return;
        }

        this.avatarClass = avatarClass;

        this.playerName = playerName;
        this.avatarName = avatarName;
        this.money = money;
        this.inventory = inventory;
        this.abilities = UpdatePlayer.getAbilities(this, 1);

    }

    public String getAvatarClass() {
        return avatarClass;
    }

    public void removeMoney(int amount) throws IllegalArgumentException {
        if (money - amount < 0) {
            throw new IllegalArgumentException("Player can't have negative money!");
        }
        money -= amount;
    }

    public void addMoney(int amount) {
        if (amount != 0) {
            money += amount;
        }
    }

    public Item getItem(String itemName) {
        return inventory.getItem(itemName);
    }

    public void addItem(String itemName) {
        Item item = ItemList.getItem(itemName);
        if (item == null) { 
            System.out.println("Item not found: " + itemName);
            return;
        }
    
        if (poidsActuel + item.getWeight() <= poidsMax) {
            inventory.addItem(item.getName());
            poidsActuel += item.getWeight();
        }
    }
    

    public void sell(Item item) {
        if (inventory.contains(item.getName())) {
            inventory.removeItem(item.getName());
            addMoney(item.getValue());
        }
    }


    public void setLevel(int xp) {
        this.level = Level.getLevel(this.xp);
    }

    public int retrieveLevel() {
        return level;
    }
    
    public int getXp() {
        return this.xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }


    public Abilities getAbilities() {
        return this.abilities;
    }

    public void setAbilities(Abilities abilities) {
        this.abilities = abilities;
    }

    // public void majFinDeTour() {
    //     if (currentHealthPoints <= 0) {
    //         System.out.println("Player " + playerName + " has died!");
    //     }
    // }


    @Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("+-----------------------+-------------------------+\n");
    sb.append("| Property              | Value                   |\n");
    sb.append("+-----------------------+-------------------------+\n");
    sb.append(String.format("| Player Name           | %s\n", playerName != null ? playerName : "N/A"));
    sb.append(String.format("| Avatar Name           | %s\n", avatarName != null ? avatarName : "N/A"));
    sb.append(String.format("| Money                 | \n", money));
    sb.append(String.format("| Level                 | %d\n", level));
    sb.append(String.format("| Health Points         | %d\n", healthPoints));
    sb.append(String.format("| Current Health Points  | %d\n", currentHealthPoints));
    sb.append(String.format("| XP                    | %d\n", xp));
    sb.append(String.format("| Avatar Class          | %s\n", avatarClass != null ? avatarClass : "N/A"));
    sb.append(String.format("| Abilities             | %s\n", abilities != null ? abilities.toString() : "N/A"));
    sb.append(String.format("| Inventory             | %s\n", inventory != null ? inventory.toString() : "N/A"));
    sb.append("+-----------------------+-------------------------+\n");
    return sb.toString();
}

public String toMarkdown() {
    StringBuilder sb = new StringBuilder();
    sb.append("# Player Details\n\n");
    sb.append("## General Information\n");
    sb.append("- **Name**: ").append(playerName != null ? playerName : "N/A").append("\n");
    sb.append("- **Avatar**: ").append(avatarName != null ? avatarName : "N/A").append("\n");
    sb.append("- **Class**: ").append(avatarClass != null ? avatarClass : "N/A").append("\n");
    sb.append("- **Money**: ").append(money).append("\n");
    sb.append("- **Level**: ").append(level).append("\n\n");

    sb.append("## Health\n");
    sb.append("- **Health Points**: ").append(healthPoints).append("\n");
    sb.append("- **Current Health**: ").append(currentHealthPoints).append("\n");
    sb.append("- **XP**: ").append(xp).append("\n\n");

    sb.append("## Abilities\n");
    sb.append("- **Abilities**: ").append(abilities != null ? abilities.toString() : "N/A").append("\n\n");

    sb.append("## Inventory\n");
    sb.append("- **Items**: ").append(inventory != null ? inventory.toString() : "N/A").append("\n");

    return sb.toString();
}


    
}
