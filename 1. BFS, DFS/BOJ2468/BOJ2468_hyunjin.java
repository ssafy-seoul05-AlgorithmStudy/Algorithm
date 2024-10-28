package DFS;

import java.util.List;
import java.util.Scanner;

public class BOJ2468_안전영역 {
	static int N, cnt;
	static int[][] board;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static List<Integer> list;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		board = new int[N][N];
		visited = new boolean[N][N];

		int maxHeight = -1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = sc.nextInt();
				maxHeight = Math.max(maxHeight, board[i][j]);
			}
		}

		int ans = 1; // 비가 오지 않은 경우, 안전 지역의 개수 == 1 
		for (int height = 1; height < maxHeight; height++) {
			cnt = 0;
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] > height && !visited[i][j]) {
						cnt++;
						dfs(i, j, height);
					}
				}
			}
			ans = Math.max(ans, cnt);
		}
		
		
		
		System.out.println(ans);

	}

	static void dfs(int r, int c, int height) {
		visited[r][c] = true;

		for (int dir = 0; dir < 4; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];

			if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || board[nr][nc] <= height)
				continue;

			dfs(nr, nc, height);

		}

	}

}