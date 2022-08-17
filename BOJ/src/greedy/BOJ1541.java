package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * - 가 나오면 뒤에 있는 값이 커지는 것이 좋음 -> 먼저 계산
 * 
 */
public class BOJ1541 {

	static char[] chArray;
	static int[] numArray=new int[5]; 	//숫자 저장
	
	static int chIdx, numIdx, sum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		chArray=br.readLine().toCharArray();
		
		sum=getNum(); 			// 첫 번째는 숫자
		boolean isMinus=false;
		int minusSum=0; 		// 앞에 부호가 -이면 뒤에 오는 +숫자를 임시로 더해줌
		
		while (true) {
			char oper = getOper();
			int num=getNum();
			
			if (isMinus) {
				if (oper =='+') {
					minusSum+=num;
				} else {
					sum-=minusSum;
					minusSum=num;
				}
			} else {
				if (oper=='+') {
					sum+=num;
				} else {
					isMinus=true;
					minusSum=num;
				}
			}
			if (chIdx==chArray.length) break;
		}
		
		if (minusSum>0) sum-=minusSum;
		System.out.println(sum);
	}	
	
	//chIdx 이용해서 현재 숫자 문자를 parsing
	static int getNum () {	
		int number=0;
		while (chIdx <chArray.length) {
			if (chArray[chIdx]=='+' || chArray[chIdx]=='-') break;
			number=(number*10) + chArray[chIdx++]-'0';
		}
		
		return number;
	}
	
	static char getOper () {
		if (chIdx<chArray.length) return chArray[chIdx++];
		return 'X';
	}
}
