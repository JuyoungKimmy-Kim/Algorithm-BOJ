package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2563 {

	static int N, ans;
	static int[][] map=new int[101][101];
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		N=Integer.parseInt(br.readLine());
		
		for (int k=0; k<N; k++) {
			StringTokenizer st=new StringTokenizer (br.readLine());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			
			for (int i=y; i<y+10; i++) {
				for (int j=x; j<x+10; j++) {
					if (map[i][j]==0) {
						ans++;
						map[i][j]=1;
					}
				}
			}
		}
		
		System.out.println(ans);
	}

}
