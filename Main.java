import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Player hero = new Player("Pahlawan", 100, 20);
        Player monster = new Player("Naga", 80, 15);  // Changed to Player for action support

        Weapon sword = new Weapon("Pedang Legendaris", 10);
        Magic fireball = new Magic("Bola Api", 15);

        hero.equipWeapon(sword);
        hero.learnMagic(fireball);

        try (Scanner scanner = new Scanner(System.in)) {
            while (hero.getHealth() > 0 && monster.getHealth() > 0) {
                System.out.println("\nPilih aksi: 1. Attack  2. Defense  3. Flex");
                System.out.print("Masukkan pilihan (1-3): ");
                int choice = scanner.nextInt();
                
                // Random action for monster
                int monsterChoice = (int)(Math.random() * 3) + 1;
                
                Action heroAction = Game.getAction(choice);
                Action monsterAction = Game.getAction(monsterChoice);
                
                BattleResult result = Game.resolveActions(heroAction, monsterAction);
                
                System.out.println("\n" + hero.getName() + " menggunakan " + heroAction);
                System.out.println(monster.getName() + " menggunakan " + monsterAction);
                
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
                
                System.out.println("\nStatus:");
                System.out.println(hero.getName() + " HP: " + hero.getHealth());
                System.out.println(monster.getName() + " HP: " + monster.getHealth());
            }
            
            String winner = hero.getHealth() > 0 ? hero.getName() : monster.getName();
            System.out.println("\nGame Over! " + winner + " menang!");
        }
    }
}