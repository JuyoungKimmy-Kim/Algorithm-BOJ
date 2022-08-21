package backtracking;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/*
 * 중복 조합
 * IXXX == XXXI 결과 값이 같기 때문에 중복은 허용하되, 순서는 신경쓰지 않는 중복 조합
 */

public class BOJ16922_2 {

	static int N, ans;
	static int[] tgt;
	static int[] src= {1,5,10,50};

	static Set<Integer> set=new HashSet<>();
	
	public static void main(String[] args) {
		Scanner sc=new Scanner (System.in);
		N=sc.nextInt();
		tgt=new int[N];
		
		dfs(0,0);
		System.out.println(set.size());
	}
	
	private static void dfs (int tgtIdx, int srcIdx) {
		
		if (tgtIdx==N) {
			int sum=0;
			for (int n : tgt)
				sum+=n;
			set.add(sum);
			return ;
		}
		
		if (srcIdx==4) return ;
		
		tgt[tgtIdx]=src[srcIdx];
		dfs (tgtIdx+1, srcIdx);
		dfs (tgtIdx, srcIdx+1);
	}
}
