package re.forestier.edu;

import re.forestier.edu.rpg.Inventory.Inventory;
import re.forestier.edu.rpg.Inventory.Item;
import re.forestier.edu.rpg.PlayerTypes.Player;
import re.forestier.edu.rpg.UpdatePlayer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class PlayerTest {

    // --- Player Creation Tests ---


    @Test
    public void testPlayerCreation() {
        Player testPlayer = new Player("John", "Hero", "ARCHER", 100, new Inventory());
        assertNotNull(testPlayer, "Player should be created successfully.");
        assertEquals("ARCHER", testPlayer.getAvatarClass(), "Avatar class should be ARCHER.");
    }

    @Test
    public void testPlayerCreationInvalid() {
        Player testPlayer = new Player("John", "Hero", "INVALID_CLASS", 100, new Inventory());
        assertNull(testPlayer.getAvatarClass(), "Invalid avatar class should result in a null value.");
    }

    // --- Player Level Up Tests ---

    @Test
    public void testPlayerLevelUp() {
        Player testPlayer = new Player("John", "Hero", "ADVENTURER", 100, new Inventory());
        UpdatePlayer.addXp(testPlayer, 20);
        assertEquals(2, testPlayer.retrieveLevel(), "Player should level up to level 2 with 20 XP.");
    }
    @Test
    public void testPlayerLevelNegative() {
        Player testPlayer = new Player("John", "Hero", "ADVENTURER", 100, new Inventory());
        UpdatePlayer.addXp(testPlayer, -20);
        assertEquals(1, testPlayer.retrieveLevel(), "Player should level up to level 2 with 20 XP.");
    }
    @Test
    public void testPlayerLevelNull() {
        Player testPlayer = new Player("John", "Hero", "ADVENTURER", 100, new Inventory());
        UpdatePlayer.addXp(testPlayer, -200000);
        assertEquals(1, testPlayer.retrieveLevel(), "Player should level up to level 2 with 20 XP.");
    }
    @Test
    public void testPlayerLevelUpMultiple() {
        Player testPlayer = new Player("John", "Hero", "ADVENTURER", 100, new Inventory());
        assertEquals(1, testPlayer.retrieveLevel(), "Player should level up to level 3 with 56 XP.");
        UpdatePlayer.addXp(testPlayer, 20);
        assertEquals(2, testPlayer.retrieveLevel(), "Player should level up to level 3 with 56 XP.");

        UpdatePlayer.addXp(testPlayer, 20);
        assertEquals(3, testPlayer.retrieveLevel(), "Player should level up to level 3 with 56 XP.");

        UpdatePlayer.addXp(testPlayer, 54);
        assertEquals(4, testPlayer.retrieveLevel(), "Player should level up to level 4 with 110 XP.");

        UpdatePlayer.addXp(testPlayer, 222);
        assertEquals(5, testPlayer.retrieveLevel(), "Player should level up to level 5 with 332 XP.");
    }

    @Test
    public void testPlayerLevelSame() {
        Player testPlayer = new Player("John", "Hero", "ADVENTURER", 100, new Inventory());
        UpdatePlayer.addXp(testPlayer, 5);
        assertEquals(1, testPlayer.retrieveLevel(), "Player should stay at level 1 with only 5 XP.");
    }

    // --- Player Inventory Tests ---

    @Test
    public void testPlayerInventoryNotEmptyAfterLevelUp() {
        Player testPlayer = new Player("John", "Hero", "DWARF", 100, new Inventory());
        testPlayer.inventory.addItem("Magic Bow");
        UpdatePlayer.addXp(testPlayer, 60);
        assertFalse(testPlayer.inventory.isEmpty(), "Player inventory should not be empty after leveling up.");
    }

    // --- Player Money Tests ---

    @Test
    public void testAddMoney() {
        Player testPlayer = new Player("John", "Hero", "ADVENTURER", 100, new Inventory());
        testPlayer.addMoney(200);
        assertEquals(300, testPlayer.money.intValue(), "Player should have 300 money after adding 200.");
    }

    @Test
    public void testAddMoneyNegative() {
        Player testPlayer = new Player("John", "Hero", "ADVENTURER", 100, new Inventory());
        testPlayer.addMoney(-500);
        assertEquals(-400, testPlayer.money.intValue(), "Player should have -400 money after adding -500.");
    }

    @Test
    public void testRemoveMoney() {
        Player testPlayer = new Player("John", "Hero", "ADVENTURER", 200, new Inventory());
        testPlayer.removeMoney(50);
        assertEquals(150, testPlayer.money.intValue(), "Player should have 150 money after removing 50.");
    }
    @Test
    public void testRemoveNegativeMoney() {
        Player testPlayer = new Player("John", "Hero", "ADVENTURER", 200, new Inventory());
        testPlayer.removeMoney(-50);
        assertEquals(250, testPlayer.money.intValue(), "Player should have 150 money after removing 50.");
    }

    @Test
    public void testRemoveExact() {
        Player testPlayer = new Player("John", "Hero", "ADVENTURER", 200, new Inventory());
        testPlayer.removeMoney(200);
        assertEquals(0, testPlayer.money.intValue(), "Player should have 150 money after removing 50.");
    }

    @Test
    public void testRemoveZeroMoney() {
        Player testPlayer = new Player("John", "Hero", "ADVENTURER", 200, new Inventory());
        testPlayer.removeMoney(0);
        assertEquals(200, testPlayer.money.intValue(), "Player should have 150 money after removing 50.");
    }
    @Test
    public void testRemoveMoneyInvalid() {
        Player testPlayer = new Player("John", "Hero", "ADVENTURER", 50, new Inventory());
        assertThrows(IllegalArgumentException.class, () -> {
            testPlayer.removeMoney(100);
        }, "Removing more money than available should throw an exception.");
    }

    // --- Player XP Tests ---

    @Test
    public void testGetXp() {
        Player testPlayer = new Player("John", "Hero", "ADVENTURER", 50, new Inventory());
        assertEquals(0, testPlayer.getXp(), "Player should have 0 XP at the beginning.");
    }

    // --- Player Health Points Tests ---

    @Test
    public void testMajFinDeTour() {
        Player testPlayer = new Player("John", "Hero", "ADVENTURER", 50, new Inventory());
        testPlayer.currentHealthPoints = 0;
        UpdatePlayer.majFinDeTour(testPlayer);
        assertEquals(0, testPlayer.currentHealthPoints, "Player should have 0 health points.");
        testPlayer.currentHealthPoints = 0;
        UpdatePlayer.majFinDeTour(testPlayer);
        assertTrue(testPlayer.currentHealthPoints == 0, "Player should be KO.");
    
        // Test case where health points are at full
        testPlayer.currentHealthPoints = testPlayer.healthPoints;
        UpdatePlayer.majFinDeTour(testPlayer);
        assertEquals(testPlayer.healthPoints, testPlayer.currentHealthPoints);

        // Test case where health points are below half
        testPlayer.currentHealthPoints = testPlayer.healthPoints / 4;
        UpdatePlayer.majFinDeTour(testPlayer);
        if (testPlayer.getAvatarClass().equals("DWARF") && testPlayer.inventory.contains("Holy Elixir")) {
            assertEquals(testPlayer.healthPoints / 4 + 2, testPlayer.currentHealthPoints); // Healing for dwarf
        }
            testPlayer.inventory.addItem("Magic Bow");
        testPlayer.currentHealthPoints = testPlayer.healthPoints / 4;
        UpdatePlayer.majFinDeTour(testPlayer);
        assertEquals(testPlayer.healthPoints / 4 + testPlayer.currentHealthPoints / 8, testPlayer.currentHealthPoints);
    
    }

    @Test
    public void testMajFinDeTourDwarfWithHolyElixir() {
        Player testPlayer = new Player("John", "Hero", "DWARF", 50, new Inventory());
        testPlayer.healthPoints = 20;
        testPlayer.currentHealthPoints = 9;
        testPlayer.inventory.addItem("Holy Elixir");
        UpdatePlayer.majFinDeTour(testPlayer);
        assertEquals(11, testPlayer.currentHealthPoints, "Dwarf Player should have 11 health points.");
    }
    @Test
    public void testMajFinDeTourDwarfWithAnotherItem() {
        Player testPlayer = new Player("John", "Hero", "DWARF", 50, new Inventory());
        testPlayer.healthPoints = 20;
        testPlayer.currentHealthPoints = 9;
        testPlayer.inventory.addItem("No Holy Elixir");
        UpdatePlayer.majFinDeTour(testPlayer);
        assertEquals(10, testPlayer.currentHealthPoints, "Dwarf Player should have 10 health points.");
    }

    @Test
    public void testMajFinDeTourAdventurer() {
        Player testPlayer = new Player("John", "Hero", "ADVENTURER", 50, new Inventory());
        testPlayer.healthPoints = 20;
        testPlayer.currentHealthPoints = 9;
        UpdatePlayer.majFinDeTour(testPlayer);
        assertEquals(10, testPlayer.currentHealthPoints, "Adventurer Player should have 10 health points.");
    }

    @Test
    public void testMajFinDeTourAdventurerWithHighXp() {
        Player testPlayer = new Player("John", "Hero", "ADVENTURER", 50, new Inventory());
        testPlayer.healthPoints = 20;
        UpdatePlayer.addXp(testPlayer, 990);
        testPlayer.currentHealthPoints = 9;
        UpdatePlayer.majFinDeTour(testPlayer);
        assertEquals(11, testPlayer.currentHealthPoints, "Adventurer Player should have 11 health points after leveling up.");
    }
@Test
    void testAffichageJoueurWithInventory() {
        Player testPlayer = new Player("John", "Hero", "ARCHER", 50, new Inventory());
        testPlayer.inventory = new Inventory();
        testPlayer.inventory.addItem("Sword");
        testPlayer.inventory.addItem("Shield");

        assertTrue(testPlayer.inventory.contains("Sword"), "Inventory item 'Sword' should be displayed");
        assertTrue(testPlayer.inventory.contains("Shield"), "Inventory item 'Shield' should be displayed");
    }
    @Test
    public void testMajFinDeTourArcherWithMagicBow() {
        Player testPlayer = new Player("John", "Hero", "ARCHER", 50, new Inventory());
        testPlayer.healthPoints = 20;
        testPlayer.currentHealthPoints = 9;
        testPlayer.inventory.addItem("Magic Bow");
        UpdatePlayer.majFinDeTour(testPlayer);
        assertEquals(10, testPlayer.currentHealthPoints, "Archer Player should have 10 health points.");
    } @Test
    public void testMajFinDeTourArcherWithNull() {
        Player testPlayer = new Player("John", "Hero", "ARCHER", 50, new Inventory());
        testPlayer.healthPoints = 20;
        testPlayer.currentHealthPoints = 9;
        testPlayer.inventory.addItem(null);
        UpdatePlayer.majFinDeTour(testPlayer);
        assertEquals(10, testPlayer.currentHealthPoints, "Archer Player should have 10 health points.");
    }
    @Test
    void testPlayerKO() {
        Player testPlayer = new Player("John", "Hero", "ARCHER", 50, new Inventory());
        testPlayer.currentHealthPoints = 0;

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        UpdatePlayer.majFinDeTour(testPlayer);

        System.setOut(originalOut);

        assertTrue(outContent.toString().contains("Le joueur est KO !"));
    }
    @Test
    public void testMajFinDeTourArcherWithoutBow() {
        Player testPlayer = new Player("John", "Hero", "ARCHER", 50, new Inventory());
        testPlayer.healthPoints = 20;
        testPlayer.currentHealthPoints = 9;
        testPlayer.inventory.addItem("No Bow");
        UpdatePlayer.majFinDeTour(testPlayer);
        assertEquals(10, testPlayer.currentHealthPoints, "Archer Player should have 10 health points.");
    }

    @Test
    public void testMajFinDeTourFullHealth() {
        Player testPlayer = new Player("John", "Hero", "ARCHER", 50, new Inventory());
        testPlayer.healthPoints = 20;
        testPlayer.currentHealthPoints = 20;
        UpdatePlayer.majFinDeTour(testPlayer);
        assertEquals(20, testPlayer.currentHealthPoints, "Player should remain at full health points.");
    }

    @Test
    public void testMajFinDeTourFullHealthSur2() {
        Player testPlayer = new Player("John", "Hero", "ARCHER", 50, new Inventory());
        testPlayer.healthPoints = 20;
        testPlayer.currentHealthPoints = 10;
        UpdatePlayer.majFinDeTour(testPlayer);
        assertEquals(10, testPlayer.currentHealthPoints, "Player should remain at full health points.");
    }

    @Test
    public void testPlayerCreationWithNegativeMoney() {
        Player testPlayer = new Player("John", "Hero", "ARCHER", -100, new Inventory());
        assertEquals(-100, testPlayer.money.intValue(), "Player should have negative money.");
    }

    @Test
    public void testPlayerLevelMaxXP() {
        Player testPlayer = new Player("John", "Hero", "ADVENTURER", 100, new Inventory());
        UpdatePlayer.addXp(testPlayer, Integer.MAX_VALUE);
        assertTrue(testPlayer.retrieveLevel()== 5, "Player should level up to the maximum level possible.");
    }
    @Test
    public void testPlayerLevelMinXP() {
        Player testPlayer = new Player("John", "Hero", "ADVENTURER", 100, new Inventory());
        UpdatePlayer.addXp(testPlayer, Integer.MIN_VALUE);
        assertTrue(testPlayer.retrieveLevel() == 1, "Player should level up to the minimum level possible.");
    }
    @Test
    void testMagicBowCondition() {
        Player testPlayer = new Player("John", "Hero", "ARCHER", 100, new Inventory());
        testPlayer.currentHealthPoints = 40; 

        testPlayer.inventory = new Inventory();
        testPlayer.inventory.addItem("Magic Bow");

        UpdatePlayer.majFinDeTour(testPlayer);

        int expectedHealthIncrease = 1+ testPlayer.currentHealthPoints + testPlayer.currentHealthPoints/8-1; 

        assertEquals(expectedHealthIncrease, testPlayer.currentHealthPoints);
    }
    @Test
    public void testAddMoneyZero() {
        Player testPlayer = new Player("John", "Hero", "ADVENTURER", 100, new Inventory());
        testPlayer.addMoney(0);
        assertEquals(100, testPlayer.money.intValue(), "Adding 0 money should not change the Player's money.");
    }


    // Goblin Tests
    @Test
    public void testGoblinLevelUp() {
        Player testPlayer = new Player("John", "Hero", "GOBLIN", 100, new Inventory());
        UpdatePlayer.addXp(testPlayer, 20);
        assertEquals(2, testPlayer.retrieveLevel(), "Goblin should level up to level 2 with 20 XP.");
    }

    @Test
    public void testGoblinLevelUpMultiple() {
        Player testPlayer = new Player("John", "Hero", "GOBLIN", 100, new Inventory());
        assertEquals(1, testPlayer.retrieveLevel(), "Goblin should level up to level 3 with 56 XP.");
        UpdatePlayer.addXp(testPlayer, 20);
        assertEquals(2, testPlayer.retrieveLevel(), "Goblin should level up to level 3 with 56 XP.");

        UpdatePlayer.addXp(testPlayer, 20);
        assertEquals(3, testPlayer.retrieveLevel(), "Goblin should level up to level 3 with 56 XP.");

        UpdatePlayer.addXp(testPlayer, 54);
        assertEquals(4, testPlayer.retrieveLevel(), "Goblin should level up to level 4 with 110 XP.");

        UpdatePlayer.addXp(testPlayer, 222);
        assertEquals(5, testPlayer.retrieveLevel(), "Goblin should level up to level 5 with 332 XP.");
    }

    @Test
    public void testGoblinLevelSame() {
        Player testPlayer = new Player("John", "Hero", "GOBLIN", 100, new Inventory());
        UpdatePlayer.addXp(testPlayer, 5);
        assertEquals(1, testPlayer.retrieveLevel(), "Goblin should stay at level 1 with only 5 XP.");
    }

    @Test
    public void testGoblinLevelMaxXP() {
        Player testPlayer = new Player("John", "Hero", "GOBLIN", 100, new Inventory());
        UpdatePlayer.addXp(testPlayer, Integer.MAX_VALUE);
        assertTrue(testPlayer.retrieveLevel() == 5, "Goblin should level up to the maximum level possible.");
    }

    @Test
    public void testGoblinLevelMinXP() {
        Player testPlayer = new Player("John", "Hero", "GOBLIN", 100, new Inventory());
        UpdatePlayer.addXp(testPlayer, Integer.MIN_VALUE);
        assertTrue(testPlayer.retrieveLevel() == 1, "Goblin should level up to the minimum level possible.");
    }


    @Test
    public void testSell() {
        Player testPlayer = new Player("John", "Hero", "ADVENTURER", 100, new Inventory());
        testPlayer.inventory.addItem("Lookout Ring");
        Item item = testPlayer.getItem("Lookout Ring");
        testPlayer.sell(item);
        assertEquals(200, testPlayer.money.intValue(), "Player should have 200 money after selling a Lookout Ring.");
        assertFalse(testPlayer.inventory.contains("Lookout Ring"), "Player should not have a Lookout Ring in their inventory after selling it.");
    }
    


    @Test
    public void testAddItem() {
        Player testPlayer = new Player("John", "Hero", "ADVENTURER", 100, new Inventory());
        testPlayer.addItem("Lookout Ring");
        testPlayer.addItem("Scroll of Stupidity");
        assertEquals(3, testPlayer.poidsActuel.intValue(), "Player should have 3 poids after adding Lookout Ring and Scroll of Stupidity.");
        testPlayer.addItem("Draupnir");
        assertEquals(6, testPlayer.poidsActuel.intValue(), "Player should have 6 poids after adding Draupnir.");
        testPlayer.addItem("Magic Charm");
        assertEquals(6, testPlayer.poidsActuel.intValue(), "Player should have 6 poids after adding Magic Charm because it exceeds the maximum weight.");
}

}
