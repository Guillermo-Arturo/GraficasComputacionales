//Guillermo Arturo Hernandez Tapia	A01321776
//Jose Maria Montiel Palacios		A01323942

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Point3D {
   public double x, y, z;
   public Point3D( double X, double Y, double Z ) {
      x = X;  y = Y;  z = Z;
   }
}

class Edge {
   public int a, b;
   public Edge( int A, int B ) {
      a = A;  b = B;
   }
}

public class ConeDrawer extends JApplet 
                  implements KeyListener, FocusListener, MouseListener {
                      
   private double radio, h;
   private int yrc, xzd;
   private int width, height;

   int azimuth = 35, elevation = 30;

   Point3D[] vertices;
   Edge[] edges;

   boolean focussed = false;   // True when this applet has input focus.
   
   DisplayPanel canvas;

   public ConeDrawer(double radio,double h, int yrc, int xzd){
      this.radio  = radio;
      this.h      = h;
      this.yrc    = yrc;
      this.xzd    = xzd;
   }

   public void init() {

      int number_of_points = (yrc * xzd) + 2;
      vertices = new Point3D[number_of_points];

      vertices[0] = new Point3D(0, h, 0);
      double aux_z, aux_h=((double)h/yrc), aux_rad=((double)radio/yrc);
      double aux_y=h;
      double aux_x=0;
      double angle = 90.0/(yrc);
      double aux_angle = 90.0 - angle;
      double zx_divitions = 360.0 / xzd;
      double aux_radio = 0.0;
      int index_up = 1, index_down = 0;

      for(int j=0; j < yrc; j++){
            double temp = 0.0;
            aux_y -=aux_h;
            aux_radio +=aux_rad;

            for(int i=0; i < xzd; i++){
                  temp += zx_divitions;  //its an angle for rotating around Y axis
                  aux_x = Math.cos(Math.toRadians(temp)) * aux_radio;
                  aux_z = Math.sin(Math.toRadians(temp)) * aux_radio;
                  vertices[index_up] = new Point3D(aux_x, aux_y, aux_z);
                  index_up ++;
            }
            aux_angle -= angle;
      }

      int aux_a = (yrc + 1) * xzd;
      int aux_b = yrc * xzd;
      edges = new Edge[aux_a + aux_b];

      /**vertical from the first point to the imidiate parallel to the XZ plane.**/
      for(int i=1; i <= xzd; i++){
            edges[index_down] = new Edge(0, i);
            index_down ++;
      }

      /**vertical union.**/
      int current_edge = 1;
      for(int i=0; i < yrc - 1; i++){
            for(int j=0; j < xzd; j++){
                  edges[index_down] = new Edge(current_edge, current_edge + xzd);
                  current_edge ++;
                  index_down ++;
            }
      }

      /**Horizontal union.**/
      for(int i=0; i < (xzd * yrc); i++){
            if((index_down + 1) % xzd == 0)
                  edges[index_down] = new Edge((i + 1), ((i + 2) - xzd));
            else
                  edges[index_down] = new Edge(i + 1, i + 2);

            index_down++;
      }
  
      canvas = new DisplayPanel();  // Create drawing surface and 
      setContentPane(canvas);       //    install it as the applet's content pane.
   
      canvas.addFocusListener(this);   // Set up the applet to listen for events
      canvas.addKeyListener(this);     //                          from the canvas.
      canvas.addMouseListener(this);
      
   } // end init();
   
   class DisplayPanel extends JPanel {
      public void paintComponent(Graphics g) {
         super.paintComponent(g);  

         if (focussed) 
            g.setColor(Color.cyan);
         else
            g.setColor(Color.lightGray);

         int width = getSize().width;  // Width of the applet.
         int height = getSize().height; // Height of the applet.
         g.drawRect(0,0,width-1,height-1);
         g.drawRect(1,1,width-3,height-3);
         g.drawRect(2,2,width-5,height-5);

         if (!focussed) {
            g.setColor(Color.magenta);
            g.drawString("Click to activate",100,120);
            g.drawString("Use arrow keys to change azimuth and elevation",100,150);

         }
         else {

            double theta = Math.PI * azimuth / 180.0;
            double phi = Math.PI * elevation / 180.0;
            float cosT = (float)Math.cos( theta ), sinT = (float)Math.sin( theta );
            float cosP = (float)Math.cos( phi ), sinP = (float)Math.sin( phi );
            float cosTcosP = cosT*cosP, cosTsinP = cosT*sinP,
                  sinTcosP = sinT*cosP, sinTsinP = sinT*sinP;

            // project vertices onto the 2D viewport
            Point[] points;
            points = new Point[ vertices.length ];
            int j;
            int scaleFactor = width/8;
            float near = 3;  // distance from eye to near plane
            float nearToObj = 1.5f;  // distance from near plane to center of object
            for ( j = 0; j < vertices.length; ++j ) {
               if(vertices[j] != null){
                  double x0 = vertices[j].x;
                  double y0 = vertices[j].y;
                  double z0 = vertices[j].z;

                  // compute an orthographic projection
                  double x1 = cosT*x0 + sinT*z0;
                  double y1 = -sinTsinP*x0 + cosP*y0 + cosTsinP*z0;

                  // now adjust things to get a perspective projection
                  double z1 = cosTcosP*z0 - sinTcosP*x0 - sinP*y0;
                  x1 = x1*near/(z1+near+nearToObj);
                  y1 = y1*near/(z1+near+nearToObj);

                  // the 0.5 is to round off when converting to int
                  points[j] = new Point(
                        (int)(width/2 + scaleFactor*x1 + 0.5),
                        (int)(height/2 - scaleFactor*y1 + 0.5)
                  );
               }
            }

            // draw the wireframe
            g.setColor( Color.black );
            g.fillRect( 0, 0, width, height );
            g.setColor( Color.white );
            for ( j = 0; j < edges.length; ++j ) {
               if(edges[j] != null){
                  g.drawLine(
                        points[ edges[j].a ].x, points[ edges[j].a ].y,
                        points[ edges[j].b ].x, points[ edges[j].b ].y
                  );
               }
            }
         } 
      }  // end paintComponent()    
    } // end nested class DisplayPanel 

   // ------------------- Event handling methods ----------------------
   
   public void focusGained(FocusEvent evt) {
      focussed = true;
      canvas.repaint();
   }
   
   public void focusLost(FocusEvent evt) {
      focussed = false;
      canvas.repaint(); 
   }
      
   public void keyTyped(KeyEvent evt) {  
   }  // end keyTyped()
      
   public void keyPressed(KeyEvent evt) { 
       
      int key = evt.getKeyCode();  // keyboard code for the key that was pressed
      
      if (key == KeyEvent.VK_LEFT) {
         azimuth += 5;
         canvas.repaint();         
      }
      else if (key == KeyEvent.VK_RIGHT) {
         azimuth -= 5;
         canvas.repaint();         
      }
      else if (key == KeyEvent.VK_UP) {
         elevation -= 5;
         canvas.repaint();         
      }
      else if (key == KeyEvent.VK_DOWN) {
         elevation += 5;         
         canvas.repaint();
      }

   }  // end keyPressed()

   public void keyReleased(KeyEvent evt) { 
      // empty method, required by the KeyListener Interface
   }
   
   public void mousePressed(MouseEvent evt) {      
      canvas.requestFocus();
   }      
   
   public void mouseEntered(MouseEvent evt) { }  // Required by the
   public void mouseExited(MouseEvent evt) { }   //    MouseListener
   public void mouseReleased(MouseEvent evt) { } //       interface.
   public void mouseClicked(MouseEvent evt) { }
   public void mouseDragged( MouseEvent e ) { }
} // end class 
