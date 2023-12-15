package fi.tuni.prog3.calc;

import javafx.event.ActionEvent;  
import javafx.event.EventHandler; 
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;

public class Calculator extends Application {
	double resultNum=0;
	double fieldOp1Int=0;
	double fieldOp2Int=0;

	@Override
	public void start(Stage primary) throws Exception {
		Label labelOp1 = new Label("First operand:");
		labelOp1.setId("labelOp1");
		Label labelOp2 = new Label("Second operand:");
		labelOp2.setId("labelOp2");
		Label fieldRes = new Label("");
		fieldRes.setId("fieldRes");
		fieldRes.setBackground(
			new Background(
				new BackgroundFill(
					Color.WHITE, 
					CornerRadii.EMPTY, 
					Insets.EMPTY
				)
			)
		);
		Label labelRes = new Label("Result:");
		labelRes.setId("labelRes");

		TextField fieldOp1 = new TextField();
		fieldOp1.setId("fieldOp1");
		TextField fieldOp2 = new TextField();
		fieldOp2.setId("fieldOp2");

		Button btnAdd = new Button("Add");
		btnAdd.setId("btnAdd");
		Button btnSub = new Button("Subtract");
		btnSub.setId("btnSub");
		Button btnMul = new Button("Multiply");
		btnMul.setId("btnMul");
		Button btnDiv = new Button("Divide");
		btnDiv.setId("btnDiv");

		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				fieldOp1Int = Double.parseDouble(fieldOp1.getText());
				fieldOp2Int = Double.parseDouble(fieldOp2.getText());

				resultNum= fieldOp1Int+fieldOp2Int;
				fieldRes.setText(Double.toString(resultNum));
			}
		});
		btnSub.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				fieldOp1Int = Double.parseDouble(fieldOp1.getText());
				fieldOp2Int = Double.parseDouble(fieldOp2.getText());

				resultNum= fieldOp1Int-fieldOp2Int;
				fieldRes.setText(Double.toString(resultNum));
			}
		});
		btnMul.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				fieldOp1Int = Double.parseDouble(fieldOp1.getText());
				fieldOp2Int = Double.parseDouble(fieldOp2.getText());

				resultNum= fieldOp1Int*fieldOp2Int;
				fieldRes.setText(Double.toString(resultNum));
			}
		});
		btnDiv.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				fieldOp1Int = Double.parseDouble(fieldOp1.getText());
				fieldOp2Int = Double.parseDouble(fieldOp2.getText());

				resultNum= fieldOp1Int/fieldOp2Int;
				fieldRes.setText(Double.toString(resultNum));
			}
		});

		GridPane root = new GridPane();

		Scene scene = new Scene(root,600,600);
		root.addRow(0,labelOp1,fieldOp1);
		root.addRow(1,labelOp2,fieldOp2);
		root.addRow(2,btnAdd);
		root.addRow(2,btnSub);
		root.addRow(2,btnMul);
		root.addRow(2,btnDiv);
		root.addRow(4,labelRes,fieldRes);

		primary.setTitle("Calculator");
		primary.setScene(scene);
		primary.show();
	}

	public static void main(String[] args) {

		launch(args);
	}
}
