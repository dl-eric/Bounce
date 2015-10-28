package edu.andover.elee.model;

import javafx.scene.layout.Pane;

import java.util.ListIterator;

import edu.andover.elee.MainApp;
import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PhysicsAnimation {
	private ObservableList<Ball> balls = FXCollections.observableArrayList();
	private final double radius = 32;

	private double xBounds = 600;
	private double yBounds = 400;

	public IntegerProperty bounces = new SimpleIntegerProperty(0);
	public DoubleProperty speed = new SimpleDoubleProperty(1);

	public static AnimationTimer timer;
	
	public void spawnBall(Pane pane) {
		double randomX = Math.random() * 520 + 32;
		double randomY = Math.random() * 320 + 32;
		//double randomSpeed = Math.random() * 900;
		double randomSpeed = 500;
		double randomAngle = Math.random() * 2 * Math.PI;
		double x_vel = randomSpeed * Math.cos(randomAngle);
		double y_vel = randomSpeed * Math.sin(randomAngle);
		Ball ball = new Ball(radius, randomX, randomY, x_vel, y_vel);
		balls.add(ball);
		pane.getChildren().add(ball.getBallView());
	}

	public void initializeWorld(final Pane pane) {

		final LongProperty lastUpdateTime = new SimpleLongProperty(0);
		timer = new AnimationTimer() {

			//Gets called every tick
			@Override
			public void handle(long timestamp) {

				if (lastUpdateTime.get() > 0) {
					long elapsedTime = timestamp - lastUpdateTime.get();
					checkCollisions();
					timeStep(elapsedTime);
				}

				lastUpdateTime.set(timestamp);
			}
		};
		timer.start();
	}

	private void timeStep(long time) {
		double seconds = time / 1000000000.0; // Convert time to seconds

		// Update ball
		for (Ball b : balls) {
			//Set speed based on speed slider
			b.setXVel(b.getConstantXVel() * speed.get());
			b.setYVel(b.getConstantYVel() * speed.get());

			//Update position based on time
			b.setXCenter(b.getXCenter() + seconds * b.getXVel());
			b.setYCenter(b.getYCenter() + seconds * b.getYVel());
		}
	}

	private void checkCollisions() {
		for(ListIterator<Ball> iterator = balls.listIterator(); iterator.hasNext();) {
			Ball b = iterator.next();
			double x_vel = b.getXVel();
			double y_vel = b.getYVel();
	
			if((b.getXCenter() - b.getRadius() <= 0 && x_vel < 0) || (b.getXCenter() + b.getRadius() >= xBounds && x_vel > 0)) {
				b.setConstantXVel(-b.getConstantXVel());
				b.setXVel(-x_vel);
				bounces.set(bounces.get() + 1);
				MainApp.BOUNCE_SOUND.play();
			}
			if((b.getYCenter() - b.getRadius() <= 0 && y_vel < 0) || (b.getYCenter() + b.getRadius() >= yBounds && y_vel > 0)){
				b.setConstantYVel(-b.getConstantYVel());
				b.setYVel(-y_vel);
				bounces.set(bounces.get() + 1);
				MainApp.BOUNCE_SOUND.play();
			}
		}
	}

	public void stopAnimation() { timer.stop(); }
	public void startAnimation() { timer.start(); }

}
