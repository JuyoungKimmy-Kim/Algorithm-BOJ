package stack.queue;

/*
 * Queue를 이용해 사용
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1158_2 {
	static int N, K;
	static List<Integer> node=new ArrayList<>();;
	static Queue <Integer> queue =new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		StringBuilder sb=new StringBuilder ();
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		for (int i=1; i<=N; i++)
			queue.offer(i);
		
		int alive=1; // 1부터 증가, 살아있는 번호에서만 증가
		sb.append("<");
		
		while (!queue.isEmpty()) {
			int num=queue.poll();
			
			if (alive % K ==0) { //K번째 해당
				sb.append(num).append(", ");
			}
			else {
				queue.offer(num);
			}
			alive++;
		}
		sb.setLength(sb.length()-2);
		sb.append(">");
		
		System.out.println(sb.toString());
	}
}
