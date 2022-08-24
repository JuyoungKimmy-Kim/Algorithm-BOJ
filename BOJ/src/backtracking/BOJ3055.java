package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ3055 {

	static final char EMPTY='.';
	static final char WATER='*';
	static final char STONE='X';
	
	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};
	
	static int R,C,ans=Integer.MAX_VALUE;
	static char[][] map;
	static boolean[][] visited;
	static int sy,sx,ey,ex;
	static boolean done=false;
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());

		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		
		map=new char[R][C];
		visited=new boolean[R][C];
		
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
			}
		}
		visited[sy][sx]=true;
		bfs (sy, sx,0, map);
		if (ans==Integer.MAX_VALUE) 
			System.out.println("KAKTUS");
		else System.out.println(ans);

		
	}
	
	static void bfs (int y, int x, int cnt,char[][] cmap) {
		
		if (done) return ;
		
		for (int d=0; d<4; d++) {
			int ny=y+dy[d];
			int nx=x+dx[d];
			
			if (ny<0 || nx<0 || ny>=R || nx>=C || visited[ny][nx]) continue;
			if (cmap[ny][nx]=='D') {
				ans=cnt+1;
				done=true;
				return;
			}
			
			if (cmap[ny][nx]==EMPTY) {
				
				char[][]tmp=new char[R][C];
				copyMap (cmap, tmp);
				visited[ny][nx]=true;
				tmp[y][x]='.';
				tmp[ny][nx]='S';
				
				if (moveWater(tmp, ny, nx)) {
					bfs (ny, nx, cnt+1, tmp);
				}
				
				tmp[ny][nx]='.';
				tmp[y][x]='S';
				visited[ny][nx]=false;

			}
		}
	}
	
	static boolean moveWater (char[][] tmp, int cy, int cx) {
		
		List<int[]> flow=new ArrayList<>();
		
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				if (tmp[i][j]==WATER) {
					
					for (int d=0; d<4; d++) {
						int ny=i+dy[d];
						int nx=j+dx[d];
						
						if (ny<0 || nx<0 || ny>=R || nx>=C) continue;
						if (cy==ny && cx==nx) return false;
						if (tmp[ny][nx]!=STONE && tmp[ny][nx]!='D')
							flow.add(new int[] {ny,nx});
					}
				}
			}
		}
		
		for (int i=0; i<flow.size(); i++) {
			tmp[flow.get(i)[0]][flow.get(i)[1]]=WATER;
		}	
		return true;
	}
	
	// a의 맵을 b에 복사
	static void copyMap (char[][] a, char[][] b) {
		for (int i=0; i<R; i++)
			for (int j=0; j<C; j++)
				b[i][j]=a[i][j];
	}
	
	static void print (char[][] map) {
		System.out.println("====================================");
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) 
				System.out.print(map[i][j]);
			System.out.println();
		}
	}
}
