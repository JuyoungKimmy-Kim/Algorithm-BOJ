package stack.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class BOJ2164 {

	static int N;
	static Deque<Integer> deque= new ArrayDeque<>();
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		
		for (int i=N; i>=1; i--) 
			deque.push(i);
		
//		for (int i=1; i<=N; i++)
//			deque.add(i); //addLast
		
		//remove--> first
		
		while (deque.size()!=1) {
			deque.pollFirst();
			
			if (deque.size()==1) break;
			int n=deque.pollFirst();
			deque.addLast(n);
		}
		
		System.out.println(deque.poll());
	}
}
