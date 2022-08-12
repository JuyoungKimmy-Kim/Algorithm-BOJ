package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//class Pos {
//	int y; int x;
//	
//	Pos (int y, int x) {
//		this.y=y;
//		this.x=x;
//	}
//}

public class BOJ15686 {

	static final int EMPTY=0, CHICKEN=2, HOME=1;
	
	static int N,M;
	static int[][] map;
	static int[] select;
	static int min=Integer.MAX_VALUE;
	
	static int c_cnt, h_cnt;
	
	static Pos[] chicken, home;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		map=new int[N][N];
		select=new int[M];
		
		chicken=new Pos[14];
		home=new Pos[2*N+1];
		

		// input 
		for (int i=0; i<N; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				
				if (map[i][j]==HOME) {
					home[h_cnt]=new Pos (i,j);
					h_cnt++;
				}
				if (map[i][j]==CHICKEN) {

					chicken[c_cnt]=new Pos(i,j);
					c_cnt++;
				}
			}
		}
		
		// M개 고르기
		comb(0,0);
		System.out.println(min);
	}
	
	private static void comb (int cnt, int start) {
		
		// 최대 M개를 고르는 것과 M개를 구해서 도시 치킨 거리를 확인해 보는 것은 같음
		if (cnt==M) {
			int result=distance();
			
			if (result<min) 
				min=result;
			return ;
		}
		
		for (int i=start; i<c_cnt; i++) {
			select[cnt]=i;
			comb (cnt+1, i+1);
		}
	}
	
	private static int distance () {
		
		int sum=0;
		
		for (int i=0; i<h_cnt; i++) {
			
			int minDist=Integer.MAX_VALUE;
						
			int sy=home[i].y;
			int sx=home[i].x;
			
			for (int j=0; j<M; j++) {
				int n=select[j];
				
				int ey=chicken[n].y;
				int ex=chicken[n].x;
				
				int dist=Math.abs(sy-ey)+Math.abs(sx-ex);
				
				if (minDist>dist) minDist=dist;
			}
			sum+=minDist;
		}
		return sum;
	}
}
