package edu.andover.elee.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import edu.andover.elee.MainApp;
import edu.andover.elee.model.PhysicsAnimation;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartController implements Initializable{

	@FXML
	private Button letsButton;

	@FXML
	private TextField numberBallsInput;

	@FXML
	private Text error;

	@FXML
	private void buttonChangeScene() throws IOException {
		Stage stage;
		Parent root;

		if (isValidBallNumber(numberBallsInput.getText()) 
				&& Integer.parseInt(numberBallsInput.getText()) == 0) {
			error.setText("Zero balls? How about not."); 
		} else if (isValidBallNumber(numberBallsInput.getText())
				&& Integer.parseInt(numberBallsInput.getText()) > 50) {
			error.setText("Okay. Let's not get ahead of ourselves now. Lower that number.");
		} else if (isValidBallNumber(numberBallsInput.getText())) {

			stage = (Stage)letsButton.getScene().getWindow();

			MainApp.setBalls(Integer.parseInt(numberBallsInput.getText()));

			root = FXMLLoader.load(getClass().getResource("BounceLayout.fxml"));

			Scene scene = new Scene(root);

			scene.setOnKeyPressed(key -> {

				if(PhysicsAnimation.isPaused == false && key.getCode() == KeyCode.P) {
					PhysicsAnimation.stopAnimation();
					PhysicsAnimation.isPaused = true;
				}
				else if(PhysicsAnimation.isPaused == true && key.getCode() == KeyCode.P) {
					PhysicsAnimation.startAnimation();
					PhysicsAnimation.isPaused = false;
				}
			});

			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();

		} else {
			error.setText("Please enter a valid number.");
		}
	}

	private BooleanBinding booleanBind;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		booleanBind = numberBallsInput.textProperty().isEqualTo("");
		letsButton.disableProperty().bind(booleanBind);
	}

	private Boolean isValidBallNumber(String text) {
		if (text.isEmpty()) { return false; }

		try { 
			int number = Integer.parseInt(text);
			if(number < 0) 
				return false;
		} catch(NumberFormatException e) { 
			return false; 
		} catch(NullPointerException e) {
			return false;
		}

		return true;
	}
}
