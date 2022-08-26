package samsungA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ17144 {

	static final int dy[] = {0,0,1,-1};
	static final int dx[] = {1,-1,0,0};
	
	static int sy,ey;
	static int R,C,T;
	static int [][] map;
	public static void main(String[] args) throws IOException  {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		st=new StringTokenizer (br.readLine());
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		T=Integer.parseInt(st.nextToken());
		
		
		map=new int[R][C];
		
		for (int i=0; i<R; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<C; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if (map[i][j]==-1) {
					if (sy==0) sy=i;
				}
			}
		}
		ey=sy+1;
		
		
		while (T>0) {
			T--;
			spreadMicroDust();
			
			//print();
			
			operate();
			
			//print();
		}
		
		System.out.println(count());
		
	}
	
	static int count() {
		int sum=0;
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				if (map[i][j]==-1) continue;
				sum+=map[i][j];
			}
		}
		return sum;
	}
	
	static void operate() {
		// 윗부분
		
		// #1. 위에서 아래로
		for (int i=sy-2; i>=0; i--) 
			map[i+1][0]=map[i][0];
		
		// #2. 오른쪽에서 왼쪽으로
		for (int i=1; i<C; i++)
			map[0][i-1]=map[0][i];
		
		// #3. 아래에서 위로
		for (int i=1; i<=sy; i++)
			map[i-1][C-1]=map[i][C-1];
		
		 //#4. 왼쪽에서 오른쪽으로
		for (int i=C-2; i>=1; i--)
			map[sy][i+1]=map[sy][i];
		map[sy][1]=0;
		
		//아랫 부분
		
		// #1. 아래에서 위로
		for (int i=ey+2; i<R; i++)
			map[i-1][0]=map[i][0];
		
		// #2. 왼쪽에서 오른쪽으로
		for (int i=1; i<C; i++)
			map[R-1][i-1]=map[R-1][i];
		
		// #3. 위에서 아래로
		for (int i=R-2; i>=ey; i--)
			map[i+1][C-1]=map[i][C-1];
		
		// #4. 왼쪽에서 오른쪽으로
		for (int i=C-2; i>=1; i--)
			map[ey][i+1]=map[ey][i];
		map[ey][1]=0;
	}
	

	static void spreadMicroDust () {
		int[][] tmp=new int[R][C];
		
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				
				// 미세먼지가 있는 구역이라면 
				if (map[i][j]>0) {
					
					int cnt=0;
					List<int[]> spread=new ArrayList<> ();
					
					for (int d=0; d<4; d++) {
						int ny=i+dy[d];
						int nx=j+dx[d];
						
						// 인접한 방향에 공기 청정기가 있거나, 칸이 없으면 확산이 일어나지 않음
						if (ny<0 || nx<0 || ny>=R || nx>=C || map[ny][nx]==-1) continue;
						cnt++;
						spread.add(new int [] {ny, nx});
					}
					
					int amount=map[i][j]/5;
					for (int s=0; s<spread.size(); s++) {
						int y=spread.get(s)[0];
						int x=spread.get(s)[1];
						
						tmp[y][x]+=amount;
					}
					
					map[i][j]=map[i][j]-(map[i][j]/5*cnt);
				}
			}
		}
		
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				if (map[i][j]==-1) continue;
				map[i][j]+=tmp[i][j];
			}
		}
	}
	
	static void print () {
		System.out.println("===========================");
		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) {
				System.out.printf("%3d", map[i][j]);
			}
			System.out.println();
		}
	}

}
