package divideandconqure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1074 {

	static int N,r,c,ans;

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer( br.readLine());

		N=Integer.parseInt(st.nextToken());		//N은 2의 제곱수이므로 N은 실제 좌, 우에 해당하는 길이로 보정
		r=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken());
		
		N=1<<N;
		recursion (0,0);
		System.out.println(ans);
	}
	
	private static void recursion (int y, int x) { //y와 x는 원점
		
		if (N==1) return ;
		N/=2;
		
		if (r<y+N && c<x+N) { //top-left 
			recursion(y,x);
		}
		else if (r<y+N && c>=x+N) { // top-right
			ans+=N*N*1;
			recursion (y,x+N);
		}
		else if (r>=y+N && c<x+N) { //bottom-left
			ans+=N*N*2;
			recursion (y+N, x);
		}
		else  { //bottome-right
			ans+=N*N*3;
			recursion (y+N, x+N);
		}
	}
}
