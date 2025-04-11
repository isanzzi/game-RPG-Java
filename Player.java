import java.util.ArrayList;
import java.util.List;

public class Player extends Character {
    private int level;
    private int experience;
    private Inventory inventory;
    private Action action;
    private int temporaryAttackBonus;
    private List<SpecialAbility> specialAbilities;

    public Player(String name, int health, int attackPower) {
        super(name, health, attackPower);
        this.level = 1;
        this.experience = 0;
        this.inventory = new Inventory();
        this.temporaryAttackBonus = 0;
        this.specialAbilities = new ArrayList<>();
    }

    public Magic getMagic() {
        return inventory.getEquippedMagic();
    }

    public void equipWeapon(Weapon weapon) {
        inventory.equipWeapon(weapon);
    }

    public void learnMagic(Magic magic) {
        inventory.equipMagic(magic);
    }

    public void addSpecialAbility(SpecialAbility ability) {
        specialAbilities.add(ability);
        System.out.println(name + " mempelajari kemampuan khusus: " + ability.getName());
    }

    public List<SpecialAbility> getSpecialAbilities() {
        return specialAbilities;
    }

    public boolean hasSpecialAbilities() {
        return !specialAbilities.isEmpty();
    }

    public void useSpecialAbility(int index, Player target) {
        if (index >= 0 && index < specialAbilities.size()) {
            SpecialAbility ability = specialAbilities.get(index);
            ability.use(this, target);
        }
    }

    public void updateAbilityCooldowns() {
        for (SpecialAbility ability : specialAbilities) {
            ability.decreaseCooldown();
        }
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public int getAttackPower() {
        int totalPower = attackPower + temporaryAttackBonus;
        Weapon weapon = inventory.getEquippedWeapon();
        if (weapon != null) {
            totalPower += weapon.getBonusDamage();
        }
        Magic magic = inventory.getEquippedMagic();
        if (magic != null) {
            totalPower += magic.getMagicPower();
        }
        return totalPower;
    }

    public void attack(Character enemy) {
        int damage = getAttackPower();
        Weapon weapon = inventory.getEquippedWeapon();
        if (weapon != null) {
            System.out.println(name + " menyerang dengan " + weapon.getName() + "!");
        }
        Magic magic = inventory.getEquippedMagic();
        if (magic != null) {
            System.out.println(name + " menggunakan sihir " + magic.getName() + "!");
        }
        System.out.println(name + " menyerang " + enemy.getName() + " dengan " + damage + " damage!");
        enemy.takeDamage(damage);
        
        // Reset temporary attack bonus after attack
        if (temporaryAttackBonus > 0) {
            System.out.println("Bonus serangan sementara " + name + " berakhir!");
            temporaryAttackBonus = 0;
        }
    }

    public void takeAction(Action action, Player opponent) {
        this.action = action;
    }

    public void addItem(Item item) {
        inventory.addItem(item);
    }

    public void useItem(int index) {
        Item item = inventory.getItem(index);
        if (item != null) {
            item.use(this);
            inventory.removeItem(index);
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void heal(int amount) {
        int maxHealth = 100 + ((level - 1) * 20); // Base health + level bonuses
        health = Math.min(health + amount, maxHealth);
        System.out.println(name + " memulihkan " + amount + " HP! HP sekarang: " + health);
    }

    public void addTemporaryAttackBonus(int bonus) {
        temporaryAttackBonus += bonus;
        System.out.println(name + " mendapatkan bonus serangan sebesar +" + bonus + " untuk ronde berikutnya!");
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        System.out.println(name + " sekarang memiliki HP: " + health);
    }

    public void gainExperience(int exp) {
        experience += exp;
        if (experience >= 100) {
            levelUp();
            experience = 0;
        }
    }

    private void levelUp() {
        level++;
        attackPower += 5;
        health += 20;
        System.out.println(name + " naik level! Sekarang level " + level);
    }
}
