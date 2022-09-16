package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1113 {

	static int N,M;
	static int[][] pool, add;
	static boolean[][] visited;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		pool=new int[N][M];
		
		for (int i=0; i<N; i++) {
			String line=br.readLine();
			for (int j=0; j<M; j++) {
				pool[i][j]=line.charAt(j)-'0';
			}
		}
		
		
		
		
	}

}
