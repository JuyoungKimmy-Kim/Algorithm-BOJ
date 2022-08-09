package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 입력을 받으면서 처리할 수 있는 문제는 반드시 그렇게 처리하도록 
// ==> 입력을 위한 자료구조를 만들 필요 X
public class BOJ1244 {

	static int N,M;
	static int[] Switch;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		N=Integer.parseInt(br.readLine());
		Switch=new int[N+1];
		
		st=new StringTokenizer (br.readLine(), " ");
		for (int i=1; i<=N; i++) 
			Switch[i]=Integer.parseInt(st.nextToken());
		
		M=Integer.parseInt(br.readLine());
		for (int i=0; i<M; i++) {
			st=new StringTokenizer (br.readLine(), " ");
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			
			if (a==1) boy_change(b);
			else if (a==2) girl_change(b);
		}
		
		for (int i=1; i<=N; i++) {
			System.out.printf("%d ", Switch[i]);
			if (i%20==0)
				System.out.println();
		}

	}
	
	static void boy_change (int n) {
		int idx=1;
		
		//내 풀이
		while (n*idx<=N) {
			Switch[n*idx]=Switch[n*idx]==0 ? 1:0;
			idx++;
		}	
	}
	
	//자기가 받으 번호의 배수를 바꿔야 한다
	static void male (int n) {
		
		/*
		//교수님 풀이 -> 삼항 연산자 사용
		for (int i=1; i<=N; i++) {
			if (i%n==0) {
				Switch[i]=Switch[i]==0 ? 1:0;
			}
		}
		
		*/
		
		//개선된 풀이
		for (int i=n; i<=N; i+=n ) 
			Switch[i]=Switch[i]==0 ? 1:0;
	}
	
	static void girl_change (int n) {
		
		int prev=n;
		int next=n;

		while (true) {
			
			if (prev<1 || next>N || Switch[prev]!=Switch[next]) break;
			Switch[prev]=Switch[next]=Switch[next]==0 ? 1 : 0;
			prev--; next++;
		}
	}
	
	static void female (int n) {
		//교수님 풀이
		
		Switch[n]= Switch[n]==0 ? 1 : 0;
		
		/*
		int cnt=1;
		while (n-cnt>=1 && n+cnt<=N) {
			if (Switch[n-cnt]==Switch[n+cnt]) {
				Switch[n-cnt]=Switch[n-cnt]==0 ? 1 : 0;
				Switch[n+cnt]=Switch[n+cnt]==0 ? 1 : 0;
				
			}else break;
		}
		
		*/
		
		int left=n-1;
		int right=n+1;
		
		while (left>=1 && right<=N) {
			if (Switch[left]==Switch[right]) {
				Switch[right]=Switch[left]=Switch[left]==0 ? 1 : 0;
				
			}else break; 
			left--;
			right++;
		}

	}

}
