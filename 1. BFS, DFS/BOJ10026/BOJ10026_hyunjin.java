package BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// BOJ10026_적록색약
public class BOJ10026_적록색약 {
	static int N, normalAns, nonNormalAns;
	static char[][] arr;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static char[] charArr = { 'R', 'G', 'B' };
	static char[] charArr2 = { 'R', 'B' };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		arr = new char[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String str = sc.next();
			for (int j = 0; j < N; j++) {
				arr[i][j] = str.charAt(j);
			}
		}

		// 적록색약이 아닌 사람
		for (int singleChar = 0; singleChar < charArr.length; singleChar++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] == charArr[singleChar] && !visited[i][j]) {
						normalBfs(i, j, charArr[singleChar]);
					}
				}
			}
		}

		// 적록색약인 사람
		visited = new boolean[N][N]; // 배열 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 'G') {
					arr[i][j] = 'R';
				}
			}
		}
		
		for (int singleChar = 0; singleChar < charArr2.length; singleChar++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] == charArr2[singleChar] && !visited[i][j]) {
						bfs(i, j, charArr2[singleChar]);
					}
				}
			}
		}

		System.out.println(normalAns + " " + nonNormalAns);
	}

	static void normalBfs(int r, int c, char singleChar) {
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

				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || arr[nr][nc] != singleChar)
					continue;

				visited[nr][nc] = true;
				queue.add(new int[] { nr, nc });
			}
		} // while
		normalAns++;

	}

	static void bfs(int r, int c, char singleChar) {
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

				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || arr[nr][nc] != singleChar)
					continue;

				visited[nr][nc] = true;
				queue.add(new int[] { nr, nc });
			}
		} // while
		nonNormalAns++;

	}
}