package re.forestier.edu.rpg.PlayerTypes;

import re.forestier.edu.rpg.Inventory.Inventory;

public class Adventurer extends Player {
     public static final String CLASS_NAME = "ADVENTURER";

     public Adventurer(String playerName, String avatarName, String avatarClass, int money, Inventory inventory) {
          super(playerName, avatarName, avatarClass, money, inventory);
          this.map_abilities = GameClasses.createAdventurerAbilities();
     }

}
