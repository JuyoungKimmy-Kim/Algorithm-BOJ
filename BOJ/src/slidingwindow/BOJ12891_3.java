package slidingwindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12891_3 {

	static int S,P,ans;
	static char[] dna;
	static int minA, minC, minG, minT, cntA, cntC, cntG, cntT;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
	
		S=Integer.parseInt(st.nextToken());
		P=Integer.parseInt(st.nextToken()); 
		dna=br.readLine().toCharArray();
		
		st=new StringTokenizer (br.readLine());
		minA=Integer.parseInt(st.nextToken());
		minC=Integer.parseInt(st.nextToken());
		minG=Integer.parseInt(st.nextToken());
		minT=Integer.parseInt(st.nextToken());
		
		solve();
		System.out.println(ans);
	}
	
	static void solve() {
		for (int i=0; i<P; i++) {
			switch (dna[i]) {
			case 'A' : cntA++; break;
			case 'C' : cntC++; break; 
			case 'G' : cntG++; break;
			case 'T' : cntT++; break;
			}
		}
		
		check();
		
		for (int i=P; i<S; i++) {
			//하나는 버리고 새로 하나를 취함
			// 버리는 것은 맨 앞,취하는 것은 맨 뒤 => 중간에 있는 것들은 재사용
			
			switch (dna[i-P]) {
			case 'A' : cntA--; break;
			case 'C' : cntC--; break; 
			case 'G' : cntG--; break;
			case 'T' : cntT--; break;
			}
			
			switch (dna[i]) {
			case 'A' : cntA++; break;
			case 'C' : cntC++; break; 
			case 'G' : cntG++; break;
			case 'T' : cntT++; break;
			}
			
			//새로운 부분 문자열 확인
			check();
		}
		
	}
	
	static void check () {
		if (cntA>=minA && cntC>=minC && cntG>=minG && cntT>=minT) ans++;
	}
}
