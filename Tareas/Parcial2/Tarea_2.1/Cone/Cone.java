import javax.swing.JFrame;
import javax.swing.JPanel;

public class Cone{

    public static void main(String[] args) {

        double radio, height;
        int base_divisions, height_divisions;

        if(args.length > 3){
            
            radio = Double.parseDouble(args[0]);
            height = Double.parseDouble(args[1]);
            base_divisions = Integer.parseInt(args[2]);
            height_divisions = Integer.parseInt(args[3]);

            ConeDrawer applet = new ConeDrawer(radio, height, base_divisions, height_divisions);
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
                                + "Height value" + "\n"
                                + "Divisions of the base value" + "\n"
                                + "Divisions of height value" + "\n");
        }
    }
}