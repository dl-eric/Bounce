package edu.andover.elee.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import edu.andover.elee.MainApp;
import edu.andover.elee.model.PhysicsAnimation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class BounceController implements Initializable {

	@FXML 
	private Text bounces;

	@FXML
	private Slider speedSlider;

	@FXML
	private Pane worldPane;

	@FXML
	private void handleKeyInput(final KeyEvent k)
	{
		if (k.getCode() == KeyCode.P)
		{
			System.out.println("YAY");
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		PhysicsAnimation world = new PhysicsAnimation();
		bounces.textProperty().bindBidirectional(world.bounces, new NumberStringConverter());
		speedSlider.valueProperty().bindBidirectional(world.speed);

		for(int i = 0; i < MainApp.getBalls(); i++) {
			world.spawnBall(worldPane);
		}

		worldPane.setOnKeyPressed(( event -> {
			if (event.getCode() == KeyCode.P) {
				PhysicsAnimation.stopAnimation();
				System.out.println("YAY");
			}
		}));
	
		world.initializeWorld(worldPane);
	}
	
	public void changeScene() throws IOException {
		Parent root;
		Stage stage = MainApp.stage;

		root = FXMLLoader.load(getClass().getResource("EndLayout.fxml"));

		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
}
