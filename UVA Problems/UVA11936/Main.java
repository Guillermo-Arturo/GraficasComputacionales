import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main{

    public static void main(String args[]){
        int numberOfData;
        try{
            File data = new File(args[0]);
            Scanner scanner = new Scanner(data);

            numberOfData = Integer.parseInt(scanner.nextLine());
            
            while(scanner.hasNextLine()){
                StringTokenizer tokens = new StringTokenizer(scanner.nextLine());
                int a = Integer.parseInt(tokens.nextToken());
                int b = Integer.parseInt(tokens.nextToken());
                int c = Integer.parseInt(tokens.nextToken());

                if(a + b > c && a + c > b && b + c > a){
                    System.out.println("OK");
                }else{
                    System.out.println("Wrong!!");
                }
            }
        }catch(IOException e){

        }
    }
}