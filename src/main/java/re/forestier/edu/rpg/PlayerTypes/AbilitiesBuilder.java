package re.forestier.edu.rpg.PlayerTypes;

public class AbilitiesBuilder {
    private int intVal = 0;
    private int defVal = 0;
    private int atkVal = 0;
    private int chaVal = 0;
    private int alcVal = 0;
    private int visVal = 0;

    public AbilitiesBuilder setINT(int intVal) {
        if (intVal > 0) this.intVal = intVal;
        return this;
    }

    public AbilitiesBuilder setDEF(int defVal) {
        if (defVal > 0) this.defVal = defVal;
        return this;
    }

    public AbilitiesBuilder setATK(int atkVal) {
        if (atkVal > 0) this.atkVal = atkVal;
        return this;
    }

    public AbilitiesBuilder setCHA(int chaVal) {
        if (chaVal > 0) this.chaVal = chaVal;
        return this;
    }

    public AbilitiesBuilder setALC(int alcVal) {
        if (alcVal > 0) this.alcVal = alcVal;
        return this;
    }

    public AbilitiesBuilder setVIS(int visVal) {
        if (visVal > 0) this.visVal = visVal;
        return this;
    }

    public Abilities build() {
        Abilities abilities = new Abilities();
        abilities.setINT(intVal);
        abilities.setDEF(defVal);
        abilities.setATK(atkVal);
        abilities.setCHA(chaVal);
        abilities.setALC(alcVal);
        abilities.setVIS(visVal);
        return abilities;
    }
}

