package bfs.dfs;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * 고슴도치, 물 따로 queue
 * 고슴도치의 queue를 중심으로 처리
 * => queue에 대한 작업을 현재 큐에 담긴 모든 대상을 전부다 처리하고 다음 고슴도치 처리
 * 
 */

class Node {
	int y,x,d;
	public Node (int y, int x, int d) {
		this.y=y;
		this.x=x;
		this.d=d;
	}
}

public class BOJ3055_2 {
	
	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};
	
	static int R,C,min;
	static char[][] map;
	
	static Queue<Node> wQueue=new ArrayDeque<> ();
	static Queue<Node> gQueue=new ArrayDeque<> ();
	
	public static void main(String[] args)  throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());

		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		
		map=new char[R][];
		for (int i=0; i<R; i++) {
			map[i]=br.readLine().toCharArray();
			
			for (int j=0; j<C; j++) {
				if (map[i][j]=='S')
					gQueue.offer(new Node (i,j,0));
				else if (map[i][j]=='*')
					wQueue.offer(new Node (i,j,0));
			}
		}
		
		min=Integer.MAX_VALUE;
		check ();
		if (min==Integer.MAX_VALUE)
			System.out.println("KAKTUS");
		else System.out.println(min);
		
	}
	
	static void check () {
		
		//고슴도치 기준으로
		while (!gQueue.isEmpty()) {
			
			//물확산
			//현재 물 Queue에 있는 물을 모두 꺼내서 다 처리
			
			int size=wQueue.size();
			for (int i=0; i<size; i++) {
				Node node=wQueue.poll();
				
				for (int d=0; d<4; d++) {
					int ny=node.y+dy[d];
					int nx=node.x+dx[d];
					
					if (ny<0 || nx<0 || ny>=R || nx>=C) continue;
					if (map[ny][nx]=='.') {
						//물이 퍼진 상태를 map에 표시 <==고슴도치 처리
						map[ny][nx]='*';
						wQueue.offer(new Node (ny, nx, 0));
					}
				}
			}
			
			//고슴도치 이동
			//물 확산을 미리 처리했으므로 고슴도치는 이동시에 빈칸으로만 이동
			//'D'이면 종료
			
			size=gQueue.size();
			for (int i=0; i<size; i++) {
				Node node=gQueue.poll();
				
				for (int d=0; d<4; d++) {
					int ny=node.y+dy[d];
					int nx=node.x+dx[d];
					
					if (ny<0 || nx<0 || ny>=R || nx>=C) continue;
					if (map[ny][nx]=='D') {					//목적지 도착
						min=Math.min(min, node.d+1);
						return ;
					}else if (map[ny][nx]=='.') {
						map[ny][nx]='S';
						gQueue.offer(new Node (ny, nx, node.d+1));
					}
				}
			}
		}
	}
}
