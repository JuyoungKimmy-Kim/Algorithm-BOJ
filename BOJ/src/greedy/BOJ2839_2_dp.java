package greedy;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ2839_2_dp {

	static int N;
	static int[] dp; 
	//어떤 값을 만드는 데 필요한 봉투의 수
	//dp[X]=Y : X무게를 만드는 게 Y만큼의 봉투를 사용
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner (System.in);
		N=sc.nextInt();

		
		if (N<=5 ) {
			if (N==3 || N==5) System.out.println(1);
			else System.out.println(-1);
			return ;
		}
		
		//6 이상
		dp=new int[N+1];
		Arrays.fill(dp, 5000);
		dp[3]=1; dp[5]=1;
		
		for (int i=6; i<=N; i++) {
			dp[i]=Math.min(dp[i], dp[i-3]+1);
			dp[i]=Math.min(dp[i], dp[i-5]+1);
		}
		
		if (dp[N]==5000) dp[N]=-1;
		System.out.println(dp[N]);
	}
}
