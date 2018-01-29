import java.util.Scanner;

public class Main{

    public static void main(String args[]){
        int numberOfData;
        Scanner scanner = new Scanner(System.in);
        
        while(scanner.hasNext()){
            numberOfData = scanner.nextInt();

            while(numberOfData > 0){
           
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                int c = scanner.nextInt();

                if(a + b > c && a + c > b && b + c > a){
                    System.out.println("OK");
                }else{
                    System.out.println("Wrong!!");
                }
                numberOfData --;
            }
        }

        System.exit(0);
    }
}