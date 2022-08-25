package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1717 {

	static int N,M;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		parents=new int[N+1];
		for (int i=0; i<=N; i++)
			parents[i]=i;
		
		for (int i=0; i<M; i++) {
			st=new StringTokenizer (br.readLine());
			int op=Integer.parseInt(st.nextToken());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			
			if (op==0) union(a,b);
			else if (op==1) {
				if (a==b || find(a)==find(b))
					System.out.println("YES");
				else
					System.out.println("NO");
			}
		}
		
	}
	
	static int find (int u) {
		if (parents[u]==u) return u;
		else return find(parents[u]);
	}
	
	
	static boolean union (int u, int v) {
		u=find(u);
		v=find(v);
		
		if (u!=v) {
			if (u>v) parents[u]=v;
			else parents[v]=u;
			
			return true;
		}
		else return false;
	}

}
