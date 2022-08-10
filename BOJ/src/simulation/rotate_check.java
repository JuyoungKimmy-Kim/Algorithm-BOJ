package simulation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class rotate_check{
    static int N, M, R;
    static int[][] arr;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        
        arr = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int shell=0; shell<Math.min(N, M)/2; shell++) {
            int inPlace = N*2+M*2-shell*8-4; // 제자리로 돌아오는 횟수
            for(int r=0; r<R%inPlace; r++) {
                rotate(shell);
            }
        }
        
        print();
    }
    static void print() {
        StringBuilder result = new StringBuilder();
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {                
                result.append(arr[i][j]).append(" ");
            }
            result.append('\n');
        }
        System.out.print(result);
    }
    
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static void rotate(int shell) {
        int x = shell;
        int y = shell;
        int cur = arr[y][x];
        
        int d=0;
        while(true) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(nx < shell || nx >= M-shell || ny < shell || ny >= N-shell) {
                if(d == 3) break;
                else {
                    d++;
                    continue;
                }
            }
            
            int nxt = arr[ny][nx]; // save
            arr[ny][nx] = cur; // rotate
            cur = nxt; // save
            
            x = nx;
            y = ny;
        }
    }
    
}