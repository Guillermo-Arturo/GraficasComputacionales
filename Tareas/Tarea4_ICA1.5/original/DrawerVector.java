//Guillermo Arturo Hernández Tapia      A01321776
//José María Montiel Palacios		    A01323942
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class DrawerVector extends JPanel{

    /* To define the X, Y position of the pencil and the rotation */
    int corX = 0;
    int corY = 0;
    int angle = 0;

    /* Assign X and Y coordinate values */
    public void position(int x, int y){
        corX = x;
        corY = y;
    }

    /*Goes forward and drawing a line between Point A and Point B */
    public void goForwardDrawing(double pixels, Graphics g){
        int x1 = corX;
		int y1 = corY;
		int x2 = (int)(pixels * Math.cos(Math.toRadians(angle)));
		int y2 = (int)( pixels * Math.sin(Math.toRadians(angle)) * -1);		
		
		g.setColor(new Color(0,0,0));
		g.drawLine(x1,y1,x1+x2,y1+y2);

        this.position(x1 + x2, y1 + y2);
    }

    /*Goes forward without drawing a line between Point A and Point B */
    public void goForward(double pixels){
        int x1 = corX;
		int y1 = corY;
		int x2 = (int)(pixels * Math.cos(Math.toRadians(angle)));
		int y2 = (int)( pixels * Math.sin(Math.toRadians(angle)) * -1);

        this.position(x1 + x2, y1 + y2);
    }

    /*To change the pencil angle for drawing a line */
    public void rotate(double angle){
        this.angle += angle; 
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        /* Drawing a square
        position(200, 300);
        goForwardDrawing(100, g);
        rotate(90);
        goForwardDrawing(100, g);
        rotate(90);
        goForwardDrawing(100, g);
        rotate(90);
        goForwardDrawing(100, g);
        rotate(90);
        */

        //Drawing a house
        position(200, 200);
        rotate(90);
        goForwardDrawing(200, g);
        rotate(90);
        goForwardDrawing(200, g);
        rotate(90);
        goForwardDrawing(200, g);
        rotate(45);
        goForwardDrawing(200*Math.cos(Math.toRadians(45)), g);
        rotate(90);
        goForwardDrawing(200*Math.cos(Math.toRadians(45)), g);
        rotate(90);
        goForward(100);
        rotate(-45);
        goForwardDrawing(100, g);
        rotate(90);
        goForwardDrawing(65, g);
        rotate(90);
        goForwardDrawing(100, g);
        rotate(90);
        goForwardDrawing(65, g);
        rotate(90);
        

    }

    public static void main(String args[]){
        DrawerVector drawer = new DrawerVector();
		JFrame application = new JFrame();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.add(drawer);
		application.setSize(600, 600);
		application.setVisible(true);
    }
}