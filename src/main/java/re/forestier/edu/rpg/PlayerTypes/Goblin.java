package re.forestier.edu.rpg.PlayerTypes;


import re.forestier.edu.rpg.Inventory.Inventory;

public class Goblin extends Player {
    public static final String CLASS_NAME = "GOBLIN";

    public Goblin(String playerName, String avatarName, String avatarClass, int money, Inventory inventory) {
        super(playerName, avatarName, avatarClass, money, inventory);
        this.map_abilities = GameClasses.createGoblinAbilities();
    }

}
