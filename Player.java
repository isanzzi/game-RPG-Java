// Player.java

public class Player extends Character {
    private int level;
    private int experience;
    private Weapon weapon;
    private Magic magic;
    private Action action;

    public Player(String name, int health, int attackPower) {
        super(name, health, attackPower);
        this.level = 1;
        this.experience = 0;
        this.weapon = null;
        this.magic = null;
    }

    public Magic getMagic() {
        return magic;
    }

    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void learnMagic(Magic magic) {
        this.magic = magic;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void attack(Character enemy) {
        int damage = attackPower;
        if (weapon != null) {
            damage += weapon.getBonusDamage();
            System.out.println(name + " menyerang dengan " + weapon.getName() + "!");
        }
        if (magic != null) {
            damage += magic.getMagicPower();
            System.out.println(name + " menggunakan sihir " + magic.getName() + "!");
        }
        System.out.println(name + " menyerang " + enemy.getName() + " dengan " + damage + " damage!");
        enemy.takeDamage(damage);
    }

    public void takeAction(Action action, Player opponent) {
        this.action = action;
        
        if (action == Action.ATTACK && opponent.getAction() == Action.ATTACK) {
            System.out.println("Kedua pemain menyerang! Keduanya terkena damage.");
            this.takeDamage(10);
            opponent.takeDamage(10);
        } else if (action == Action.ATTACK && opponent.getAction() == Action.DEFENSE) {
            System.out.println(name + " menyerang, tapi " + opponent.getName() + " bertahan! Tidak ada damage.");
        } else if (action == Action.DEFENSE && opponent.getAction() == Action.ATTACK) {
            System.out.println(opponent.getName() + " menyerang, tapi " + name + " bertahan! Tidak ada damage.");
        } else if (action == Action.ATTACK && opponent.getAction() == Action.FLEX) {
            System.out.println(name + " menyerang! " + opponent.getName() + " terkena damage.");
            opponent.takeDamage(10);
        } else if (action == Action.FLEX && opponent.getAction() == Action.ATTACK) {
            System.out.println(opponent.getName() + " menyerang! " + name + " terkena damage.");
            this.takeDamage(10);
        } else if (action == Action.DEFENSE && opponent.getAction() == Action.DEFENSE) {
            System.out.println("Keduanya bertahan. Tidak ada yang terjadi.");
        } else if (action == Action.FLEX && opponent.getAction() == Action.FLEX) {
            System.out.println("Keduanya melakukan flex! Keduanya terkena damage.");
            this.takeDamage(10);
            opponent.takeDamage(10);
        } else {
            System.out.println("Tidak ada yang terjadi.");
        }
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
