package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


class Pos implements Comparable <Pos> {
	int cost;
	int y; int x; 
	
	Pos (int y, int x, int cost) {
		this.y=y;
		this.x=x;
		this.cost=cost;
	}

	@Override
	public int compareTo(Pos o) {
		return this.cost-o.cost;
	}
}

public class BOJ4485 {

	static final int INF=Integer.MAX_VALUE;
	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};
	
	
	static int N;
	static int[][] map, cost;
	static PriorityQueue<Pos> pq=new PriorityQueue <>(); 
	static StringBuilder sb=new StringBuilder ();
	
	public static void main(String[] args) throws IOException  {
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		int tc=1;
		while (true) {
			
			N=Integer.parseInt(br.readLine());
			if (N==0) break;
			map=new int[N][N];
			cost=new int[N][N];
			
			for (int i=0; i<N; i++) {
				Arrays.fill(cost[i], INF);
			}
			
			for (int i=0; i<N; i++) {
				st=new StringTokenizer (br.readLine());	
				for (int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			sb.append("Problem ").append(tc).append(": ");
			dijkstra ();
			tc++;
		}
		
		System.out.println(sb.toString());

	}
	
	static void dijkstra () {
		cost[0][0]=map[0][0];
		
		pq.offer(new Pos (0,0,cost[0][0]));
		
		while (!pq.isEmpty()) {
			
			Pos p=pq.poll();
			int c=p.cost;
			int y=p.y;
			int x=p.x;
			
			for (int d=0; d<4; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
				
				if (ny<0 || nx<0 || ny>=N || nx>=N) continue;
				
				int newCost=cost[y][x]+map[ny][nx];
				if (newCost<cost[ny][nx]) {
					cost[ny][nx]=newCost;
					
					print();
					
					pq.add(new Pos (ny, nx, newCost));
				}
			}
		}
		
		sb.append(cost[N-1][N-1]).append("\n");
	}
	
	static void print () {
		System.out.println();
		for (int i=0;i<N; i++) {
			for (int j=0; j<N; j++) {
				if (cost[i][j]==INF)
					System.out.printf("%3c", 'X');
				else 
					System.out.printf("%3d", cost[i][j]);
			}
			System.out.println();
		}
	}
}
