package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ13023_2 {

	static int N,M;
	static boolean[][] friends; 	//matrix
	static boolean done;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		friends=new boolean[N][N];

		
		for (int i=0; i<M; i++) {
			st=new StringTokenizer (br.readLine());
			int from=Integer.parseInt(st.nextToken());
			int to=Integer.parseInt(st.nextToken());
			
			friends[from][to]=true;
			friends[to][from]=true;
		}
	
		
		//dfs 탐색
		//모든 정점에서 따져봄 -> 가능한 경우를 만나면 바로 종료
		for (int i=0; i<N; i++) {
			dfs (i,0,1<<i);
			if (done) break;
		}
		if (done) System.out.println(1);
		else System.out.println(0);
	}
	
	static void dfs (int num, int cnt, int visit)  {
		if (done) return;
		if (cnt==4) {
			done=true;
			return ;
		}
		
		for (int i=0; i<N; i++) {
			if (i==num || (visit & (1<<i))!=0) continue;
			if (friends[num][i])
				dfs (i, cnt+1, visit | (1<<i));
		}
	}
}
