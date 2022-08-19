package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1987 {

	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};
	
	static int R,C, ans;
	static char[][] map;
	static int [][] dp;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		map=new char[R][];

		for (int i=0; i<R; i++) 
			map[i]=br.readLine().toCharArray();

		int mask=1<<(map[0][0]-'A');
		dfs (0,0,1, mask);
		
		System.out.println(ans);
	}
	
	//방문할 때마다 max 확인
	private static void dfs (int y, int x, int cnt, int mask ) {
		if (cnt>ans) 
			ans=cnt;
	
		for (int d=0; d<4; d++) {
			int ny=y+dy[d];
			int nx=x+dx[d];
			
			if (ny<0 || nx<0 || ny>=R || nx>=C) continue;
			
			int next=1<<(map[ny][nx]-'A');
			if ((mask & next)==0) 
				dfs (ny, nx, cnt+1, mask|next);
		}
	}
}
