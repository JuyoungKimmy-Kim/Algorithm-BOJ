package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1260_2 {

	static int N,M,V;
	static int[][]adj;
	static boolean[] visited;
	
	static StringBuilder sb1=new StringBuilder ();
	static StringBuilder sb2=new StringBuilder ();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());

		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		V=Integer.parseInt(st.nextToken());
		
		adj=new int[N+1][N+1];
		
		visited=new boolean[N+1];
		
		for (int i=0; i<M; i++) {
			st=new StringTokenizer (br.readLine());
			int from=Integer.parseInt(st.nextToken());
			int to=Integer.parseInt(st.nextToken());
			
			adj[from][to]=1;
			adj[to][from]=1;
		}
	
		dfs (V);
		System.out.println(sb1.toString());
		bfs (V);
		System.out.println(sb2.toString());
		
		
		
	}
	
	private static void dfs (int cur) {
		
		visited[cur]=true;
		sb1.append(cur).append(" ");
		
		for (int i=1; i<=N; i++) {
			if (adj[cur][i]==0 || visited[i]) continue;
			dfs (i);
		}
	}
	
	private static void bfs (int start) {
		Queue <Integer> q=new ArrayDeque<>();
		visited=new boolean[N+1];
		
		q.add(start);
		visited[start]=true;
		
		while (!q.isEmpty()) {
			
			int cur=q.poll();
			sb2.append(cur).append(" ");
			
			for (int i=1 ;i<=N; i++) {
				if (adj[cur][i]==0 || visited[i]) continue;
				visited[i]=true;
				q.add(i);
			}
		}
	}

}
