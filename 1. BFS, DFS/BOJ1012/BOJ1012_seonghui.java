import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BOJ1012_seonghui {
	static int t, m, n, k, x, y;
	static int[][] ground;
	static boolean[][] visited;
	static int cnt;
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		t = sc.nextInt();
		
		for(int tc = 0; tc < t; tc++) {
			m = sc.nextInt();
			n = sc.nextInt();
			k = sc.nextInt();
			ground = new int[n][m];
			visited = new boolean[n][m];
			cnt = 0;
			
			for(int i = 0; i < k; i++) {
				x = sc.nextInt();
				y = sc.nextInt();
				ground[y][x] = 1;
			}
			
			for(int r = 0; r < n; r++) {
				for(int c = 0; c < m; c++) {
					if(!visited[r][c] && ground[r][c] == 1) {
						bfs(r,c);
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		}// tc
	}// main
	
	static void bfs(int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[]{r,c});
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			
			for(int j = 0; j < 4; j++) {
				int nextRow = tmp[0] + dr[j];
				int nextCol = tmp[1] + dc[j];
				
				if(nextRow < 0 | nextRow >= n | nextCol < 0 | nextCol >= m) continue;
				if(!visited[nextRow][nextCol] && ground[nextRow][nextCol] == 1) {
					q.add(new int[] {nextRow, nextCol});
					visited[nextRow][nextCol] = true;
				}
			}
			
		}
	}
}
