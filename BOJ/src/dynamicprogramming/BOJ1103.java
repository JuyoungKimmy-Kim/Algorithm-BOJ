package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1103 {

	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};
	
	static boolean done;
	static int N, M, ans;
	static char [][] map;
	static int[][] dp;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());

		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		visited=new boolean[N][M];
		dp=new int[N][M];
//		for (int i=0; i<N; i++) 
//			Arrays.fill(dp[i], -1);

		map=new char[N][];
		
		for (int i=0; i<N; i++) {
			String line=br.readLine();
			map[i]=line.toCharArray();
		}

		dfs (0,0,1);
		
		
		if (done) ans=-1;
		System.out.println(ans);
	}
	
	private static void dfs (int y, int x, int cnt) {
		
		if (cnt>ans) 
			ans=cnt;
		
		dp[y][x]=cnt;
		
		print();
		
		for (int d=0; d<4; d++) {
			int ny=y+dy[d]*(map[y][x]-'0');
			int nx=x+dx[d]*(map[y][x]-'0');
			
			if (ny<0 || nx<0 || ny>=N || nx>=M || map[ny][nx]=='H') continue;
			
			if (visited[ny][nx]) {
				done=true;
				return ;
			}
			
			if (dp[ny][nx]>cnt)continue;
			
			visited[ny][nx]=true;
			dfs (ny, nx, cnt+1);
			visited[ny][nx]=false;
		}
	}
	


	private static void print () {
		System.out.println("===============dp===============");
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				System.out.printf("%3d", dp[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		
		System.out.println("===============map===============");
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				System.out.printf("%3c", map[i][j]);
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println();
	}


}
