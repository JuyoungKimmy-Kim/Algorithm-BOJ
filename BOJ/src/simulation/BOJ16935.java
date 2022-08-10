package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16935 {
	
	static int N,M,R;
	static int array [][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		R=Integer.parseInt(st.nextToken());
		
		array=new int[N][M];
		
		for (int i=0; i<N; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<M; j++) {
				array[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		st=new StringTokenizer (br.readLine());
		for (int i=0; i<R; i++) {
			int no=Integer.parseInt(st.nextToken());
			
			switch (no) {
			case 1: rotate_up_down (); break;
			case 2: rotate_left_right(); break;
			case 3: rotate_cc(); break;
			case 4: rotate_rcc(); break;
			case 5: divide_rotate_cc(); break;
			case 6: divide_rotate_rcc(); break;
			}
			
			//print();
			//System.out.println();
		}
		print();
		


	}
	
	private static void rotate_up_down () {
		
		for (int j=0; j<M; j++) {
			for (int i=0; i<=(N-1)/2; i++) {
				
				int tmp=array[i][j];
				array[i][j]=array[N-i-1][j];
				array[N-i-1][j]=tmp;
			}
		}
	}
	
	private static void rotate_left_right () {
		for (int i=0; i<N; i++) {
			for (int j=0; j<=(M-1)/2; j++) {
				
				int tmp=array[i][j];
				array[i][j]=array[i][M-j-1];
				array[i][M-j-1]=tmp;

			}
		}
	}
	
	
	private static void rotate_cc () {

		int[][] array2=new int[M][N];
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				array2[j][N-1-i]=array[i][j];
			}
		}
		
		int tmp=N; 
		N=M; M=tmp;
		
		array=array2;
	}
	
	private static void rotate_rcc() {
		
		int[][] array2=new int[M][N];
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				array2[M-1-j][i]=array[i][j];
			}
		}
		
		int tmp=N; 
		N=M; M=tmp;
		
		array=array2;
	}
	
	private static void divide_rotate_cc() {
		int n=N/2; int m=M/2;
		int[][] tmp=new int[n][m];
		
		for (int i=0; i<n; i++)
			for (int j=0; j<m; j++)
				tmp[i][j]=array[i][j];
		
		// 4->1
		for (int i=n; i<N; i++) 
			for (int j=0; j<m; j++) 
				array[i-n][j]=array[i][j];
		
		//3->4
		for (int i=n; i<N; i++)
			for (int j=m; j<M; j++)
				array[i][j-m]=array[i][j];
		
		//2->3
		for (int i=0; i<n; i++)
			for (int j=m; j<M; j++)
				array[i+n][j]=array[i][j];
		
		//tmp->2
		for (int i=0; i<n; i++)
			for (int j=m; j<M; j++)
				array[i][j]=tmp[i][j-m];
		
	}
	
	private static void divide_rotate_rcc() {
		int n=N/2; int m=M/2;
		int[][] tmp=new int[n][m];
		
		for (int i=0; i<n; i++)
			for (int j=0; j<m; j++)
				tmp[i][j]=array[i][j];
		
		//2->1
		
		for (int i=0; i<n; i++)
			for (int j=m; j<M; j++)
				array[i][j-m]=array[i][j];
		
		//3->2
		
		for (int i=n; i<N; i++)
			for (int j=m; j<M; j++)
				array[i-n][j]=array[i][j];
						
		//4->3
		
		for (int i=n; i<N; i++)
			for (int j=0; j<m; j++)
				array[i][j+m]=array[i][j];
		//tmp->4
		
		for (int i=n; i<N; i++)
			for (int j=0; j<m; j++)
				array[i][j]=tmp[i-n][j];
		
		
	}
	
	private static void copy (int[][] tmp) {
		for (int i=0; i<N; i++)
			for (int j=0; j<M; j++)
				tmp[i][j]=array[i][j];
	}
	
	private static void print () {
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				System.out.print(array[i][j]+" ");
			}
			System.out.println();
		}
	}

}
