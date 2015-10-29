package edu.andover.elee;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class MainApp extends Application{

	public static Stage stage;
	private Pane startLayout;
	
	private static int balls;
	
	//http://soundbible.com/1949-Pew-Pew.html#Pew Pew Sound
	public static final AudioClip BOUNCE_SOUND = new AudioClip("file:res/pew.wav");

	@Override
	public void start(Stage startStage) {
		stage = startStage;
		stage.setTitle("Balls");
		stage.setResizable(false);

		try {
			// Load FXML file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/StartLayout.fxml"));
			startLayout = (Pane) loader.load();
		
			// Show the Scene
			Scene scene = new Scene(startLayout);
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	

	public static void main(String[] args) {
		launch(args);
	}

	public static int getBalls() { return balls; }
	public static void setBalls(int b) { balls = b; }
}
