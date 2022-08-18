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
		z(0,0);
		
		System.out.println(ans);
	}
	
	private static void z (int y, int x) {
		
		if (N==1) return;
		N/=2;
		
		if (r<y+N && c<x+N) {
			z(y,x);
		}
		else if (r<y+N && c>=x+N ) {
			ans+=N*N*1;
			z(y,x+N);
		}
		else if (r>=y+N && c<x+N) {
			ans+=N*N*2;
			z(y+N, x);
		}
		else if (r>=y+N && c>=x+N) {
			ans+=N*N*3;
			z(y+N, x+N);
		}
	}
}
