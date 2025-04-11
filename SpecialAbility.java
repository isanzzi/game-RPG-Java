public class SpecialAbility {
    private final String name;
    private final AbilityType type;
    private final int power;
    private int cooldown;
    private int currentCooldown;

    public SpecialAbility(String name, AbilityType type, int power, int cooldown) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.cooldown = cooldown;
        this.currentCooldown = 0;
    }

    public String getName() {
        return name;
    }

    public AbilityType getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    public boolean isReady() {
        return currentCooldown == 0;
    }

    public void resetCooldown() {
        currentCooldown = cooldown;
    }

    public void decreaseCooldown() {
        if (currentCooldown > 0) {
            currentCooldown--;
        }
    }

    public int getRemainingCooldown() {
        return currentCooldown;
    }

    public void use(Player user, Player target) {
        if (!isReady()) {
            System.out.println("Kemampuan " + name + " masih dalam cooldown!");
            return;
        }

        switch (type) {
            case DAMAGE:
                int damage = power;
                System.out.println(user.getName() + " menggunakan kemampuan khusus " + name + "!");
                target.takeDamage(damage);
                break;
            case HEAL:
                int healAmount = power;
                System.out.println(user.getName() + " menggunakan kemampuan penyembuhan " + name + "!");
                user.heal(healAmount);
                break;
            case BUFF:
                System.out.println(user.getName() + " menggunakan buff " + name + "!");
                user.addTemporaryAttackBonus(power);
                break;
        }

        resetCooldown();
    }
}

enum AbilityType {
    DAMAGE, HEAL, BUFF
}
