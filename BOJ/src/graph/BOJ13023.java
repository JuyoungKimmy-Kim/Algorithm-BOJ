package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ13023 {

	static int N,M;
	static List<Integer> adj[];
	static boolean flag=false;
	static boolean[] checked;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		checked=new boolean[N];
		adj=new ArrayList [N];
		for (int i=0; i<N; i++)
			adj[i]=new ArrayList<>();
		
		for (int i=0; i<M; i++) {
			st=new StringTokenizer (br.readLine());
			int from=Integer.parseInt(st.nextToken());
			int to=Integer.parseInt(st.nextToken());
			
			adj[from].add(to);
			adj[to].add(from);
		}
		
		for (int i=0; i<N; i++) {
			if (flag) break;
			checked[i]=true;
			dfs (1, i);
			checked[i]=false;
		}
		
		if (flag) System.out.println(1);
		else System.out.println(0);
	}
	
	static void dfs (int cnt, int idx) {
		if (cnt==5) {
			flag=true;
			return ;
		}
		
		for (int i=0; i<adj[idx].size(); i++) {
			int next=adj[idx].get(i);
			
			if (checked[next]) continue;
			
			checked[next]=true;
			dfs (cnt+1, next);
			if (flag) return;
			checked[next]=false;
		}
	}
}
