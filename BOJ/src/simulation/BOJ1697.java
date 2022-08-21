package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



public class BOJ1697 {

	static int N, K, min, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		min=Math.abs(N-K);
		ans=min;
		
		perm (0,N);
		System.out.println(ans);
		
	}
	
	private static void perm (int cnt, int n) {
		if (n==K) {
			ans=Math.min(ans, cnt);
			return ;
		}
		
		if (cnt>min || n>100000) return ;

		perm (cnt+1, n-1);
		perm (cnt+1, n+1);
		perm (cnt+1, n*2);
			
	}
}
