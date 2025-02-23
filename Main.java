public class Main {
    public static void main(String[] args) {
        Player hero = new Player("Pahlawan", 100, 20);
        Character monster = new Character("Naga", 80, 15);

        Weapon sword = new Weapon("Pedang Legendaris", 10);
        Magic fireball = new Magic("Bola Api", 15);

        hero.equipWeapon(sword);
        hero.learnMagic(fireball);

        Game game = new Game(hero, monster);
        game.start();
    }
}