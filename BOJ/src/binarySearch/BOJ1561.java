package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1561 {

	static long low, high, time;
	static int N,M;
	static int[] Time;
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		Time=new int[M];
		
		st=new StringTokenizer (br.readLine());
		for (int i=0; i<M; i++) {
			Time[i]=Integer.parseInt(st.nextToken());
			high=Math.max(high, Time[i]);
		}

		low=1;
		high*=N;
		
		while (low<high) {
			long mid=(low+high)/2;
			time=mid;
			
			if (count(mid)) {
				high=mid-1;
			} else low=mid+1;
		}
		

		// 시간이 ans일 때, 몇 명이 탈 수 있는지 확인
		int cnt=0;
		for (int i=0; i<M; i++) 
			cnt+=(time/Time[i]);

		System.out.println("time: "+time+" ,cnt: "+cnt);
		
		// N명보다 초과하는 사람은 몇 명?

		for (int i=0; i<M; i++) {
			
			if (time%Time[i]==0) 
				cnt++;
			
			if (cnt==N) {
				System.out.println(i);
				break;
			}
		}
		
	}
	
	
	/*
	 * 시간이 time일 때, 모든 학생들이 다 탔느냐 못 탔느냐를 반환하는 함수
	 * true: 학생들 모두 탔음 -> 종료 시간을 줄임
	 * false: 학생들 다 못 탔음 -> 종료 시간을 늘림
	 */
	private static boolean count (long time) {
		
		long cnt=0;
		
		for (int i=0; i<M; i++) {
			cnt+=(time/Time[i]);
			
			if (cnt>=N) return true;
		}
		return false;
	}

}
