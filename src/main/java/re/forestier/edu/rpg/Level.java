package re.forestier.edu.rpg;

public class Level {

    public static final int[] LEVEL_XP_REQUIREMENTS = {
        0, 10, 27, 57, 111
    };

    public static int getLevel(int xp) {
        for (int i = 0; i < LEVEL_XP_REQUIREMENTS.length - 1; i++) {
            if (xp < LEVEL_XP_REQUIREMENTS[i + 1]) {
                return i +1;
            }
        }
        return LEVEL_XP_REQUIREMENTS.length;
    }

    
}
