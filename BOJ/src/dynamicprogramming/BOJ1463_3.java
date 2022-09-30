package dynamicprogramming;

import java.util.Scanner;

public class BOJ1463_3 {
	static final int INF=Integer.MAX_VALUE;
	static int X;
	static int[] memoi;
	public static void main(String[] args) {
		Scanner sc=new Scanner (System.in);
		X=sc.nextInt();
		memoi=new int[X+1];
		
		System.out.println(dp(X));
	}
	
	static int dp (int n) {
		// 기저 조건
		if (n==1) return 0;
		
		// n에 대해 미리 계산된 값이 있으면 그 값을 활용
		if ( memoi[n]>0 ) return memoi[n];
		
		// 아직 n에 대해서 계산된 값이 없다
		
		// 이전 단계 + 1
		memoi[n]=dp(n-1)+1; //n보다 1작은 숫자를 만드는 경우의 수
		
		if (n%2==0)
			memoi[n]=Math.min(memoi[n], dp(n/2)+1);
		
		if (n%3==0)
			memoi[n]=Math.min(memoi[n], dp(n/3)+1);
		
		return memoi[n];
	}

}
