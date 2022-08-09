package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// �Է��� �����鼭 ó���� �� �ִ� ������ �ݵ�� �׷��� ó���ϵ��� 
// ==> �Է��� ���� �ڷᱸ���� ���� �ʿ� X
// Switch : int -> boolean

public class BOJ1244_2 {

	static int N,M;
	static boolean[] Switch;
	static int gender, n;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		N=Integer.parseInt(br.readLine());
		Switch=new boolean[N+1];
		
		st=new StringTokenizer (br.readLine(), " ");
		for (int i=1; i<=N; i++) {
			if (st.nextToken().equals("1")) //false�� boolean default
				Switch[i]=true;
		}
		
		
		M=Integer.parseInt(br.readLine());
		for (int i=0; i<M; i++) {
			st=new StringTokenizer (br.readLine(), " ");
			gender=Integer.parseInt(st.nextToken());
			n=Integer.parseInt(st.nextToken());
			
			if (gender ==1) male();
			else if (gender==2) female();
		}
		
		for (int i=1; i<=N; i++) {
			System.out.print(Switch[i]? 1 : 0);
			if (i%20==0)
				System.out.println();
			else System.out.print(" ");
		}

	}
	
	static void male () {

		for (int i=n; i<=N; i+=n ) 
			Switch[i]=!Switch[i]; //�ݴ��
	}
	
	
	static void female () {

		Switch[n]= !Switch[n];
		
		int left=n-1;
		int right=n+1;
		
		while (left>=1 && right<=N) {
			if (Switch[left]==Switch[right]) {
				Switch[right]=!Switch[right];
				Switch[left]=!Switch[left];
				
			}else break; 
			left--;
			right++;
		}

	}

}
