//Guillermo Arturo Hernandez Tapia  A01321776

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
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
    private static Color[] colors;

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        /*
        * To determine the lowest and the greatest value from de data set array.
        */
        int minValue = 0;
        int maxValue = 0;
        for(int i=0; i < data.length; i++){
            if(minValue > data[i])
                minValue = data[i];

            if(maxValue < data[i])
                maxValue = data[i];
        }

        /* Set the area dimentions for drawing the bar chart */
        int windowWidth = 550;
        int windowHeight = 400;
        int barWidth = windowWidth / data.length;

        /* Set the font family and font size of labels */
        Font barLabel = new Font("Book Antiqua", Font.PLAIN, 12);
        FontMetrics barLabelMetrics = g.getFontMetrics(barLabel);

        int bottom = barLabelMetrics.getHeight();
        if(minValue == maxValue)
            return;

        /* Calculating the scale of each bar */
        double scale = (windowHeight - bottom) / (maxValue - minValue);
        int stringPosY = windowHeight - barLabelMetrics.getDescent();
        g.setFont(barLabel);

        /* Calculating the X and Y coordinates of each bar */
        for(int i=0; i < data.length; i++){
            int valueX = i * barWidth + 1;
            int valueY = 20;
            int height = (int) (data[i] * scale);

            if(data[i] >= 0){
                valueY += (int) ((maxValue - data[i]) * scale);
            }else{
                valueY += (int) (maxValue * scale);
                height = -height;
            }

            /* Drawing bars */
            g.setColor(colors[i]);
            g.fillRect(valueX, valueY, barWidth - 2, height);
            g.setColor(Color.black);
            g.drawRect(valueX, valueY, barWidth - 2, height);
            g.drawString(Integer.toString(data[i]), valueX, valueY);

            /* Drawing labels */
            int labelWidth = barLabelMetrics.stringWidth(labels[i]);
            int stringPosX = i * barWidth + (barWidth - labelWidth) / 2;
            g.drawString(labels[i], stringPosX, stringPosY);
            g.setColor(colors[i]);
            g.fillRect(stringPosX, stringPosY + 20, 20, 20);
        }
    }

    /*
    * Method that read data from a text file.
    * Arguments:
    *   --> String fileName: the name of the file which the method read.
    */
    public static void readDataFromFile(String fileName){
        try{
            FileReader file = new FileReader(fileName);
            BufferedReader bf = new BufferedReader(file);
            numberOfData = Integer.parseInt(bf.readLine());

            data = new int[numberOfData];
            labels = new String[numberOfData];

            for(int i=0; i < numberOfData; i++){
                data[i] = Integer.parseInt(bf.readLine());
            }

            for(int i=0; i< numberOfData; i++){
                labels[i] = bf.readLine();
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

        readDataFromFile(fileName);
        /* Color Array which establishes each bar color */
        colors = new Color[]{ Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.magenta, Color.cyan, Color.black,
                            Color.darkGray, Color.gray, Color.lightGray, Color.pink, Color.yellow, Color.magenta, Color.orange, Color.blue,
                            Color.black, Color.green};

        GraphicsDrawer drawer = new GraphicsDrawer();
		JFrame application = new JFrame();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.add(drawer);
		application.setSize(1200, 500);
		application.setVisible(true);
    }
}