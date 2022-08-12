package heap;

/*
 * Priority Queue -> comparable 구현
 * 
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ11286_2 {

	static int N;
	static int cnt;
	
	//절대값이 가장 작은 값 출력, 여러개 -> 가장 작은 수 출력
	static PriorityQueue <Node> pq=new PriorityQueue <>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		
		N=Integer.parseInt(br.readLine());
		
		for (int i=0; i<N; i++) {
			int n=Integer.parseInt(br.readLine());
			if (n==0) {
				Node min=pq.poll();
				System.out.println(min==null ? 0 : min.n);
			}
			else pq.add(new Node (n));
		}
	}
	
	static class Node implements Comparable <Node> {
		int n;
		Node (int n) {this.n=n;}
		@Override
		public int compareTo(Node o) {
			if (Math.abs(n)==Math.abs(o.n)) 
				return n-o.n;
			else return Math.abs(n)-Math.abs(o.n);
		}
	}
}
