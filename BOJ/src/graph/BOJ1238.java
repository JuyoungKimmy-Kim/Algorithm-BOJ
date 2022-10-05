package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1238 {
	
	static final int INF=987654321;
	
	static int N,M,X, ans, max;
	static List<Edge> adj[];
	static boolean[] visited;
	static int[] dist;
	static PriorityQueue<Edge> pq;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		st=new StringTokenizer (br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		X=Integer.parseInt(st.nextToken());
		
		
		adj=new ArrayList[N+1];
		for (int i=0; i<=N; i++)
			adj[i]=new ArrayList<>();
		
		
		for (int i=0; i<M; i++) {
			st=new StringTokenizer (br.readLine());
			int from =Integer.parseInt(st.nextToken());
			int to=Integer.parseInt(st.nextToken());
			int dist=Integer.parseInt(st.nextToken());
			
			adj[from].add(new Edge (from, to, dist));	
		}
		
		for (int i=0; i<N; i++) {
			int ret=dijkstra (i);
			if (max<ret) {
				max=ret;
				ans=i;
			}
		}
	}
	
	static int dijkstra (int n) {
		
		dist=new int[N];
		Arrays.fill(dist, INF);
		pq=new PriorityQueue<>( (Edge e1, Edge e2 ) -> e1.d-e2.d);
		
		for (int i=0; i<adj[n].size(); i++) 
			pq.add(adj[n].get(i));

		dist[n]=0;
		
		while (!pq.isEmpty()) {
			Edge edge=pq.poll();
			int u=edge.u;
			int v=edge.v;
			int d=edge.d;
			
			
			for (int i=0; i<adj[v].size(); i++) {
				if (dist[v]==INF) {
					dist[v]=dist[u]+d;
					
					for (int k=0; k<adj[v].size(); k++)
						pq.offer(adj[v].get(k));
					
				} else if (dist[v]>dist[u]+d) {
					dist[v]=dist[u]+d;		
				}
			}
		}
		
		return -1;
	}
	
	static class Edge {
		int u, v;
		int d;
		
		Edge (int u, int v, int d) {
			this.u=u;
			this.v=v;
			this.d=d;
		}
	}

}
