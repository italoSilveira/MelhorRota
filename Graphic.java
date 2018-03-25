package trabalho;
import java.awt.*;
import javax.swing.*;
public class Graphic extends JPanel{
	private static final long serialVersionUID = 210120171359L;
	public static JFrame _window = new JFrame("CybTricks.com - Algoritmo Genético - Daniel Oliveira");
	public static Graphic g = null;
	public static Tour _bestTour;
	public static Tour _lastTour;
	public static int _generation;
	
	
	public Graphic(){};
	public Graphic(Tour bestTour){
		_lastTour = _bestTour;
		_bestTour = bestTour;
	}

	public static void createWindow(){
		 _window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     _window.setVisible(true);
	     _window.setSize(1300, 1300);
	}
	public static void updateWindow(Tour bestTour){
		if(_bestTour != null && _bestTour.equals(bestTour)){
			return;
		}
		if(g != null){
			_window.remove(g);
		}
		Graphic g = new Graphic(bestTour);
		_window.add(g);
		_window.setVisible(true);
	}
	
	public static void setGeneration(int generation){
		_generation = generation;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		g.setFont(g.getFont().deriveFont(20.0f));
		g.setColor(Color.RED);
		g.drawString("Geração: "+_generation, 230, 20);
		g.setColor(Color.BLACK);
		g.drawString("Mutações: "+GA.getMutations(), 390, 20);
		g.setColor(Color.BLUE);
		g.drawString("Comprimento: "+(int)_bestTour.getDistance(), 10, 20);
		City city, city2;
		for(int i = 0; i < _bestTour.tourSize(); i++){
        	city = _bestTour.getCity(i);
        	if(i+1 < _bestTour.tourSize()){
        		city2 = _bestTour.getCity(i+1);
        	}else{
        		city2 = _bestTour.getCity(0);
        	}
        	g.setColor(Color.BLUE);
        	g.fillRect(city.getX()*6-5,city.getY()*6-5,10,10);
        	g.setColor(Color.RED);
        	//g.drawString(city.getName(), city.getX()*6-5, city.getY()*6-10);
        	g.drawLine(city.getX()*6-5,city.getY()*6-5, city2.getX()*6-5,city2.getY()*6-5);
        	
        }
		
	}

}