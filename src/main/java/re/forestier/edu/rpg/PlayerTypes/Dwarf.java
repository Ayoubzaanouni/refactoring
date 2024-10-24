package re.forestier.edu.rpg.PlayerTypes;

import java.util.HashMap;

public class Dwarf {
     public static final String CLASS_NAME = "DWARF";

    public static HashMap<Integer, Abilities> createAbilities() {
        HashMap<Integer, Abilities> dwarfMap = new HashMap<>();

        Abilities level1 = new Abilities();
        level1.ALC = 4;
        level1.INT = 1;
        level1.ATK = 3;
        dwarfMap.put(1, level1);

        Abilities level2 = new Abilities();
        level2.DEF = 1;
        level2.ALC = 5;
        dwarfMap.put(2, level2);

        Abilities level3 = new Abilities();
        level3.ATK = 4;
        dwarfMap.put(3, level3);

        Abilities level4 = new Abilities();
        level4.DEF = 2;
        dwarfMap.put(4, level4);

        Abilities level5 = new Abilities();
        level5.CHA = 1;
        dwarfMap.put(5, level5);

        return dwarfMap;
    }
}
