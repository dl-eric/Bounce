package edu.andover.elee.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {
	
	private DoubleProperty x_vel;
	private DoubleProperty y_vel;
	private double y_vel_constant;
	private double x_vel_constant;
	private final Circle ballView;
	private final double radius;
	
	public Ball(double radius, double center_x, double center_y, double x_vel, double y_vel) {
		
		this.radius = radius;
		
		this.x_vel = new SimpleDoubleProperty(x_vel);
		this.y_vel = new SimpleDoubleProperty(y_vel);
		
		x_vel_constant = x_vel;
		y_vel_constant = y_vel;
		
		ballView = new Circle(center_x, center_y, radius);
		ballView.setFill(Color.GRAY);
	}
	
	public Circle getBallView() { return ballView; }
	public double getXCenter() { return ballView.getCenterX(); }
	public double getYCenter() { return ballView.getCenterY(); }
	public double getXVel() { return x_vel.get(); }
	public double getYVel() { return y_vel.get(); }
	public double getConstantXVel() { return x_vel_constant; }
	public double getConstantYVel() { return y_vel_constant; }
	public double getRadius() { return radius; }

	public void setXCenter(double x) { ballView.setCenterX(x); }
	public void setYCenter(double y) { ballView.setCenterY(y); }
	public void setXVel(double x) { x_vel.set(x); }
	public void setYVel(double y) { y_vel.set(y); }
	public void setConstantXVel(double x) { x_vel_constant = x; }
	public void setConstantYVel(double y) { y_vel_constant = y; }
	
}
