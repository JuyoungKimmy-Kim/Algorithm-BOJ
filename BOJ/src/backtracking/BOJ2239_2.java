package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ2239_2 {
	static int[][] map=new int[9][9];
	static ArrayList<Node> zero=new ArrayList<>();;
	static boolean complete=false;
	static int size;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		for (int i=0; i<9; i++) {
			char[] line=br.readLine().toCharArray();
			for (int j=0; j<9; j++) {
				map[i][j]=line[j]-'0';
				
				if (map[i][j]==0)
					zero.add(new Node (i,j));
			}
		}
		
		size=zero.size();
		dfs (0);
	}
	
	
	static void dfs (int idx) {
		if (complete) return ;
		if (idx==size) {
			complete=true;
			
			// 완성된 스도쿠 출력
			StringBuilder sb=new StringBuilder ();
			for (int i=0; i<9; i++) {
				for (int j=0; j<9; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			System.out.println(sb.toString());
			
			return ;
		}
		
		int y=zero.get(idx).y;
		int x=zero.get(idx).x;
		
		int visit=1<<10;
		
		// #1. 가로
		for (int i=0; i<9; i++) {
			if (map[y][i]!=0) 
				visit |=1 << map[y][i];
		}
		
		// #2. 세로
		for (int i=0; i<9; i++) {
			if (map[i][x]!=0) 
				visit |=1 << map[i][x];
		}
		
		// #3. 3x3 
		int ny=(y/3)*3;
		int nx=(x/3)*3;
		
		for (int i=ny; i<ny+3; i++) {
			for (int j=nx; j<nx+3; j++) {
				if (map[i][j]!=0) 
					visit |=1 << map[i][j];
			}
		}
		
		// 현재 0인 (idx) y,x 좌표의 위에서 사용되지 않은 숫자
		
		for (int i=1; i<=9; i++) {
			if ((visit & 1<<i)!=0) continue;
			map[y][x]=i;
			dfs (idx+1);
			map[y][x]=0;
		}
	}

	
	// 0인 (미완성)
	static class Node {
		int y, x;
		Node (int y, int x) {
			this.y=y;
			this.x=x;
		}
	}
}
