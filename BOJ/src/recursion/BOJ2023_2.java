package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2023_2 {

	static int N;
	static StringBuilder sb=new StringBuilder ();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		N=Integer.parseInt(br.readLine());
		
		//search (index, num)
		//어차피 맨 앞자리가 소수가 아니면 출발할 수 없음
		
		search(1,2);
		search(1,3);
		search(1,5);
		search(1,7);
		
		System.out.println(sb.toString());
	}
	
	// idx: 자리수
	// 현재 자리수의 수가 소수이면 다음 자리수로 재귀호출
	// 다음 자리수는 선택해서 진행 (1,3,7,9) <= 짝수 와 5제외
	static void search (int idx, int num) {
		//기저조건
		if (idx==N) {
			//complete code
			sb.append(num).append("\n");
			return ;
		}
		
		//소수인지 따져보고 맞으면 재귀호출
		//2 다음자리 1을 따지기 위해서 21을 만드는 건 2를 문자열로 바꾼 뒤 +1하고 문자열 -> 다시 숫자로 전환
		
		int nextNum=Integer.parseInt(String.valueOf(num)+1);
		if (isPrime(nextNum)) search(idx+1, nextNum);
		
		nextNum=Integer.parseInt(String.valueOf(num)+3);
		if (isPrime(nextNum)) search(idx+1, nextNum);
		
		nextNum=Integer.parseInt(String.valueOf(num)+7);
		if (isPrime(nextNum)) search(idx+1, nextNum);
		
		nextNum=Integer.parseInt(String.valueOf(num)+9);
		if (isPrime(nextNum)) search(idx+1, nextNum);
		
	}
	
	static boolean isPrime (int num) {
		if (num==1) return false;
		int sqrt=(int)Math.sqrt(num);
		for (int i=2; i<=sqrt; i++) {
			if (num%i==0) return false;
		}
		return true;
	}
}
