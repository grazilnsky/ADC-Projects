import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Madripoor {
	public static void main(String[] args) throws IOException {
		
		BufferedReader fr=new BufferedReader(new FileReader("madripoor.in"));
		Scanner sc=new Scanner(fr);
		BufferedWriter fw = null;
		fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("madripoor.out")));	
		
		int N=sc.nextInt();		
		int currentX, currentY, neighbourX, neighbourY, foundX, foundY;
		char[][] rooms=new char[N][N];
		String temp = null;
		int[][] distances=new int[N][N];
		Queue<Integer> q=new LinkedList<Integer>();
		Queue<Integer> found=new LinkedList<Integer>();

		
		for(int i=0;i<N;i++) {
			if(sc.hasNextLine()) {
				temp=sc.next();
			}
			for(int j=0;j<N;j++) {
				rooms[i][j]=temp.charAt(j);
				if(rooms[i][j]=='P') {
					distances[i][j]=0;
					found.add(i);
					found.add(j);						
				}
				else if(rooms[i][j]=='#') {
					distances[i][j]=-2;
				}	
				else
					distances[i][j]=-1;
			}
		}
		
		
		int[] dx= {-1, 1, 0, 0};
		int[] dy= {0, 0, -1, 1};
		
		while(!found.isEmpty()) {
			foundX=found.poll();
			foundY=found.poll();			
			q.add(foundX);
			q.add(foundY);
			
			while(!q.isEmpty()) {
				currentX = (int)q.poll();
				currentY = (int)q.poll(); 
				
				for(int k = 0; k < 4; k++) {
					neighbourX = dx[k] + currentX;
					neighbourY = dy[k] + currentY;
				    if(neighbourX>= 0 && neighbourX<N && neighbourY>=0 && neighbourY<N && rooms[neighbourX][neighbourY]!='#') {	
				    	if(distances[neighbourX][neighbourY]>distances[currentX][currentY]+1 || distances[neighbourX][neighbourY]==-1) {
				            distances[neighbourX][neighbourY]=distances[currentX][currentY]+1;
							q.add(neighbourX);
							q.add(neighbourY);
				    	}
				    }
				} 
			}							
																						
		}						
			
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				fw.write(distances[i][j]+ " ");
			}
			fw.newLine();
		}
		sc.close();
		fw.close();
	}
}
