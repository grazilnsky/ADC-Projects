import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Plants {
	
	public static void main(String[] args) throws IOException {
		FileReader fr=new FileReader("plants.in");
		Scanner sc=new Scanner(fr);
		BufferedWriter fw = null;
		fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("plants.out")));
		int N=sc.nextInt();
		int M=sc.nextInt();
		int K=sc.nextInt();
		float r,g,b,res,max;
		int pos;
		int[][] knownPlantsRGB=new int[N][3];
		String[] knownType=new String[N];
		int[][] unknownPlantsRGB=new int[M][3];

		String[]similarityTypes=new String[K];
		
		for(int i=0;i<N;i++) {
			knownPlantsRGB[i][0]=sc.nextInt();
			knownPlantsRGB[i][1]=sc.nextInt();
			knownPlantsRGB[i][2]=sc.nextInt();
			knownType[i]=sc.next();
		}
		
		for(int i=0;i<M;i++) {
			unknownPlantsRGB[i][0]=sc.nextInt();
			unknownPlantsRGB[i][1]=sc.nextInt();
			unknownPlantsRGB[i][2]=sc.nextInt();
		}
		
		for(int i=0;i<M;i++) {
			int countA=0;
			int countB=0;
			float[] minK= new float[K];
			Arrays.fill(minK, 443);
			
			for(int j=0;j<N;j++) {
				r=unknownPlantsRGB[i][0]-knownPlantsRGB[j][0];
				g=unknownPlantsRGB[i][1]-knownPlantsRGB[j][1];
				b=unknownPlantsRGB[i][2]-knownPlantsRGB[j][2];
				res=(float) Math.sqrt(r*r + g*g + b*b);	
				max=0;
				pos=0;
				for(int k=0;k<K;k++) {
					if(minK[k]>max)	{			
						max=minK[k];
						pos=k;
					}				
				}
				if(res<max) {
					minK[pos]=res;
					similarityTypes[pos]=knownType[j];
				}
			}
			
			for(int j=0;j<K;j++) {
				if(similarityTypes[j].equals("A")){
					countA++;
				}

				if(similarityTypes[j].equals("B")) {
					countB++;
				}
			}
			
			if(countA>countB) {
				fw.write("Probably A");
			}
			else {
				fw.write("Probably B");
			}
			fw.newLine();
		}
		
		sc.close();
		fw.close();
	}
}
