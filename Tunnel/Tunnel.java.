import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

public class Tunnel {
	public static void main(String[] args) throws IOException, FileNotFoundException {	
		FileReader fr=new FileReader("tunnel.in");
		Scanner sc=new Scanner(fr);
		Writer fw = null;
		fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("tunnel.out")));
		
		int N=sc.nextInt();
		int M=sc.nextInt();
		int escapeTime;
		escapeTime=0;
		
		int traps[]=new int[N];
		int switchLoc[]=new int[M];
		int switchTime[]=new int[M];
		int trapActivator[][]=new int[M][N];
		
		
		for(int i=0;i<N;i++) {
			traps[i]=sc.nextInt();
		}
		

		for(int i=0;i<M;i++) {
			switchLoc[i]=sc.nextInt();
			switchTime[i]=sc.nextInt();
			int r=sc.nextInt();
			for(int j=0;j<r;j++) {
				trapActivator[i][sc.nextInt()]=1;
			}
		}
		

		for(int k=0;k<N;k++) {
			if(traps[k]==1) {
				for(int i=0;i<M;i++)
					if(switchLoc[i]==k) {
						for(int j=0;j<N;j++)
							if(trapActivator[i][j]==1) {
								if(traps[j]==1) {
									traps[j]=0;
								}
								else if(traps[j]==0) {
										traps[j]=1;
									}
							}
						escapeTime=escapeTime + switchTime[i];
					}
			}
		}
				
		fw.write("" + escapeTime);

		sc.close();
		fw.close();
	}
}
