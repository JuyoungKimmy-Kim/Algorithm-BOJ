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
		// ���� ����
		if (n==1) return 0;
		
		// n�� ���� �̸� ���� ���� ������ �� ���� Ȱ��
		if ( memoi[n]>0 ) return memoi[n];
		
		// ���� n�� ���ؼ� ���� ���� ����
		
		// ���� �ܰ� + 1
		memoi[n]=dp(n-1)+1; //n���� 1���� ���ڸ� ����� ����� ��
		
		if (n%2==0)
			memoi[n]=Math.min(memoi[n], dp(n/2)+1);
		
		if (n%3==0)
			memoi[n]=Math.min(memoi[n], dp(n/3)+1);
		
		return memoi[n];
	}

}
