package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1541_2 {

	static int sum;
	
	/*
	 * e.g. 2-34+56+78-9-10+11 
	 * 2 / 34+56+78 / 9 / 10+11
	 * 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		
		// 1. -로 Tokenizing
		StringTokenizer stMinus=new StringTokenizer (br.readLine(), "-");

		int sizeMinus=stMinus.countTokens();	//현재 시점에 남아 있는 토큰 수
		for (int i=0; i<sizeMinus; i++) {
			
			// 2. 토큰한 것 가져옴
			String token=stMinus.nextToken();
			
			
			// 3. +로 Tokenizing 하고 숫자들을 더해줌
			int num=0;
			StringTokenizer stPlus=new StringTokenizer (token, "+");
			int sizePlus=stPlus.countTokens();
			
			for (int j=0; j<sizePlus; j++)
				num+=Integer.parseInt(stPlus.nextToken());
			
			if (i==0) sum=num;	//맨 앞 숫자
			else sum-=num;		//-로 잘라 낸 것이기 때문
		}
		System.out.println(sum);
	}
}
