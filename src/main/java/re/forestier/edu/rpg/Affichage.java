package re.forestier.edu.rpg;

public class Affichage {

    public static String afficherJoueur(player player) {
        final String[] finalString = {"Joueur " + player.avatarName + " joué par " + player.playerName};
        finalString[0] += "\nNiveau : " + player.retrieveLevel() + " (XP totale : " + player.xp + ")";
        finalString[0] += "\n\nCapacités :";
        player.abilities.forEach((name, level) -> {
            finalString[0] += "\n   " + name + " : " + level;
        });
        finalString[0] += "\n\nInventaire :";
        player.inventory.display();

        return finalString[0];
    }
}
