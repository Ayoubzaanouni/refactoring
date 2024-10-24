package re.forestier.edu.rpg.PlayerTypes;
import java.util.HashMap;

public class GameClasses {
    public static final String ARCHER = "ARCHER";
    public static final String ADVENTURER = "ADVENTURER";
    public static final String DWARF = "DWARF";

    public static final String[] CLASSES = {ARCHER, ADVENTURER, DWARF};

    public static final boolean isValidClass(String avatarClass) {
        for (String validClass : CLASSES) {
            if (validClass.equals(avatarClass)) {
                return true;
            }
        }
        return false;
    }
      // Create abilities for ADVENTURER class
      public static HashMap<Integer, Abilities> createAdventurerAbilities() {
        HashMap<Integer, Abilities> adventurerMap = new HashMap<>();

        Abilities level1 = new AbilitiesBuilder().setINT(1).setDEF(1).setATK(3)
                .setCHA(2).setALC(3).build();
        Abilities level2 = new AbilitiesBuilder().setINT(2).setCHA(3).build();
        Abilities level3 = new AbilitiesBuilder().setATK(5).setALC(1).build();
        Abilities level4 = new AbilitiesBuilder().setDEF(3).build();
        Abilities level5 = new AbilitiesBuilder().setVIS(1).setDEF(4).build();

        adventurerMap.put(1, level1);
        adventurerMap.put(2, level2);
        adventurerMap.put(3, level3);
        adventurerMap.put(4, level4);
        adventurerMap.put(5, level5);

        return adventurerMap;
    }

    
    // Create abilities for ARCHER class
    public static HashMap<Integer, Abilities> createArcherAbilities() {
        HashMap<Integer, Abilities> archerMap = new HashMap<>();

        Abilities level1 = new AbilitiesBuilder().setINT(1).setDEF(0).setATK(3)
                .setCHA(1).setALC(3).build();
        Abilities level2 = new AbilitiesBuilder().setDEF(1).setCHA(2).build();
        Abilities level3 = new AbilitiesBuilder().setATK(3).setALC(1).build();
        Abilities level4 = new AbilitiesBuilder().setDEF(2).build();
        Abilities level5 = new AbilitiesBuilder().setALC(4).build();

        archerMap.put(1, level1);
        archerMap.put(2, level2);
        archerMap.put(3, level3);
        archerMap.put(4, level4);
        archerMap.put(5, level5);

        return archerMap;
    }

    // Create abilities for DWARF class
    public static HashMap<Integer, Abilities> createDwarfAbilities() {
        HashMap<Integer, Abilities> dwarfMap = new HashMap<>();
        Abilities level1 = new AbilitiesBuilder().setINT(1).setDEF(0).setATK(3)
                .setCHA(0).setALC(4).build();

        Abilities level2 = new AbilitiesBuilder().setINT(0).setDEF(1).setATK(0)
                .setCHA(5).build();

        Abilities level3 = new AbilitiesBuilder().setINT(0).setDEF(0).setATK(4)
                .setCHA(0).build();

        Abilities level4 = new AbilitiesBuilder().setINT(0).setDEF(2).setATK(0)
                .setCHA(0).build();
        Abilities level5 = new AbilitiesBuilder().setINT(0).setDEF(0).setATK(0)
                .setCHA(1).build();
        dwarfMap.put(1, level1);
        dwarfMap.put(2, level2);
        dwarfMap.put(3, level3);
        dwarfMap.put(4, level4);
        dwarfMap.put(5, level5);

        return dwarfMap;
    }

    
}
