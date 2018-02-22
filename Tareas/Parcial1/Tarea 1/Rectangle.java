//Guillermo Arturo Hernández Tapia      A01321776

public class Rectangle{
    public static void main(String args[]){
        int rows = 0;
        int columns = 0;

        if(args.length < 2){
            System.out.println("You need to give me the number of rows and columns");
            System.exit(1);
        }

        rows = Integer.parseInt(args[0]);
        columns = Integer.parseInt(args[1]);

        for(int r=1; r <= rows; r++){
            for(int c=1; c <= columns; c++){
                if(c == 1 || r == 1 || r == rows || c == columns)
                    System.out.print(" + ");
                else
                    System.out.print("   ");
            }
            System.out.print("\n");
        }
    }
}