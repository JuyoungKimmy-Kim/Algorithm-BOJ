package recursion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1991 {

	static int N;
	static char[][] adj;
	static char[] ret;
	static int cnt=0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		N=Integer.parseInt(br.readLine());
		adj=new char[N][2];
		ret=new char[2*N];
		

		for (int i=0; i<N; i++) {
			StringTokenizer st=new StringTokenizer (br.readLine());
			char root=st.nextToken().charAt(0);
			char left=st.nextToken().charAt(0);
			char right=st.nextToken().charAt(0);
			
			adj[root-'A'][0]=left;
			adj[root-'A'][1]=right;
		}
			ret[cnt++]='A';
			preorder (0);
			print();
			
			cnt=0;
			
	}
	static void preorder (int n) {
		
		if (adj[n][0]!='.') {
			ret[cnt++]=adj[n][0];
			preorder(adj[n][0]-'A');
		}
		
		if (adj[n][1]!='.') {
			ret[cnt++]=adj[n][1];
			preorder (adj[n][1]-'A');
		}
	}
	
	static void inorder (int n) {
		if (adj[n][0]!='.') {
			preorder(adj[n][0]-'A');
		}
		
		ret[cnt++]=(char) ('A'+n);
		
		if (adj[n][1]!='.') {
			
		}
	}
	
	static void print () {
		for (int i=0; i<cnt; i++)
			System.out.print(ret[i]);
		System.out.println();
	}

}
