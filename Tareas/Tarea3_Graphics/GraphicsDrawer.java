import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class GraphicsDrawer extends JPanel{

    private static int numberOfData;
    private static String[] labels;
    private static int[] data;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

    public static void readDataFromFile(String fileName){
        try{
            FileReader file = new FileReader(fileName);
            BufferedReader bf = new BufferedReader(file);
            numberOfData = Integer.parseInt(bf.readLine());

            data = new int[numberOfData];
            labels = new String[numberOfData];

            for(int i=0; i < numberOfData; i++){
                data[i] = Integer.parseInt(bf.readLine());
                System.out.println(data[i]);
            }

            for(int i=0; i< numberOfData; i++){
                labels[i] = bf.readLine();
                System.out.println(labels[i]);
            }
        }catch(FileNotFoundException e){

        }catch(IOException e){

        }
    }

    public static void main(String args[]){
        String fileName = "";

        if(args.length > 0)
            fileName = args[0];
        else
            System.out.println("You need to give me a file name for reading data");

        GraphicsDrawer drawer = new GraphicsDrawer();
		JFrame application = new JFrame();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.add(drawer);
		application.setSize(1000, 500);
		application.setVisible(true);

        readDataFromFile(fileName);
    }
}