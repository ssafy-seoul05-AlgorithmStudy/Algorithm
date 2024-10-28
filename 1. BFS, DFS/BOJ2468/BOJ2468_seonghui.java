import java.util.Scanner;

public class BOJ2468_seonghui {
	static int n;
	static int[][] ground;
	static boolean[][] visited;
	static int max_h; // 다 잠기는 높이
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		ground = new int[n][n];
		
		for(int r = 0; r < n; r++) {
			for(int c = 0; c < n; c++) {
				ground[r][c] = sc.nextInt();
				
				if(ground[r][c] > max_h) {
					max_h = ground[r][c];
				}
			}
		}
		
		int max = 0;
		for(int h = 0; h <= max_h; h++) {
			visited = new boolean[n][n];
			int cnt = 0;
			for(int r = 0; r < n; r++) {
				for(int c = 0; c < n; c++) {
					if(!visited[r][c] && ground[r][c] > h){
						cnt++;
						dfs(r,c,h);
					}
				}
			}
			if(cnt > max) {
				max = cnt;
			}
		}
		
		System.out.println(max);
		
	}
	
	static void dfs(int r, int c, int h) {
		visited[r][c] = true;
		
		for(int k = 0; k < 4; k++) {
			int nextRow = r + dr[k];
			int nextCol = c + dc[k];
			
			if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
			if(!visited[nextRow][nextCol] && ground[nextRow][nextCol] > h) {
				dfs(nextRow, nextCol, h);
			}
		}
	}
}
