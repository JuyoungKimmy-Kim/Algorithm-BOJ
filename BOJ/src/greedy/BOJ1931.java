package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1931 {

	static int N;
	static int[][] time;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		
		N=Integer.parseInt(br.readLine());
		time=new int[N][2];
		
		for (int i=0; i<N; i++) {
			StringTokenizer st=new StringTokenizer (br.readLine());
			time[i][0]=Integer.parseInt(st.nextToken());
			time[i][1]=Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(time, (o1, o2)-> o1[1]==o2[1] ? o1[0]-o2[0] : o1[1]-o2[1]);
		
//		for (int[] a : time) 
//			System.out.println(a[0]+" "+a[1]);
		
		int count=1;		//무조건 1개는 보장
		int now=0;			//현재 진행되고 있는 회의 index
			
		boolean flag=true;
		while (flag) {
			flag=false;
			
			//다음에 진행될 회의는 idx 다음으로 종료 시간이 빠른 것중
			//시작 시간이 idx의 종료 시간 이후여야 함
			for (int next=now+1; next<N; next++) {
				if (time[next][0]>=time[now][1]) {
					count++;
					now=next;
					flag=true;
					break;
				}
			}
		}
		System.out.println(count);
	}
}
