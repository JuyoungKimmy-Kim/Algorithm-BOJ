package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ3954 {
	
	static final int MAX= (int) (5*1e7);
	static final int DATA_MAX=(int)(1e5+100);
	static final int MOD=256;
	
	// sm: 배열 크기, sc: 프로그램 코드 크기, si: 입력 크기
	static int T, sm, sc, si;
	static String program, input;
	
	static HashMap <Integer, Integer> map=new HashMap<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;
		
		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			
			map.clear();
			
			st=new StringTokenizer (br.readLine());
			sm=Integer.parseInt(st.nextToken());
			sc=Integer.parseInt(st.nextToken());
			si=Integer.parseInt(st.nextToken());
			
			program=br.readLine();
			input=br.readLine();
				
			
			// loop 쌍을 만들어 줌
			Stack <Integer> stack=new Stack<>();
			
			for (int i=0; i<sc; i++) {
				if (program.charAt(i)=='[')
					stack.add(i);
				
				else if (program.charAt(i)==']') {
					int j=stack.pop();
					map.put(j, i);
					map.put(i, j);
				}
			}
			
			int idx=0;
			int programIdx=0;
			int inputIdx=0;
			int loopStartIdx=Integer.MAX_VALUE;
			boolean isTerminated=true;
			
			int[] datas = new int[DATA_MAX];
			
			for (int i=0; i<sc; i++) {
				
				char c=program.charAt(i);
				
				switch (c) {
				
				//포인터가 가리키는 수를 1감소
				case '-': datas[idx]=(datas[idx]+MOD-1)%MOD;
					break;
				//포인터가 가리키는 수를 1증가
				case '+': datas[idx]=(datas[idx]+1)%MOD;
					break;
				//포인터를 왼쪽으로 한 칸 움직임
				case '<': idx= (idx+sm-1) % sm;
					break;
				//포인터를 오른쪽으로 한 칸 움직임
				case '>': idx= (idx+1)%sm;
					break;
				//만약 포인터가 가리키는 수가 0이라면 [와 짝을 이루는 ] 다음 명령으로 점프
				case '[': if (datas[idx]==0) i=map.get(i);
					break;
				//만약 포인터가 가리키는 수가 0이 아니라면 ]와 짝을 이루는 [의 다음 명령으로 점프
				case ']': if (datas[idx]!=0) i=map.get(i);
					break;
				//포인터가 가리키는 수를 출력
				case '.': //System.out.println(datas[idx]);
					break;
				//문자 하나를 읽고 포인터가 가리키는 곳에 저장
				//입력의 마지막인 경우 255저장
				case ',' : datas[idx]= (inputIdx==si) ? MOD-1 : (int)input.charAt(inputIdx++);
					break;	
				}
				
				if (++programIdx>MAX) {
					loopStartIdx=Math.min(loopStartIdx, i);
				}
				if (programIdx>MAX*2) {
					isTerminated=false;
					break;
				}
			}
			
			if (isTerminated) 
				System.out.println("Terminates");
			else {
				System.out.println("Loops "+loopStartIdx+" "+map.get(loopStartIdx));
			}
		}
	}

}
