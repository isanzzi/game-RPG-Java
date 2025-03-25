// Game.java

import java.util.Scanner;

public class Game {
    private final Player player;
    private final Character enemy;

    public Game(Player player, Character enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    public void start() {
        System.out.println("Game dimulai antara " + player.getName() + " dan " + enemy.getName() + "!");
        player.attack(enemy);
        if (enemy.getHealth() <= 0) {
            System.out.println(player.getName() + " menang!");
        } else {
            System.out.println(enemy.getName() + " masih hidup dengan HP: " + enemy.getHealth());
        }
    }

    public static Action getAction(int choice) {
        switch (choice) {
            case 1: return Action.ATTACK;
            case 2: return Action.DEFENSE;
            case 3: return Action.FLEX;
            default: return Action.DEFENSE;
        }
    }

    public static BattleResult resolveActions(Action player1Action, Action player2Action) {
        if (player1Action == player2Action) {
            return BattleResult.DRAW;
        }

        // Rock-Paper-Scissors style resolution:
        // Attack beats Flex
        // Defense beats Attack
        // Flex beats Defense
        if ((player1Action == Action.ATTACK && player2Action == Action.FLEX) ||
            (player1Action == Action.DEFENSE && player2Action == Action.ATTACK) ||
            (player1Action == Action.FLEX && player2Action == Action.DEFENSE)) {
            return BattleResult.PLAYER1_WINS;
        }
        
        return BattleResult.PLAYER2_WINS;
    }
}

enum BattleResult {
    PLAYER1_WINS,
    PLAYER2_WINS,
    DRAW
}