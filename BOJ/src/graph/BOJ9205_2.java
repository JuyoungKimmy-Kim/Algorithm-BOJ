package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9205_2 {

	static final int INF = 101 * 32767 * 2;
	static int T, N, V;
	static int[][] input, matrix;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			V = N + 2;
			input = new int[V][2];
			matrix = new int[V][V];

			for (int i = 0; i < V; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());

				input[i][0] = y;
				input[i][1] = x;
			}

			for (int i = 0; i < V; i++) {
				int vy = input[i][0];
				int vx = input[i][1];

				for (int j = 0; j < V; j++) {
					if (i == j)
						continue;

					int ty = input[j][0];
					int tx = input[j][1];

					int dis = Math.abs(ty - vy) + Math.abs(tx - vx);

					// 중요! => dis>50*20 => 연결 x
					matrix[i][j] = dis > 1000 ? INF : dis;
				}
			}

			// floydWarshall
//			for (int k=0; k<V; k++) {//경유지
//				for (int i=0; i<V; i++) { //출발지
//					
//					if (k==i) continue;
//					
//					for (int j=0; j<V; j++) {
//						if ( i==j || k==j ) continue;
//						
//						matrix[i][j]=Math.min(matrix[i][j], matrix[i][k]+matrix[k][j]);
//					}
//				}
//			}

			for (int k = 0; k < V; k++) {// 경유지

				for (int j = 1; j < V; j++) {
					if (k == j)
						continue;

					matrix[0][j] = Math.min(matrix[0][j], matrix[0][k] + matrix[k][j]);
				}
			}
			System.out.println(matrix[0][V - 1] != 0 && matrix[0][V - 1] < INF ? "happy" : "sad");
		}

	}
}
