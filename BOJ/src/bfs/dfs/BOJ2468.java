package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
	int y,x;
	
	Pair (int y, int x) {
		this.y=y;
		this.x=x;
	}
}

public class BOJ2468 {
	
	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};

	static int N;
	static int safeArea=1;
	
	static boolean [][] checked;
	static int [][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		N=Integer.parseInt(br.readLine());
		map=new int[N][N];
		
		for (int i=0; i<N; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		solution();
		System.out.println(safeArea);
	}
	
	private static void solution () {
		for (int high=1; high<=100; high++) {
			
			checked =new boolean[N][N];
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (map[i][j]<=high)
						checked[i][j]=true;
				}
			}
			
			int area=0;	
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					
					if (!checked[i][j]) {
						area++;
						checked[i][j]=true;
						bfs(i,j);
					}	
				}
			}
			safeArea=Math.max(area, safeArea);
		}
	}

	private static void bfs (int i, int j) {
		
		Queue <Pair> q =new ArrayDeque<>();
		q.add(new Pair(i,j));
		
		while (!q.isEmpty()) {
			Pair cur=q.poll();
			int y=cur.y;
			int x=cur.x;
			
			for (int d=0; d<4; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
				
				if (ny<0 || nx<0 || ny>=N || nx>=N || checked[ny][nx]) continue;
				
				checked[ny][nx]=true;
				q.add(new Pair(ny, nx));
			}
		}
	}
	

}
