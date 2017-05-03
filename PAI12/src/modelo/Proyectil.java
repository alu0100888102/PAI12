package modelo;

import javax.swing.*;
import java.awt.*;

public class Proyectil {
	private double x;
	private double y;
	private double speedX;
	private double speedY;
	private double gravity;
	private double angle;
	private double speed;
	private Color color;
	
	public Proyectil(){
		setX(0);
		setY(0);
		setSpeedX(0);
		setSpeedY(0);
		setGravity(9.8);
		setAngle(0);
		setSpeed(0);
		setColor(Color.RED);
	}
	
	public Proyectil(double x, double y){
		setX(x);
		setY(y);
		setSpeedX(0);
		setSpeedY(0);
		setGravity(9.8);
		setAngle(0);
		setSpeed(0);
		setColor(Color.RED);
	}
	
	public Proyectil(double x, double y, double angle){
		setX(x);
		setY(y);
		setSpeedX(0);
		setSpeedY(0);
		setSpeed(0);
		setGravity(9.8);
		setAngle(angle);
		setColor(Color.RED);
	}
	
	public Proyectil(double x, double y, double angle, double speed){
		setX(x);
		setY(y);
		setSpeedX(calculateXspeed(angle, speed));
		setSpeedY(calculateYspeed(angle, speed));
		setSpeed(speed);
		setGravity(9.8);
		setAngle(angle);
		setColor(Color.RED);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public double calculateXspeed(double angle, double speed){
		return speed * Math.cos(angle);
	}
	
	public double calculateYspeed(double angle, double speed){
		return speed * Math.sin(angle);
	}
	
	public double getSpeed() {
		return speed;
	}
	public double getSpeed(double time) {
		double xsq = (getSpeedX() * getSpeedX());
		double ysq = (getSpeedY(time) * getSpeedY(time));
		return Math.sqrt( xsq + ysq);
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getSpeedX() {
		return speedX;
	}
	
	public double getSpeedX(double time) {
		return getSpeedX();
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	public double getSpeedY() {
		return speedY;
	}
	
	public double getSpeedY(double time) {
		double caida = -getGravity() * time;
		return caida + getSpeedY();
	}

	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}

	public double getGravity() {
		return gravity;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public int[] posatTime(double time){
		int x = (int)(getX() + getSpeedX() * time);
		double caida = -getGravity() * time * time / (double)2;
		int y = (int)(caida + getSpeedY() * time + getY());
		return new int[]{x ,y};
	}
	
}
