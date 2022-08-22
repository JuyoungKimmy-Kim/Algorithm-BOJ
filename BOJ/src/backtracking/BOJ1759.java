package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ1759 {

	static final char[] mo= {'a', 'e', 'i', 'o', 'u'};
	
	static int L,C;
	static char[] src, tgt;
	
	static Set <String> set=new TreeSet<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		L=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		
		src=new char[C];
		tgt=new char[L];
		
		st=new StringTokenizer (br.readLine());
		
		for (int i=0; i<C; i++)
			src[i]=st.nextToken().charAt(0);
		
		Arrays.sort(src);
		comb (0,0,0,0);
		
		Iterator it=set.iterator();
		while (it.hasNext())
			System.out.println(it.next());
	}
	
	static void comb (int tgtIdx, int srcIdx, int mSum, int jSum) {
		
		if (tgtIdx==L) {
			StringBuilder sb=new StringBuilder ();
			if (mSum>=1 && jSum>=2) {
				for (char c : tgt)
					sb.append(c);
				set.add(sb.toString());
			}
			
			return ;
		}
		
		if (srcIdx==C) return ;
		
		tgt[tgtIdx]=src[srcIdx];
		
		if (check(src[srcIdx])) {
			comb (tgtIdx+1, srcIdx+1, mSum+1, jSum);
		}
		else comb (tgtIdx+1, srcIdx+1, mSum, jSum+1);
		
		comb (tgtIdx, srcIdx+1, mSum, jSum);
	}
	
	
	static boolean check (char c) {
		for (int i=0; i<mo.length; i++) { 	//a,e,i,o,u 중에서
			if (mo[i]==c)					//현재 들어온 알파벳과 같은 것이 있다면
				return true;				//true return
		}
		return false;						//그렇지 않다면 false return
	}

}
