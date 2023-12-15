public class WGTest {
    public static void main(String[] args) {
        System.out.println("Test started");
        WordGame game = new WordGame("words.txt");
        game.initGame(3,2);
        game.guess('a');
        game.guess('j');
        game.guess('t');
        game.guess('t');
        game.guess('d');
        game.guess('u');
        game.guess('s');
        game.guess('e');
        game.guess('n');
        game.guess('h');
        game.guess('h');
        game.guess('h');

    }
}


