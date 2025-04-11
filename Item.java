public class Item {
    private final String name;
    private final ItemType type;
    private final int power;

    public Item(String name, ItemType type, int power) {
        this.name = name;
        this.type = type;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    public void use(Player player) {
        switch (type) {
            case HEALTH_POTION:
                int healAmount = power;
                player.heal(healAmount);
                System.out.println(player.getName() + " menggunakan " + name + " dan memulihkan " + healAmount + " HP!");
                break;
            case STRENGTH_POTION:
                player.addTemporaryAttackBonus(power);
                System.out.println(player.getName() + " menggunakan " + name + " dan mendapatkan +" + power + " attack power untuk ronde berikutnya!");
                break;
        }
    }
}

enum ItemType {
    HEALTH_POTION, STRENGTH_POTION
}
