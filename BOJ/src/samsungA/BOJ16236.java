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
	
		int dist[][]=new int[N][N];
		for (int i=0; i<N; i++)
			Arrays.fill(dist[i], -1);
		
		Queue<int[]> queue=new ArrayDeque<>();
		queue.offer(new int[] {sy, sx});
		dist[sy][sx]=0;
		
		List<Fish> eat=new ArrayList<>();

		while (!queue.isEmpty()) {
			
			int y=queue.peek()[0];
			int x=queue.poll()[1];
			
			for (int d=0; d<4; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
				
				if (ny<0 || nx<0 || ny>=N || nx>=N || dist[ny][nx]!=-1) continue;
				if (map[ny][nx]<=size) {
					queue.offer(new int[] {ny, nx});
					dist[ny][nx]=dist[y][x]+1;
					
					if (map[ny][nx]!=0 && map[ny][nx]<size) {
						eat.add(new Fish (ny, nx, map[ny][nx], dist[ny][nx]));
					}
				}
				
				
			}
		}
			
		if (eat.size()==0) return false;
		
		Collections.sort(eat);
		int ny=eat.get(0).y;
		int nx=eat.get(0).x;
		map[sy][sx]=0;
		map[ny][nx]=9;
		Time+=eat.get(0).dist;
		
		sy=ny; sx=nx;
		cnt++;
		
		if (size==cnt) {
			size++;
			cnt=0;
		}
		
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
