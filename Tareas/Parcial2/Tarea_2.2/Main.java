//Guillermo Arturo Hernandez Tapia	A01321776
//Jose Maria Montiel Palacios		A01323942

import javax.swing.JFrame;
import javax.swing.JPanel;

class Main{
      public static void main(String[] args) {

            TetrisDrawer applet = new TetrisDrawer();
            applet.init();
            final JFrame frame = new JFrame("Tetris T Viewer");
            frame.setContentPane(applet.getContentPane());
            frame.setJMenuBar(applet.getJMenuBar());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1300, 700);
            frame.setLocation(100, 100);
            frame.setVisible(true);
            applet.start();
      }
}