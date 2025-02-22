import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Player player1 = new Player("Hero", 100, 15);
        Player player2 = new Player("Villain", 100, 15);

        Scanner scanner = new Scanner(System.in);

        while (player1.getHealth() > 0 && player2.getHealth() > 0) {
            System.out.println("\nPilih aksi: 1. Attack  2. Defense  3. Flex");
            System.out.print(player1.getName() + " pilih: ");
            int choice1 = scanner.nextInt();
            System.out.print(player2.getName() + " pilih: ");
            int choice2 = scanner.nextInt();

            Action action1 = getAction(choice1);
            Action action2 = getAction(choice2);

            player1.takeAction(action1, player2);
            player2.takeAction(action2, player1);
        }

        System.out.println("\nGame Over! Pemenang: " + (player1.getHealth() > 0 ? player1.getName() : player2.getName()));
    }

    public static Action getAction(int choice) {
        return switch (choice) {
            case 1 -> Action.ATTACK;
            case 2 -> Action.DEFENSE;
            case 3 -> Action.FLEX;
            default -> Action.DEFENSE;
        };
    }
}
