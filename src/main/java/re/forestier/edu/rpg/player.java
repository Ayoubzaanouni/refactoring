package re.forestier.edu.rpg;

import java.util.HashMap;

import re.forestier.edu.rpg.Inventory.Inventory;

public class player {
    // Public Fields
    public String playerName;
    public String avatarName;
    public Integer money;
    public int level;
    public int healthPoints;
    public int currentHealthPoints;

    
    protected int xp;

    private String avatarClass;

    public HashMap<String, Integer> abilities;
    
    public Inventory inventory;


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

    // Method for retrieving player level based on XP
    public int retrieveLevel() {
        level = Level.getLevel(this.xp);
        return level;
    }

    public int getXp() {
        return this.xp;
    }

    /*
    Ingredients for the recipe:
        - 250 g flour
        - 125 g cold butter
        - 70 g sugar
        - 1 egg
        - A pinch of salt
    */
}
