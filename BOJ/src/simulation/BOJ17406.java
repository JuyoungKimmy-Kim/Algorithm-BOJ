package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class RCS {
	int r,c,s;
	RCS (int r, int c, int s) {
		this.r=r;
		this.c=c;
		this.s=s;
	}
}

public class BOJ17406 {
	
	static ArrayList<RCS> rcs =new ArrayList<>();
	
	static int minSum=Integer.MAX_VALUE;
	static int N,M,K;
	
	static int[] order;
	static int [][] array;
	static int [][] tmp_array;
	static boolean[] selected;
	
	static int sy,sx,ey,ex;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		array=new int[N+1][M+1];
		tmp_array=new int[N+1][M+1];
		
		for (int i=1; i<=N; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=1; j<=M; j++) {
				array[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		selected=new boolean[K];
		order=new int[K];
		
		for (int i=0; i<K; i++) {
			st=new StringTokenizer (br.readLine());
			int r=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			int s=Integer.parseInt(st.nextToken());
			
			rcs.add(new RCS (r,c,s));	
		}
		dfs(0);

		System.out.println(minSum);

	}
	
	private static void dfs (int cnt) {
		if (cnt==K) {
			
			copy (tmp_array, array);
			
			for (int i=0; i<K; i++) 
				rotate (order[i]);

			for (int i=1; i<=N; i++) {
				int sum=0;
				for (int j=1; j<=M; j++ )
					sum+=array[i][j];
				if (sum<minSum) minSum=sum;
			}
			copy (array, tmp_array);
					
			return ;
		}
		
		for (int i=0; i<K; i++) {
			if (selected[i]) continue;
			
			copy(tmp_array, array);
			
			selected[i]=true;
			order [cnt]=i;

			dfs(cnt+1);
			
			selected[i]=false;
		}
		
	}
	
	private static void rotate (int idx) {

		int r=rcs.get(idx).r;
		int c=rcs.get(idx).c;
		int s=rcs.get(idx).s;

		sy=r-s; sx=c-s;
		ey=r+s; ex=c+s;
		
		while (true) {
			
			if (ey-sy<1 || ex-sx<1) return ;

			int tmp=array[sy][sx];
			
			for (int y=sy+1; y<=ey; y++)
				array[y-1][sx]=array[y][sx];
				
			for (int x=sx+1; x<=ex; x++)
				array[ey][x-1]=array[ey][x];
			
			for (int y=ey-1; y>=sy; y--)
				array[y+1][ex]=array[y][ex];
			
			for (int x=ex-1; x>=sx; x--)
				array[sy][x+1]=array[sy][x];
				
			array[sy][sx+1]=tmp;
						
			sy+=1; sx+=1;
			ey-=1; ex-=1;
		}
			
	}
	
	//a에 b복사
	private static void copy (int[][] a, int [][]b) {
		for (int i=1; i<=N; i++)
			for (int j=1; j<=M; j++)
				a[i][j]=b[i][j];
	}
	
	private static void print () {
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=M; j++) {
				System.out.print(array[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
