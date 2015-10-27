package edu.andover.elee.model;

import javafx.scene.layout.Pane;
import javafx.animation.AnimationTimer;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class PhysicsAnimation {
	private ObservableList<Ball> balls = FXCollections.observableArrayList();
	private final double radius = 32;
	private int bounces = 0;
	
	public PhysicsAnimation(final Pane pane) {
	    balls.addListener(new ListChangeListener<Ball>() {
            @Override
            public void onChanged(Change<? extends Ball> change) {
                while (change.next()) {
                    for (Ball b : change.getAddedSubList()) {
                        pane.getChildren().add(b.getBallView());
                    }
                    for (Ball b : change.getRemoved()) {
                        pane.getChildren().remove(b.getBallView());
                    }
                }
            }
        });
	}
	
	public void spawnBall() {
		double randomX = Math.random() * 520 + 32;
		double randomY = Math.random() * 320 + 32;
		double randomSpeed = Math.random() * 1000;
		double randomAngle = Math.random() * 2 * Math.PI;
		double x_vel = randomSpeed * Math.cos(randomAngle);
		double y_vel = randomSpeed * Math.sin(randomAngle);
		Ball ball = new Ball(radius, randomX, randomY, x_vel, y_vel);
		balls.add(ball);
	}
	
	public void initializeWorld(final Pane pane) {
		//TODO: paraphrase
		final LongProperty lastUpdateTime = new SimpleLongProperty(0);
        final AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
            	
                if (lastUpdateTime.get() > 0) {
                    long elapsedTime = timestamp - lastUpdateTime.get();
                    checkCollisions(pane.getWidth(), pane.getHeight());
                    timeStep(elapsedTime);
                }
                
                lastUpdateTime.set(timestamp);
            }

        };
        timer.start();
	}
	
	private void timeStep(long time) {
		double seconds = time / 1000000000; // Convert time to seconds
		
		// Update ball positions in relation to time.
        for (Ball b : balls) {
            b.setXCenter(b.getXCenter() + seconds * b.getXVel());
            b.setYCenter(b.getYCenter() + seconds * b.getYVel());
        }
	}
	
	private void checkCollisions(int width, int height) {
		
	}
	
}
