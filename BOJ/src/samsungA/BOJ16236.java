package samsungA;

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

class Fish implements Comparable <Fish>{
	int y; int x;
	int size;
	int dist;
	
	Fish (int y, int x, int size, int dist) {
		this.y=y;
		this.x=x;
		this.size=size;
		this.dist=dist;
	}

	@Override
	
	//거리가 가깝고, 더 위에 있고, 더 왼쪽인 순으로 정렬하기 위함
	public int compareTo(Fish o) {
		if (this.dist==o.dist)
			if (this.y==o.y)
				return this.x-o.x;
			else return this.y-o.y;
		else return this.dist-o.dist;
	}
}

public class BOJ16236 {

	
	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};
	
	static int N, Time=0;
	static int sy, sx, size=2, cnt;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		N=Integer.parseInt(br.readLine());
		map=new int[N][N];
		
		for (int i=0; i<N; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				
				if (map[i][j]==9) {
					sy=i; sx=j;
				}
			}
		}
		move();
		
	}
	
	static void move () {

		boolean flag=true;
		while (flag) {
			flag=findFish();	
		}
		System.out.println(Time);
	}
	
	static boolean findFish () {
	
		//현재 상어의 위치에서 얼마나 떨어졌는지 저장
		int dist[][]=new int[N][N];
		for (int i=0; i<N; i++)
			Arrays.fill(dist[i], -1);
		
		//상어가 움직일 수 있는 모든 위치를 저장
		Queue<int[]> queue=new ArrayDeque<>();
		queue.offer(new int[] {sy, sx});
		dist[sy][sx]=0;
		
		//상어가 먹을 수 있는 모든 물고기를 저장
		List<Fish> eat=new ArrayList<>();

		while (!queue.isEmpty()) {
			
			int y=queue.peek()[0];
			int x=queue.poll()[1];
			
			for (int d=0; d<4; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
				
				// 범위를 벗어나거나 이미 방문된 지점이면 continue
				if (ny<0 || nx<0 || ny>=N || nx>=N || dist[ny][nx]!=-1) continue;
				
				// 현재 상어의 크기보다 작거나 같다면 -> 지나갈 수 있음 -> 큐에 넣음
				if (map[ny][nx]<=size) {
					queue.offer(new int[] {ny, nx});
					dist[ny][nx]=dist[y][x]+1;
					
					//게다가, 현재 상어의 크기보다 작다면 -> 먹을 수도 있음 -> eat List에 넣어줌 
					if (map[ny][nx]!=0 && map[ny][nx]<size) {
						eat.add(new Fish (ny, nx, map[ny][nx], dist[ny][nx]));
					}
				}		
			}
		}
			
		//먹을 수 있는 게 없다면 false
		if (eat.size()==0) return false;
		
		// 먹을 수 있는 것들을 sorting
		Collections.sort(eat);
		
		// 우선순위가 제일 높은 놈을 먹음
		int ny=eat.get(0).y;
		int nx=eat.get(0).x;
		map[sy][sx]=0;
		map[ny][nx]=9;
		
		// 이동한 거리만큼 시간을 더해줌
		Time+=eat.get(0).dist;
		
		sy=ny; sx=nx;
		cnt++;
		
		if (size==cnt) {
			size++;
			cnt=0;
		}
		
		print(map);
		System.out.println(size);
		
		return true;
		
	}
	
	static void print (int[][] dist) {
		System.out.println("===========================");
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				System.out.print(dist[i][j]+" ");
			}
			System.out.println();
		}
	}

}
