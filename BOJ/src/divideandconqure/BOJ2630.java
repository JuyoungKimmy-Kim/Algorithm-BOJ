package divideandconqure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2630 {
	
	static int N;
	static int[][] map;
	static int white, blue;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		N=Integer.parseInt(br.readLine());

		map=new int[N][N];
		
		for (int i=0; i<N; i++) {
			StringTokenizer st=new StringTokenizer (br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		divide (0,0,N);
		System.out.printf("%d\n%d", white, blue);
		
	}
	
	private static void divide (int y, int x, int L) {
		
		if (!isVaild(y,x,L)) {
			L/=2;
			
			divide (y,x,L);
			divide (y, x+L, L);
			divide (y+L, x, L);
			divide (y+L, x+L, L);
		}
	}
	
	private static boolean isVaild (int y, int x, int L) {
		int standard=map[y][x];
		
		for (int i=y; i<y+L; i++) {
			for (int j=x; j<x+L; j++) {
				if (map[i][j]!=standard)
					return false;
			}
		}
		
		if (standard==1) blue++;
		else white++;
		
		return true;
	}

}
