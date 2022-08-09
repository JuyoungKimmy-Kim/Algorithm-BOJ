package slidingwindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12891_2 {

	static int S,P;
	static String str;
	static int[] minCnt;
	static int totalCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
	
		S=Integer.parseInt(st.nextToken());//��ü DNA ����
		P=Integer.parseInt(st.nextToken()); // ���Ƿ� ����� �κ� ���ڿ� ����
		minCnt=new int[4];
		
		str=br.readLine();
		
		st=new StringTokenizer (br.readLine());
		for (int i=0; i<4; i++)
			minCnt[i]=Integer.parseInt(st.nextToken());
		
		int[] cnt=new int[4];
	
		for (int i=0; i<=S-P; i++) {
			boolean flag=true;		
			if (i==0) {
				for (int j=i; j<i+P; j++) {
					char ch=str.charAt(j);
					
					if (ch!='A' && ch!='C' && ch!='G'&& ch!='T' ) {
						flag=false;
					}
					switch (ch) {
					case 'A' : cnt[0]++; break;
					case 'C' : cnt[1]++; break;
					case 'G' : cnt[2]++; break;
					case 'T' : cnt[3]++; break;
					}
				}
				
				
			}
			
			else {
				char prev=str.charAt(i-1);
				switch (prev) {
				case 'A' : cnt[0]--; break;
				case 'C' : cnt[1]--; break;
				case 'G' : cnt[2]--; break;
				case 'T' : cnt[3]--; break;
				}
				
				char ch=str.charAt(i+P-1);
				
				if (ch!='A' && ch!='C' && ch!='G'&& ch!='T' ) {
					flag=false;
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
