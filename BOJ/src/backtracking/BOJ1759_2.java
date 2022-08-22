package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ1759_2 {

	static final char[] mo= {'a', 'e', 'i', 'o', 'u'};
	
	static int L,C;
	static char[] src, tgt;
	static boolean[] isMo;
	
	static Set <String> set=new TreeSet<>();
	
	static StringBuilder sb=new StringBuilder ();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		L=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		
		src=new char[C];
		tgt=new char[L];
		isMo=new boolean[C];
		
		st=new StringTokenizer (br.readLine());
		
		for (int i=0; i<C; i++) {
			src[i]=st.nextToken().charAt(0);
			
			if (check (src[i])) isMo[i]=true; 
 		}
		
		Arrays.sort(src);
		comb (0,0,0,0);
		System.out.println(sb.toString());
		
	}
	
	static void comb (int srcIdx, int tgtIdx, int moCnt, int jaCnt) {
		
		if (tgtIdx==L) {
			if (moCnt>=1 && jaCnt>=2) {
				for (int i=0; i<L; i++) {
					sb.append(tgt[i]);
				}
				sb.append('\n');
			}
			
			return ;
		}
		
		if (srcIdx==C) return ;
		
		tgt[tgtIdx]=src[srcIdx];
		
		if (isMo[srcIdx]) {
			comb (srcIdx+1, tgtIdx+1, moCnt+1, jaCnt);
		}else {
			comb (srcIdx+1, tgtIdx+1, moCnt, jaCnt+1);
		}
		
		comb (srcIdx+1, tgtIdx, moCnt, jaCnt);
	}
	
	
	static boolean check (char c) {
		for (int i=0; i<mo.length; i++) { 	//a,e,i,o,u 중에서
			if (mo[i]==c)					//현재 들어온 알파벳과 같은 것이 있다면
				return true;				//true return
		}
		return false;						//그렇지 않다면 false return
	}

}
