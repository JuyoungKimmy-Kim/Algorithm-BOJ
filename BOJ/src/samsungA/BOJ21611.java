package samsungA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ21611 {
	
	//상어가 토네이도 돌아가는 방향
	static final int dy[] = {0,1,0,-1};
	static final int dx[] = {-1,0,1,0};
	
	//맨 처음 상어가 돌을 던질 때 방향
	static final int tdy[]= {0,-1,1,0,0};	
	static final int tdx[]= {0,0,0,-1,1};

	static int N, M;
	static int[][] map;
	
	static List<int[]> order=new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		map=new int[N][N];
		for (int i=0; i<N; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		// #1. 상어가 도는 좌표를 순서대로 order에 저장
		makeOrder ();
		
		// #2. 상어가 얼음 파편을 던진다
		for (int i=0; i<M; i++) {
			st=new StringTokenizer (br.readLine());
			throwIce (Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			merge();
		}

	}
	
	static void makeOrder () {
		int y=N/2; int x=N/2;
		order.add(new int[] {y,x});
		
		int cnt=0, dist=1, d=0; 
		
		outer: while (true) {
			
			for (int i=0; i<dist; i++) {
				y+=dy[d];
				x+=dx[d];
				
				order.add(new int[] {y,x});
				if (y==0 && x==0) break outer;
			}
			d=(d+1)%4;
			cnt++;
			
			if (cnt==2) {
				cnt=0; dist++;
			}
		}
	}
	
	static void throwIce (int d, int s) {
		int y=N/2; int x=N/2;
		
		for (int i=0; i<s; i++) {
			int ny=y+dy[d];
			int nx=x+dx[d];
			map[ny][nx]=0;
		}
	}
	
	static void merge () {
		List<Integer> marble=new ArrayList<>();
		for (int i=0, size=order.size(); i<size; i++) {
			int y=order.get(i)[0];
			int x=order.get(i)[1];
			if (map[y][x]!=0) {
				marble.add(map[y][x]);
			}
		}
		updateMap (marble);
	}
	
	static void updateMap (List<Integer> m) {
		
		for (int i=0; i<N; i++)
			Arrays.fill(map[i], 0);
		
		for (int i=0, size=m.size(); i<size; i++) {
			int y=order.get(i)[0];
			int x=order.get(i)[1];
			
			map[y][x]=m.get(i);
		}
	}
	
	static void explode () {
		boolean flag=true; 		//폭발이 1번이라도 일어났는지
		
		while (flag) { 
			flag=false;
			
			int prev=map[order.get(1)[0]][order.get(1)[1]];
			
			for (int i=2, size=order.size(); i<size; i++) {
				int now=map[order.get(i)[0]][order.get(i)[1]];
			}
			
		}
	}
	
	static void print () {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) 
				System.out.printf("%3d", map[i][j]);
			System.out.println();
		}
	}

}
