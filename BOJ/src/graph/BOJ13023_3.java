package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ13023_3 {

	static int N,M;
	static Friend[] friends;
	static boolean done;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		friends=new Friend[N];			//배열 생성
		for (int i=0; i<N; i++)	
			friends[i]=new Friend(i);	//배열 각 항목에 Friend 객체 생성 연결
		
		for (int i=0; i<M; i++) {
			st=new StringTokenizer (br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			
			friends[a].list.add(new Friend (b));
			friends[b].list.add(new Friend (a));
		}

		for (int i=0; i<N; i++)
			dfs (i,0, 1<<i);
		
		if (done) System.out.println(1);
		else System.out.println(0);
	}
	
	static void dfs (int num, int cnt, int visit) {
		
		if (done) return ;
		if (cnt==4) {
			done=true;
			return ;
		}
		
		friends[num].list.forEach((f)-> {
			if ((visit & (1<<f.num))==0) {
				dfs (f.num, cnt+1, (visit | 1<<f.num));
			}
		});
	}
	
	//정점
	static class Friend {
		int num;		//해당 정점 번호
		List<Friend> list=new ArrayList<>();
		
		public Friend (int num) {
			this.num=num;
		}
	}
}
