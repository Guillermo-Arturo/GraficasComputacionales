//Guillermo Arturo Hernandez Tapia  A01321776
//Jose Maria Montiel Palacios   A01323942
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TieFighter extends JApplet 
                  implements KeyListener, FocusListener, MouseListener {
                      // (Note:  MouseListener is implemented only so that
                      //         the applet can request the input focus when
                      //         the user clicks on it.)

    Color tieFighterColor;
    Utils utils;
    Transformations transformations;
   
    boolean focussed = false;   // True when this applet has input focus.
   
    DisplayPanel canvas;  // The drawing surface on which the applet draws,
                         // belonging to a nested class DisplayPanel, which
                         // is defined below.

    double[] xCoordinates = { 10, 10, 10, 10, 10, 10, 10, 30, 30, 40, 70, 100, 100, 110, 115, 125, 130, 135, 135, 140, 140, 150, 160, 180,
                            190, 200, 210, 220, 230, 240, 240, 245, 245, 250, 255, 265, 270, 280, 280, 310, 340, 350, 350, 370, 370, 370,
                            370, 370, 370, 370, 370, 350, 350, 340, 310, 280, 250, 240, 240, 235, 235, 235, 230, 230, 230, 225, 225, 225, 
                            220, 220, 210, 170, 160, 170, 180, 200, 210, 220, 160, 160, 155, 155, 155, 150, 150, 150, 145, 145, 145, 140, 
                            140, 130, 100, 70, 40, 30, 30, 10 };

    double[] yCoordinates = { 210, 170, 130, 90, 50, 90, 110, 110, 120, 120, 110, 100, 90, 90, 80, 80, 90, 90, 80, 80, 90, 80, 70, 70, 70, 
                            70, 70, 70, 80, 90, 80, 80, 90, 90, 80, 80, 90, 90, 100, 110, 120, 120, 110, 110, 90, 50, 90, 130, 170, 210, 
                            150, 150, 140, 140, 150, 160, 180, 190, 210, 220, 230, 220, 220, 230, 220, 220, 230, 220, 210, 200, 210, 210, 
                            200, 200, 210, 210, 200, 200, 200, 210, 220, 230, 220, 220, 230, 220, 220, 230, 220, 210, 190, 180, 160, 150,
                            140, 140, 150, 150 };

    double angleDirection=0.0;

    double tieFighterSizeWidth = 0f;
    double tieFighterSizeHeight = 0f;

    int numberOfPoints = xCoordinates.length;

    public void init() {
        // Initialize the applet; set it up to receive keyboard
        // and focus events.  Place the square in the middle of
        // the applet, and make the initial color of the square red.
        // Then, set up the drawing surface.
        
	    setSize(1200,600); 
	   
        tieFighterColor = Color.black;
        utils = new Utils();
        transformations = new Transformations();
        
        canvas = new DisplayPanel();  // Create drawing surface and 
        setContentPane(canvas);       //    install it as the applet's content pane.

        canvas.setBackground(Color.white);  // Set the background color of the canvas.

        canvas.addFocusListener(this);   // Set up the applet to listen for events
        canvas.addKeyListener(this);     //                          from the canvas.
        canvas.addMouseListener(this);

        /*
        * To move the original points to the center of the window.
        */
        for(int i=0; i < numberOfPoints; i++){
            xCoordinates[i] += 450;
            yCoordinates[i] += 150;
        }

        tieFighterSizeWidth = utils.getMaxArrayValue(xCoordinates) - utils.getMinArrayValue(xCoordinates);
        tieFighterSizeHeight = utils.getMaxArrayValue(yCoordinates) - utils.getMinArrayValue(yCoordinates);

      
   } // end init();
   
   
   class DisplayPanel extends JPanel {
          // An object belonging to this nested class is used as
          // the content pane of the applet.  It displays the
          // moving square on a white background with a border
          // that changes color depending on whether this 
          // component has the input focus or not.
   
        public void paintComponent(Graphics g) {
        
            super.paintComponent(g);  // Fills the panel with its
                                    // background color, which is white.

            /* Draw a 3-pixel border, colored cyan if the applet has the
                keyboard focus, or in light gray if it does not. */

            if (focussed) 
                g.setColor(Color.cyan);
            else
                g.setColor(Color.lightGray);
            
            

            int width = getSize().width;  // Width of the applet.
            int height = getSize().height; // Height of the applet.
            g.drawRect(0,0,width-1,height-1);
            g.drawRect(1,1,width-3,height-3);
            g.drawRect(2,2,width-5,height-5);

            /* Draw the Tie Fighter Ship into the window. */
            final int xComponent[] = new int[numberOfPoints];
            final int yComponent[] = new int[numberOfPoints];

            for(int i=0; i < numberOfPoints; i++){
                xComponent[i] = (int) xCoordinates[i];
                yComponent[i] = (int) yCoordinates[i];
            }

            g.setColor(tieFighterColor);
            g.drawPolygon(xComponent, yComponent, numberOfPoints);
        
        

         /* If the applet does not have input focus, print a message. */

         if (!focussed) {
            g.setColor(Color.magenta);
            g.drawString("Click to activate",7,20);
         }

      }  // end paintComponent()
    
    } // end nested class DisplayPanel 
    
    

   // ------------------- Event handling methods ----------------------
   
   public void focusGained(FocusEvent evt) {
         // The applet now has the input focus.
      focussed = true;
      canvas.repaint();  // redraw with cyan border
   }
   

   public void focusLost(FocusEvent evt) {
         // The applet has now lost the input focus.
      focussed = false;
      canvas.repaint();  // redraw without cyan border
   }
   
   
   public void keyTyped(KeyEvent evt) {
          // The user has typed a character, while the
          // applet has the input focus.  If it is one
          // of the keys that represents a color, change
          // the color of the square and redraw the applet.
          
        char ch = evt.getKeyChar();  // The character typed.

        switch(ch){
            case 'E':{
                rotate(-3.0);
                angleDirection+=-1.0;
                break;
            }
            case 'e':{
                rotate(-3.0);
                angleDirection+=-1.0;
                break;
            }
            case 'D':{
                rotate(3.0);
                angleDirection+=1.0;
                break;
            }
            case 'd':{
                rotate(3.0);
                angleDirection+=1.0;
                break;
            }



            case 'R':{
                scale(1.5);
                break;
            }
            case 'r':{
                scale(1.5);
                break;
            }
            case 'F':{
                scale(.8);
                break;
            }
            case 'f':{
                scale(.8);
                break;
            }
        }
   }  // end keyTyped()

  private void scale(double scale){
        transformations.scale(xCoordinates, yCoordinates, scale, utils);
        canvas.repaint();
   }
   private void rotate(double angle){
        transformations.rotation(xCoordinates, yCoordinates, angle, utils);
        canvas.repaint();
   }
   
   
   public void keyPressed(KeyEvent evt) { 
          // Called when the user has pressed a key, which can be
          // a special key such as an arrow key.  If the key pressed
          // was one of the arrow keys, move the square (but make sure
          // that it doesn't move off the edge, allowing for a 
          // 3-pixel border all around the applet).
          
      int key = evt.getKeyCode();  // keyboard code for the key that was pressed
      
      move(key);
      /*
      switch(key){
            case 38:{ // up Arrow
                moveUp();
                break;
            }
            case 39:{ // right Arrow
                moveRight();
                break;
            }
            case 40:{ // down Arrow
                moveDown(1);
                break;
            }
            case 37:{ // left Arrow
                moveLeft(1);
                break;
            }
        }
        */
      
   }  // end keyPressed()
    private void move(int key){
        transformations.move(xCoordinates, yCoordinates, key, utils, angleDirection);
        canvas.repaint();
    }

   public void keyReleased(KeyEvent evt) { 
      // empty method, required by the KeyListener Interface
   }
   

   public void mousePressed(MouseEvent evt) {
        // Request that the input focus be given to the
        // canvas when the user clicks on the applet.
      canvas.requestFocus();
   }   
   
   
   public void mouseEntered(MouseEvent evt) { }  // Required by the
   public void mouseExited(MouseEvent evt) { }   //    MouseListener
   public void mouseReleased(MouseEvent evt) { } //       interface.
   public void mouseClicked(MouseEvent evt) { }

   


} // end class KeyboardAndFocusDemo
