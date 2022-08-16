package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2961_3 {

	static int N;
	static int[][] src;
	static int min=Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		N=Integer.parseInt(br.readLine());
		src=new int[N][2];
		
		for (int i=0; i<N; i++) {
			StringTokenizer st=new StringTokenizer (br.readLine());
			src[i][0]=Integer.parseInt(st.nextToken());
			src[i][1]=Integer.parseInt(st.nextToken());
		}
		
		int count= 1<<N; //0 0 0 1 0 0 0 0
		
		//i==0 -> 0 0 0 0 0 0 0 0
		//i==1 -> 0 0 0 0 0 0 0 1
		//i==2 -> 0 0 0 0 0 0 1 0
		//i==3 -> 0 0 0 0 0 0 1 1
		//i==4 -> 0 0 0 0 0 1 0 0 
		
		for (int i=1; i<count; i++) { //재료는 한 가지 이상 선택
			// 각각 부분 집합에서 어떤 원소가 선택/비선택 -> i가 select 역할
			
			int sin=1;
			int ssn=0;
			
			for (int j=0; j<N; j++) {
				if ((i & 1<<j)!=0) {
					sin*=src[j][0];
					ssn+=src[j][1];
					
					System.out.print(j+" ");
				}
			}
			System.out.println();
			min=Math.min(min, Math.abs(sin-ssn));
		}	
		System.out.println(min);	
	}
}
