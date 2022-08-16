package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2294 {

	static final int MAX=10001;
	static int N,K;
	static int[] coin;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		coin=new int[N+1];
		dp=new int[K+1];
		Arrays.fill(dp, MAX);
		

		dp[0]=0;
		for (int i=1; i<=N; i++) {
			coin[i]=Integer.parseInt(br.readLine());
			for (int j=coin[i]; j<=K; j++) {
				dp[j]=Math.min(dp[j], dp[j-coin[i]]+1);
			}
		}
		
		if (dp[K]==MAX) dp[K]=-1;
		System.out.println(dp[K]);
	}

}
