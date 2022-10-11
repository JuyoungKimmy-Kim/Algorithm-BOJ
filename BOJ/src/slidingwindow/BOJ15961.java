package slidingwindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15961 {

	static int N, D, K,C, ans;
	static int[] sushi;
	static int[] selected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		D=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		
		sushi=new int[N];
		selected=new int[D+1];
		
		for (int i=0; i<N; i++) {
			sushi[i]=Integer.parseInt(br.readLine());
		}
			
		int cnt=0;
		for (int i=0; i<K; i++) {
			int n=sushi[i];
			selected[n]++;
			if (selected[n]==1) cnt++;
		}
		selected[C]++;
		if (selected[C]==1)
			cnt++;
		ans=Math.max(ans, cnt);

		
		for (int i=0; i<N; i++) {
			int n=sushi[i];
			selected[n]--;
			if (selected[n]==0) cnt--;
			
			n=sushi[(i+K)%N];
			selected[n]++;
			if (selected[n]==1) cnt++;
			
			ans=Math.max(ans, cnt);
		}
		
		System.out.println(ans);
	}

}
