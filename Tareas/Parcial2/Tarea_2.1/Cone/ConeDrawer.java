
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
                      
   double radio, height;
   int base_divisions, height_divisions;

   int azimuth = 35, elevation = 30;

   Point3D[] vertices;
   Edge[] edges;

   boolean focussed = false;   // True when this applet has input focus.
   
   DisplayPanel canvas;

   public ConeDrawer(double radio, double height, int bd, int hd){
      this.radio = radio;
      this.height = height;
      base_divisions = bd;
      height_divisions = hd;
   }

   public void init() {

      int num_vertex = 2 + base_divisions * 2 + base_divisions * (height_divisions - 1);
      int num_edges = base_divisions + (base_divisions * 4) + (base_divisions * (height_divisions - 1));
      vertices = new Point3D[num_vertex];
      edges = new Edge[num_edges];

      int vertex_count = 0;
      int edges_count = 0;
      double step_size = 0;

      double angle_step = 360.0 / base_divisions;

      Point3D middle_cone = new Point3D(0, height / 2.0, 0);

      vertices[vertex_count++] = middle_cone;

      double y_middle_coordinate = middle_cone.y;

      for(int base=0; base < base_divisions; base++){
            double x = middle_cone.x + Math.cos(Math.toRadians(step_size)) * radio;
            double z = middle_cone.z - Math.sin(Math.toRadians(step_size)) * radio;

            vertices[vertex_count++] = new Point3D(x, y_middle_coordinate, z);
            if(base > 0)
                  edges[edges_count++] = new Edge(vertex_count -1, vertex_count - 2);

            edges[edges_count++] = new Edge(vertex_count - 1, 0);
            step_size += angle_step;
      }

      edges[edges_count++] = new Edge(vertex_count -1, 1);

      double y_num_divisions = height / (double) height_divisions;

      for(int y=1; y < height_divisions; y++){
            y_middle_coordinate += y_num_divisions;

            for(int base=0; base < base_divisions; base++){
                  Point3D top_cone = vertices[vertex_count - base_divisions];
                  vertices[vertex_count++] = new Point3D(top_cone.x, top_cone.y - y_num_divisions, top_cone.z);

                  if(base > 0)
                        edges[edges_count++] = new Edge(vertex_count -2 , vertex_count - 1);           
            }

            edges[edges_count++] = new Edge(vertex_count - 1, vertex_count - base_divisions);
      }

      middle_cone = new Point3D(0, - (height / 2), 0);
      vertices[vertex_count++] = middle_cone;
      int index_base_point = vertex_count - 1;

      y_middle_coordinate = middle_cone.y;
      step_size = 0;
      int index_cover = 1;

      for(int base=0; base < base_divisions; base++){
            double x = middle_cone.x + Math.cos(Math.toRadians(step_size)) * radio;
            double z = middle_cone.z - Math.sin(Math.toRadians(step_size)) * radio;

            vertices[vertex_count++] = new Point3D(x, y_middle_coordinate, z);

            if(base > 0)
                  edges[edges_count++] = new Edge(vertex_count -1, vertex_count - 2);

            edges[edges_count++] = new Edge(vertex_count - 1, index_base_point);
            edges[edges_count++] = new Edge(vertex_count - 1, index_cover++);

            step_size += angle_step; 
      }

      edges[edges_count++] = new Edge(vertex_count - 1, index_base_point + 1);

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
