package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2239 {

	static int[][] map;
	static boolean done;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		map=new int[9][9];
		for (int i=0; i<9; i++) {
			char[] line=br.readLine().toCharArray();
			for (int j=0; j<9; j++) {
				map[i][j]=line[j]-'0';
			}
		}
		dfs (0,0);
	}
	
	static void dfs (int y, int x) {
		
		if (y==9) {
			print();
			done=true;
			return ;
		}
		
		
		if (map[y][x]!=0) {
			if (x==8) dfs(y+1, 0);
			else dfs(y, x+1);
			return ;
		}
		
		boolean changed=false;
		for (int i=1; i<=9; i++) {
			if (rowCheck (y,x, i) && colCheck (y, x, i) && squareCheck (y,x, i)) {
				
				changed=true;
				
				map[y][x]=i;
				
				if (x==8) dfs (y+1, 0);
				else dfs (y, x+1);
				
				if (done) return ;
				
				map[y][x]=0;
			}
		}
		if (!changed) return ;
	}
	
	static boolean rowCheck (int y, int x, int n) {
		
		for (int i=0; i<9; i++) {
			if (map[y][i]==n) return false;
		}
		
		return true;
	}
	
	static boolean colCheck (int y, int x, int n) {
		
		for (int i=0; i<9; i++) {
			if (map[i][x]==n) return false;
		}
		
		return true;
	}
	
	static boolean squareCheck (int y, int x, int n) {
		int sy=y/3;
		int sx=x/3;
		
		for (int i=sy*3; i<sy*3+3; i++) {
			for (int j=sx*3; j<sx*3+3; j++) {
				if (map[i][j]==n) return false;
			}
		}
		return true;
	}
	
	static void print () {
		StringBuilder sb=new StringBuilder ();
		for (int i=0; i<9; i++) {
			for (int j=0; j<9; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}
