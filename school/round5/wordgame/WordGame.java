import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordGame {
    private ArrayList<String> words = new ArrayList<>();
    private WordGameState currentState;
    private String correctWord;

    public WordGame(String wordFilename) {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(wordFilename));
            String line = reader.readLine();

            while (line != null) {
                this.words.add(line.toLowerCase());
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initGame(int wordIndex, int mistakeLimit) {
       this.correctWord = words.get(wordIndex % (this.words.size()));
       currentState = new WordGameState(
               "_".repeat(this.correctWord.length()), 
               0, 
               mistakeLimit, 
               this.correctWord.length()
        );

    }

    public boolean isGameActive() {
        if (this.currentState == null || this.currentState.active == false) {
            return false;
        } else {
            return true;
        }
    }

    public WordGameState getGameState() throws
        GameStateException {
        if (isGameActive()) {
            return this.currentState;
        } else {
            throw new GameStateException("There is currently no active word game!");
        }
    }

    public WordGameState guess(char c) throws 
        GameStateException{

        if (this.currentState == null || this.currentState.active == false) {
            throw new GameStateException("There is currently no active word game!");            
        }

        c = Character.toLowerCase(c);
            
        if (this.currentState.getWord().indexOf(c) == -1) {
            int indx = this.correctWord.indexOf(c);
            if (indx == -1) {
                this.currentState.mistakes += 1;
            } else {
                while (indx >= 0) {
                    this.currentState.missingChars -= 1;
                    
                    String start = this.currentState.getWord().substring(0,indx);
                    String end = this.currentState.getWord().substring(indx+1,this.currentState.getWord().length());
                    String newWord = start + c + end;
                    this.currentState.word = newWord;

                    indx = this.correctWord.indexOf(c,indx+1);
                }
            }
        } else {
            this.currentState.mistakes += 1;
        }

        if (this.currentState.getMistakes() > this.currentState.getMistakeLimit() || this.currentState.getMissingChars() == 0) {
            this.currentState.active = false;
            this.currentState.word = correctWord;
        }

        return this.currentState;
        
    }

    public WordGameState guess(String word) throws
        GameStateException {
        if ( this.currentState == null || this.currentState.active == false) {
            throw new GameStateException("There is currently no active word game!");
        } else {
            if ( word.equals(this.correctWord)) {
                this.currentState.word = correctWord;
                this.currentState.missingChars = 0;
                this.currentState.active = false;                
            } else {
                this.currentState.mistakes += 1;
                if (this.currentState.getMistakes() > this.currentState.getMistakeLimit()) {
                    this.currentState.word = correctWord;
                    this.currentState.active = false;

                    return this.currentState;
                }
            }
            return this.currentState;
        }
    }

    public static class WordGameState {
        private String word;
        private int mistakes;
        private int mistakeLimit;
        private int missingChars;
        private boolean active;
        
        private WordGameState(
                String word,
                int mistakes,
                int mistakeLimit,
                int missingChars ) {
            
            this.word = word;
            this.mistakes = mistakes;
            this.mistakeLimit = mistakeLimit;
            this.missingChars = missingChars;
            this.active = true;
        }

        public String getWord() {
         return this.word;
        }

        public int getMistakes() {
            return this.mistakes;
        }

        public int getMistakeLimit() {
            return this.mistakeLimit;
        }

        public int getMissingChars() {
            return this.missingChars;
        }
    }
}

