import java.util.Arrays;
import java.util.Scanner;

public class BOJ4963_seonghui {
	static int[] dr = {-1,1,0,0,-1,-1,1,1};
	static int[] dc = {0,0,-1,1,-1,1,-1,1};
	static int w,h;
	static int[][] map;
	static boolean[][] visited;
	static int cnt = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			w = sc.nextInt();
			h = sc.nextInt();
			if(w == 0 && h == 0) return;
			
			map = new int[h][w];
			visited = new boolean[h][w];
			
			for(int r = 0; r < h; r++) {
				for(int c = 0; c < w; c++) {
					map[r][c] = sc.nextInt();
				}
			}
			
			cnt = 0;
			for(int r = 0; r < h; r++) {
				for(int c = 0; c < w; c++) {
					if(!visited[r][c] && map[r][c] == 1) {
						dfs(r,c);
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		
		}
	}//main
	
	static void dfs(int r, int c) {
		visited[r][c] = true;
		
		for(int k = 0; k < 8; k++) {
			int nextRow = r + dr[k];
			int nextCol = c + dc[k];
			
			if(nextRow < 0 || nextRow >= h || nextCol < 0 || nextCol >= w) continue;
			
			if(!visited[nextRow][nextCol] && map[nextRow][nextCol] == 1) {
				dfs(nextRow, nextCol);
			}
		}
	}
}
