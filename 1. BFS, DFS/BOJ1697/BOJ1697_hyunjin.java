package BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 
public class BOJ1697_숨바꼭질 {
	static int N, K, ans;
	static boolean[] visited = new boolean[100001]; // 앞뒤로 왔다갔다 무한루프를 돌 수도 있으므로 방문체크 해주기

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();

		System.out.println(bfs(N));

	}

	static int bfs(int startPos) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { startPos, 0 });
		visited[startPos] = true;

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int currPos = curr[0];
			int time = curr[1];

			if (currPos == K)
				return time;

			int[] nextPosition = { currPos - 1, currPos + 1, currPos * 2 };

			for (int next : nextPosition) {
				if (next < 0 || next > 100000 || visited[next])
					continue;
				
				visited[next] = true;
				queue.add(new int[] { next, time + 1 });
			}

		}

		return -1;

	}
}
