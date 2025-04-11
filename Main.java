import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Player hero = new Player("Pahlawan", 100, 20);
        Player monster = new Player("Naga", 80, 15);  // Changed to Player for action support

        Weapon sword = new Weapon("Pedang Legendaris", 10);
        Magic fireball = new Magic("Bola Api", 15);

        // Add items to hero's inventory
        hero.addItem(new Item("Ramuan Kesehatan", ItemType.HEALTH_POTION, 30));
        hero.addItem(new Item("Ramuan Kekuatan", ItemType.STRENGTH_POTION, 15));
        
        // Add special abilities
        hero.addSpecialAbility(new SpecialAbility("Serangan Berapi", AbilityType.DAMAGE, 25, 3));
        hero.addSpecialAbility(new SpecialAbility("Penyembuhan Diri", AbilityType.HEAL, 20, 2));
        
        monster.addSpecialAbility(new SpecialAbility("Nafas Api", AbilityType.DAMAGE, 20, 2));

        hero.equipWeapon(sword);
        hero.learnMagic(fireball);

        try (Scanner scanner = new Scanner(System.in)) {
            while (hero.getHealth() > 0 && monster.getHealth() > 0) {
                // Update ability cooldowns at start of each turn
                hero.updateAbilityCooldowns();
                monster.updateAbilityCooldowns();
                
                System.out.println("\n=====================================");
                System.out.println("Giliran " + hero.getName() + ":");
                System.out.println("1. Aksi Dasar (Attack/Defense/Flex)");
                System.out.println("2. Gunakan Item");
                System.out.println("3. Gunakan Kemampuan Khusus");
                System.out.print("Pilih tindakan (1-3): ");
                int actionType = scanner.nextInt();
                
                switch (actionType) {
                    case 1:
                        System.out.println("\nPilih aksi: 1. Attack  2. Defense  3. Flex");
                        System.out.print("Masukkan pilihan (1-3): ");
                        int choice = scanner.nextInt();
                        
                        Action heroAction = Game.getAction(choice);
                        
                        // Random action for monster
                        int monsterChoice = (int)(Math.random() * 3) + 1;
                        Action monsterAction = Game.getAction(monsterChoice);
                        
                        System.out.println("\n" + hero.getName() + " menggunakan " + heroAction);
                        System.out.println(monster.getName() + " menggunakan " + monsterAction);
                        
                        BattleResult result = Game.resolveActions(heroAction, monsterAction);
                        
                        switch (result) {
                            case PLAYER1_WINS:
                                monster.takeDamage(hero.getAttackPower());
                                System.out.println(hero.getName() + " memenangkan ronde ini!");
                                break;
                            case PLAYER2_WINS:
                                hero.takeDamage(monster.getAttackPower());
                                System.out.println(monster.getName() + " memenangkan ronde ini!");
                                break;
                            case DRAW:
                                System.out.println("Ronde ini seri!");
                                break;
                        }
                        break;
                    case 2:
                        if (hero.getInventory().hasItems()) {
                            System.out.println("\nInventory:");
                            for (int i = 0; i < hero.getInventory().getItems().size(); i++) {
                                System.out.println((i+1) + ". " + hero.getInventory().getItem(i).getName());
                            }
                            System.out.print("Pilih item untuk digunakan (1-" + hero.getInventory().getItems().size() + "): ");
                            int itemChoice = scanner.nextInt() - 1;
                            hero.useItem(itemChoice);
                        } else {
                            System.out.println("Inventory kosong!");
                            continue;  // Skip monster's turn
                        }
                        break;
                    case 3:
                        if (hero.hasSpecialAbilities()) {
                            System.out.println("\nKemampuan Khusus:");
                            for (int i = 0; i < hero.getSpecialAbilities().size(); i++) {
                                SpecialAbility ability = hero.getSpecialAbilities().get(i);
                                System.out.println((i+1) + ". " + ability.getName() + 
                                                  " (" + (ability.isReady() ? "Siap" : 
                                                  "Cooldown: " + ability.getRemainingCooldown() + " ronde") + ")");
                            }
                            System.out.print("Pilih kemampuan (1-" + hero.getSpecialAbilities().size() + "): ");
                            int abilityChoice = scanner.nextInt() - 1;
                            hero.useSpecialAbility(abilityChoice, monster);
                        } else {
                            System.out.println("Tidak memiliki kemampuan khusus!");
                            continue;  // Skip monster's turn
                        }
                        break;
                }
                
                // Monster's turn if still alive
                if (monster.getHealth() > 0) {
                    System.out.println("\n=====================================");
                    System.out.println("Giliran " + monster.getName() + ":");
                    
                    // Monster AI: 70% chance to use basic attack, 30% chance to use special ability if available
                    if (monster.hasSpecialAbilities() && Math.random() < 0.3 && 
                        monster.getSpecialAbilities().get(0).isReady()) {
                        monster.useSpecialAbility(0, hero);
                    } else {
                        // Random action for monster
                        int monsterChoice = (int)(Math.random() * 3) + 1;
                        Action monsterAction = Game.getAction(monsterChoice);
                        System.out.println(monster.getName() + " menggunakan " + monsterAction);
                        
                        if (monsterAction == Action.ATTACK) {
                            hero.takeDamage(monster.getAttackPower());
                        }
                    }
                }
                
                System.out.println("\nStatus:");
                System.out.println(hero.getName() + " HP: " + hero.getHealth());
                System.out.println(monster.getName() + " HP: " + monster.getHealth());
            }
            
            String winner = hero.getHealth() > 0 ? hero.getName() : monster.getName();
            System.out.println("\nGame Over! " + winner + " menang!");
            
            if (hero.getHealth() > 0) {
                // Award experience when hero wins
                System.out.println(hero.getName() + " mendapatkan 50 pengalaman!");
                hero.gainExperience(50);
            }
        }
    }
}