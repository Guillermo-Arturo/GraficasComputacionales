import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;

import java.io.BufferedReader;
import java.io.FileReader;

public class Graphs extends JPanel{

    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

    public static void main(String args[]){
		Graphs graph = new Graphs();
		JFrame application = new JFrame();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.add(graph);
		application.setSize(1000, 500);
		application.setVisible(true);
	}

    public void readDataFile(String filePath){
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
        } catch (IOEX)
        } finally {
            br.close();
        }
    }
}