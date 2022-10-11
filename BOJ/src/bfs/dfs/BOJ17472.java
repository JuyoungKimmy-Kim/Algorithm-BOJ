package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17472 {

	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};
	
	static int N,M,K, ans, cntAns=1, V;
	static int [][] map;
	static boolean[][] visited;
	
	// mst에 필요한 변수들
	static int[] parent;
	static List<Bridge> bridge;
	static List<Integer> []adj;
	static boolean[] checked;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		map=new int[N][M];
		visited=new boolean[N][M];
		bridge=new ArrayList<>();
		
		for (int i=0; i<N; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		go();
		if (V==K-1) System.out.println(ans);
		else System.out.println(-1);
	}
	
	static void go () {
		
		// #1. 각 섬에 numbering 
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (map[i][j]==1 && !visited[i][j]) {
					numbering(++K, i,j);
				}
			}
		}
		
		// #2. 구한 섬의 개수를 가지고 mst 준비
		parent=new int[K+1];
		for (int i=0; i<K+1; i++)
			parent[i]=i;
		
		adj=new ArrayList[K+1];
		for (int i=0; i<K+1; i++)
			adj[i]=new ArrayList<>();
		
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (map[i][j]==0) continue;
				makeBridge (map[i][j], i,j);
			}
		}
		checked=new boolean[K+1];
		
		
		if (bridge.size()==0) return ;
		// #3. mst
		Collections.sort(bridge, (Bridge b1, Bridge b2) -> b1.dist-b2.dist);
			
		mst();
		//isConnected(1);

				
	}
	
	static int find (int u) {
		if (u==parent[u]) return u;
		else return find(parent[u]);
	}
	
	static boolean union (int u, int v) {
		int a=find(u);
		int b=find(v);
		
		if (a==b) return false;
		if (a>b) parent[a]=b;
		else parent[b]=a;
		
		adj[a].add(b);
		adj[b].add(a);
		
		return true;
	}
	
	static void mst () {
		for (int i=0; i<bridge.size(); i++) {
			int dist=bridge.get(i).dist;
			
			int u=bridge.get(i).from;
			int v=bridge.get(i).to;
			
			if (union(u,v)) {
				ans+=dist;
				V++;
			}
		}	
	}

	
	static void makeBridge (int from, int y, int x) {
		
		for (int d=0; d<4; d++) {
			int cnt=0;
			int ny=y+dy[d], nx=x+dx[d];
			
			while (isInRange (ny, nx) && map[ny][nx]==0) {
				ny+=dy[d];
				nx+=dx[d];
				cnt++;
			}
			
			// 여기까지 왔다는 것은 범위를 벗어났거나, 섬이 나왔다는 것
			if (!isInRange(ny,nx) || map[ny][nx]==from || cnt<2) continue;
			
			int to=map[ny][nx]; 				//연결된 다른 섬의 번호
			bridge.add(new Bridge (from,to,cnt));
	
		}
	}

	static void numbering (int idx, int i, int j) {
		
		Queue<int[]> queue=new ArrayDeque<>();
		queue.add(new int[] {i,j});
		visited[i][j]=true;
		map[i][j]=idx;
		
		while (!queue.isEmpty()) {
			int y=queue.peek()[0];
			int x=queue.poll()[1];
			
			for (int d=0; d<4; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
				
				// 범위를 넘거나, 방문된 적이 있거나, 0일 때 => 무시
				if (!isInRange(ny, nx) || visited[ny][nx] || map[ny][nx]==0) continue;
				
				visited[ny][nx]=true;
				map[ny][nx]=idx;
				queue.add(new int[] {ny, nx});
			}
		}
	}
	
	static boolean isInRange (int y, int x) {
		if (y<0 || x<0 || y>=N || x>=M) return false;
		return true;
	}
	
	static void print () {
		System.out.println("=======================");
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}

	static class Bridge {
		int from,to,dist;
		
		Bridge (int from, int to, int dist) {
			this.from=from;
			this.to=to;
			this.dist=dist;
		}

		@Override
		public String toString() {
			return "Bridge [from=" + from + ", to=" + to + ", dist=" + dist + "]";
		}
		
		
	}
}
