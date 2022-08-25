package samsungA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

	static int T, N,R, ans;
	static int[]tgt, p;
	static Gate[] gate;
	static boolean[] selected;
	static int[][] memoi;
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++ ) {
			N=Integer.parseInt(br.readLine());
			
			memoi=new int[N+1][3];
			selected=new boolean[N+1];
			tgt=new int[3];
			gate=new Gate[3];
			p=new int[3];
			R=0; ans=Integer.MAX_VALUE;
			
			for (int i=0; i<3; i++) {
				st=new StringTokenizer (br.readLine());
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				p[i]=b;
				R+=b;
				
				gate[i]=new Gate (a, b);
			}
						
			getDist();
			//comb(0,1,0);
			comb(0,1);
			System.out.println("#"+tc+" "+ans);
			
		}
	}
	
	static void getDist () {

		for (int i=0; i<3; i++) {
			int location=gate[i].location;
			
			int d=1;
			gate[i].dist[location]=1;
			
			for (int j=location-1; j>=0; j--)
				gate[i].dist[j]=++d;
			
			d=1;
			for (int j=location+1; j<=N; j++)
				gate[i].dist[j]=++d;
		}
		
		for (int i=1; i<=N; i++) {
			
			List<int[]> dist=new ArrayList<>();
			for (int j=0; j<3; j++) {
				dist.add(new int[] {j, gate[j].dist[i]});
			}
			
			Collections.sort(dist, (e1, e2)-> e1[1]-e2[1]);
			
			memoi[i][0]=dist.get(0)[0];
			memoi[i][1]=dist.get(1)[0];
			memoi[i][2]=dist.get(2)[0];
			
			System.out.println(memoi[i][0]+" "+memoi[i][1]+" "+memoi[i][2]);
		}	
	}
	
//	static void comb (int tgtIdx, int srcIdx, int sum) {
//	
//		if (tgtIdx==R) {
//			ans=Math.min(ans, sum);
//			return ;
//		}
//		if (sum>ans) return;
//		
//		//i를 선택 or 비선택 할 차례
//		for (int i=srcIdx; i<=N; i++) {
//			
//			int idx=0;
//			for (int j=0; j<3; j++) {
//				idx=memoi[i][j];
//				if (p[idx]==0) continue;
//				else break;
//			}
//			
//			p[idx]--;
//			comb (tgtIdx+1, i+1, sum+gate[idx].dist[i]);
//			p[idx]++;
//			comb (tgtIdx, i+1, sum);
//	
//		}
//	}
	
	static void comb (int tgtIdx, int srcIdx) {
		if (tgtIdx==R) {
			select();
			return ;
		}
		
		if (srcIdx==N+1) return ;
		selected[srcIdx]=true;
		comb (tgtIdx+1, srcIdx+1);
		selected[srcIdx]=false;
		comb (tgtIdx, srcIdx+1);
	}
	
	static void select () {
		
		int sum=0;
		int[] p=new int[3];
		p[0]=gate[0].people;
		p[1]=gate[1].people;
		p[2]=gate[2].people;
	
		//1자리부터 N자리까지 탐색
		for (int i=1; i<=N; i++) {
			if (selected[i]==false) continue;
			
			for (int j=0; j<3; j++) {
				int idx=memoi[i][j];
				if (p[idx]==0) continue;
				
				p[idx]--;
				sum+=gate[idx].dist[i];
				break;
			}
			
			if (sum>=ans) return;
		}
		
		if (sum<ans) ans=sum;
	}
}
