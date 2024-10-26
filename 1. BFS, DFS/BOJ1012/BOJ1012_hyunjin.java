package BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//BOJ1012_유기농 배추 
public class BOJ1012_유기농배추 {
	static int M, N, K, ans;
	static int[][] board;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			M = sc.nextInt(); // 가로 길이
			N = sc.nextInt(); // 세로 길이
			K = sc.nextInt(); // 배추 위치의 개수

			board = new int[M][N];
			visited = new boolean[M][N];

			for (int i = 0; i < K; i++) {
				board[sc.nextInt()][sc.nextInt()] = 1;
			}

			ans = 0;
			visited = new boolean[M][N];

			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == 1 && !visited[i][j]) {
						bfs(i, j);
					}
				}
			}

			System.out.println(ans);

		} // tc
	}

	static void bfs(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { r, c });
		visited[r][c] = true;

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int currR = curr[0];
			int currC = curr[1];

			for (int dir = 0; dir < 4; dir++) {
				int nr = currR + dr[dir];
				int nc = currC + dc[dir];

				if (nr < 0 || nr >= M || nc < 0 || nc >= N || visited[nr][nc] || board[nr][nc] == 0)
					continue;

				visited[nr][nc] = true;
				queue.add(new int[] { nr, nc });
			}
		}
		ans++;
	}
}
