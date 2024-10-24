package re.forestier.edu.rpg.PlayerTypes;
import java.util.HashMap;

import re.forestier.edu.rpg.Level;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.Inventory.Inventory;

public class Player {
    public String playerName;
    public String avatarName;
    public Integer money;

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
    sb.append(String.format("| Money                 | \n", money)); // Assuming money is a double
    sb.append(String.format("| Level                 | %d\n", level)); // Correct format for integer
    sb.append(String.format("| Health Points         | %d\n", healthPoints)); // Correct format for integer
    sb.append(String.format("| Current Health Points  | %d\n", currentHealthPoints)); // Correct format for integer
    sb.append(String.format("| XP                    | %d\n", xp)); // Correct format for integer
    sb.append(String.format("| Avatar Class          | %s\n", avatarClass != null ? avatarClass : "N/A"));
    sb.append(String.format("| Abilities             | %s\n", abilities != null ? abilities.toString() : "N/A"));
    sb.append(String.format("| Inventory             | %s\n", inventory != null ? inventory.toString() : "N/A"));
    sb.append("+-----------------------+-------------------------+\n");
    return sb.toString();
}

    
}
