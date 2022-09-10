package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/*
 * #1. 자신이 어떤 지점가지 가는 데 걸리는 최단 거리를 구하는 dijkstra
 * #2. 아줌마가 i번째 지점에서 출발하여 i+1번째 지점으로 도달하는 데 걸리는 최단거리를 구하는 dijkstra 9번
 * 
 * #3. 1번 정점부터 자신이 도달한 시간과 야쿠르트 아줌마가 도달한 시간을 비교해서
 * 	   자신이 도달한 시간이 야쿠르트 아줌마가 도달한 시간보다 적거나 같다면 정답
 * 
 */
public class BOJ20160 {

	static final int MAX=10001;
	static final int INF=Integer.MAX_VALUE;
	
	static int V,E;
	static List<int[]> W[];
	static int[] pos=new int[11];
	static int start;
	static int[][] cost=new int[11][MAX];
	static int[] Ycost=new int[11];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		V=Integer.parseInt(st.nextToken());
		E=Integer.parseInt(st.nextToken());
		
		W=new ArrayList[MAX];
		for (int i=0; i<MAX; i++)
			W[i]=new ArrayList<>();
		
		for (int i=0; i<E; i++) {
			st=new StringTokenizer (br.readLine());
			int u=Integer.parseInt(st.nextToken());
			int v=Integer.parseInt(st.nextToken());
			int w=Integer.parseInt(st.nextToken());
			
			W[u].add(new int[] {v,w});
			W[v].add(new int[] {w,v});
		}
		
		st=new StringTokenizer (br.readLine());
		for (int i=0; i<10; i++) {
			pos[i]=Integer.parseInt(st.nextToken());
			Ycost[i]=INF;
		}
		
		for (int i=0; i<=V; i++)
			Arrays.fill(cost[i], INF);
		
		start=Integer.parseInt(br.readLine());
		
	}
	
	
	private static void dijkstra(int I, int X ) {
		PriorityQueue<int[]> pq=new PriorityQueue<>();
		cost[I][X]=0;
		pq.add(new int[] {0,X});
		
		while (!pq.isEmpty()) {
			int curCost=pq.peek()[0];
			int cur=pq.poll()[1];
			
			if (cost[I][cur]<curCost) continue;
			
			for (int i=0; i<W[cur].size(); i++) {
				int nextCost=W[cur].get(i)[0];
				int next=W[cur].get(i)[1];
				
				if (cost[I][next]>curCost+nextCost) {
					cost[I][next]=curCost+nextCost;
					pq.add(new int[] {cost[I][next], next});
				}
			}
		}
	}

}
