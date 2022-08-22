package samsungA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Bridge {
	int from,to,dist;
	
	Bridge (int from, int to, int dist) {
		this.from=from;
		this.to=to;
		this.dist=dist;
	}
}

public class BOJ17472 {
	
	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};
	
	static int N, M, K=1;
	static int ans;
	static List<Bridge> brList=new ArrayList<>();
	
	static int[] parents;
	static List<Integer> adj[];
	
	static int[][] map;
	static boolean[][] visited;
	static boolean[] checked;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		map=new int[N][M];
		visited=new boolean[N][M];
		
		for (int i=0; i<N; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		solution();
		System.out.println(ans);
	}
	
	static void solution () {
		
		// #1. 각 섬에 numbering
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j]!=0 && !visited[i][j]) {
					dfs (i,j,K);
					K++;
				}

			}
		}
		parents=new int[K+1];
		adj=new ArrayList [K+1];
		for (int i=1; i<=K; i++)
			adj[i]=new ArrayList<>();
		
		for (int i=1; i<=K; i++)
			parents[i]=i;
		
		for (int i=0; i<N; i++)
			Arrays.fill(visited[i], false);
		
		checked=new boolean[K+1];
		
		// #2. 각 섬에서 만들 수 있는 다리 조사
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (map[i][j]==0) continue;
				makeBridge(i,j);
			}
		}
		
		// #3. 다리 길이가 짧은 순으로 정렬
		Collections.sort(brList, (Bridge b1, Bridge b2)->b1.dist-b2.dist);
		
		// #3. mst 만들기
		mst();
		
//		for (int i=0; i<brList.size(); i++) {
//			int from=brList.get(i).from;
//			int to=brList.get(i).to;
//			int dist=brList.get(i).dist;
//			
//			System.out.print(from+"=>"+to+"="+dist);
//			System.out.println();
//		}
		
		// #4. 모든 섬이 연결되어 있는지 확인
		
		
	}
	
	static void dfs (int y, int x, int idx) {
		
		visited[y][x]=true;
		map[y][x]=idx;
		
		for (int d=0; d<4; d++) {
			int ny=y+dy[d];
			int nx=x+dx[d];
			
			if (isInRange (ny, nx) && map[ny][nx]!=0 && !visited[ny][nx])
				dfs (ny, nx, idx);
		}
	}
	
	static void makeBridge (int y, int x) {
		int from=map[y][x];
		
		for (int d=0; d<4; d++) {
			int cnt=0;
			int ny=y+dy[d], nx=x+dx[d];
			
			while (isInRange (ny, nx) && map[ny][nx]==0) {
				ny+=dy[d];
				nx+=dx[d];
				cnt++;
			}
			
			// 여기까지 왔다는 것은 범위를 벗어났거나, 섬이 나왔다는 것
			if (!isInRange(ny,nx) || map[ny][nx]==from) continue;
			
			int to=map[ny][nx]; 				//연결된 다른 섬의 번호
			brList.add(new Bridge (from,to,cnt));
		}
	}
	
	static int find (int u) {
		if (u==parents[u]) return u;
		else return find(parents[u]);
	}
	
	static boolean union (int u, int v) {
		u=find(u);
		v=find(v);
		
		if (u!=v) {
			parents[u]=v;
			adj[u].add(v);
			adj[v].add(u);
			return true;
		}
		else return false;
	}
	
	static void mst () {
		for (int i=0, size=brList.size(); i<size; i++) {
			int dist=brList.get(i).dist;
			
			if (dist<2) continue;
			int u=brList.get(i).from;
			int v=brList.get(i).to;
			
			if (union (u,v))
				ans+=dist;
			
//			for (int j=1; j<=K; j++) {
//				System.out.print(j+": ");
//				
//				for (int k : adj[j])
//					System.out.print(k+" ");
//				System.out.println();
//			}
//			System.out.println();
		}
	}
	
	static void isConnected () {
		
		
	}
	
	static boolean isInRange (int y, int x) {
		if (y>=0 && x>=0 && y<N && x<M) return true;
		return false;
	}

	static void print () {
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
