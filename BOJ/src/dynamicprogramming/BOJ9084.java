package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ9084 {

	static final int INF=987654321;
	static int T,N,M;
	static int[] coin, dp;
	public static void main(String[] args) throws IOException  {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			N=Integer.parseInt(br.readLine());
			coin=new int[N];
			dp=new int[N];
			Arrays.fill(dp, INF);
			
			st=new StringTokenizer (br.readLine());
			for (int i=0; i<N; i++) {
				coin[i]=Integer.parseInt(st.nextToken());
				if (M%coin[i]==0) dp[i]=M/coin[i];
			}
			
			for (int i=1; i<N; i++) {
				for (int j=0; j<i; j++) {
					if (M%coin[i-coin[j]]==0) {
						coin[i]+=1;
					}
				}
			}
			
			int sum=0;
			for (int i=0; i<N; i++)
				sum+=coin[i];
			
			System.out.println(sum);
			
			
			
		}
		
		
	}

}
