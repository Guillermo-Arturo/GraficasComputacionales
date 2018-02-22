
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

public class BoxDrawer extends JApplet 
                  implements KeyListener, FocusListener, MouseListener {
                      
   double x_len, y_len, z_len;
   int x_divisions, y_divisions, z_divisions;

   int azimuth = 35, elevation = 30;

   Point3D[] vertices;
   Edge[] edges;

   boolean focussed = false;   // True when this applet has input focus.
   
   DisplayPanel canvas;

   public BoxDrawer(double x, double y, double z, int x_divisions, int y_divisions, int z_divisions){
         this.x_len = x;
         this.y_len = y;
         this.z_len = z;
         this.x_divisions = x_divisions - 1;
         this.y_divisions = y_divisions - 1;
         this.z_divisions = z_divisions - 1;
   }

   public void init() {
      // Calculate the number of vertex in each axis.
      int num_vertex_x = (x_divisions * 4) + 8;
      int num_vertex_y = (y_divisions * 4) + 8;
      int num_vertex_z = (z_divisions * 4) + 8;

      int total_vertices = num_vertex_x + num_vertex_y + num_vertex_z;
      
      //Calculate the size of each division in X, Y and Z axes. Step by defualt if x_divitions, y_divitions and z_divitions are 
      // equal to 0.
      double step_x = this.x_len / 2;
      double step_y = this.y_len / 2;
      double step_z = this.z_len / 2;

      //Calculate the size of each division in X, Y and Z axes.
      if(this.x_divisions > 0){
            step_x = (double) this.x_len / (double) (this.x_divisions + 1);
      }
      if(this.y_divisions > 0){
            step_y = (double) this.y_len / (double) (this.y_divisions + 1);
      }
      if(this.z_divisions > 0){
            step_z = (double) this.z_len / (double) (this.z_divisions + 1);
      }
      
      vertices = new Point3D[total_vertices];
      edges = new Edge[total_vertices];

      double x = -1;
      double y = -1;
      double z = -1;

      int vertex_unions = 0;
      int edges_unions = 0;

      //Defining all the divisions into de X face of the box.
      for(int i=0; i < x_divisions + 2; i++){
            
            double point_x1 = x, point_y1 = y, point_z1 = z;
            double point_x2, point_y2, point_z2;
            double point_x3, point_y3, point_z3;
            double point_x4, point_y4, point_z4;

            vertices[vertex_unions++] = new Point3D(point_x1, point_y1, point_z1);

            point_x2 = point_x1;
            point_y2 = point_y1 + this.y_len;
            point_z2 = point_z1;
            vertices[vertex_unions++] = new Point3D(point_x2, point_y2, point_z2);
            edges[edges_unions++] = new Edge(vertex_unions - 2, vertex_unions - 1);

            point_x3 = point_x2;
            point_y3 = point_y2;
            point_z3 = point_z2 + this.z_len;
            vertices[vertex_unions++] = new Point3D(point_x3, point_y3, point_z3);
            edges[edges_unions++] = new Edge(vertex_unions - 2, vertex_unions - 1);

            point_x4 = point_x3;
            point_y4 = point_y3 - this.y_len;
            point_z4 = point_z3;
            vertices[vertex_unions++] = new Point3D(point_x4, point_y4, point_z4);
            edges[edges_unions++] = new Edge(vertex_unions - 2, vertex_unions - 1);
            edges[edges_unions++] = new Edge(vertex_unions -1, vertex_unions - 4);

            x += step_x;
      }

      x = -1;
      y = -1;
      z = -1;

      //Defining all the divisions into de Y face of the box.
      for(int j=0; j < y_divisions + 2; j++){
         
            double point_x1 = x, point_y1 = y, point_z1 = z;
            double point_x2, point_y2, point_z2;
            double point_x3, point_y3, point_z3;
            double point_x4, point_y4, point_z4;

            vertices[vertex_unions++] = new Point3D(point_x1, point_y1, point_z1);

            point_x2 = point_x1 + this.x_len;
            point_y2 = point_y1;
            point_z2 = point_z1;
            vertices[vertex_unions++] = new Point3D(point_x2, point_y2, point_z2);
            edges[edges_unions++] = new Edge(vertex_unions - 2, vertex_unions - 1);

            point_x3 = point_x2;
            point_y3 = point_y2;
            point_z3 = point_z2 + this.z_len;
            vertices[vertex_unions++] = new Point3D(point_x3, point_y3, point_z3);
            edges[edges_unions++] = new Edge(vertex_unions - 2, vertex_unions - 1);

            point_x4 = point_x3 - this.x_len;
            point_y4 = point_y3;
            point_z4 = point_z3;
            vertices[vertex_unions++] = new Point3D(point_x4, point_y4, point_z4);
            edges[edges_unions++] = new Edge(vertex_unions - 2, vertex_unions - 1);
            edges[edges_unions++] = new Edge(vertex_unions -1, vertex_unions - 4);

            y += step_y;
      }

      x = -1;
      y = -1;
      z = -1;

      //Defining all the divisions into de Z face of the box.
      for(int k=0; k < z_divisions + 2; k++){
            double point_x1 = x, point_y1 = y, point_z1 = z;
            double point_x2, point_y2, point_z2;
            double point_x3, point_y3, point_z3;
            double point_x4, point_y4, point_z4;

            vertices[vertex_unions++] = new Point3D(point_x1, point_y1, point_z1);

            point_x2 = point_x1 + this.x_len;
            point_y2 = point_y1;
            point_z2 = point_z1;
            vertices[vertex_unions++] = new Point3D(point_x2, point_y2, point_z2);
            edges[edges_unions++] = new Edge(vertex_unions - 2, vertex_unions - 1);

            point_x3 = point_x2;
            point_y3 = point_y2 + this.y_len;
            point_z3 = point_z2;
            vertices[vertex_unions++] = new Point3D(point_x3, point_y3, point_z3);
            edges[edges_unions++] = new Edge(vertex_unions - 2, vertex_unions - 1);

            point_x4 = point_x3 - this.x_len;
            point_y4 = point_y3;
            point_z4 = point_z3;
            vertices[vertex_unions++] = new Point3D(point_x4, point_y4, point_z4);
            edges[edges_unions++] = new Edge(vertex_unions - 2, vertex_unions - 1);
            edges[edges_unions++] = new Edge(vertex_unions -1, vertex_unions - 4);

            z += step_z;
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

            // draw the wireframe
            g.setColor( Color.black );
            g.fillRect( 0, 0, width, height );
            g.setColor( Color.white );
            for ( j = 0; j < edges.length; ++j ) {
               g.drawLine(
                  points[ edges[j].a ].x, points[ edges[j].a ].y,
                  points[ edges[j].b ].x, points[ edges[j].b ].y
               );
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
