package recursion;

import java.util.Scanner;

public class BOJ2023 {

	static int N;
	static int[] num;
	
	public static void main(String[] args) {
		Scanner sc=new Scanner (System.in);
		N=sc.nextInt();
		num=new int[N];
		
		make_num(0);
	}
	
	
	private static void make_num (int idx) {
		
		if (idx==N) {
			System.out.println(make_origin_num(N-1));
			return ;
		}
		
		for (int i=1; i<=9; i++) {
			num[idx]=i;
			if (isPrimeNumber(make_origin_num(idx)))
				make_num(idx+1);
		}
		
	}
	
	private static int make_origin_num (int idx) {
		int number=0;

		int p=0;
		for (int i=idx; i>=0; i--) {
			number+=num[i]*Math.pow(10, p);
			p++;
		}
		
		return number;
	}
	
	private static boolean isPrimeNumber (int n) {
		if (n<2) return false;
		
		for (int i=2; i<=Math.sqrt(n); i++) {
			if (n%i==0) return false;
		}
		return true;
	}
}
