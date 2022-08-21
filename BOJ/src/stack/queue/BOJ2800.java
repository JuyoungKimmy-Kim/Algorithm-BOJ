package stack.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

class Bracket {
	int open;
	int close;
	
	Bracket (int open, int close) {
		this.open=open;
		this.close=close;
	}
}

public class BOJ2800 {

	static String str;
	static List<Bracket> brList=new ArrayList<>();
	static boolean[] removed;
	static Set <String> set=new TreeSet<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		Stack <Integer> stack=new Stack<>();
		
		str=br.readLine();
		removed=new boolean[str.length()];
		
		// bracket 쌍 만들기
		for (int i=0; i<str.length(); i++) {
			if (str.charAt(i)=='(') 
				stack.add(i);
			else if (str.charAt(i)==')') 
				brList.add(new Bracket (stack.pop(), i));
		}
		
		comb (0);
		Iterator it=set.iterator();
		while (it.hasNext()) 
			System.out.println(it.next());
		
	}
	
	static void comb (int tgtIdx) {
		if (tgtIdx==brList.size()) {
			
			StringBuilder sb=new StringBuilder ();
			for (int i=0; i<str.length(); i++) {
				if (!removed[i])
					sb.append(str.charAt(i));
			}
			
			if (!sb.toString().equals(str)) 
				set.add(sb.toString());
			
			return ;
		}
		
		
		int open=brList.get(tgtIdx).open;
		int close=brList.get(tgtIdx).close;
		
		removed[open]=true;
		removed[close]=true;
		comb (tgtIdx+1);
		
		removed[open]=false;
		removed[close]=false;
		comb (tgtIdx+1);
	}

}
