package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.Timer;
import java.util.*;
import modelo.*;

public class PanelProyectiles extends JPanel {
	private Proyectil proyectil;
	private ArrayList<Integer> times;
	private Timer timer;
	
	public PanelProyectiles(){
		setProyectil(new Proyectil());
		setTimes(new ArrayList<Integer>());
		getTimes().add(0);
		setTimer(new Timer(50, new TimeListener()));
	}
	
	public void launch(double x, double y, double angle, double speed){
		Proyectil p = new Proyectil(x,y,angle,speed);
		p.setColor(randomColor());
		setProyectil(p);
		getTimer().stop();
		setTimer(new Timer(50, new TimeListener()));
		setTimes(new ArrayList<Integer>());
		getTimes().add(0);
		getTimer().start();
	}
	

	public ArrayList<Integer> getTimes() {
		return times;
	}

	public void setTimes(ArrayList<Integer> times) {
		this.times = times;
	}

	public Proyectil getProyectil() {
		return proyectil;
	}

	public void setProyectil(Proyectil proyectil) {
		this.proyectil = proyectil;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
	public void reset(){
		setProyectil(new Proyectil());
		getTimer().stop();
		setTimes(new ArrayList<Integer>());
		getTimes().add(0);
		this.repaint();
	}
	
	public void parar(){
		getTimer().stop();
		setTimes(new ArrayList<Integer>());
		getTimes().add(0);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawLine(650, 500, 50, 500);
		g.drawLine(50, 500, 50, 0);
		
		for(int i=0; i<60; i++){
			g.drawLine(50+i*10, 500, 50+i*10, 510);
		}
		for(int i=0; i<7; i++){
			g.drawLine(50+i*100, 500, 50+i*100, 520);
			g.drawString(""+(i*100),50+i*100, 535);
		}
		for(int i=0; i<60; i++){
			g.drawLine(50, 500-i*10, 40, 500-i*10);
		}
		for(int i=0; i<6; i++){
			g.drawLine(50, 500-i*100, 30, 500-i*100);
			g.drawString(""+(i*100),5, 510-i*100);
		}
		
		Proyectil p = getProyectil();	
		g.setColor(p.getColor());
		int[] cords= new int[]{-1,-1};
		int ymax =0;
	    for(int i : getTimes()){
	    	cords = p.posatTime((double)i/1000);
		   	if(cords[1]>= 0 && getTimes().size()>1)
		  		g.fillOval(cords[0]+47, 497-cords[1], 6, 6);
		   	if(cords[1] > ymax)
		   		ymax = cords[1];
	    }
	    if(cords[1]<=0 && getTimes().size()>1)
	    	getTimer().stop();
		if(cords[1]>=0 && getTimes().size()>1)
			g.fillOval(cords[0]+45, 495-cords[1], 10, 10);
		
		double time =((double)getTimes().get(getTimes().size()-1)/1000);
		g.setColor(Color.BLACK);
		g.drawString("t = "+ time ,550,50);
		g.drawString("x = "+ cords[0] ,550,65);
		g.drawString("y = "+ cords[1] ,550,80);
		g.drawString("vx = "+ p.getSpeedX(time) ,550,95);
		g.drawString("vy = "+ p.getSpeedY(time) ,550,110);
		g.drawString("v = "+ p.getSpeed(time) ,550,125);
		g.drawString("Y max = "+ ymax ,550,140);
		
	}

	public Color randomColor(){
		Random randomGenerator = new Random();
		int temp;
		temp = randomGenerator.nextInt(10);
    	switch(temp){
    	case 0: return(Color.RED);
    	case 1: return(Color.BLUE);
    	case 2: return(Color.BLACK);
    	case 3: return(Color.WHITE);
    	case 4: return(Color.PINK);
    	case 5: return(Color.YELLOW);
    	case 6: return(Color.CYAN);
    	case 7: return(Color.MAGENTA);
    	case 8: return(Color.GREEN);
    	case 9: return(Color.GRAY);
    	}
    	return(Color.GREEN);
	}
	
	private class TimeListener implements ActionListener{
		int t;
		
		public TimeListener(){
			t=0;
			setTimes(new ArrayList<Integer>());
			getTimes().add(0);
		}
		public void actionPerformed(ActionEvent e) {
			t+=50;
			getTimes().add(t);
			repaint();
		}
	}	
}
