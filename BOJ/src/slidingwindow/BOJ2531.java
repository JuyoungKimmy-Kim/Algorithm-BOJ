package slidingwindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2531 {

	static int N,D,K,C;
	static int[] sushi;
	static int[] kind;
	static int cnt, max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		D=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		
		sushi=new int[N+K];
		kind=new int[D+1];
		
		for (int i=0; i<N; i++) 
			sushi[i]=Integer.parseInt(br.readLine());


		for (int i=0; i<K; i++) {
			if (kind[sushi[i]]==0) 
				cnt++;
			kind[sushi[i]]++;
		}
		
		max=cnt;
		
		for (int i=1; i<N; i++) {
			if (max<=cnt) {
				if (kind[C]==0) 
					max=cnt+1;
				else
					max=cnt;
			}
			
			int end=(i+K-1)%N;
			if (kind[sushi[end]]==0) cnt++;
			kind[sushi[end]]++;
			
			kind[sushi[i-1]]--;
			if (kind[sushi[i-1]]==0) cnt--;
		}
		
		System.out.println(max);
	}
}
