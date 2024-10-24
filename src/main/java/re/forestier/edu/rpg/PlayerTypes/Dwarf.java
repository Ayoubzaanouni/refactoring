package re.forestier.edu.rpg.PlayerTypes;


import re.forestier.edu.rpg.Inventory.Inventory;

public class Dwarf  extends Player{
     public static final String CLASS_NAME = "DWARF";

       public Dwarf(String playerName, String avatarName, String avatarClass, int money, Inventory inventory) {
        super(playerName, avatarName, avatarClass, money, inventory);
        this.map_abilities = GameClasses.createDwarfAbilities();
    }
}
