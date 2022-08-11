package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1010 {
	
	static int[][] memoi=new int[30][30]; // 반복적인 재귀호출을 줄여주는 효과

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		StringBuilder sb=new StringBuilder ();
		
		int T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			
			st=new StringTokenizer (br.readLine());
			int R=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt (st.nextToken());
			
			sb.append(comb(N,R)).append('\n');
		}
		System.out.println(sb.toString());

	}
	
	private static int comb (int n, int r) {
	
		if (memoi[n][r]>0) return memoi[n][r];
		
		if (n==r || r==0) 
			return memoi[n][r]=1;
		
		return memoi[n][r]=comb(n-1, r-1) + comb(n-1, r);
		
	}

}
