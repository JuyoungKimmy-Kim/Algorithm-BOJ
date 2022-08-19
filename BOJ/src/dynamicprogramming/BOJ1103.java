package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1103 {

	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};
	
	static int N, M;
	static int [][] map;
	static int [][]dp;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		map=new int[N][M];
		for (int i=0; i<N; i++)
			Arrays.fill(dp[i], -1);
		visited=new boolean[N][N];
		
		for (int i=0; i<N; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if (map[i][j]=='A'+7) map[i][j]=0;
			}
		}
	}
	
	private static int dfs (int y, int x) {
		if (y<0 || x<0|| y>=N || x>=N) return -1;
		if (dp[y][x]!=-1) return dp[y][x];
		
		visited[y][x]
	}

}
