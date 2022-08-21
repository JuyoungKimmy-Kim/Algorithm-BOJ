package simulation;

import java.util.Scanner;

public class BOJ3040 {

	static int[] src=new int[9];
	static int[] tgt=new int[7];
	static boolean find=false;
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner (System.in);
		
		for (int i=0; i<9; i++)
			src[i]=sc.nextInt();

		comb (0,0,0);
	}
	
	static void comb (int srcIdx, int tgtIdx, int sum) {
		
		if (tgtIdx==7 && sum==100) {
			StringBuilder sb=new StringBuilder ();
			for (int i=0; i<7; i++) {
				sb.append(tgt[i]).append("\n");
			}
			sb.deleteCharAt(sb.length()-1);
			System.out.println(sb.toString());
			find=true;
			return ;
		}
		if (tgtIdx==7) return ;
		
		for (int i=srcIdx; i<9; i++) {
			
			if (sum+src[i]>100) continue;
			tgt[tgtIdx]=src[i];
			comb (i+1, tgtIdx+1, sum+src[i]);
			if (find) return ;
		}
	}

}
