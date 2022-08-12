package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2961_2 {

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
		
		//dfs (-1,1,0,0);
		dfs2(-1, 0);
		System.out.println(min);
		
	}
	
	private static void dfs (int idx, int S, int B, int mask) {
		
		if (idx==N-1) {
			if (B==0) return ;
			if (Math.abs(S-B)<min) 
				min=Math.abs(S-B);
			return ;
		}
		
		dfs (idx+1, S*taste[idx+1][0], B+taste[idx+1][1], mask|=1<<idx+1);
		dfs (idx+1, S, B, mask);
	}
	
	private static void dfs2 (int idx, int mask) {
		
		if (idx==N-1) {			
			int total_S=1; int total_B=0;
			
			for (int i=0; i<N; i++) {
				if ((mask & 1<<i)==0) continue; 
				
					total_S*=taste[i][0];
					total_B+=taste[i][1];		
			}
			
			if (total_B==0) return;
			if (Math.abs(total_S-total_B) < min)
				min=Math.abs(total_S-total_B);
			return ;
		}
		
		dfs2 (idx+1, mask | (1<<idx+1));
		dfs2 (idx+1, mask);
	}

}
