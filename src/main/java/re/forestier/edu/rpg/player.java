package re.forestier.edu.rpg;

import java.util.HashMap;

public class player {
    // Public Fields
    public String playerName;
    public String avatarName;
    public Integer money;
    public int level;
    public int healthPoints;
    public int currentHealthPoints;

    public static final String[] classes = GameClasses.CLASSES;
    
    protected int xp;

    private String avatarClass;

    public HashMap<String, Integer> abilities;
    
    public Inventory inventory;

    public player(String playerName, String avatarName, String avatarClass, int money, Inventory inventory) {
        
        boolean isValidClass = false;
        for (String validClass : classes) {
            if (validClass.equals(avatarClass)) {
                isValidClass = true;
                break;
            }
        }
        if (!isValidClass) {
            return;
        }
        this.playerName = playerName;
        this.avatarName = avatarName;
        this.avatarClass = avatarClass;
        this.money = money;
        this.inventory = inventory;

        this.abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get(avatarClass).get(1);
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
