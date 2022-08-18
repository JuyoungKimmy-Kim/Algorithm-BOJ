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

public class BOJ1260 {

	static int N,M,V;
	static List<Integer> adj[];
	static boolean[] visited;
	
	static StringBuilder sb1=new StringBuilder ();
	static StringBuilder sb2=new StringBuilder ();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());

		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		V=Integer.parseInt(st.nextToken());
		
		adj=new ArrayList [N+1];
		for (int i=0; i<=N; i++)
			adj[i]=new ArrayList<>();
		
		visited=new boolean[N+1];
		
		for (int i=0; i<M; i++) {
			st=new StringTokenizer (br.readLine());
			int from=Integer.parseInt(st.nextToken());
			int to=Integer.parseInt(st.nextToken());
			
			adj[from].add(to);
			adj[to].add(from);
		}
		
		//Collections.sort(adj, (o1,o2)-> );
		
		
		dfs (V);
		System.out.println(sb1.toString());
		bfs (V);
		System.out.println(sb2.toString());
		
		
		
	}
	
	private static void dfs (int idx) {
		
		visited[idx]=true;
		sb1.append(idx).append(" ");
		
		for (int i=0; i<adj[idx].size(); i++) {
			int next=adj[idx].get(i);
			
			if (!visited[next]) {
				dfs (next);
			}
		}
	}
	
	private static void bfs (int idx) {
		Queue <Integer> q=new ArrayDeque<>();
		visited=new boolean[N+1];
		
		q.add(idx);
		visited[idx]=true;
		
		while (!q.isEmpty()) {
			
			int now=q.poll();
			sb2.append(now).append(" ");
			
			for (int i=0 ;i<adj[now].size(); i++) {
				int next=adj[now].get(i);
				
				if (!visited[next]) {
					visited[next]=true;
					q.add(next);
				}
			}
		}
	}

}
