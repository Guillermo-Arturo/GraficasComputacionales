import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Graficos extends JPanel{
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.setColor(new Color(255, 0, 0));
		g.drawLine(0, 0, 300, 200);	
	}

	public static void main(String args[]){
		Graficos panel = new Graficos();
		JFrame application = new JFrame();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.add(panel);
		application.setSize(1000, 600);
		application.setVisible(true);
	}
}
