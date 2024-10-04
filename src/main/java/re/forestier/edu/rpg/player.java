package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;

public class player {
    // Public Fields
    public String playerName;
    public String avatarName;
    public Integer money;
    public int level;
    public int healthPoints;
    public int currentHealthPoints;
    public static final String[] classes = {"ARCHER", "ADVENTURER", "DWARF"};
    
    // Protected Fields
    protected int xp;

    // Private Fields
    private String avatarClass;

    // Data Structures
    public HashMap<String, Integer> abilities;
    
    public ArrayList<String> inventory;

    // Constructor
    public player(String playerName, String avatarName, String avatarClass, int money, ArrayList<String> inventory) {
        
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

    // Getter for AvatarClass
    public String getAvatarClass() {
        return avatarClass;
    }

    // Methods for managing money
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
        HashMap<Integer, Integer> levels = new HashMap<>();
        levels.put(2, 10);
        levels.put(3, 27);
        levels.put(4, 57);
        levels.put(5, 111);

        
        // TODO: Add more levels if needed

        if (xp < levels.get(2)) {
            return 1;
        } else if (xp < levels.get(3)) {
            return 2;
        } else if (xp < levels.get(4)) {
            return 3;
        } else if (xp < levels.get(5)) {
            return 4;
        } else {
            return 5;
        }
    }

    // Getter for XP
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
