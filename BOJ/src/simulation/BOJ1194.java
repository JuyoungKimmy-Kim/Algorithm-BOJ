package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1194 {
	
	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};
	static final int INIT=1<<6;	 //아무런 키도 가지지 않은 상태
	
	static int N,M, ans=Integer.MAX_VALUE;
	static char[][] map;
	//static boolean[][] visited, revisited;
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		map=new char[N][M];
		//visited=new boolean[N][M];
		//revisited=new boolean[N][M];
		int sy=0, sx=0;
		
		for (int i=0; i<N; i++) {
			char [] line=br.readLine().toCharArray();
			for (int j=0; j<M; j++) {
				map[i][j]=line[j];
				
				if (map[i][j]=='0') {
					sy=i; sx=j;
					map[i][j]='.';
				}
			}
		}
		
		int ret=bfs (sy, sx);
		System.out.println(ret);
	}
	
	static int bfs (int sy, int sx) {
		PriorityQueue<Pos> q=new PriorityQueue<> ( (Pos p1, Pos p2) -> p1.cnt-p2.cnt);
		
		boolean[][] visited=new boolean[N][M];
		visited[sy][sx]=true;
		q.offer(new Pos (sy, sx, INIT, 0, visited));
		
		while (!q.isEmpty()) {
			Pos p=q.poll();
			int y=p.y;
			int x=p.x;
			int key=p.key;
			int cnt=p.cnt;
			boolean[][] v1=p.visited;
			boolean[][] v2=new boolean[N][M];
			for (int i=0; i<N; i++)
				for (int j=0; j<M; j++)
					v2[i][j]=v1[i][j];
			
			
			for (int d=0; d<4; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
				
				// #1. 범위를 벗어나거나, 방문하려는 곳이 벽일 때 -> 실패
				if (ny<0 || nx<0 || ny>=N || nx>=M || map[ny][nx]=='#') continue;
				
				// #2. 아무런 키도 가지지 않고, 방문했던 곳 재방문하려고 하면 -> 실패
				if (key==INIT && v2[ny][nx]) continue;
				
				// #3. 출구에 도착한 경우
				if (map[ny][nx]=='1') 
					return cnt+1;
				
				// #4. 빈칸인 경우 -> 키를 가지고 움직이는 경우
				else if (map[ny][nx]=='.' && key!=INIT && !v2[ny][nx]) {
					v2[ny][nx]=true;
					
					q.offer(new Pos (ny, nx, key, cnt+1, v2));
				}
				
				// #4-2. 빈칸인데 -> 키가 없는 경우 -> 방문했던 길 못 감
				else if (map[ny][nx]=='.' && key==INIT && !v2[ny][nx]) {
					v2[ny][nx]=true;
					q.offer(new Pos (ny, nx, key, cnt+1, v2));
				}
				
				// #5. 열쇠인 경우 -> 가지고 이동 -> 방문했던 길도 갈 수 있음
				else if (map[ny][nx]>='a' && map[ny][nx]<='f' && !v2[ny][nx]) {
					

					
					int keyNo=map[ny][nx]-'a';
					for (int i=0; i<N; i++)
						Arrays.fill(v2[i], false);
					v2[ny][nx]=true;
					q.offer(new Pos (ny, nx, key | 1<<keyNo, cnt+1, v2));
				}
				
				
				// #6. 문인 경우 -> 해당 열쇠를 가지고 있어야 함, 재방문인 경우는 안 됨
				else if (map[ny][nx]>='A' && map[ny][nx]<='F') {
					
				
					int doorNo=map[ny][nx]-'A';
					
					// 해당 
					if ( (key & 1<<doorNo)!=0) {
						v2[ny][nx]=true;
						q.offer(new Pos (ny, nx, key, cnt+1, v2));
					}
				}
			}
		}
		return -1;
	}
	
	static class Pos {
		int y, x;
		int key, cnt;
		boolean[][] visited=new boolean[N][M];
		
		Pos (int y, int x, int key, int cnt, boolean [][] visited) {
			this.y=y;
			this.x=x;	
			this.key=key;
			this.cnt=cnt;
			
			for (int i=0; i<N; i++) 
				for (int j=0; j<M; j++)
					this.visited[i][j]=visited[i][j];

		}
	}

}
