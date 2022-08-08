package stack.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1158_3 {
	static int N, K;
	static List<Integer> node=new ArrayList<>();;
	static int[] input;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		StringBuilder sb=new StringBuilder ();
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		input=new int[N+1]; 
		for (int i=0; i<N; i++)
			input[i]=i+1;
		
		int count=0; //죽은 사람이 결정되면 하나씩 증가
		int idx=1; // 순회하는 index, 죽은 사람이 결정되면 그 사람 input[idx]=0
		int step=1; // 살아있는 사람만 계산하기 위해

		sb.append("<");
		while (true) {
			if (count==N) break;
			
			int newIdx=idx%N;
			
			if (input[newIdx]!=0) {
				if (step % K ==0) { //살아있는 사람 중에 K번 째
					sb.append(input[newIdx]).append(", ");
					input[newIdx]=0;
					count++;
				}
				step++;
			}
			
			idx++;
			//if (idx>N) idx=1;
		}
		
		sb.setLength(sb.length()-2);
		sb.append(">");
		
		System.out.println(sb.toString());
	}
}
