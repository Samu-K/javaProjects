package fi.tuni.prog3.wordle;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;  
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class Wordle extends Application {
	ArrayList<Character> corrWord = new ArrayList<>();
	int activeRow=0;
	int activeCell=0;
	int round=0;
	boolean gameState=true;

	ArrayList<String> words = new ArrayList<>();
	ArrayList<Character> guess = new ArrayList<>();

	@Override
	public void start(Stage primary) throws Exception {
		fillWordList("words.txt");
		//System.out.println(String.format("Corr word is %s",words.get(0)));
		setCorrWord(words.get(round));

		HBox header = new HBox();
		Label infoLabel = new Label("");
		infoLabel.setId("infoBox");
		Button restartBtn = new Button("Start new game");
		restartBtn.setId("newGameBtn");
		restartBtn.setDefaultButton(false);
		header.getChildren().addAll(restartBtn, infoLabel);

		GridPane grid = new GridPane();
        grid.setPadding(new Insets(5));
        grid.setHgap(5);
        grid.setVgap(5);
		fillGrid(grid);

		BorderPane root = new BorderPane();
		root.setCenter(grid);
		root.setTop(header);

		Scene scene = new Scene(root,corrWord.size()*80+50,450);
		listenForInput(scene);

		restartBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				corrWord = new ArrayList<>();
				activeRow=0;
				activeCell=0;
				grid.getChildren().remove(0,6*words.get(round).length());
				round++;
				setCorrWord(words.get(round));
				fillGrid(grid);
				
				gameState=true;
				//System.out.println(String.format("New word is %s",words.get(round)));
			}
		});

		primary.setTitle("Wordle");
		primary.setScene(scene);
		primary.show();
		grid.requestFocus();
		grid.setFocusTraversable(true);
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void fillGrid(GridPane grid) {
		for (int row = 0; row < 6; row++) {
			for (int col=0;col<corrWord.size();col++) {
				String id = String.format("%d_%d",row,col);
				Label cell = new Label("");
				cell.setId(id);

				cell.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
				cell.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
				cell.setPadding(new Insets(5));
				cell.setPrefSize(80,80);
				grid.add(cell,col,row);
			}
		}
	}

	private void setActiveCellValue(String value, Scene scene) {
		if (activeCell==corrWord.size()) {
			return;
		}
		String activeId = String.format("#%d_%d",activeRow,activeCell);
		Label active = (Label) scene.lookup(activeId);
		active.setText(value);
		if (value.isEmpty() == false) {
			guess.add(value.charAt(0));
			activeCell++;
		}
	}

	private void setCellColor(int i, char c, Scene scene) {
		Color clr;
		switch (c) {
			case 'g': clr=Color.GREEN;
					  break;
			case 'y': clr=Color.ORANGE;
					  break;
			default: clr=Color.GRAY;
		}
		String id = String.format("#%d_%d", activeRow,i);
		Label active = (Label) scene.lookup(id);
		active.setBackground(new Background(new BackgroundFill(clr, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	private void listenForInput(Scene scene) {
		scene.setOnKeyPressed(event -> {
			String text = event.getText();
			
			if (text.chars().allMatch(Character::isLetter) && text.isEmpty() == false && gameState == true) {
				setActiveCellValue(text.toUpperCase(), scene); 
			} else if (event.getCode() == KeyCode.ENTER && gameState==true) {
				if (activeCell == corrWord.size()) {
					if (activeRow == 5) {
						Label inf = (Label) scene.lookup("#infoBox");
						inf.setText("Game over, you lost!");
						gameState=false;
					}

					for (int i=0;i < corrWord.size();i++) {
						char color='x';
						char guessLetter = guess.get(i);
						char corrLetter = corrWord.get(i);
						if (guessLetter==corrLetter) {
							color = 'g';
						} else if (corrWord.contains(guessLetter)) {
							color = 'y';
						}
						setCellColor(i,color,scene);
					}
					if (guess.equals(corrWord)) {
						Label inf = (Label) scene.lookup("#infoBox");
						inf.setText("Congratulations, you won!");
						gameState=false;
					}
					guess = new ArrayList<Character>();
					activeRow++;
					activeCell=0;
				} else {
					Label inf = (Label) scene.lookup("#infoBox");
					inf.setText("Give a complete word before pressing Enter!");
				}
			} else if (event.getCode() == KeyCode.BACK_SPACE && gameState == true) {
				activeCell--;
				setActiveCellValue("",scene);
				guess.remove(guess.size()-1);
			}
		});
	}

	private void fillWordList(String inputfile) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(inputfile));
			String line = reader.readLine();

			while (line != null) {
				words.add(line.toUpperCase());
				// read next line
				line = reader.readLine();
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setCorrWord(String word) {
		for (int i = 0;i<word.length();i++) {
			char letter = word.charAt(i);
			corrWord.add(letter);
		}
	}

}
