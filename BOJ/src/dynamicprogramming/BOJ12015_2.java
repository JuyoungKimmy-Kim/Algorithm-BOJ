package dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ12015_2 {

	static int N, ans;
	static int [] arr, increArr;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		N=Integer.parseInt(br.readLine());
		arr=new int[N];
		increArr=new int[N];
		
		
		st=new StringTokenizer (br.readLine());
		for (int i=0; i<N; i++)
			arr[i]=Integer.parseInt(st.nextToken());
		
		int size=0;
		for (int i=0; i<N; i++) {
			int pos=Arrays.binarySearch(increArr, 0,size, arr[i]);
			if (pos>=0) continue;
			
			int insertPos=Math.abs(pos)-1;
			increArr[insertPos]=arr[i];
			
			if (insertPos==size) size++;
		}
		
		System.out.println(size);
	}
}

