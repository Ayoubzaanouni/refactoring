package re.forestier.edu.rpg;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import re.forestier.edu.rpg.Inventory.Item;
import re.forestier.edu.rpg.Inventory.ItemList;
import re.forestier.edu.rpg.PlayerTypes.Abilities;
import re.forestier.edu.rpg.PlayerTypes.GameClasses;
import re.forestier.edu.rpg.PlayerTypes.Player;

public class UpdatePlayer {

    
    public static HashMap<String, HashMap<Integer, Abilities>> abilitiesPerTypeAndLevel() {
        HashMap<String, HashMap<Integer, Abilities>> abilitiesPerClass = new HashMap<>();
        abilitiesPerClass.put(GameClasses.ADVENTURER, GameClasses.createAdventurerAbilities());
        abilitiesPerClass.put(GameClasses.ARCHER, GameClasses.createArcherAbilities());
        abilitiesPerClass.put(GameClasses.DWARF, GameClasses.createDwarfAbilities());
        abilitiesPerClass.put(GameClasses.GOBLIN, GameClasses.createGoblinAbilities());
        return abilitiesPerClass;
    }

    public static Abilities getAbilities(Player player, int level) {
        String avatarClass = player.getAvatarClass();
        if (!GameClasses.isValidClass(avatarClass)) {
            return null;
        }
        return abilitiesPerTypeAndLevel().get(avatarClass).get(level);
    }

    // Add experience points to the player
    public static boolean addXp(Player player, int xp) {

        int currentLevel = Level.getLevel(player.getXp());//+xp
        int newXp = player.getXp() + xp;
        player.setXp(newXp);
        player.setLevel(newXp);
        int newLevel = player.retrieveLevel();

        if (newLevel > currentLevel) {
            grantRandomItem(player);
            updatePlayerAbilities(player, newLevel);
            return true;
        }
        return false;
    }

    // Grant a random item to the player's inventory
    private static void grantRandomItem(Player player) {
        Random random = new Random();
        List<Item> items = ItemList.getItems();
        Item randomItem = items.get(random.nextInt(items.size()));

        if (player.poids + randomItem.getWeight() > 7) {
            System.out.println("L'inventaire est plein !");
            return; 
        }
        player.inventory.addItem(randomItem.toString());
        player.poids += randomItem.getWeight();
    }

    // Update the player's abilities based on their class and level
    private static void updatePlayerAbilities(Player player, int newLevel) {
        Abilities abilities = getAbilities(player, newLevel);
        player.setAbilities(abilities);
    }

    // Update player status at the end of the turn
    public static void majFinDeTour(Player player) {
        if (player.currentHealthPoints <= 0) {
            System.out.println("Le joueur est KO !");
            return;
        }

        boolean isLowHealth = player.currentHealthPoints < player.healthPoints / 2;
        switch (player.getAvatarClass()) {
            case "DWARF":
                handleDwarfHealth(player, isLowHealth);
                break;
            case "ADVENTURER":
                handleAdventurerHealth(player, isLowHealth);
                break;
            case "ARCHER":
                handleArcherHealth(player, isLowHealth);
                break;
        }

        // Restore health if above max
        if (player.currentHealthPoints > player.healthPoints) {
            player.currentHealthPoints = player.healthPoints;
        }
    }

    // Handle health management for DWARF class
    private static void handleDwarfHealth(Player player, boolean isLowHealth) {
        if (isLowHealth) {
            player.currentHealthPoints += 1; // Dwarfs regain 2 health if low
            if (player.inventory.contains("Holy Elixir")) {
                player.currentHealthPoints += 1; // Extra health from Holy Elixir
            }
        }
    }

    // Handle health management for ADVENTURER class
    private static void handleAdventurerHealth(Player player, boolean isLowHealth) {
        if (isLowHealth) {
            player.currentHealthPoints += 2; // Adventurers regain 2 health if low
            if (player.retrieveLevel() < 3) {
                player.currentHealthPoints -= 1; // Penalty for lower levels
            }
        }
    }

    // Handle health management for ARCHER class
    private static void handleArcherHealth(Player player, boolean isLowHealth) {
        if (isLowHealth) {
            player.currentHealthPoints += 1; // Archers regain 1 health if low
            if (player.inventory.contains("Magic Bow")) {
                player.currentHealthPoints += (player.currentHealthPoints / 8) - 1; // Extra health if they have a Magic
                                                                                    // Bow
            }
        }
    }
}
