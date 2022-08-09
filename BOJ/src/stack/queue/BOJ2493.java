package stack.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2493 {

	static int N;
	static int[] pre_tower;
	static int[] tower;
	static Stack <Integer> stack=new Stack<>();
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		N=Integer.parseInt(br.readLine());
		tower=new int[N+1];
		pre_tower=new int[N+1];
	
		
		st=new StringTokenizer (br.readLine());
		
//		for (int i=1; i<=N; i++) {
//			tower[i]=Integer.parseInt(st.nextToken());
//			
//			if (tower[i-1]>tower[i]) 
//				pre_tower[i]=i-1;
//			else {
//				int idx=i-1;
//				while (idx>0 && tower[idx]<tower[i]) {
//					idx--;
//				}
//				pre_tower[i]=pre_tower[++idx];
//			}
//		}
		
		
		stack.push(0);
		for (int i=1; i<=N; i++) {
			tower[i]=Integer.parseInt(st.nextToken());
			
			if (stack.isEmpty() || tower[stack.peek()]>tower[i]) {
				pre_tower[i]=stack.peek();
				stack.push(i);
				
			}
			else {
				while (!stack.isEmpty() && tower[stack.peek()]<tower[i]) {
					stack.pop();
				}
				
				if (stack.isEmpty()) pre_tower[i]=0;
				else pre_tower[i]=stack.peek();
				stack.push(i);
			}	
		}
		
		StringBuilder sb=new StringBuilder ();
		for (int i=1; i<=N; i++)
			sb.append(pre_tower[i]).append(" ");
		
		System.out.println(sb.toString());
	}

}
