package stack.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ1935 {

	static int N;
	static int[] num;
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		Stack <Double> stack=new Stack<>();
		
		N=Integer.parseInt(br.readLine());
		
		String str=br.readLine();
		num=new int[N];
		
		for (int i=0; i<N; i++)
			num[i]=Integer.parseInt(br.readLine());
		
		for (int i=0; i<str.length(); i++) {
			char c=str.charAt(i);
			
			if (c>='A' && c<='Z') 
				stack.push((double) num[c-'A']);
			else {
				double r=stack.pop();
				double l=stack.pop();
				
				switch (c) {
				case '+' : stack.push(l+r); break;
				case '-' : stack.push(l-r); break;
				case '*' : stack.push(l*r); break;
				case '/' : stack.push(l/r); break;
				}
			}
		}
		System.out.printf("%.2f\n", stack.pop());
	}

}
