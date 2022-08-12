package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ15686_2 {
	
	static int N,M;
	static int min=Integer.MAX_VALUE;

	static List	<int[]> house, src,tgt;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		house=new ArrayList<>();
		src=new ArrayList<>(); //전체 치킨집
		tgt=new ArrayList<>(); //선택된 치킨집
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		// input 
		for (int i=0; i<N; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<N; j++) {
				int n=Integer.parseInt(st.nextToken());
				
				if (n==1) house.add(new int[] {i,j});
				else if (n==2) src.add(new int[] {i,j});
			}
		}
		
		// M개 고르기
		comb(0,0);
		System.out.println(min);
	}
	
	private static void comb (int  srcIdx, int tgtIdx) {
		
		if (tgtIdx==M) {
			
			//현재 치킨 조합을 기준으로 모든 집에서 최단거리 치킨집을 계산해서 모두 더함
			
			int sum=0;
			int size=house.size();
			
			for (int i=0; i<size; i++) {
				int dist=Integer.MAX_VALUE;
				int[] h=house.get(i); // 이 집으로부터 선택된 M개의 치킨집의 거리 계산
				
				for (int j=0; j<M; j++) {
					int[] c=tgt.get(j);
					
					dist=Math.min (dist, Math.abs(h[0]-c[0]) + Math.abs(h[1]-c[1]));
				}
				sum+=dist; //현재 M개 선택의 치킨 거리의 누적 합	
			}
			
			min=Math.min(min, sum);
			return ;
		}
		
		if (srcIdx==src.size()) return ;
		tgt.add(src.get(srcIdx));
		comb (srcIdx+1, tgtIdx+1);
		tgt.remove(src.get(srcIdx));
		comb (srcIdx+1, tgtIdx);	
	}
}
