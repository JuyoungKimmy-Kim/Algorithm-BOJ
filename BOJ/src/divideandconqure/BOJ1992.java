package divideandconqure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1992 {

	static int N;
	static char[][] map;
	
	static StringBuilder sb=new StringBuilder ();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		N=Integer.parseInt( br.readLine());
		map=new char[N][];
		
		for (int i=0; i<N; i++) {
			String line=br.readLine();
			map[i]=line.toCharArray();
		}

		quadTree (0,0,N);
		System.out.println(sb.toString());
	}
	
	private static void quadTree (int y, int x, int N) {
		
		char standard=map[y][x];
		
		boolean flag=true;
		
		for (int i=y; i<y+N; i++) {
			for (int j=x; j<x+N; j++) {
				if (standard!=map[i][j]) {
					flag=false; 
					break;
				}
			}
			if (!flag) break;
		}
		
		if (flag) 
			sb.append(standard);
		
		else {
			sb.append("(");
			N/=2;
			quadTree (y,x,N);
			quadTree (y, x+N, N);
			quadTree (y+N, x, N);
			quadTree (y+N, x+N, N);
			sb.append(")");
		}	
	}
}
