package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BOJ2023_4 {

	static int N;
	static Deque<Item> stack=new ArrayDeque<>();
	static StringBuilder sb=new StringBuilder ();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		N=Integer.parseInt(br.readLine());
		
		//search (index, num)
		//어차피 맨 앞자리가 소수가 아니면 출발할 수 없음
		
		search(2);
		search(3);
		search(5);
		search(7);
		
		System.out.println(sb.toString());
	}
	
	static void search (int num) {

		stack.push(new Item(num, 1));
		
		while (!stack.isEmpty()) {
			
			Item item=stack.pop();
			
			if (item.idx==N) {
				sb.append(item.n).append("\n");
				continue;
			}
			
			int nextNum=nextNum(item.n,1);
			if (isPrime(nextNum)) stack.add(new Item(nextNum, item.idx+1));
			
			nextNum=nextNum(item.n,3);
			if (isPrime(nextNum)) stack.add(new Item(nextNum, item.idx+1));
			
			nextNum=nextNum(item.n,7);
			if (isPrime(nextNum)) stack.add(new Item(nextNum, item.idx+1));
			
			nextNum=nextNum(item.n,9);
			if (isPrime(nextNum)) stack.add(new Item(nextNum, item.idx+1));
			
		}		
	}
	
	static boolean isPrime (int num) {
		if (num==1) return false;
		int sqrt=(int)Math.sqrt(num);
		for (int i=2; i<=sqrt; i++) {
			if (num%i==0) return false;
		}
		return true;
	}
	
	static int nextNum(int num, int next) {
		return num*10+next;
	}
	
	
	static class Item {
		int n;
		int idx;
		
		public Item (int n, int idx) {
			this.n=n;
			this.idx=idx;
		}

		@Override
		public String toString() {
			return "Item [n=" + n + ", idx=" + idx + "]";
		}
		
		
	}
}
