package DFS;

import java.util.Scanner;

// BOJ4963_섬의 개수 
public class BOJ4963_섬의개수 {
	static int w, h, cnt;
	static int[][] board;
	static boolean[][] visited;

	// 8방 탐색
	static int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int[] dc = { 0, 0, -1, 1, 1, -1, 1, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (sc.hasNext()) {
			w = sc.nextInt(); // 너비
			h = sc.nextInt(); // 높이

			if (w == 0 && h == 0)
				return;

			board = new int[h][w];


			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					board[i][j] = sc.nextInt();
				}
			}

			visited = new boolean[h][w];
			cnt = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (board[i][j] == 1 && !visited[i][j]) {
						dfs(i, j);
						cnt++;
					}
				}
			}
			System.out.println(cnt);

		} // while

	}

	static void dfs(int r, int c) {
		visited[r][c] = true;

		for (int dir = 0; dir < 8; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];

			if (nr < 0 || nr >= h || nc < 0 || nc >= w || visited[nr][nc] || board[nr][nc] == 0)
				continue;

			dfs(nr, nc);
		}

	}
}
