package re.forestier.edu;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.Inventory.Inventory;
import re.forestier.edu.rpg.PlayerTypes.Player;


public class Main {
    public static void main(String[] args) {
        Player firstPlayer = new Player("Florian", "Ruzberg de Rivehaute", "DWARF", 200, new Inventory());
        firstPlayer.addMoney(400);
        UpdatePlayer.addXp(firstPlayer, 15);
        System.out.println(firstPlayer.toString());
        System.out.println("------------------");
        UpdatePlayer.addXp(firstPlayer, 20);
        System.out.println(firstPlayer.toString());
    }
}