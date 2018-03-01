//Guillermo Arturo Hernandez Tapia	A01321776
//Jose Maria Montiel Palacios		A01323942

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Cone{

    public static void main(String[] args) {

        double radio, h;
        int y_divisions, zx_divisions;

        if(args.length > 3){
            
            radio = Double.parseDouble(args[0]);
            h = Integer.parseInt(args[1]);
            y_divisions = Integer.parseInt(args[3]);
            zx_divisions = Integer.parseInt(args[2]);

            ConeDrawer applet = new ConeDrawer(radio,h,  y_divisions, zx_divisions);
            applet.init();
            final JFrame frame = new JFrame("Cone Viewer");
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
                                + "Heigth value" + "\n"
                                + "bd divisions of the base" + "\n"
                                + "hd divisions in height" + "\n");
        }
    }
}