package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2098 {

	static final int INF=11000000;
	
	static int N, ans;
	static int[][]W;
	static int[][] memoi;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		N=Integer.parseInt(br.readLine());
		W=new int[N][N];
		memoi=new int[N][(1<<N)-1];
		
		for (int i=0; i<N; i++)
			Arrays.fill(memoi[i], INF);
		
		for (int i=0; i<N; i++) {
			StringTokenizer st=new StringTokenizer (br.readLine());
			for (int j=0; j<N; j++) {
				W[i][j]=Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(dfs (0,1));
	}
	
	static int dfs (int cur, int mask) {
		
		if ((mask == (1<<N)-1)) {
			if (W[cur][0]==0) return INF;
			return W[cur][0];
		}
		
		if (memoi[cur][mask]!=INF) return memoi[cur][mask];
		
		memoi[cur][mask]=INF;
		
		for (int i=0; i<N; i++) {
			if ((mask & 1<<i)!=0) continue;
			if (W[cur][i]==0) continue;
			
			memoi[cur][mask]=Math.min(memoi[cur][mask], dfs(i, mask | 1<<i) + W[cur][i]);
		}
		
		return memoi[cur][mask];
	}

}
