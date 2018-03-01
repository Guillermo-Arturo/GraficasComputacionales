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

class Face{
    public int a,b,c,d;
    public double avg;

    public Face(int A, int B, int C, int D){
            a= A; b=B; c=C; d=D;
            avg = 0;
    }
}

public class TetrisDrawer extends JApplet 
                  implements KeyListener, FocusListener, MouseListener {
                      
   int width, height;
   // int mx, my;  // the most recently recorded mouse coordinates

   int azimuth = 35, elevation = 30;

   Point3D[] vertices;
   Edge[] edges;
   Face[] faces;

   boolean focussed = false;   // True when this applet has input focus.
   
   DisplayPanel canvas;  

   public void init() {
      vertices = new Point3D[20];
      vertices[0] = new Point3D(-.5, -.5, -.5);
      vertices[1] = new Point3D(-.5, -.5,  .5);
      vertices[2] = new Point3D(-.5, .5, -.5);
      vertices[3] = new Point3D(-.5, .5, .5);
      vertices[4] = new Point3D( .5, -.5, -.5);
      vertices[5] = new Point3D( .5, -.5,  .5);
      vertices[6] = new Point3D( .5, .5, -.5);
      vertices[7] = new Point3D( .5, .5, .5);
      vertices[8] = new Point3D( 1.5, -.5, -.5);
      vertices[9] = new Point3D(  1.5,  -.5,  .5);
      vertices[10] = new Point3D(  1.5,  .5,  -.5);
      vertices[11] = new Point3D(  1.5,  .5,  .5);
      vertices[12] = new Point3D(  -.5,  1.5,  -.5);
      vertices[13] = new Point3D(  -.5,  1.5,  .5);
      vertices[14] = new Point3D(  .5,  1.5,  -.5);
      vertices[15] = new Point3D(  .5,  1.5,  .5);
      vertices[16] = new Point3D(  -1.5,  .5,  -.5);
      vertices[17] = new Point3D(  -1.5,  .5,  .5);
      vertices[18] = new Point3D(  -1.5,  -0.5,  .5);
      vertices[19] = new Point3D(  -1.5,  -0.5,  -.5);
      
      edges = new Edge[ 36];
      edges[ 0] = new Edge( 0, 1 );
      edges[ 1] = new Edge( 0, 2 );
      edges[ 2] = new Edge( 0, 4 );
      edges[ 3] = new Edge( 1, 3 );
      edges[ 4] = new Edge( 1, 5 );
      edges[ 5] = new Edge( 2, 3 );
      edges[ 6] = new Edge( 2, 6 );
      edges[ 7] = new Edge( 3, 7 );
      edges[ 8] = new Edge( 4, 5 );
      edges[ 9] = new Edge( 4, 6 );
      edges[10] = new Edge( 5, 7 );
      edges[11] = new Edge( 6, 7 );
      edges[12] = new Edge( 8, 4 );
      edges[13] = new Edge( 8, 9 );
      edges[14] = new Edge( 9, 5 );
      edges[15] = new Edge( 9, 11 );
      edges[16] = new Edge( 10, 6 );
      edges[17] = new Edge( 10, 11);
      edges[18] = new Edge( 11, 7 );
      edges[19] = new Edge( 10, 8 );
      edges[20] = new Edge( 12, 2 );
      edges[21] = new Edge( 12, 13 );
      edges[22] = new Edge( 12, 14 );
      edges[23] = new Edge( 13, 3 );
      edges[24] = new Edge( 13, 15 );
      edges[25] = new Edge( 14, 6 );
      edges[26] = new Edge( 14, 15 );
      edges[27] = new Edge( 15, 7 );
      edges[28] = new Edge( 16, 2 );
      edges[29] = new Edge( 16, 19 );
      edges[30] = new Edge( 16, 17 );
      edges[31] = new Edge( 17, 3 );
      edges[32] = new Edge( 17, 18 );
      edges[33] = new Edge( 18, 19 );
      edges[34] = new Edge( 18, 1 );
      edges[35] = new Edge( 19, 8 );

      faces = new Face[18];
      faces[0] = new Face(0,1,3,2);
      faces[1] = new Face(0,4,6,2);
      faces[2] = new Face(1,5,7,3);
      faces[3] = new Face(0,4,5,1);
      faces[4] = new Face(4,8,10,6);
      faces[5] = new Face(5,9,11,7);
      faces[6] = new Face(6,10,11,7);
      faces[7] = new Face(4,8,9,5);
      faces[8] = new Face(8,9,11,10);
      faces[9] = new Face(2,6,14,12);
      faces[10] = new Face(3,7,15,13);
      faces[11] = new Face(12,14,15,13);
      faces[12] = new Face(6,7,15,14);
      faces[13] = new Face(16,17,18, 1);
      faces[14] = new Face(16,2,12,18);
      faces[15] = new Face(17,3,13,19);
      faces[16] = new Face(18,12,13,19);
      faces[17] = new Face(16,2,3,17);
    
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
            double z_axes[] = new double[vertices.length];
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
               z_axes[j] = z1;
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

            this.drawFaces(faces, z_axes, points, g);
         } 
      }  // end paintComponent()   

      public void drawFaces(Face[] faces, double[] z_axes, Point[] points, Graphics g){
            double aux = 0;
            int number_of_faces = faces.length;
            double[] faces_array = new double[number_of_faces];

            for(int i=0; i < number_of_faces; i++){
                  aux = ((z_axes[faces[i].a] + z_axes[faces[i].b] + z_axes[faces[i].c] + z_axes[faces[i].d])) / 4;
                  faces_array[i] = aux;
            }

            int[] face_id = new int[faces.length];
            for(int id=0; id < face_id.length; ++id){
                  face_id[id] = id;
            }

            double temp = 0;
            for(int i=0; i < face_id.length; i++){
                  temp = faces_array[i];
                  for(int j=(i+1); j < face_id.length; j++){
                        if(temp > faces_array[j]){
                              int id = face_id[i];
                              face_id[i] = face_id[j];
                              face_id[j] = id;

                              double aux_id = faces_array[i];
                              faces_array[i] = faces_array[j];
                              faces_array[j] = aux_id;

                              temp = faces_array[i];
                        }
                  }
            }

            int[] x = new int[4];
            int[] y = new int[4];

            for(int j = faces.length - 1; j >= 0; j--){
                  x[0] = points[faces[face_id[j]].a].x;
                  y[0] = points[faces[face_id[j]].a].y;
                  x[1] = points[faces[face_id[j]].b].x;
                  y[1] = points[faces[face_id[j]].b].y;
                  x[2] = points[faces[face_id[j]].c].x;
                  y[2] = points[faces[face_id[j]].c].y;
                  x[3] = points[faces[face_id[j]].d].x;
                  y[3] = points[faces[face_id[j]].d].y;

                  switch(face_id[j]){
                        case 0:{ 
                              g.setColor(new Color(255, 0, 0)); 
                              break;
                        } // Red
                        case 1:{
                              g.setColor(new Color(0, 255, 0)); 
                              break;
                        } // Green
                        case 2:{
                              g.setColor(new Color(0, 0, 255)); 
                              break;
                        } // Blue
                        case 3:{
                              g.setColor(new Color(255,0,255));
                              break;
                        } // Magenta
                        case 4:{
                              g.setColor(new Color(255, 255,0)); 
                              break;
                        } //yellow
                        case 5:{
                              g.setColor(new Color(0, 255, 255)); 
                              break;
                        } //cian
                        case 6:{
                              g.setColor(new Color(255, 0, 255)); 
                              break;
                        }case 7:{
                              g.setColor(new Color(255, 0, 0)); 
                              break;
                        }case 8:{
                              g.setColor(new Color(0, 255, 0)); 
                              break;
                        }case 9:{
                              g.setColor(new Color(255, 0, 255)); 
                              break;
                        }case 10:{
                              g.setColor(new Color(0, 255, 0)); 
                              break;
                        }case 11:{
                              g.setColor(new Color(0, 255, 255)); 
                              break;
                        }case 12:{
                              g.setColor(new Color(255, 0, 0)); 
                              break;
                        }case 13:{
                              g.setColor(new Color(255, 0, 255)); 
                              break;
                        }case 14:{
                              g.setColor(new Color(0, 0, 255)); 
                              break;
                        }case 15:{
                              g.setColor(new Color(255, 255, 0)); 
                              break;
                        }case 16:{
                              g.setColor(new Color(255, 0, 0)); 
                              break;
                        }case 17:{
                              g.setColor(new Color(0, 255, 255)); 
                              break;
                        }
                  }

                  g.fillPolygon(x, y, 4);
            }
      } 
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
