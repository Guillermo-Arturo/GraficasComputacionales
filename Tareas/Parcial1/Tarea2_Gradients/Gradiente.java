import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Gradiente extends JPanel{
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		Color matriz[][]= new Color[500][1000];

		for(int i=0; i<500;i++){
			for(int j=0;j<1000;j++){
				matriz[i][j]=new Color(0,0,0);
			}
		}

		for(int i=0; i<500; i++){
			for(int j=0;j<500;j++){
				matriz[j][i]=setRed(250-(i/2),matriz[j][i]);
			}
		}

		for(int i=0; i<500; i++){
			for(int j=0;j<500;j++){
				matriz[i][j]=setGreen(250-(i/2),matriz[i][j]);
			}
		}

		for(int i=0; i<500; i++){
			for(int j=0;j<500;j++){
				matriz[j][i]=setBlue((i/2),matriz[j][i]);
			}
		}

		//========================

		for(int i=500; i<1000; i++){
			for(int j=0;j<500;j++){
				matriz[j][i]=setRed(((i-500)/2),matriz[j][i]);
			}
		}

		
		Color matTemp[][]=copySecondHalf(matriz);
		Color y[][]=transverse(matTemp,500);
		Color s[][]=mirror(y,500);
		Color a[][]=setGreenSH(s);
		Color b[][]=mirror(a,500);
		Color c[][]=transverse(b,500);
		matriz=copySecondHalf2(matriz, c);
		
		/*
		Color matTemp[][]=copySecondHalf(matriz);
		Color a[][]=setGreenSHV2(matTemp);
		matriz=copySecondHalf2(matriz, a);
		*/
		

		for(int i=500; i<1000; i++){
			for(int j=0;j<500;j++){
				matriz[j][i]=setBlue(250,matriz[j][i]);
			}
		}
		
		//========================

		for(int i=0; i<500;i++){
			for(int j=0;j<1000;j++){
				g.setColor(matriz[i][j]);
				g.fillRect(j, i, 1, 1);
			}
		}
	}

	public static void main(String args[]){
		Gradiente panel = new Gradiente();
		JFrame application = new JFrame();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.add(panel);
		application.setSize(1000, 500);
		application.setVisible(true);
	}
	public Color setRed(int red, Color color) {
	    int r = color.getRed();
	    int g = color.getGreen();
	    int b = color.getBlue();
	    return new Color(red,g,b);
	}
	public Color setGreen(int green, Color color) {
	    int r = color.getRed();
	    int g = color.getGreen();
	    int b = color.getBlue();
	    return new Color(r,green,b);
	}
	public Color setBlue(int blue, Color color) {
	    int r = color.getRed();
	    int g = color.getGreen();
	    int b = color.getBlue();
	    return new Color(r,g,blue);
	}
	public Color[][] copySecondHalf(Color matriz[][]){
		Color newMat[][]= new Color[500][500];

		for(int i=0; i<500;i++){
			int index=0;
			for(int j=500;j<1000;j++){
				newMat[i][index]=matriz[i][j];
				index++;
			}
		}

		return newMat;

	}

	Color[][] transverse(Color mat[][], int n){
        Color newMat[][]= new Color[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                newMat[i][j]=mat[j][i];
            }
        }
        return newMat;
    }

    Color[][] mirror(Color mat[][], int n){
        Color newMat[][]= new Color[n][n];
        for(int i=0;i<n;i++){
            int index=n-1;
            for(int j=0;j<n;j++){
                newMat[i][j]=mat[i][index];
                index--;
            }
        }
        return newMat;
    }

    Color[][] setGreenSH(Color matriz[][]){
    	int n = 500;
        for (int slice = 0; slice < 2 * n - 1; slice++) {
            //System.out.print("Slice: "+ slice);
            int z = (slice < n) ? 0 : slice - n + 1;
            //System.out.println(" and z: "+z);
            for (int j = z; j <= slice - z; j++) {
                //System.out.print( matriz[j][slice - j]);
                if(z==0){
                	matriz[j][slice - j]=setGreen( slice/2,matriz[j][slice - j]);
                	//System.out.print(" "+slice/2+",");
                }else{
                	matriz[j][slice - j]=setGreen(255-(slice/2-250),matriz[j][slice - j]);
                	//System.out.print(" "+(250-(slice/2-250))+",");
                }

            }
            //System.out.println();
        }
        return matriz;
    }
    Color[][] copySecondHalf2(Color matriz[][],Color c[][]){
    	for(int i=0; i<500;i++){
			int index=0;
			for(int j=500;j<1000;j++){
				matriz[i][j]=c[i][index];
				index++;
			}
		}

		return matriz;
    }

     Color[][] setGreenSHV2(Color matriz[][]){
    	int n = 500;
        for (int i = 0; i<500; i++) {
        	int step = (i == 0) ? 0 : 255/(i);
        	int acu=0;
        	for(int j=0;j<i;j++){
        		acu+=step;
        		matriz[i][j]=setGreen(acu,matriz[i][j]);
        	}
        	step=255/(500-i);
        	System.out.println("Step is: "+step);
        	for(int j=i;j<500;j++){
        		if(acu<0) {
        			System.out.println("error en i:"+i+" y acu="+acu+" y step="+step);
        			acu+=step;
        		}
        		matriz[i][j]=setGreen(acu,matriz[i][j]);
        		acu-=step;

        	}
        }
        return matriz;
    }
}


