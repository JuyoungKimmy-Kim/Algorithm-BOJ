package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class CCTV {
	int no;
	int y, x;
	
	public CCTV (int no, int y, int x) {
		this.no=no;
		this.y=y;
		this.x=x;
	}
}

public class BOJ15683 {
	
	static final int EMPTY=0;
	static final int WALL=6;
	static final int INSPECT=7;
	
						// 0 1 2 3
						// 동 서 남 북
	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};
	
	static int N,M,K,ans=Integer.MAX_VALUE;
	static int [][] map;
	static List<CCTV> cctv=new ArrayList<CCTV>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		map=new int[N][M];
		
		for (int i=0; i<N; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if (map[i][j]>=1 && map[i][j]<=5) {
					cctv.add(new CCTV (map[i][j], i, j));
					K++;
				}
			}
		}
		
		dfs (0, map);
		System.out.println(ans);
		
	}
	
	
	private static void dfs (int idx, int [][] tmp) {
		if (idx==K) {
			findSafeArea(tmp);
			return ;
		}
		
		// idx번 째 CCTV 정보를 받아 옴
		int no=cctv.get(idx).no;
		int y=cctv.get(idx).y;
		int x=cctv.get(idx).x;
		
		if (no==1) {
		// 동, 서, 남, 북 4방향 중에 1개를 선택할 수 있음
			int[][] cmap=new int[N][M];
			copyMap (tmp, cmap);		//현재 맵의 상태(tmp)을 cmap에 복사
			
			for (int d=0; d<4; d++) {	
				inspect (d, cmap, y, x);		// 현재 방향으로 감시 시작
				dfs (idx+1, cmap);		// 현재 방향으로 감시한 cmap을 가지고 다시 다른 cctv설치 위해 dfs 탐색
				
				//print(cmap);
				
				copyMap (tmp, cmap);	// 탐색이 끝나고 돌아왔을 때 다시 원래 맵의 상태 (tmp)로 복구
			}	
			
		} else if (no==2) {
		// (동,서), (남,북) 2개 중에 선택
			int[][] cmap=new int[N][M];
			copyMap (tmp, cmap);
			
			for (int d=0; d<4; d++) {
				inspect (d++, cmap, y, x);	// (0,1) 동서 방향 탐색하고 다음 dfs
				inspect (d, cmap, y, x);		// 그 후 (2,3) 남북 방향 탐색하고 다음 dfs
				
				dfs (idx+1, cmap);
				
				copyMap (tmp, cmap);
			}
			
		} else if (no==3) {
		//(북동30), (동남02), (남서21), (서북13) 4개중에 선택
			int[][] cmap=new int[N][M];
			copyMap (tmp, cmap);
			
			inspect (3, cmap, y, x);
			inspect (0, cmap, y, x);
			dfs (idx+1, cmap);
			copyMap (tmp, cmap);
			
			inspect (0, cmap, y, x);
			inspect (2, cmap, y, x);
			dfs (idx+1, cmap);
			copyMap (tmp, cmap);
			
			inspect (2, cmap, y, x);
			inspect (1, cmap, y, x);
			dfs (idx+1, cmap);
			copyMap (tmp, cmap);
			
			inspect (1, cmap, y, x);
			inspect (3, cmap, y, x);
			dfs (idx+1, cmap);
			copyMap (tmp, cmap);
			
		} else if (no==4) {
			//(서북동), (북동남), (동남서), (남서북) 4개 중 1개 선택
			int[][] cmap=new int[N][M];
			copyMap (tmp, cmap);
			
			for (int i=0; i<4; i++) {
				for (int j=0; j<4; j++) {
					if (i==j) continue;
					inspect (j, cmap, y, x);
				}
				dfs (idx+1, cmap);
				copyMap (tmp, cmap);
			}
			
			
		} else if (no==5) {
			// (동서남북) 1개 탐색 => 1개밖에 없으므로 copy map 필요 X		
			for (int d=0; d<4; d++)
				inspect (d, tmp, y, x);
			dfs (idx+1, tmp);

		}
	}
	
	//y,x 위치에서 dir 방향으로 탐색
	private static void inspect (int dir, int [][]cmap, int y, int x) {
		int ny=y, nx=x;
		
		while (ny>=0 && ny<N && nx>=0 && nx<M && cmap[ny][nx]!=WALL) {
			//다른 CCTV가 나왔을 때는 그냥 통과함
			if (cmap[ny][nx]==EMPTY) 
				cmap[ny][nx]=INSPECT;
			
			ny+=dy[dir];
			nx+=dx[dir];
		}
	}
	
	private static void findSafeArea (int [][]tmp) {
		
		int sum=0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (tmp[i][j]==EMPTY)
					sum++;
			}
		}
		
		if (sum<ans) ans=sum;
	}
	// a map을 b에 복사함
	private static void copyMap (int[][]a , int [][]b) {
		
		for (int i=0; i<N; i++)
			for (int j=0; j<M; j++)
				b[i][j]=a[i][j];
	}

	private static void print (int[][] a) {
		System.out.println("=============================");
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				System.out.print(a[i][j]+" ");
				
			}
			System.out.println();
		}
	}

}
