package backtracking;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3055_2 {

	static final char EMPTY='.';
	static final char WATER='*';
	static final char STONE='X';
	
	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};
	
	static int R,C,ans=Integer.MAX_VALUE;
	static char[][] map;
	static boolean[][] visited;
	static int [][] dist;
	static int sy,sx,ey,ex,wy,wx;
	static boolean done=false;
	
	static Queue<int[]> q=new ArrayDeque<> ();
	
	public static void main(String[] args)  throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());

		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		
		map=new char[R][C];
		visited=new boolean[R][C];
		dist=new int[R][C];
		
		for (int i=0; i<R; i++)
			Arrays.fill(dist[i], R*C);
		
		for (int i=0; i<R; i++) {
			String line=br.readLine();
			for (int j=0; j<C; j++) {
				map[i][j]=line.charAt(j);
				
				if (map[i][j]=='S') {
					sy=i; sx=j;
				}
				if (map[i][j]=='D') {
					ey=i; ex=j;
				}
				if (map[i][j]==WATER) {
					q.add(new int[] {i,j});
					dist[i][j]=0;
				}
			}
		}
		
		bfs();
		move();
		
		if (ans==Integer.MAX_VALUE) 
			System.out.println("KAKTUS");
		else System.out.println(ans);
		
	}
	
	static void bfs () {
	
		while (!q.isEmpty()) {
		
			int y=q.peek()[0];
			int x=q.poll()[1];
				
			for (int d=0; d<4; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
					
				if (ny< 0 || nx<0 || ny>=R || nx>=C || dist[ny][nx]!=R*C) continue;
				if (map[ny][nx]==STONE || map[ny][nx]=='D') continue;
					
				dist[ny][nx]=dist[y][x]+1;
				q.add(new int[] {ny, nx});
			}
		}
	}
	
	static void move () {
		q.clear();
		int[][] sDist=new int[R][C];
		for (int i=0; i<R; i++)
			Arrays.fill(sDist[i], -1);
		
		q.add(new int[] {sy, sx});
		sDist[sy][sx]=0;
		
		while (!q.isEmpty()) {
			
			int y=q.peek()[0];
			int x=q.poll()[1];
				
				
			for (int d=0; d<4; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
					
				if (ny< 0 || nx<0 || ny>=R || nx>=C ||
						map[ny][nx]==STONE || sDist[ny][nx]!=-1 || map[ny][nx]==WATER) continue;
				
				if (map[ny][nx]=='D') {
					ans=sDist[y][x]+1;
					return ;
				}
				
				if (map[ny][nx]==EMPTY && sDist[y][x]+1<dist[ny][nx]) {
					sDist[ny][nx]=sDist[y][x]+1;
					q.add(new int[] {ny,nx});
				}		
			}
		}
	}
}
