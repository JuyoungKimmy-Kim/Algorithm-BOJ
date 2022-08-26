package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 외판원 순회
// 다이나믹 프로그래밍
// 모든 도시를 맨 앞에 두면서 따질 필요가 없다
// 순환 구조이므로 맨 앞의 도시를 하나만 따져도 가능
/*
 *  #1. 모든 도시를 맨 앞에 두면서 따질 필요가 없음-> 순환 구조이므로
 *  #2. DP 점진적으로 계산한 결과를 저장
 *  	dp[i][j] : 현재 i번 도시에 있고, 거쳐온 도시들이 j
 *  	값은 남은 도시를 방문하는 데 필요한 최소비용
 *  	현재 3 도시에 있고, 거쳐온 도시는 1,3,6,7 -> 남은 2,4,5 도시를 방문하는 데 최소 비용이 100
 *  #3. 거쳐온 도시 1,3,6,7 표현 -> BitMasking
 *  	dp[3][1100101] // 1,3,6,7 번 방문
 *  
 *  
 *  	dp[3][1100101] => 1,3,6,7 방문 후, 남은 2,4,5 도시를 방문하는 최소비용
 *  	=> 2,4,5 를 가는 경우 구하고 갱신
 *  	2번 : dp[2][1100111] => 2번 먼저 가는 경우
 *  	4번 : dp[4][1101101] => 4번 먼저 가는 경우
 *  	5번 : dp[5][1110101] => 5번 먼저 가는 경우
 *  
 *  	dp[3][1100101] ==> [dp[2][1100111] + 3->2 비용] [dp[4][1101101]+ 3->4 비용],
 *  	[dp[5][1110101] + 3->5 비용]
 *  
 *  위 과정을 반복하게 되면 dp[5][1111111], dp[4][1111111] ....
 */
public class BOJ2098_2 {
	static int N, allMask;
	static int[][] W;
	static int[][] dp;
	static final int INF = 987654321;
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.valueOf(br.readLine());
		W = new int[N][N];
		allMask= (1<<N)-1; 
		dp = new int[N][allMask];	
		
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				W[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(tsp(0, 1));
	}

	
	static int tsp (int x, int mask) {
		if (mask == allMask) {
			if (W[x][0]==0) return INF;
			else return W[x][0];
		}
		
		if (dp[x][mask]!=0) return dp[x][mask];
		
		dp[x][mask]=INF;
		for (int i=0; i<N; i++) {
			if (W[x][i]==0 || (mask & 1<<i)!=0) continue;
			
			dp[x][mask]= Math.min(dp[x][mask], tsp (i, mask | 1<<i) + W[x][i]);
		}
		
		return dp[x][mask];
	}
	
}