package greedy;

import java.util.Scanner;

public class BOJ2839 {

	static int N;
	public static void main(String[] args) {
		
		Scanner sc=new Scanner (System.in);
		N=sc.nextInt();
		
		int i5=N/5;
		
		for (int i=i5; i>=0; i--) {
			int M=N-i*5;
			
			if (M%3==0) {
				System.out.println(i+M/3);
				return ;
			}
		}
		System.out.println(-1);
		

		//교수님 풀이
		
//		int count=0;
//		while (true) {
//			if (N<0) {
//				System.out.println(-1);
//				break;
//			}
//			if (N%5==0) {
//				System.out.println(N/5+count);
//				break;
//			}
//			else { //3을 하나 쓴다
//				count++;
//				N-=3;
//			}
//		}
		
	}
	
	// 완전 탐색 -> 시간 초과
	
//	static void comb (int five, int three) {
//		int sum=five*5+three*3;
//		
//		if (sum==N) {
//			ans=Math.min(ans, five+three);
//			return;
//		}
//		else if (sum>N) return ;
//		
//		comb (five+1, three);
//		comb(five, three+1);
//	}
}
