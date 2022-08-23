package samsungA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class RCS {
	int r,c,s;

	RCS (int r, int c, int s) {
		this.r=r;
		this.c=c;
		this.s=s;
	}
}

public class BOJ17406 {

	static int N,M,K;
	static int[][] map;
	static RCS[] rcs;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		rcs=new RCS[K];
		
		map=new int[N][M];
		for (int i=0; i<N; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i=0; i<K; i++) {
			st=new StringTokenizer (br.readLine());
			int r=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			int s=Integer.parseInt(st.nextToken());
		}
	}
	
	static void perm () {
		
	}

}
