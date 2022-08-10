package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//class Pair {
//	int y,x;
//	
//	Pair (int y, int x) {
//		this.y=y;
//		this.x=x;
//	}
//}

public class BOJ2146 {
	
	static final int SEA=0;
	static final int LAND=-1;
	
	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};
	
	static int N, minDist;
	static int [][] map;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		N=Integer.parseInt(br.readLine());

		map=new int[N][N];
		
		for (int i=0; i<N; i++) {
			StringTokenizer st=new StringTokenizer (br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if (map[i][j]==1) map[i][j]=-1;
			}
		}
		
		
		int number=1;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j]==LAND) {
					map[i][j]=number;
					numbering (i,j, number);
					number++;
				}
			}
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j]!=SEA) 
					makeBridge (i,j);
			}
		}
		
		System.out.println(minDist);
	}
	
	// #1. 각 섬에 번호를 매김
	
	private static void numbering (int i, int j, int number) {
		Queue <Pair> q=new ArrayDeque<>();
		boolean [][] visited=new boolean [N][N];
		q.add(new Pair (i, j));
		visited[i][j]=true;
		
		while (!q.isEmpty()) {
			Pair cur=q.poll();
			int y=cur.y;
			int x=cur.x;
			
			for (int d=0; d<4; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
				
				if (!isInRange (ny,nx)) continue;
				if (map[ny][nx]==LAND && !visited[ny][nx]) {
					map[ny][nx]=number;
					visited[ny][nx]=true;
					q.add(new Pair (ny, nx));
				}
			}
		}
		
	}
	
	private static void makeBridge (int y, int x) {
		for (int d=0; d<4; d++) {
			int ny=y+dy[d]; 
			int nx=x+dx[d];
			
			int dist=1;
			while (isInRange(ny, nx) && map[ny][nx]==SEA) {
				ny+=dy[d]; 
				nx+=dx[d];
				dist++;
			}
			
			if (!isInRange(ny,nx) || map[ny][nx]==map[y][x] ) continue; //벽에 부딪혀서 끝났을 때 , 자신과 부딪혀서 끝났을 때
			if (dist<minDist) minDist=dist;
		}
	}
	
	private static boolean isInRange (int y, int x) {
		if (y<0 || x<0 || y>=N || x>=N) return false;
		return true;
	}
	
	private static void print () {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				System.out.printf("%3d", map[i][j]);
			}
			System.out.println();
		}
	}

}
