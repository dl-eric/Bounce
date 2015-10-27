package edu.andover.elee.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.andover.elee.MainApp;
import edu.andover.elee.model.PhysicsAnimation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class BounceController implements Initializable {

	@FXML
	private Text bounces;
	
	@FXML
	private Slider speedSlider;
	
	@FXML
	private Pane worldPane;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		PhysicsAnimation world = new PhysicsAnimation(worldPane);
		
		for(int i = 0; i < MainApp.getBalls(); i++) {
			world.spawnBall();
		}
		
		world.initializeWorld(worldPane);
	}

}
