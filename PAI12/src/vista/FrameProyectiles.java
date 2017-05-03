package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class FrameProyectiles extends JFrame{
		private PanelProyectiles panel; /** Panel con la bola */
		private JSlider angle;
		private JSlider altura;
		private JSlider velocidad;
		
		public FrameProyectiles(){
			this.setLayout(new BorderLayout());
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setResizable(false);
			this.setSize(700, 700);
			panel = new PanelProyectiles();
			this.add(panel, BorderLayout.CENTER);
			/** Creacion de los botones y el panel que los contendrá */
			JButton botonLanzar= new JButton("Lanzar");
			JButton botonParar = new JButton("Parar");
			JButton botonBorrar = new JButton("Borrar");
			JPanel options = new JPanel();
			
			options.setLayout(new GridLayout(1,3));
			JPanel buttons = new JPanel();
			buttons.setLayout(new GridLayout(3,1));
			buttons.add(botonLanzar);
			buttons.add(botonParar);
			buttons.add(botonBorrar);
			options.add(buttons);
			
			JPanel sliders = new JPanel();
			sliders.setLayout(new GridLayout(3,2));
			angle = new JSlider(JSlider.HORIZONTAL, 0, 90, 45);
			altura = new JSlider(JSlider.HORIZONTAL, 0, 200, 0);
			velocidad = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
			sliders.add(angle);
			sliders.add(new JLabel("Angulo"));
			sliders.add(altura);
			sliders.add(new JLabel("Altura"));
			sliders.add(velocidad);
			sliders.add(new JLabel("Velocidad"));
			options.add(sliders);
			
			/** Añadimos los listener */
			botonLanzar.addActionListener(new LanzarListener());
			botonBorrar.addActionListener(new BorrarListener());
			botonParar.addActionListener(new PararListener());
			
			add(options, BorderLayout.SOUTH);
		}
		
		
		/** Setters y getters */
		public PanelProyectiles getPanel() {
			return panel;
		}
		
		public JSlider getAngle() {
			return angle;
		}


		public void setAngle(JSlider angle) {
			this.angle = angle;
		}


		public JSlider getAltura() {
			return altura;
		}


		public void setAltura(JSlider altura) {
			this.altura = altura;
		}


		public JSlider getVelocidad() {
			return velocidad;
		}


		public void setVelocidad(JSlider velocidad) {
			this.velocidad = velocidad;
		}


		public void setPanel(PanelProyectiles panel) {
			this.panel = panel;
		}
		
		private class LanzarListener implements ActionListener{
			
			public void actionPerformed(ActionEvent e) {
				getPanel().launch(0, getAltura().getValue(), getAngle().getValue()*0.0174533, getVelocidad().getValue());
			}
			
		}
		
		private class BorrarListener implements ActionListener{
			
			public void actionPerformed(ActionEvent e) {
				getPanel().reset();
			}
			
		}
		
		private class PararListener implements ActionListener{
			
			public void actionPerformed(ActionEvent e) {
				getPanel().parar();
			}
			
		}
}
