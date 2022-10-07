package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17471 {

	static int N, ans=Integer.MAX_VALUE;
	static int[] population;
	static boolean[][] adj;
	
	static boolean[] selected;
	static List<Integer> red, blue;
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		N=Integer.parseInt(br.readLine());
		population=new int[N+1];
		
		adj=new boolean[N+1][N+1];
		
		selected=new boolean[N+1];
		red=new ArrayList<>();
		blue=new ArrayList<>();
		
		st=new StringTokenizer (br.readLine());
		for (int i=1; i<=N; i++)
			population[i]=Integer.parseInt(st.nextToken());
		
		for (int i=1; i<=N; i++) {
			st=new StringTokenizer (br.readLine());
			int n=Integer.parseInt(st.nextToken());
			
			for (int j=0; j<n; j++) {
				adj[i][Integer.parseInt(st.nextToken())]=true;
			}
		}
		
		subSet(1);
		
		if (ans==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
	}
	
	static void subSet (int tgtIdx) {
		if (tgtIdx==N+1) return ;
		
		red.clear(); blue.clear();
		for (int i=1; i<=N; i++) {
			if (selected[i]) {
				red.add(i);
			} else blue.add(i);
		}
		
		if (isConnected (red,true) && isConnected(blue, false)) {
			int redSum=0; int blueSum=0;
			for (int i=0; i<red.size(); i++) redSum+=population[red.get(i)];
			for (int i=0; i<blue.size(); i++) blueSum+=population[blue.get(i)];
			
			ans=Math.min(ans, Math.abs(redSum-blueSum));
		}	
		
		selected[tgtIdx]=true;
		subSet (tgtIdx+1);
		
		selected[tgtIdx]=false;
		subSet (tgtIdx+1);
	}
	
	static boolean isConnected (List<Integer>area, boolean flag) {
		
		if (area.size()==0) return false;
		
		Queue<Integer> queue=new ArrayDeque<>();
		queue.add(area.get(0));
		
		boolean[]  v=new boolean[N+1];
		v[area.get(0)]=true;
		int cnt=1; 
		
		while (!queue.isEmpty()) {
			int n=queue.poll();
			
			for (int i=1; i<=N; i++) {
				if (i==n || v[i] ) continue;
				if (adj[n][i] && selected[i]==flag) {
					cnt++;
					v[i]=true;
					queue.add(i);
				}
			}
		}
		
		if (cnt==area.size()) return true;
		return false;
	}

}
