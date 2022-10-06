package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9205 {
	
	static int T,N,sy,sx,ey,ex;
	static List<Node> nodeList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			N=Integer.parseInt(br.readLine());
			nodeList=new ArrayList<>();
			
			for (int i=0; i<N+2; i++) {
				st=new StringTokenizer (br.readLine());
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
			
				if (i==0) {
					sy=y; sx=x;
				} else if (i==N+1) {
					ey=y; ex=x;
				} else {
					nodeList.add(new Node (y,x));
				}
			}
			
			boolean ret=bfs();
			if (ret) System.out.println("happy");
			else System.out.println("sad");
		}
	}
	
	static boolean bfs () {
		Queue<Node> q=new ArrayDeque<>();
		q.add(new Node (sy, sx));
		
		boolean visited[]=new boolean[N];
		
		while (!q.isEmpty()) {
			Node node=q.poll();
			int y=node.y;
			int x=node.x;
			
			if (Math.abs(ey-y)+Math.abs(ex-x)<=1000) return true;
			
			for (int i=0; i<N; i++) {
				if (visited[i]) continue;
				
				int ny=nodeList.get(i).y;
				int nx=nodeList.get(i).x;
				
				int dist=Math.abs(ny-y)+Math.abs(nx-x);
				if (dist<=1000) {
					visited[i]=true;
					q.add(new Node (ny, nx));
				}
			}
		}
		
		return false;
	}
	
	static class Node {
		int y,x;
		
		Node (int y, int x) {
			this.y=y;
			this.x=x;
		}
	}

}
