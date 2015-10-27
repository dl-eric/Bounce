package edu.andover.elee.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {
	
	private DoubleProperty x_vel;
	private DoubleProperty y_vel;
	
	private final Circle ballView;
	
	public Ball(double radius, double center_x, double center_y, double x_vel, double y_vel) {
		ballView = new Circle(center_x, center_y, radius);
		
		this.x_vel = new SimpleDoubleProperty(x_vel);
		this.y_vel = new SimpleDoubleProperty(y_vel);
		
		ballView.setFill(Color.GRAY);
	}
	
	public Circle getBallView() { return ballView; }
	public double getXCenter() { return ballView.getCenterX(); }
	public double getYCenter() { return ballView.getCenterY(); }
	public double getXVel() { return x_vel.get(); }
	public double getYVel() { return y_vel.get(); }

	public void setXCenter(double x) { ballView.setCenterX(x); }
	public void setYCenter(double y) { ballView.setCenterY(y); }
	public void setXVel(double x) { x_vel.set(x); }
	public void setYVel(double y) { y_vel.set(y); }
	
}
