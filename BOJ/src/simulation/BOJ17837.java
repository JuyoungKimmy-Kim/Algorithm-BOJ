package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ17837 {
	
	static final int WHITE=0;
	static final int RED=2;
	static final int BLUE=3;
	
	static final int dy[] = {0,0,0,-1,1};
	static final int dx[] = {0,1,-1,0,0};
	
	static int N,K;
	static int [][] map;
	static List<Integer> chess[][];
	static Pos[] pos;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		map=new int[N+1][N+1];
		chess=new ArrayList[N+1][N+1];
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=N; j++) {
				chess[i][j]=new ArrayList<>();
			}
		}
		
		for (int i=1; i<=N; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=1; j<=N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		pos=new Pos[K+1];
		for (int i=1; i<=K; i++) {
			st=new StringTokenizer (br.readLine());
			int y=Integer.parseInt(st.nextToken());
			int x=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());
			
			pos[i]=new Pos (y,x,d);
			chess[y][x].add(i);
		}
		
		simulation();
		
		
	}
	
	static void simulation() {
		
	}
	
	static class Pos {
		int y,x;
		int d;
		
		Pos (int y, int x, int d) {
			this.y=y;
			this.x=x;
			this.d=d;
		}
	}

}
