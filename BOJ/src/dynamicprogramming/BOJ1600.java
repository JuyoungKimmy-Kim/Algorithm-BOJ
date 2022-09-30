package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1600 {

	static final int EMPTY=0;
	static final int WALL=1;

	static final int dy[] = {0,1,0,-1};
	static final int dx[] = {1,0,-1,0};
	
	static final int hdy[] = {-2,-1,1,2,2,1,-1,-2};
	static final int hdx[] = {1,2,2,1,-1,-2,-2,-1};
	
	static int K,W,H;
	static int[][] map;
	static boolean[][][] visited;	
		
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		K=Integer.parseInt(br.readLine());
		st=new StringTokenizer (br.readLine());
		
		W=Integer.parseInt(st.nextToken());
		H=Integer.parseInt(st.nextToken());
		map=new int[H][W];	
		visited=new boolean[H][W][K+1];
		
		for (int i=0; i<H; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<W; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}	
		System.out.println(bfs());
	}
	
	static int bfs () {
		
		int ans=Integer.MAX_VALUE;
		Queue<Pos> q=new ArrayDeque<>();
		q.add(new Pos (0,0,0,0));

		while (!q.isEmpty()) {
			Pos p=q.poll();
			int y=p.y;
			int x=p.x;
			int horseCnt=p.horseCnt;
			int totalCnt=p.totalCnt;		
			
			if (y==H-1 && x==W-1) {
				ans=Math.min(ans, totalCnt);
			}
			
			if (horseCnt<K) {
				for (int d=0; d<8; d++) {
					int ny=y+hdy[d];
					int nx=x+hdx[d];
										
					if (isInRange (ny, nx) && map[ny][nx]==EMPTY && !visited[ny][nx][horseCnt+1]) {
						visited[ny][nx][horseCnt+1]=true;
						q.add(new Pos (ny, nx, horseCnt+1, totalCnt+1));
					}
				}
			}
			
			for (int d=0; d<4; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
	
				if (isInRange (ny, nx) && map[ny][nx]==EMPTY && !visited[ny][nx][horseCnt]) {
					visited[ny][nx][horseCnt]=true;
					q.add(new Pos (ny, nx, horseCnt, totalCnt+1));
				}
			}
		}
		
		if (ans==Integer.MAX_VALUE) return -1;
		else return ans;
	}
	

	static boolean isInRange (int ny, int nx) {
		if (ny<0 || nx<0 || ny>=H || nx>=W) return false;
		return true;
	}
	
	static class Pos {
		int y, x;
		int horseCnt, totalCnt;
		
		Pos (int y, int x, int horseCnt, int totalCnt) {
			this.y=y;
			this.x=x;
			this.horseCnt=horseCnt;
			this.totalCnt=totalCnt;
		}
	}
}
