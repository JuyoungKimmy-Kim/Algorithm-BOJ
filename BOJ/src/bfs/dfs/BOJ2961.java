package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2961 {

	static int N;
	static int[][] taste;
	static int min=Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		N=Integer.parseInt(br.readLine());
		taste=new int[N][2];
		
		for (int i=0; i<N; i++) {
			StringTokenizer st=new StringTokenizer (br.readLine());
			taste[i][0]=Integer.parseInt(st.nextToken());
			taste[i][1]=Integer.parseInt(st.nextToken());
		}
		
		dfs (0,1,0);
		System.out.println(min);
		
	}
	
	private static void dfs (int idx, int S, int B) {
		
		if (idx==N) {
			if (B==0) return ;		
			if (Math.abs(S-B)<min) 
				min=Math.abs(S-B);
			return ;
		}
		
		dfs (idx+1, S*taste[idx][0], B+taste[idx][1]);
		dfs (idx+1, S, B);
		
	}

}
