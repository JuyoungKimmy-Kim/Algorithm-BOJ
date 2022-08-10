package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16926 {

	static int N,M,R;
	static int [][]map;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		R=Integer.parseInt(st.nextToken());
		
		// 입력 처리
		map=new int[N][M];
		for (int i=0; i<N; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i=0; i<R; i++) {
			rotate ();
		}
		print();
	}
	
	private static void rotate () {
		
		int sy=0, ey=N-1; 
		int sx=0, ex=M-1;
		
		while (true) {
			//기저 조건
			if (ey-sy <1 || ex-sx<1) return ;
			
			//반 시계 방향으로 이동
			
			int temp=map[sy][sx]; // 첫 번째 값 back up
			
			//top : 좌로 이동
			for (int i=sx; i<ex; i++) {
				map[sy][i]=map[sy][i+1];
			}
			
			//right : 상으로 이동 (밑의 값이 위로 이동)
			for (int i=sy; i<ey; i++) {
				map[i][ex]=map[i+1][ex];
			}
			
			//bottom : 우로 이동 (왼쪽의 값이 오른쪽으로 이동)
			for (int i=ex; i> sx; i--) {
				map[ey][i]=map[ey][i-1];
			}
			
			//left : 하로 이동 (위의 값이 밑으로 이동)
			for (int i=ey; i>sy; i--) {
				map[i][sx]=map[i-1][sx];
			}
			
			map[sy+1][sx]=temp;
			
			//sy, ey, sx, ex  보정
			sy+=1; sx+=1;
			ey-=1; ex-=1;
		}
	}
	
	private static void print () {
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}


}
