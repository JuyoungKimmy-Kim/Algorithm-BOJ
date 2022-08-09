package slidingwindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12891 {

	static int S,P;
	static String str;
	static int[] minCnt;
	static int totalCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
	
		S=Integer.parseInt(st.nextToken());//전체 DNA 길이
		P=Integer.parseInt(st.nextToken()); // 임의로 사용할 부분 문자열 길이
		minCnt=new int[4];
		
		str=br.readLine();
		
		st=new StringTokenizer (br.readLine());
		for (int i=0; i<4; i++)
			minCnt[i]=Integer.parseInt(st.nextToken());
		

		for (int i=0; i<=S-P; i++) {
			boolean flag=true;
			int[] cnt=new int[4];
			
			for (int j=i; j<i+P; j++) {
				
				char ch=str.charAt(j);
				
				if (ch!='A' && ch!='C' && ch!='G'&& ch!='T' ) {
					flag=false;
					break;
				}
				
				switch (ch) {
				case 'A' : cnt[0]++; break;
				case 'C' : cnt[1]++; break;
				case 'G' : cnt[2]++; break;
				case 'T' : cnt[3]++; break;
				}
			}
			
			for (int k=0; k<4; k++) {
				if (cnt[k]<minCnt[k]) {
					flag=false;
					break;
				}
			}
			
			if (flag) totalCnt++;
		}
		
		System.out.println(totalCnt);
	}
}
