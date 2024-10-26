package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14940_쉬운최단거리 {

	static int N, M, ans;
	static int[][] board;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int startR = 0, startC = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 2) { 
					startR = i;
					startC = j;
				}
			} // j
		} // i
		
		// 2부터 출발해서 1위치에 도착할때까지 걸리는 시간
		bfs(startR, startC, 1);

		board[startR][startC] = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(board[i][j] == 1 && !visited[i][j])
					board[i][j] = -1;
			}
		}
		

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}

	}

	static void bfs(int r, int c, int dist) {
		Queue<int[]> queue = new LinkedList<>();
		visited[r][c] = true;
		queue.add(new int[] { r, c, dist });

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int currR = curr[0]; // 현재 R 위치
			int currC = curr[1]; // 현재 C 위치
			int currDist = curr[2]; // 거리

			for (int dir = 0; dir < 4; dir++) {
				int nr = currR + dr[dir];
				int nc = currC + dc[dir];

				// 경계 조건 : 경계 밖이거나 갈 수 없는 길
				if (nr < 0 || nr >= N || nc < 0 || nc >= M || board[nr][nc] == 0 || visited[nr][nc]) {
					continue;
				}

				if (board[nr][nc] == 1) {
					board[nr][nc] = currDist;
					visited[nr][nc] = true;
					queue.add(new int[] { nr, nc, currDist + 1 });
				}
			}
		}

	}
}


