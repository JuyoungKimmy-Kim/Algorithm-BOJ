package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 외판원 순회
// 다이나믹 프로그래밍
public class BOJ2098_2 {
	static int N;
	static int[][] W;
	static int[][] memoi;
	static final int INF = 11000000;
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.valueOf(br.readLine());
		W = new int[N][N];

		memoi = new int[N][(1 << N) - 1];	
		
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				W[i][j]=Integer.parseInt(st.nextToken());
			}
		}

		for(int i = 0; i < N; i++) 
			Arrays.fill(memoi[i], -1);
		
		System.out.println(dfs(0, 1));
	}

	private static int dfs(int cur, int mask) {
		
		if(mask == (1 << N) - 1) {	
			if(W[cur][0] == 0) 
				return INF;
			
			return W[cur][0];
		}
		
		if(memoi[cur][mask] != -1) 
			return memoi[cur][mask];

		
		memoi[cur][mask]=INF;
		
		for(int i = 0; i < N; i++) {	
			if((mask & (1 << i)) == 0 && W[cur][i] != 0) {	
				memoi[cur][mask] = 
						Math.min(memoi[cur][mask], dfs(i, mask | (1 << i)) + W[cur][i]);	
			}
		}
		
		return memoi[cur][mask];
	}
}