package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9997_2 {
	
	static int N,ans;
	static int[] words;
	
	static int ALPA= (1<<26)-1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		N=Integer.parseInt(br.readLine());
		words=new int[N];
		
		for (int i=0; i<N; i++) {
			String word=br.readLine();
			
			for (int j=0; j<word.length(); j++)
				words[i] |= 1<<(word.charAt(j)-'a');
		}
		
		comb (0,0);
		System.out.println(ans);
	}
	
	private static void comb (int idx,  int mask) {
		if (idx==N) {
			if (ALPA == mask) 
				ans++;
			return ;
		}
		
		comb (idx+1, mask | words[idx]);	//선택하는 경우
		comb (idx+1, mask); 				//선택하지 않는 경우
		
	}
}
