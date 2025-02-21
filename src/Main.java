public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.loadFromTxt("src/input.txt");
        game.solve();
    }
}