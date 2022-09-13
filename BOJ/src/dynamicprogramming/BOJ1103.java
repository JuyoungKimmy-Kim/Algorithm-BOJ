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
	
	static int N, M;
	static char [][] map;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());

		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		dp=new int[N][M];
		for (int i=0; i<N; i++)
			Arrays.fill(dp[i], -1);
		

		map=new char[N][];
		
		for (int i=0; i<N; i++) {
			String line=br.readLine();
			map[i]=line.toCharArray();
		}
		System.out.println(bfs());
		
	}
//	private static int bfs () {
//		Queue<int[] >q=new ArrayDeque<> ();
//		q.add(new int[] {0,0});
//		dp[0][0]=1;
//		
//		int cnt=1;
//		while (!q.isEmpty()) {
//			int y=q.peek()[0];
//			int x=q.poll()[1];
//			int s=map[y][x]-'0';
//			
//			boolean flag=false;
//			for (int d=0; d<4; d++) {
//				int ny=y+dy[d]*s;
//				int nx=x+dx[d]*s;
//				
//				if (ny<0 || nx<0 || ny>=N || nx>=M || map[ny][nx]=='H') continue;
//				
//				if (dp[ny][nx]!=-1 && dp[ny][nx]-'0'<=s)
//					flag=true;
//				
//				if (dp[ny][nx]==-1) {
//					dp[ny][nx]=dp[y][x]+1;
//					q.add(new int[] {ny, nx});
//					cnt=dp[ny][nx];
//				}
//			}
//			if (flag) return -1;
//		}
//		
//		print();
//		return cnt;
//	}
	


	private static void print () {
		System.out.println("===============================");
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				System.out.printf("%3d", dp[i][j]);
			}
			System.out.println();
		}
	}


}
