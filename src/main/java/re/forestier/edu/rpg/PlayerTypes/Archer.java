package re.forestier.edu.rpg.PlayerTypes;


import re.forestier.edu.rpg.Inventory.Inventory;

public class Archer extends Player {
    public static final String CLASS_NAME = "ARCHER";

     public Archer(String playerName, String avatarName, String avatarClass, int money, Inventory inventory) {
        super(playerName, avatarName, avatarClass, money, inventory);
        this.map_abilities = GameClasses.createArcherAbilities();
    }

}
