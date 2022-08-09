package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2573 {

	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};
	
	static final int SEA=0;
	
	static int N,M;
	static int[][] map;
	static int[][] melt;
	
	static int ice_cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(br.readLine());
		M=Integer.parseInt(br.readLine());
		
		map=new int[N][N];
		melt=new int[N][N];
		
		for (int i=0; i<N; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				
				if (map[i][j]>0) ice_cnt++;
			}
		}

	}
	
	private static void meltIce () {
		for (int i=0; i<M; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j]>0) {
					
					for (int d=0; d<4; d++) {
						int ny=i+dy[d];
						int nx=j+dx[d];
					}
				}
			}
		}
	}

}
