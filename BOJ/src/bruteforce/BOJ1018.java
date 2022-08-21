package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1018 {

	static int N, M, ans=Integer.MAX_VALUE;
	static char[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		map=new char[N][];
		for (int i=0; i<N; i++)
			map[i]=br.readLine().toCharArray();
		
		for (int i=0; i<=N-8; i++) {
			for (int j=0; j<=M-8; j++) {
				int ret=paint (i, j);
				if (ret<ans) ans=ret;
			}
		}
		System.out.println(ans);
	}
	
	static int paint (int y, int x) {
		char colour=map[y][x];
		int cnt=0; 
		
		for (int i=y; i<y+8; i++) {
			for (int j=x; j<x+8; j++) {
				
				if (map[i][j]!=colour)
					cnt++;
				
				if (colour=='W') colour='B';
				else colour='W';
			}
			if (colour=='W') colour='B';
			else colour='W';
		}
		
		cnt=Math.min(cnt, 64-cnt);
		return cnt;
	}

}
