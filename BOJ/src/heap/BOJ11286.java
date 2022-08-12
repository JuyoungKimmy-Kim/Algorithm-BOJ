package heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ11286 {

	static int N;
	static int cnt;
	
	//절대값이 가장 작은 값 출력, 여러개 -> 가장 작은 수 출력
	static PriorityQueue <Integer> pq=new PriorityQueue <>(
			(n1, n2)-> Math.abs(n1)==Math.abs(n2) ?
				n1-n2 : Math.abs(n1)-Math.abs(n2)	);
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		
		N=Integer.parseInt(br.readLine());
		
		for (int i=0; i<N; i++) {
			int n=Integer.parseInt(br.readLine());
			if (n==0) {
				Integer min=pq.poll();
				System.out.println(min==null ? 0 : min);
			}
			else pq.offer(n);
		}
	}
}
