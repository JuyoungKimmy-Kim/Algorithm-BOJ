package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// idea : LIS 를 구하고, 전체에서 LIS를 뺀 수들만 옮기면 됨

public class BOJ7570 {

	static int N, ans;
	static int [] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		N=Integer.parseInt(br.readLine());
		dp=new int[N+1];
		
		st=new StringTokenizer (br.readLine());
		for (int i=1; i<=N; i++) {
			int x=Integer.parseInt(st.nextToken());
			dp[x]=dp[x-1]+1;
			ans=Math.max(ans, dp[x]);
		}
		System.out.println(N-ans);
	}

}

/*
 * 5,2,4,1,3 인 경우
 * dp[5]=dp[4]+1=1
 * dp[2]=dp[1]+1=1
 * dp[4]=dp[3]+1=1
 * dp[1]=dp[0]+1=1
 * dp[3]=dp[2]+1=2
 * 
 * ==> 증가하는 수가 2,3 --> 2개 있음 
 * 		움직여야 하는 것 : N-2개
 */
