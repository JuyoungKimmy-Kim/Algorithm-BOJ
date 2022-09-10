package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1525 {
	
	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};

	static int sy,sx,ans=Integer.MAX_VALUE;
	static int [][]map=new int[3][3];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		for (int i=0; i<3; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<3; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if (map[i][j]==0) {
					sy=i; sx=j;
				}
			}
		}
		dfs (-1, 0,sy,sx,map);
		if (ans==Integer.MAX_VALUE) ans=-1;
		System.out.println(ans);
		
	}
	
	private static void dfs (int prev, int cnt, int y, int x, int [][]map) {
		
		if (y==2 && x==2) {
			if (check(map)) {
				ans=Math.min(ans, cnt);
			}
			return ;
		}
		
		int[][] tmp=new int[3][3];
		copyMap(tmp, map);
		
		for (int d=0; d<3; d++) {
			if (isOpposite(prev,d)) continue;
			
			int ny=y+dy[d];
			int nx=x+dx[d];
			
			if (ny<0 || nx<0 || ny>=3 || nx>=3) continue;
			tmp[y][x]=tmp[ny][nx];
			tmp[ny][nx]=0;
			
			dfs (d, cnt+1, ny, nx, tmp);
			
			tmp[ny][nx]=tmp[y][x];
			tmp[y][x]=0;
			copyMap(map, tmp);
		}
	}
	
	private static void copyMap (int [][]a, int [][]b) {
		for (int i=0; i<3; i++)
			for (int j=0; j<3; j++)
				a[i][j]=b[i][j];
	}
	
	private static boolean check(int[][] a) {
		int idx=1;
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				if (a[i][j]!=idx) return false;
				idx=(idx+1)%9;
			}
		}
		return true;
	}
	
	private static boolean isOpposite (int prev, int now) {
		if (prev==0 && now==1) return true;
		else if (prev==1 && now==0) return true;
		else if (prev==2 && now==3) return true;
		else if (prev==3 && now==2) return true;
		
		return false;
	}
	
	private static void print (int[][] a) {
		System.out.println("================================");
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
	}

}
