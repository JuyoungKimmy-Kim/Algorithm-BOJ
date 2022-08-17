package samsungA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ16637 {

	static int N, bracket, ans;
	static String str;

	static boolean[] selected;

	static Queue<Character>queue=new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		N=Integer.parseInt(br.readLine());
		selected=new boolean[N];
		str=br.readLine();
		bracket=N/2-1; //추가할 수 있는 최대 bracket 쌍 수 (제일 처음 쌍 빼고)
		
		if (N==1) {
			System.out.println(str.charAt(0)-'0');
			return ;
		}
		
		dfs (0,2);
		System.out.println(ans);
		
	}
	
	/*N=9
	 * 3 + 8 * 7 - 9 * 2
	 * 0 1 2 3 4 5 6 7 8
	 */
	
	
	private static void dfs (int cnt, int idx) {
		
		//매번 식 검사
		calc();
		
		//추가할 수 있는 최대 괄호 쌍의 개수를 넘거나, 더 이상 괄호를 추가할 수 없는 위치에 도달했을 때
		if (bracket==N+1 || idx>N-3) return ;

		//bracket 추가
		selected[idx]=true;
		dfs (cnt+1, idx+4);
		
		//bracket 추가하지 않았을 때 
		selected[idx]=false;
		dfs (cnt, idx+2);
		
	}
	
	private static void calc () {
		
		for (int i=0; i<N; i++) {
			if (selected[i]) {
				int left=str.charAt(i++)-'0';
				char op=str.charAt(i++);
				int right=str.charAt(i)-'0';
				
				switch (op) {
				case '+' : queue.add((char) (left+right)); break;
				case '-' : queue.add((char) (left-right)); break;
				case '×' : queue.add((char) (left*right)); break;
				}
			}
			else queue.add(str.charAt(i));
		}
		
		int sum=queue.poll()-'0';
		while (!queue.isEmpty()) {
			
			char op= queue.poll();
			int right=queue.poll()-'0';
			
			switch (op) {
			case '+' : sum+=right; break;
			case '-' : sum-=right; break;
			case '*' : sum*=right; break;
			}
		}
		
		if (sum>ans) ans=sum;
	}

}
