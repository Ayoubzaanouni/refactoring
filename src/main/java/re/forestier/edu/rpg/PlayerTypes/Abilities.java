package re.forestier.edu.rpg.PlayerTypes;

public class Abilities {
    public int INT;
    public int DEF;
    public int ATK;
    public int CHA;
    public int ALC;
    public int VIS;

    public Abilities() {
        this.INT = 0;
        this.DEF = 0;
        this.ATK = 0;
        this.CHA = 0;
        this.ALC = 0;
        this.VIS = 0;
    }


    public Abilities(int INT, int DEF, int ATK, int CHA, int ALC, int VIS) {
        this.INT = INT;
        this.DEF = DEF;
        this.ATK = ATK;
        this.CHA = CHA;
        this.ALC = ALC;
        this.VIS = VIS;
    }

    //return keys
    public String[] getKeys() {
        return new String[]{"INT", "DEF", "ATK", "CHA", "ALC", "VIS"};
    }

    //sets
    public void setINT(int INT) {
        this.INT = INT;
    }

    public void setDEF(int DEF) {
        this.DEF = DEF;
    }

    public void setATK(int ATK) {
        this.ATK = ATK;
    }

    public void setCHA(int CHA) {
        this.CHA = CHA;
    }

    public void setALC(int ALC) {
        this.ALC = ALC;
    }

    public void setVIS(int VIS) {
        this.VIS = VIS;
    }

    //gets

    public int getINT() {
        return INT;
    }

    public int getDEF() {
        return DEF;
    }

    public int getATK() {
        return ATK;
    }

    public int getCHA() {
        return CHA;
    }

    public int getALC() {
        return ALC;
    }

    public int getVIS() {
        return VIS;
    }

    // override toString
    @Override
    public String toString() {
        return "INT: " + INT + ", DEF: " + DEF + ", ATK: " + ATK + ", CHA: " + CHA + ", ALC: " + ALC + ", VIS: " + VIS;
    }

    
}
