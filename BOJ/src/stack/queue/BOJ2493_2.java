package stack.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ2493_2 {

	static int N;
	static Deque<int[]> stack=new ArrayDeque<>();
	static StringBuilder sb=new StringBuilder ();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		N=Integer.parseInt(br.readLine());
		st=new StringTokenizer (br.readLine());
		
		
		//index : 자리, 숫자 : height
		for (int i=1; i<=N; i++) {
			int height=Integer.parseInt(st.nextToken());
			//나보다 큰 넘 있으면!!
			while (!stack.isEmpty()) {
				//나보다 큰 수가 있으면 꾸뻑하고 큰 수의 index를 sb에 더하고 while을 끝냄
				
				if (stack.peek()[1] >=height) {
					sb.append(stack.peek()[0]).append(" ");
					break;
				}
				
				//나보다 작은 수를 만나면 (너 꺼져!!) stack에서 제거 <= 자료의 수 감소
				stack.pop();
			}
			
			if (stack.isEmpty())
				sb.append("0 ");
			
			stack.push(new int[] {i, height});
		}
		
		System.out.println(sb.toString());
	}

}
