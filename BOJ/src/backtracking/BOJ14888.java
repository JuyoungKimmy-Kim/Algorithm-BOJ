package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14888 {

	static int N, sum, max=Integer.MIN_VALUE, min=Integer.MAX_VALUE;
	static int[] num;
	static int[] op=new int[4]; //0:+, 1:-, 2:x, 3:/
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;

		N=Integer.parseInt(br.readLine());
		num=new int[N];
		
		st=new StringTokenizer (br.readLine());
		for (int i=0; i<N; i++)
			num[i]=Integer.parseInt(st.nextToken());
		
		st=new StringTokenizer (br.readLine());
		for (int i=0; i<4; i++) 
			op[i]=Integer.parseInt(st.nextToken());
		
		dfs (0, num[0]);
		System.out.println(max);
		System.out.println(min);
	}
	
	static void dfs (int tgtIdx, int sum) {
		
		//연산자 N-1 개를 모두 선택한 경우
		if (tgtIdx==N-1) {
			max=Math.max(max, sum);
			min=Math.min(min, sum);
			
			return ;
		}
		
		for (int i=0; i<4; i++) {
			if (op[i]==0) continue;
			
			op[i]--;
			switch (i) {
			case 0: dfs (tgtIdx+1, sum+num[tgtIdx+1]); break;
			case 1: dfs (tgtIdx+1, sum-num[tgtIdx+1]); break;
			case 2: dfs (tgtIdx+1, sum*num[tgtIdx+1]); break;
			case 3: dfs (tgtIdx+1, sum/num[tgtIdx+1]); break;
			}
			op[i]++;
		}
		
	}

}
