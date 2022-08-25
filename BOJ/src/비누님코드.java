import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 실패..
 * 
 * 아기상어 크기 처음 2
 * 먹은 물고기 카운트 (크기 변하면 0으로 초기화)
 * 
 * 자기보다 큰 물고기는 못지나감
 * 같은 물고기는 못먹고 지나갈 수 잇음
 * 
 * bfs로 먹을 수 있는 물고기 찾고 그까지 길이 체크 (bfs로 찾으면 가장 가까운 물고기부터 찾아짐)
 * 위의 물고기, 그 다음 왼쪽 물고기 우선이므로 상 좌 우 하 순서로 확인~~
 * */
public class 비누님코드 {
    static int N, time, bbsharkSize = 2; 
    static int eatFish = 0;    // 먹은 물고기 수
    static Point bbShark;    // 아기상어 위치
    static int currLen;
    static int[][] map;
    static boolean[][] visit;
    
    static int[] dy = { -1, 0,  0, 1 };
    static int[] dx = {  0, -1, 1, 0 };
    
    static Queue<Point> q = new ArrayDeque<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int input = Integer.parseInt(st.nextToken());
                map[i][j] = input;
                if(input == 9) {
                    bbShark = new Point(j, i);        
                    q.add(bbShark);
                    visit[i][j] = true;
                    map[i][j] = 0;    // 이거 깜빡했었다..근데 해도 안댐
                    
                }
            }
        }

        while(bfs()) {
        	
        	System.out.println(bbsharkSize);
        	print();
        	
            // 초기화
            for(int i = 0; i < N; i++) {
                Arrays.fill(visit[i], false);
            }
            q.clear();
            
            q.add(bbShark);
            visit[bbShark.y][bbShark.x] = true;
            
            if(eatFish==bbsharkSize) {
                bbsharkSize++;
                eatFish = 0;
            }
        }
        System.out.println(time);
    }
    
    static boolean bfs() {
        currLen = 0;
        
        while(!q.isEmpty()) {
            
            int size = q.size();
            currLen++;
            
            for(int i = 0; i < size; i++) {
                Point p = q.poll();
                
                
                for(int d = 0; d < 4; d++) {
                    int ny = p.y + dy[d];
                    int nx = p.x + dx[d];
                    
                    // 범위 벗어남, 이미 방문, 자기보다 큰 물고기
                    if( ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx] || map[ny][nx] > bbsharkSize) continue;
                    
                    if(map[ny][nx] != 0 && map[ny][nx] < bbsharkSize) {    // 먹을 수 있는 물고기칸임
                        map[ny][nx] = 0;
                        eatFish++;
                        time += (currLen);
                        bbShark = new Point(nx, ny); 
                        return true;
                    }
                    
                    // 지나갈 수 있는 칸 (빈칸, 자기와 같은 크기 물고기)
                    q.add(new Point(nx, ny));
                    visit[ny][nx] = true;

                }

            }
                
        }

        return false;
    }
    
    static void print () {
    	
    	System.out.println("========================================");
    	for (int i=0; i<N; i++) {
    		for (int j=0; j<N; j++) {
    			System.out.print(map[i][j]+" ");
    		}
    		System.out.println();
    	}
    }

}