package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1149 {

	static int N, ans=Integer.MAX_VALUE;
	static int[][] colour, memoi;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		N=Integer.parseInt(br.readLine());
		colour=new int[N][3];
		memoi=new int[N][3];
		
		for (int i=0; i<N; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<3; j++) {
				colour[i][j]=Integer.parseInt(st.nextToken());
			}
		}

		memoi[0][0]=colour[0][0];
		memoi[0][1]=colour[0][1];
		memoi[0][2]=colour[0][2];
		
		for (int i=1; i<N; i++) {
			memoi[i][0]=Math.min(memoi[i-1][1], memoi[i-1][2])+colour[i][0];
			memoi[i][1]=Math.min(memoi[i-1][0], memoi[i-1][2])+colour[i][1];
			memoi[i][2]=Math.min(memoi[i-1][0], memoi[i-1][1])+colour[i][2];
		}
		
		for (int i=0; i<3; i++)
			ans=Math.min(ans, memoi[N-1][i]);
		
		System.out.println(ans);
	}

}
