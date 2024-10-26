package DFS;

import java.util.Scanner;

//BOJ1987_알파벳 
public class BOJ1987_알파벳 {
	static int R, C, ans;
	static char[][] board;
	static boolean[][] visited;
	static boolean[] used = new boolean[26];
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();

		board = new char[R][C];
		visited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String str = sc.next();
			for (int j = 0; j < C; j++) {
				board[i][j] = str.charAt(j);
			}
		}

		ans = -1;
		dfs(0, 0, 1);
		System.out.println(ans);

	}

	static void dfs(int r, int c, int cnt) {
		if (cnt > ans) {
			ans = cnt;
		}

		visited[r][c] = true;
		used[board[r][c] - 65] = true; // 사용한 알파벳 체크

		for (int dir = 0; dir < 4; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];

			if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc] || used[board[nr][nc] - 65])
				continue;

			dfs(nr, nc, cnt + 1);
		}
		// 백트래킹 후 다시 돌면서 dfs 돌아야 하므로 다시 방문할 수 있게 해주기.
		visited[r][c] = false;
		used[board[r][c] - 65] = false;
	}
}
