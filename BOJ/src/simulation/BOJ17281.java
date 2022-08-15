package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17281 {

	static int N, ans;
	static int[][] score;
	static int[] order=new int[9];
	static boolean[] selected=new boolean[9];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in)); 
		StringTokenizer st=null;
		
		N=Integer.parseInt(br.readLine());
		score=new int[N][9];
		
		//1번 선수 -> 4번 타자로 미리 지정
		selected[0]=true;
		order[3]=0;
		
		for (int i=0; i<N; i++) {
			st=new StringTokenizer (br.readLine());
			
			for (int j=0; j<9; j++)
				score[i][j]=Integer.parseInt(st.nextToken());
		}
		
		perm(0);
		System.out.println(ans);
	}
	
	private static void perm (int idx) {
		if (idx==9) {
			play();
			return ;
		}
		
		for (int i=0; i<9; i++) {
			
			if (idx==3) {
				perm(idx+1);
				continue;
			}
			
			if (selected[i]) continue;
			
			selected[i]=true;
			order[idx]=i;
			perm(idx+1);
			selected[i]=false;
		}
	}
	
	private static boolean move (boolean[] ground) {
		boolean flag=false;
		
		for (int i=3; i>=0; i--) {
			if (ground[i]) {
				if (i==3) {
					ground[3]=false;
					flag=true;
				}
				else {
					ground[i+1]=true;
					ground[i]=false;
				}
			}
		}
		
		return flag;
	}
	
	private static void play () {
		
//		for (int n : order)
//			System.out.print(n+" ");
//		System.out.println();
		
		int count=0;
		int no=0;
		
		for (int i=0; i<N; i++) {
			int out=0;
			
			boolean[] ground=new boolean[4]; //0-home, 1-1루, 2-2루, 3-3루
			
			while (true) {
				
				int sco=score[i][order[no]];
				
				if (sco==0) 
					out++;
				else {
					ground[0]=true;
					for (int k=0; k<sco; k++) {
						boolean flag=move(ground);
						if (flag) count++;
					}
				}
				
				no=(no+1)%9;
				if (out==3) break;
			}
		}
		
		//System.out.println(count);
		
		if (count>ans) ans=count;
	}
}
