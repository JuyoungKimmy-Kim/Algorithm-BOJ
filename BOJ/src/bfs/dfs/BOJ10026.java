package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BOJ10026 {
	
	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};
	
	static int N;
	static char[][] map;
	
	static boolean [][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		N=Integer.parseInt(br.readLine());
		
		map=new char[N][];
		visited=new boolean[N][N];
		
		for (int i=0; i<N; i++)
			map[i]=br.readLine().toCharArray();
		
		int cnt=0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (!visited[i][j]) {
					cnt++;
					bfs (i,j, map[i][j], false);
				}
			}
		}
		System.out.println(cnt);
		
		for (int i=0; i<N; i++)
			Arrays.fill(visited[i], false);
		
		cnt=0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (!visited[i][j]) {
					cnt++;
					bfs (i,j, map[i][j], true);
				}
			}
		}
		System.out.println(cnt);
	}
	
	static void bfs (int i, int j, char ch, boolean flag) {
		
		Queue<int[]> q=new ArrayDeque<> ();
		q.add(new int[] {i,j});
		visited[i][j]=true;
		
		while (!q.isEmpty()) {
			int y=q.peek()[0];
			int x=q.poll()[1];
			
			for (int d=0; d<4; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
				
				if (ny<0 || nx<0 || ny>=N || nx>=N || visited[ny][nx]) continue;
				
				if (flag==true) {
					if (ch=='R' || ch=='G' ) {
						if (map[ny][nx]=='R' || map[ny][nx]=='G') {
							visited[ny][nx]=true;
							q.add(new int[] {ny, nx});
						}
					}
					else {
						if (map[ny][nx]==ch) {
							visited[ny][nx]=true;
							q.add(new int[] {ny, nx});
						}
					}
				}
				
				else {
					if (map[ny][nx]==ch) {
						visited[ny][nx]=true;
						q.add(new int[] {ny, nx});
					}
				}
			}
			
		}
	}

}
