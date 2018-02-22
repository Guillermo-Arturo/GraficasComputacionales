// Guillermo Arturo Hernández Tapia     A01321776

public class Hexagon{
    public static void main(String args[]){
        int numberOfPlusSymbol = 0;
        int maxNumberSymbols = 0;

        numberOfPlusSymbol = Integer.parseInt(args[0]);

        //Calculate the number of plus symbols that draw the above side of the Hexagon.
        maxNumberSymbols = numberOfPlusSymbol + ((numberOfPlusSymbol - 1) * 2);

        int aboveStart = numberOfPlusSymbol;
        int aboveFinish = numberOfPlusSymbol;
        int pivotRight = numberOfPlusSymbol;
        int pivotLeft = numberOfPlusSymbol;
        int inversePivotRight = 2;

        //Drawing the above side.
        for(int i=1; i <= numberOfPlusSymbol; i++){
            int limit = 0;
            for(int j=1; j <= maxNumberSymbols; j++){
                if((i == 1 && j == aboveStart && j <= (maxNumberSymbols -limit)) || 
                    j == pivotRight || j == ((maxNumberSymbols + 1) - pivotRight)){
                    System.out.print("+");
                    aboveStart ++;
                }else{
                    System.out.print(" ");
                    limit ++;
                }
            }
            pivotRight --;
            pivotLeft ++;
            System.out.print("\n");
        }

        //Drawing the down side.
        for(int i=1; i < numberOfPlusSymbol; i++){
            int limit = 0;
            for(int j=1; j < maxNumberSymbols; j++){
                if((i == numberOfPlusSymbol - 1 && j >= numberOfPlusSymbol && j <= maxNumberSymbols - limit) || 
                    j == inversePivotRight || j == ((maxNumberSymbols + 1) - inversePivotRight)){
                    System.out.print("+");
                }else{
                    System.out.print(" ");
                    limit ++;
                }
            }
            inversePivotRight ++;
            System.out.print("\n");
        }
    }
}