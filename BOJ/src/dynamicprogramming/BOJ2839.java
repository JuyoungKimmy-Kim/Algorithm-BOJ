package dynamicprogramming;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ2839 {

	static final int INF=987654321;
	
	static int N;
	static int [] dp;
	public static void main(String[] args) {
		Scanner sc=new Scanner (System.in);
		N=sc.nextInt();
		dp=new int[N+1];
		
		Arrays.fill(dp, INF);
		
		if (N<=5) {
			if (N==3 || N==5) 
				System.out.println(1);
			else System.out.println(-1);
			return ;
		}
		
		dp[3]=dp[5]=1;
		
		for (int i=6; i<=N; i++) {
			if (dp[i-3]!=INF) {
				dp[i]=Math.min(dp[i], dp[i-3]+1);
			}
			if (dp[i-5]!=INF) {
				dp[i]=Math.min(dp[i], dp[i-5]+1);
			}
		}
		
		if (dp[N]==INF) dp[N]=-1;
		System.out.println(dp[N]);
	}

}
