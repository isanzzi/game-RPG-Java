public class Weapon {
    private String name;
    private int bonusDamage;

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
