package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2293 {

	static int N,K;
	static int[] coin;
	static int[] dp;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		coin=new int[N];
		dp=new int[K+1];
		
		/*
		 * dp[a]=b 일 때, a원을 만드는 방법은 b개이다
		 * dp[0]=1 : 0원을 만드는 방법은 1개
		 * 
		 * 현재 동전이 X원이고, Y원을 만들고 싶을 때
		 * X원은 Y원을 넘지 않고, Y원에서 X원을 빼고 구했던 방법의 수를 더해주면 됨
		 */

		
		dp[0]=1;
		
		for (int i=0; i<N; i++)
			coin[i]=Integer.parseInt(br.readLine());
	
		/*
		 * 동전이 1,2,5 원이 있을 때
		 * 처음 1원만 사용해서 만들 수 있는 동전의 범위는 1~K이고
		 * dp[1]~dp[K]에서 1원만큼 뺀 dp[1-1]~dp[K-1] 값을 더해준다
		 * 1원을 사용했다는 뜻은 1원을 빼고 만든 동전에 1원을 더한 것과 같다
		 */
		
		for (int i=0; i<N; i++) 
			for (int j=coin[i]; j<=K; j++) 
				dp[j]+=dp[j-coin[i]];
		
		System.out.println(dp[K]);
	}

}
