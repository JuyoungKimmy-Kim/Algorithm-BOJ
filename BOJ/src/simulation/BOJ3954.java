package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ3954 {
	
	static final int MAX= (int) (5*1e7);
	//static final int MAX=50_000_000;
	
	static final int DATA_MAX=(int)(1e5+100);
	//static final int DATA_MAX=100_000+100;
	
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
					map.put(j, i);
				}
			}
			
			int idx=0;
			int programIdx=0;
			int inputIdx=0;
			int loopStartIdx=0;
			boolean isTerminated=true;
			
			int[] datas = new int[DATA_MAX];
			
			for (int i=0; i<sc; i++) {
				
				char c=program.charAt(i);
				
				switch (c) {
				case '-': datas[idx]=(datas[idx]+MOD-1)%MOD;
					break;
				case '+': datas[idx]=(datas[idx]+1)%MOD;
					break;
				case '<': idx= (idx+sm-1) % sm;
					break;
				case '>': idx= (idx+1)%sm;
					break;
				case '[': if (datas[idx]==0) programIdx=map.get(idx);
	
				case ']': if (datas[idx]!=0) programIdx=map.get(idx);
					break;
				case '.': //System.out.println(datas[idx]);
					break;
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
