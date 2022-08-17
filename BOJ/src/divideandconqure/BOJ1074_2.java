package divideandconqure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1074_2 {

	static int N,r,c,ans;

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer( br.readLine());

		N=Integer.parseInt(st.nextToken());		//N은 2의 제곱수이므로 N은 실제 좌, 우에 해당하는 길이로 보정
		r=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken());
		
		int y=0, x=0;
		N=1<<N;
		
		while (N>1) {
			N/=2;
			
			//r,c가 4공간 중 어느 곳에 해당하는 지 판단, 각 공간에서 r,c 값에 따라 ans 값 갱신, 원점 보정
			
			if (r<y+N && c<x+N) { //top-left 
				;
			}
			else if (r<y+N && c>=x+N) { // top-right
				ans+=N*N*1;
				x+=N; 
			}
			else if (r>=y+N && c<x+N) { //bottom-left
				ans+=N*N*2;
				y+=N;
			}
			else  { //bottome-right
				ans+=N*N*3;
				y+=N;
				x+=N;
			}
		}
		
		System.out.println(ans);
	}
	

}
