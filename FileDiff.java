import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class FileDiff {
	public static void main(String[] args) throws IOException, FileNotFoundException {
		FileReader fr1=new FileReader("1.in");
		FileReader fr2=new FileReader("2.in");
		Scanner sc1=new Scanner(fr1);
		Scanner sc2=new Scanner(fr2);
		BufferedWriter fw = null;
		fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("intersection.out")));
		
		
		String[] X=new String[1320];
		String[] Y=new String[1320];
		int countX, countY, currentX, currentY;
		int length[][]=new int[1320][1320];
		int direction[][]=new int[1320][1320];
		int pos1[]=new int[1320];
		int pos2[]=new int[1320];
		countX=1;
		countY=1;
		
		
		while(sc1.hasNext() || sc2.hasNext()) {
			if(sc1.hasNext()) {
				X[countX]=sc1.nextLine();
				X[countX]=X[countX].replaceAll("[^a-zA-Z]", "");
				countX++;
			}
			if(sc2.hasNext()) {
				Y[countY]=sc2.nextLine();
				Y[countY]=Y[countY].replaceAll("[^a-zA-Z]", "");
				countY++;
			}
		}
		sc1.close();
		sc2.close();
		
		
		for(int i=1;i<countX;i++) {
			length[i][0]=0;
		}
		for(int j=0;j<countY;j++) {
			length[0][j]=0;
		}
		for(int i=1;i<countX;i++)
			for(int j=1;j<countY;j++)
				if(X[i].equals(Y[j])) {
					length[i][j]=length[i-1][j-1]+1;
					direction[i][j]=1;
				}
				else if(length[i-1][j]>=length[i][j-1]) {
						length[i][j]=length[i-1][j];
						direction[i][j]=2;
					}
				else {
					length[i][j]=length[i][j-1];
					direction[i][j]=3;
				}
		
		
		currentX=countX-1;
		currentY=countY-1;
		
		while(length[currentX][currentY]!=0) {
			if(direction[currentX][currentY]==1) {
				pos1[length[currentX][currentY]-1]=currentX;
				pos2[length[currentX][currentY]-1]=currentY;
				currentX--;
				currentY--;
			}
			else if(direction[currentX][currentY]==2) {
				currentX--;
			}
			else if(direction[currentX][currentY]==3) {
				currentY--;
			}	
		}
		
		if(length[countX-1][countY-1]!=0) {
			fw.write("> ");
			for(int i=0;i<length[countX-1][countY-1];i++) {
				fw.write(pos1[i]+ " ");
			}
			
			fw.newLine();
			fw.write("< ");
			for(int i=0;i<length[countX-1][countY-1];i++) {
				fw.write(pos2[i]+ " ");
			}
		}
		else
			fw.write("");
		fw.close();
	}
}
