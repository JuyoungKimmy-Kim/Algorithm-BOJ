package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17070 {

	static int N, cnt;
	static int [][] map;
	
	// dp[d][y][x] => 현재 y,x로  d방향으로 올 때까지의 경우의 수의 합
	// 0: 대각선, 1: 가로, 2:세로
	// dp[0][3][2] : (3,2)좌표로 대각선에서 이동해올 때 모든 경우의 수의 합
	static int [][][] dp; 
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		
		N=Integer.parseInt(br.readLine());
		map=new int[N+1][N+1];
		dp=new int[3][N+1][N+1];
		
		for (int i=1; i<=N; i++) {
			StringTokenizer st=new StringTokenizer (br.readLine());
			for (int j=1; j<=N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}

		// 처음 상태 => 가로, 좌표 1,2
		dp[1][1][2]=1;
		
		for (int i=1; i<=N; i++) {
			for (int j=2; j<=N; j++) {
				
				//현재 (i,j) 이동할 때 
				// 대각선  (i+1, j+1)
				// 가로	(i, j+1)
				// 세로	(i+1, j)
				
				// 대각선으로 => dp[0][][]
				if (i+1<=N && j+1<=N && map[i+1][j]==0 && map[i][j+1]==0 && map[i+1][j+1]==0) {
					// 대각선 - 대각선
					dp[0][i+1][j+1]+=dp[0][i][j];
					// 가로 - 대각선
					dp[0][i+1][j+1]+=dp[1][i][j];
					// 세로 - 대각선
					dp[0][i+1][j+1]+=dp[2][i][j];

				}
				
				// 가로로	=> dp[1][][]
				if (j+1<=N && map[i][j+1]==0) {
					// 대각선 - 가로
					dp[1][i][j+1]+=dp[0][i][j];
					// 가로 - 가로
					dp[1][i][j+1]+=dp[1][i][j];
				}
				
				// 세로로	=> dp[2][][]
				if (i+1<=N && map[i+1][j]==0) {
					// 대각선 - 세로
					dp[2][i+1][j]+=dp[0][i][j];
					// 세로 - 세로
					dp[2][i+1][j]+=dp[2][i][j];
				}
				
			}
		}
		
		// (N,N)까지 대각선, 가로, 세로에서 오는 각각의 경우 더하기
		System.out.println(dp[0][N][N]+dp[1][N][N]+dp[2][N][N] );
	}

}
