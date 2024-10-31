import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BOJ14940_seonghui {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        int[][] map = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new ArrayDeque<>();
        int[] dr = {-1,1,0,0};
        int[] dc = {0,0,-1,1};
        
        for(int r=0; r<n; r++) {
            for(int c=0; c<m; c++) {
                map[r][c] = sc.nextInt();
                
                if(map[r][c] == 2) {
                    queue.add(new int[] {r,c});
                    map[r][c] = 0;
                    visited[r][c] = true;
                }
            }
        } // 입력 완료
        
        while(!queue.isEmpty()) {
            int[] p = queue.poll();
            
            //4방 탐색
            for(int dir=0; dir<4; dir++) {
                int nr = p[0] + dr[dir];
                int nc = p[1] + dc[dir];
                // 경계 밖이거나, 땅이 0이거나, 이미 방문한 곳은 못간다
                if(nr<0 || nr>= n || nc<0 || nc>=m || map[nr][nc] == 0 || visited[nr][nc])
                    continue;
                queue.add(new int[] {nr, nc});
                map[nr][nc] = map[p[0]][p[1]] + 1;
                visited[nr][nc] = true;
            }
            
        }
        
        StringBuilder sb = new StringBuilder();
        
        for(int r=0; r<n; r++) {
            for(int c=0; c<m; c++) {
                // 갈 수 있는 길(1)인데 방문 안했다면
                if(!visited[r][c] && map[r][c] == 1)
                    map[r][c] = -1; // 도달할 수 없는 곳!
                
                sb.append(map[r][c]).append(" ");
//                System.out.printf("%d ", map[r][c]);
            }
            sb.append("\n");
//            System.out.println();
        }
        
        System.out.println(sb.toString());
    } //main

}