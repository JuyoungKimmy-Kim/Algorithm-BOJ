package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1010 {
	
	static int[][] dp=new int[30][30]; // 반복적인 재귀호출을 줄여주는 효과

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		StringBuilder sb=new StringBuilder ();
		
		int T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			
			st=new StringTokenizer (br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt (st.nextToken());
			
			dp=new int [M+1][N+1];
			
			for (int i=0; i<=M; i++) {
				for (int j=0; j<=Math.min(i, N); j++) {
					if (j==0 || j==i) dp[i][j]=1;
					else dp[i][j] =(dp[i-1][j-1] + dp[i-1][j]);
				}
			}	
		}
		System.out.println(sb.toString());
	}
}
