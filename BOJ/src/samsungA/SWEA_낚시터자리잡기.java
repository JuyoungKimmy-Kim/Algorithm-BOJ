package samsungA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Gate {
	int location;
	int people;
	int[] dist=new int[61];
	
	Gate (int location, int people) {
		this.location=location;
		this.people=people;
	}
}

public class SWEA_낚시터자리잡기 {

	static int T, N, total, minTotal;
	static int[]tgt;
	static Gate[] gate=new Gate[3];
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++ ) {
			N=Integer.parseInt(br.readLine());
			
			//각 게이트의 우선 순위 저장
			tgt=new int[3];
			
			for (int i=0; i<3; i++) {
				st=new StringTokenizer (br.readLine());
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				
				gate[i]=new Gate (a, b);
			}
			getDist();
			
		}
	}
	
	static void getDist () {

		for (int i=0; i<3; i++) {
			int location=gate[i].location;
			
			int d=1;
			gate[i].dist[location]=1;
			
			for (int j=location-1; j>=0; j--)
				gate[i].dist[j]=d++;
			
			d=1;
			for (int j=location+1; j<N; j++)
				gate[i].dist[j]=d++;
		}
	}

	static void perm (int tgtIdx, int mask) {
		
		if (tgtIdx==3) {
			
			for (int i=0; i<3; i++) {
				boolean[] selected=new boolean[N];
				
			
				
			}
			
			return ;
		}
		
		for (int i=0; i<3; i++) {
			if ((mask & 1<<i)!=0) continue;
			tgt[tgtIdx]=i;
			perm (tgtIdx+1, mask | 1<<i);
		}
	}
	
	static void comb (int n, int tgtIdx, int srcIdx, int sum, int remain, boolean[] selected) {
		if (tgtIdx==gate[tgt[n]].people) {

			if (sum<total) 
				comb (n+1, 0,0,sum, remain-gate[tgt[n]].people, selected);
			
			
			
			return;
		}
		
		if (srcIdx==remain) return;
		selected[tgtIdx]=true;
		comb (n, tgtIdx+1, srcIdx+1, sum+ gate[tgt[n]].dist[tgtIdx], remain, selected);
		selected[tgtIdx]=false;
		comb (n, tgtIdx, srcIdx+1, sum, remain, selected);
		
	}
	
}
