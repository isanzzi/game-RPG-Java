public class Weapon {
    private final String name;
    private final int bonusDamage;

    public Weapon(String name, int bonusDamage) {
        this.name = name;
        this.bonusDamage = bonusDamage;
    }

    public String getName() {
        return name;
    }

    public int getBonusDamage() {
        return bonusDamage;
    }
}
