package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10971 {

	static int N, ans=Integer.MAX_VALUE;
	static int[][] W;

	public static void main(String[] args) throws IOException {
	
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		N=Integer.parseInt(br.readLine());
		W=new int[N][N];
		
		for (int i=0; i<N; i++) {
			StringTokenizer st=new StringTokenizer (br.readLine());
			for (int j=0; j<N; j++) {
				W[i][j]=Integer.parseInt(st.nextToken());
			}
		}

		for (int i=0; i<N; i++) 
			perm (1,i,0,1<<i, i);
	
		System.out.println(ans);
	}
	
	
	static void perm (int tgtIdx, int prevIdx, int cost, int mask, int start) {
	
		if (tgtIdx==N) {
			
			if (W[prevIdx][start]==0) return;
			
			cost+=W[prevIdx][start];
			ans=Math.min(ans, cost);
			return ;
		}
		
		for (int i=0; i<N; i++) {
			if ((mask & 1<<i)!=0) continue;			
			if (W[prevIdx][i]==0) continue;
			
			if (cost+W[prevIdx][i]<ans)
				perm (tgtIdx+1, i, cost+W[prevIdx][i], mask | 1<<i, start);
		}
	}
}
