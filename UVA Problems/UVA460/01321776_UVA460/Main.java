import java.util.Scanner;

class Point2D{
    int x, y;
    public Point2D(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main{

    public static void main(String args[]){
        int numberOfData;
        Scanner sc = new Scanner(System.in);
        Point2D p1, p2, p3, p4;
        numberOfData = sc.nextInt();
        sc.nextLine();
        sc.nextLine();

        int a, b, c, d;

        p1 = new Point2D(sc.nextInt(), sc.nextInt());
        p2 = new Point2D(sc.nextInt(), sc.nextInt());
        sc.nextLine();
        p3 = new Point2D(sc.nextInt(), sc.nextInt());
        p4 = new Point2D(sc.nextInt(), sc.nextInt());
        isOverlapping(p1, p2, p3, p4);

           
        for(int i=1; i<numberOfData;i++){
            System.out.println();

            sc.nextLine();
            sc.nextLine();
            p1 = new Point2D(sc.nextInt(), sc.nextInt());
            p2 = new Point2D(sc.nextInt(), sc.nextInt());
            sc.nextLine();
            p3 = new Point2D(sc.nextInt(), sc.nextInt());
            p4 = new Point2D(sc.nextInt(), sc.nextInt());

            

            isOverlapping(p1, p2, p3, p4);
        }
    }
    public static void isOverlapping(Point2D p1, Point2D p2, Point2D p3, Point2D p4){
        int a, b, c, d;

        a = (p1.x > p3.x ? p1.x : p3.x);
        b = (p1.y > p3.y ? p1.y : p3.y);
        c = (p2.x < p4.x ? p2.x : p4.x);
        d = (p2.y < p4.y ? p2.y : p4.y);

        if(a >= c || b >= d)
            System.out.println("No Overlap");
        else
            System.out.println(a + " " + b + " " + c + " " + d);
    }
}