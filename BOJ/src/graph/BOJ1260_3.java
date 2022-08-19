package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1260_3 {

	static int N,M,V;
	static ArrayList<ArrayList<Integer>> adj=new ArrayList<ArrayList<Integer>> ();
	static boolean[] visited;
	
	static StringBuilder sb1=new StringBuilder ();
	static StringBuilder sb2=new StringBuilder ();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());

		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		V=Integer.parseInt(st.nextToken());

		for (int i=0; i<=N; i++)
			adj.add(new ArrayList<Integer>());
		
		visited=new boolean[N+1];
		
		for (int i=0; i<M; i++) {
			st=new StringTokenizer (br.readLine());
			int from=Integer.parseInt(st.nextToken());
			int to=Integer.parseInt(st.nextToken());
			
			adj.get(from).add(to);
		}
		
		for (int i=1; i<=N; i++)
			Collections.sort(adj.get(i));
		
		dfs (V);
		sb1.setCharAt (sb1.length()-1, '\n');
		bfs (V);
		sb1.setLength(sb1.length()-1);
		System.out.println(sb1);
	}
	
	private static void dfs (int idx) {
		
		visited[idx]=true;
		sb1.append(idx).append(" ");
		
		for (int i=0; i<adj.get(idx).size(); i++) {
			int next=adj.get(idx).get(i);
			
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
			sb1.append(now).append(" ");
			
			for (int i=0 ;i<adj.get(now).size(); i++) {
				int next=adj.get(now).get(i);
				
				if (!visited[next]) {
					visited[next]=true;
					q.add(next);
				}
			}
		}
	}

}
