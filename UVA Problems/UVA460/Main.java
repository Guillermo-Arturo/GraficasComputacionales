import java.util.Scanner;

class Point2D{
    int x, y;
    public Point2D(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main{
    static boolean r = true;

    public static void main(String args[]){
        int numberOfData;
        Scanner sc = new Scanner(System.in);
        Point2D p1, p2, p3, p4;

        while(sc.hasNext()){
            numberOfData = sc.nextInt();
            sc.nextLine();

            while(numberOfData > 0){
                int a, b, c, d;

                p1 = new Point2D(sc.nextInt(), sc.nextInt());
                p2 = new Point2D(sc.nextInt(), sc.nextInt());
                sc.nextLine();
                p3 = new Point2D(sc.nextInt(), sc.nextInt());
                p4 = new Point2D(sc.nextInt(), sc.nextInt());

                isOverlapping(p1, p2, p3, p4);

                numberOfData --;

            }
        }

        System.exit(0);
    }

    public static void isOverlapping(Point2D p1, Point2D p2, Point2D p3, Point2D p4){
        int a, b, c, d;

        a = Math.max(p1.x, p3.x);
        b = Math.max(p1.y, p3.y);
        c = Math.min(p2.x, p4.x);
        d = Math.min(p2.y, p4.y);

        if(a >= c || b >= d)
            System.out.println("\nNo Overlap");
        else
            System.out.println("\n" + a + " " + b + " " + c + " " + d);
    }
}