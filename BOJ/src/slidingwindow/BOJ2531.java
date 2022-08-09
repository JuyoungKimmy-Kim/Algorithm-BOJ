package slidingwindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2531 {

	static int N,D,K,C;
	static int[] sushi;
	static int[] kind;
	static int maxCnt;
	static int cnt;
	static boolean couponsushi;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		D=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		
		sushi=new int[N+K];
		kind=new int[D+1];
		
		for (int i=0; i<N; i++) {
			sushi[i]=Integer.parseInt(br.readLine());
			if (sushi[i]==C)
				couponsushi=true;
		}
		if (couponsushi==false) cnt=1;
			
		eat();
		System.out.println(maxCnt);

	}
	
	static boolean check=false;
	
	public static void eat () {
		for (int i=0; i<=N-K; i++) {
						
			if (i==0) {
				for (int j=0; j<K; j++) {
					kind[sushi[j]]++;

					if (kind[sushi[j]]==1) cnt++;
					if (sushi[j]==C) check=true;
				}
			}
			else {
				int prev=sushi[i-1];
				kind[prev]--;
				if (kind[prev]==0) cnt--;
				if (kind[prev]==0 && prev==C) 
					check=false;
				
				int next=sushi[i+K-1];
				kind[next]++;
				if (kind[next]==1) cnt++;
				if (next==C) check=true;
			}		
			//next -> coupon or not

			if (!couponsushi) {
				maxCnt=Math.max(maxCnt, cnt);
			}
			
			//쿠폰 사용했으므로 다음 꺼 확인 가능
			else if (i+K<=N && check && kind[sushi[i+K]]==0) {	
					maxCnt=Math.max(maxCnt, cnt+1);
			}
			else
				maxCnt=Math.max(maxCnt, cnt);
				
		}
	}

}
