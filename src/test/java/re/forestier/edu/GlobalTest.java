package re.forestier.edu;

import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.Inventory;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.player;


import static org.approvaltests.Approvals.verify;

public class GlobalTest {

    @Test
    void testAffichageBase() {
        player player = new player("Florian", "Gnognak le Barbare", "ADVENTURER", 200, new Inventory());
        UpdatePlayer.addXp(player, 20);
        player.inventory = new Inventory();

        verify(Affichage.afficherJoueur(player));
    }
}
