package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3109 {
	
	static final int dy[]= {-1,0,1};

	static final char EMPTY='.';
	static final char WALL='x';
	static final char PIPE='p';
	
	static int R,C, ans;
	static char[][] map;
	static boolean flag;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		
		map=new char[R][];
		
		for (int i=0; i<R; i++) 
			map[i]=br.readLine().toCharArray();

		
		for (int i=0; i<R; i++) {
			map[i][0]='s';
			dfs (i,0);
		}
		
		System.out.println(ans);
	}
	
	private static boolean dfs (int y, int x) {

		if (x==C-1) {
			ans++;
			return true;
		}
		
		for (int d=0; d<3; d++) {
		
			int ny=y+dy[d];
			int nx=x+1;
			
			if (ny<0 || nx<0 || ny>=R || nx>=C ||
					 map[ny][nx]!=EMPTY) continue;
					
			//print();
			map[ny][nx]=PIPE;
			
			if (dfs (ny, nx)) 
				return true;
			
			
			//(ny, nx) 지점을 거쳐서 갔는데 실패했는 경우 다른 점에서 그 길을 거쳐도 실패임 -> 다시 되돌릴 필요 없음
			
			//map[ny][nx]=EMPTY;
		}
		return false;
	}
	
	private static void print () {
		System.out.println("==========================");
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
