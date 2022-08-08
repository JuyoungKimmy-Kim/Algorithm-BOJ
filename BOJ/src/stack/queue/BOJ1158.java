package stack.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1158 {
	static int N, K;
	static List<Integer> node=new ArrayList<>();;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		StringBuilder sb=new StringBuilder ();
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());

		for (int i=1; i<=N; i++)
			node.add(i);
		
		sb.append("<");
		
		int idx=K-1;
		while (true) {
			
			if (node.size()==1) {
				sb.append(node.get(0)).append(">");
				break;
			}
			
			sb.append(node.get(idx)).append(", ");
			node.remove(idx);
			idx+=(K-1);
			
			if (idx>=node.size()) 
				idx%=node.size();
		}
		System.out.println(sb.toString());
	}

}
