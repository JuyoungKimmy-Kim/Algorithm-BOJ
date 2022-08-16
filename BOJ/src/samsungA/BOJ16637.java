package samsungA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ16637 {
	
	static int N, bracket;
	static String str;
	
	static Stack<Character>stack=new Stack<>();
	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		N=Integer.parseInt(br.readLine());
		str=br.readLine();
		bracket=N/2;
		
		
	}
	
	/*
	 * 3 + 8 * 7 - 9 * 2
	 * 0 1 2 3 4 5 6 7 8
	 */
	
	
	private static dfs (int cnt) {
		
		// 매번 식 검사
		
		if (bracket==N+1) return ;
		
		// bracket 추가
		
	}

}
