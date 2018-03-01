//Guillermo Arturo Hernandez Tapia	A01321776
//Jose Maria Montiel Palacios		A01323942

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Sphere{

    public static void main(String[] args) {

        double radio;
        int y_divisions, zx_divisions;

        if(args.length > 2){
            
            radio = Double.parseDouble(args[0]);
            y_divisions = Integer.parseInt(args[2]);
            zx_divisions = Integer.parseInt(args[1]);

            SphereDrawer applet = new SphereDrawer(radio, y_divisions, zx_divisions);
            applet.init();
            final JFrame frame = new JFrame("Sphere Viewer");
            frame.setContentPane(applet.getContentPane());
            frame.setJMenuBar(applet.getJMenuBar());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 700);
            frame.setLocation(100, 100);
            frame.setVisible(true);
            applet.start();

        }else{
            System.out.println("ERROR: You need to give me " + "\n"
                                + "Radio value" + "\n"
                                + "Number of divisions rotating y plane" + "\n"
                                + "Number of divitions parallel zx plane" + "\n");
        }
    }
}