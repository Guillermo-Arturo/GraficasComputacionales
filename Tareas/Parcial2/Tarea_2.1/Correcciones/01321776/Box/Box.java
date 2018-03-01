//Guillermo Arturo Hernandez Tapia	A01321776
//Jose Maria Montiel Palacios		A01323942

import javax.swing.JFrame;
import javax.swing.JPanel;

class Box{
    public static void main(String[] args) {

        double x_len, y_len, z_len;
        int x_divisions, y_divisions, z_divisions;

        if(args.length > 5){
            x_len = Double.parseDouble(args[0]);
            y_len = Double.parseDouble(args[1]);
            z_len = Double.parseDouble(args[2]);

            x_divisions = Integer.parseInt(args[3]);
            y_divisions = Integer.parseInt(args[4]);
            z_divisions = Integer.parseInt(args[5]);

            BoxDrawer applet = new BoxDrawer(x_len, y_len, z_len, x_divisions, y_divisions, z_divisions);
            applet.init();
            final JFrame frame = new JFrame("Box Viewer");
            frame.setContentPane(applet.getContentPane());
            frame.setJMenuBar(applet.getJMenuBar());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 700);
            frame.setLocation(100, 100);
            frame.setVisible(true);
            applet.start();
        }else{
            System.out.println("ERROR: You need to give me " + "\n"
                                + "X length value" + "\n"
                                + "Y length value" + "\n"
                                + "Z length value" + "\n"
                                + "Number of divisions in X" + "\n"
                                + "Number of divisions in Y" + "\n"
                                + "Number of divisions in Z" + "\n");
        }
    }
}