package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1753 {

	static final int INF=Integer.MAX_VALUE;
	
	static int V,E, K;
	static List<List<Edge>> vertex=new ArrayList<>();
	static boolean[] visit;
	static int[] cost;			//핵심
	static PriorityQueue<Edge> pq=new PriorityQueue<> ((e1, e2)-> e1.w-e2.w);
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		V=Integer.parseInt(st.nextToken());
		E=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(br.readLine());
		
		visit = new boolean [V+1];
		cost=new int[V+1];
		
		for (int i=0; i<=V; i++) {
			vertex.add(new ArrayList<Edge>());
			cost[i]=INF;
		}
		
		for (int i=0; i<E; i++) {
			st=new StringTokenizer (br.readLine());
			
			int u=Integer.parseInt(st.nextToken());
			int v=Integer.parseInt(st.nextToken());
			int w=Integer.parseInt(st.nextToken());
			
			// 방향이 있음
			vertex.get(u).add(new Edge(v,w));
		}
		
		dijkstra();
		for (int i=1; i<=V; i++) {
			System.out.println(cost[i]==INF ? "INF" : cost[i]);
		}

	}
	
	static void dijkstra() {
		
		//시작 정점 K
		cost[K]=0;
		pq.offer(new Edge (K, 0));
		
		while (!pq.isEmpty()) {
			Edge e=pq.poll();
			
			//visit check
			if (visit[e.v]) continue;
			
			//e.v 로부터 갈 수 있는 다른 정점 고려
			//고려하는 목적은 cost[] 갱신
			visit[e.v]=true; //뺄 때 visit 체크
			for (Edge ne : vertex.get(e.v)) {
				if (!visit[ne.v] && cost[e.v]+ne.w < cost[ne.v]) {
					cost[ne.v]=cost[e.v]+ne.w;
					pq.offer(new Edge (ne.v, cost[ne.v]));
				}
			}
			
		}
	}
	
	static class Edge {
		int v,w;
		
		Edge (int v, int w) {
			this.v=v;
			this.w=w;
		}
	}
}
