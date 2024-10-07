package re.forestier.edu;

import re.forestier.edu.rpg.player;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.UpdatePlayer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;


public class PlayerTest {

    // --- Player Creation Tests ---


    @Test
    public void testPlayerCreation() {
        player testPlayer = new player("John", "Hero", "ARCHER", 100, new ArrayList<>());
        assertNotNull(testPlayer, "Player should be created successfully.");
        assertEquals("ARCHER", testPlayer.getAvatarClass(), "Avatar class should be ARCHER.");
    }

    @Test
    public void testPlayerCreationInvalid() {
        player testPlayer = new player("John", "Hero", "INVALID_CLASS", 100, new ArrayList<>());
        assertNull(testPlayer.getAvatarClass(), "Invalid avatar class should result in a null value.");
    }

    // --- Player Level Up Tests ---

    @Test
    public void testPlayerLevelUp() {
        player testPlayer = new player("John", "Hero", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(testPlayer, 20);
        assertEquals(2, testPlayer.retrieveLevel(), "Player should level up to level 2 with 20 XP.");
    }
    @Test
    public void testPlayerLevelNegative() {
        player testPlayer = new player("John", "Hero", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(testPlayer, -20);
        assertEquals(1, testPlayer.retrieveLevel(), "Player should level up to level 2 with 20 XP.");
    }
    @Test
    public void testPlayerLevelNull() {
        player testPlayer = new player("John", "Hero", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(testPlayer, -200000);
        assertEquals(1, testPlayer.retrieveLevel(), "Player should level up to level 2 with 20 XP.");
    }
    @Test
    public void testPlayerLevelUpMultiple() {
        player testPlayer = new player("John", "Hero", "ADVENTURER", 100, new ArrayList<>());
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
        player testPlayer = new player("John", "Hero", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(testPlayer, 5);
        assertEquals(1, testPlayer.retrieveLevel(), "Player should stay at level 1 with only 5 XP.");
    }

    // --- Player Inventory Tests ---

    @Test
    public void testPlayerInventoryNotEmptyAfterLevelUp() {
        player testPlayer = new player("John", "Hero", "DWARF", 100, new ArrayList<>());
        testPlayer.inventory.add("Magic Bow");
        UpdatePlayer.addXp(testPlayer, 60);
        assertFalse(testPlayer.inventory.isEmpty(), "Player inventory should not be empty after leveling up.");
    }

    // --- Player Money Tests ---

    @Test
    public void testAddMoney() {
        player testPlayer = new player("John", "Hero", "ADVENTURER", 100, new ArrayList<>());
        testPlayer.addMoney(200);
        assertEquals(300, testPlayer.money.intValue(), "Player should have 300 money after adding 200.");
    }

    @Test
    public void testAddMoneyNegative() {
        player testPlayer = new player("John", "Hero", "ADVENTURER", 100, new ArrayList<>());
        testPlayer.addMoney(-500);
        assertEquals(-400, testPlayer.money.intValue(), "Player should have -400 money after adding -500.");
    }

    @Test
    public void testRemoveMoney() {
        player testPlayer = new player("John", "Hero", "ADVENTURER", 200, new ArrayList<>());
        testPlayer.removeMoney(50);
        assertEquals(150, testPlayer.money.intValue(), "Player should have 150 money after removing 50.");
    }
    @Test
    public void testRemoveNegativeMoney() {
        player testPlayer = new player("John", "Hero", "ADVENTURER", 200, new ArrayList<>());
        testPlayer.removeMoney(-50);
        assertEquals(250, testPlayer.money.intValue(), "Player should have 150 money after removing 50.");
    }

    @Test
    public void testRemoveExact() {
        player testPlayer = new player("John", "Hero", "ADVENTURER", 200, new ArrayList<>());
        testPlayer.removeMoney(200);
        assertEquals(0, testPlayer.money.intValue(), "Player should have 150 money after removing 50.");
    }

    @Test
    public void testRemoveZeroMoney() {
        player testPlayer = new player("John", "Hero", "ADVENTURER", 200, new ArrayList<>());
        testPlayer.removeMoney(0);
        assertEquals(200, testPlayer.money.intValue(), "Player should have 150 money after removing 50.");
    }
    @Test
    public void testRemoveMoneyInvalid() {
        player testPlayer = new player("John", "Hero", "ADVENTURER", 50, new ArrayList<>());
        assertThrows(IllegalArgumentException.class, () -> {
            testPlayer.removeMoney(100);
        }, "Removing more money than available should throw an exception.");
    }

    // --- Player XP Tests ---

    @Test
    public void testGetXp() {
        player testPlayer = new player("John", "Hero", "ADVENTURER", 50, new ArrayList<>());
        assertEquals(0, testPlayer.getXp(), "Player should have 0 XP at the beginning.");
    }

    // --- Player Health Points Tests ---

    @Test
    public void testMajFinDeTour() {
        player testPlayer = new player("John", "Hero", "ADVENTURER", 50, new ArrayList<>());
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
            testPlayer.inventory.add("Magic Bow");
        testPlayer.currentHealthPoints = testPlayer.healthPoints / 4;
        UpdatePlayer.majFinDeTour(testPlayer);
        assertEquals(testPlayer.healthPoints / 4 + testPlayer.currentHealthPoints / 8, testPlayer.currentHealthPoints);
    
    }

    @Test
    public void testMajFinDeTourDwarfWithHolyElixir() {
        player testPlayer = new player("John", "Hero", "DWARF", 50, new ArrayList<>());
        testPlayer.healthPoints = 20;
        testPlayer.currentHealthPoints = 9;
        testPlayer.inventory.add("Holy Elixir");
        UpdatePlayer.majFinDeTour(testPlayer);
        assertEquals(11, testPlayer.currentHealthPoints, "Dwarf player should have 11 health points.");
    }
    @Test
    public void testMajFinDeTourDwarfWithAnotherItem() {
        player testPlayer = new player("John", "Hero", "DWARF", 50, new ArrayList<>());
        testPlayer.healthPoints = 20;
        testPlayer.currentHealthPoints = 9;
        testPlayer.inventory.add("No Holy Elixir");
        UpdatePlayer.majFinDeTour(testPlayer);
        assertEquals(10, testPlayer.currentHealthPoints, "Dwarf player should have 10 health points.");
    }

    @Test
    public void testMajFinDeTourAdventurer() {
        player testPlayer = new player("John", "Hero", "ADVENTURER", 50, new ArrayList<>());
        testPlayer.healthPoints = 20;
        testPlayer.currentHealthPoints = 9;
        UpdatePlayer.majFinDeTour(testPlayer);
        assertEquals(10, testPlayer.currentHealthPoints, "Adventurer player should have 10 health points.");
    }

    @Test
    public void testMajFinDeTourAdventurerWithHighXp() {
        player testPlayer = new player("John", "Hero", "ADVENTURER", 50, new ArrayList<>());
        testPlayer.healthPoints = 20;
        UpdatePlayer.addXp(testPlayer, 990);
        testPlayer.currentHealthPoints = 9;
        UpdatePlayer.majFinDeTour(testPlayer);
        assertEquals(11, testPlayer.currentHealthPoints, "Adventurer player should have 11 health points after leveling up.");
    }
@Test
    void testAffichageJoueurWithInventory() {
        player testPlayer = new player("John", "Hero", "ARCHER", 50, new ArrayList<>());
        testPlayer.inventory = new ArrayList<>();
        testPlayer.inventory.add("Sword");
        testPlayer.inventory.add("Shield");

        String result = Affichage.afficherJoueur(testPlayer);
        assertTrue(result.contains("Sword"), "Inventory item 'Sword' should be displayed");
        assertTrue(result.contains("Shield"), "Inventory item 'Shield' should be displayed");
    }
    @Test
    public void testMajFinDeTourArcherWithMagicBow() {
        player testPlayer = new player("John", "Hero", "ARCHER", 50, new ArrayList<>());
        testPlayer.healthPoints = 20;
        testPlayer.currentHealthPoints = 9;
        testPlayer.inventory.add("Magic Bow");
        UpdatePlayer.majFinDeTour(testPlayer);
        assertEquals(10, testPlayer.currentHealthPoints, "Archer player should have 10 health points.");
    } @Test
    public void testMajFinDeTourArcherWithNull() {
        player testPlayer = new player("John", "Hero", "ARCHER", 50, new ArrayList<>());
        testPlayer.healthPoints = 20;
        testPlayer.currentHealthPoints = 9;
        testPlayer.inventory.add(null);
        UpdatePlayer.majFinDeTour(testPlayer);
        assertEquals(10, testPlayer.currentHealthPoints, "Archer player should have 10 health points.");
    }
    @Test
    void testPlayerKO() {
        player testPlayer = new player("John", "Hero", "ARCHER", 50, new ArrayList<>());
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
        player testPlayer = new player("John", "Hero", "ARCHER", 50, new ArrayList<>());
        testPlayer.healthPoints = 20;
        testPlayer.currentHealthPoints = 9;
        testPlayer.inventory.add("No Bow");
        UpdatePlayer.majFinDeTour(testPlayer);
        assertEquals(10, testPlayer.currentHealthPoints, "Archer player should have 10 health points.");
    }

    @Test
    public void testMajFinDeTourFullHealth() {
        player testPlayer = new player("John", "Hero", "ARCHER", 50, new ArrayList<>());
        testPlayer.healthPoints = 20;
        testPlayer.currentHealthPoints = 20;
        UpdatePlayer.majFinDeTour(testPlayer);
        assertEquals(20, testPlayer.currentHealthPoints, "Player should remain at full health points.");
    }

    @Test
    public void testMajFinDeTourFullHealthSur2() {
        player testPlayer = new player("John", "Hero", "ARCHER", 50, new ArrayList<>());
        testPlayer.healthPoints = 20;
        testPlayer.currentHealthPoints = 10;
        UpdatePlayer.majFinDeTour(testPlayer);
        assertEquals(10, testPlayer.currentHealthPoints, "Player should remain at full health points.");
    }

    @Test
    public void testPlayerCreationWithNegativeMoney() {
        player testPlayer = new player("John", "Hero", "ARCHER", -100, new ArrayList<>());
        assertEquals(-100, testPlayer.money.intValue(), "Player should have negative money.");
    }

    @Test
    public void testPlayerLevelMaxXP() {
        player testPlayer = new player("John", "Hero", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(testPlayer, Integer.MAX_VALUE);
        assertTrue(testPlayer.retrieveLevel()== 5, "Player should level up to the maximum level possible.");
    }
    @Test
    public void testPlayerLevelMinXP() {
        player testPlayer = new player("John", "Hero", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.addXp(testPlayer, Integer.MIN_VALUE);
        assertTrue(testPlayer.retrieveLevel() == 1, "Player should level up to the minimum level possible.");
    }
    @Test
    void testMagicBowCondition() {
        player testPlayer = new player("John", "Hero", "ARCHER", 100, new ArrayList<>());
        testPlayer.currentHealthPoints = 40; 

        testPlayer.inventory = new ArrayList<>();
        testPlayer.inventory.add("Magic Bow");

        UpdatePlayer.majFinDeTour(testPlayer);

        int expectedHealthIncrease = 1+ testPlayer.currentHealthPoints + testPlayer.currentHealthPoints/8-1; 

        assertEquals(expectedHealthIncrease, testPlayer.currentHealthPoints);
    }
    @Test
    public void testAddMoneyZero() {
        player testPlayer = new player("John", "Hero", "ADVENTURER", 100, new ArrayList<>());
        testPlayer.addMoney(0);
        assertEquals(100, testPlayer.money.intValue(), "Adding 0 money should not change the player's money.");
    }

}
