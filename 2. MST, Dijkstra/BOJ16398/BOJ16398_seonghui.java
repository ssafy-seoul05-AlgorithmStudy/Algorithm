import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ16398_seonghui {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int v = sc.nextInt();
		int[][] adjArr = new int[v][v];
		for (int r = 0; r < v; r++) {
			for (int c = 0; c < v; c++) {
				adjArr[r][c] = sc.nextInt();
			}
		}

		boolean[] visited = new boolean[v];

		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});

		long ans = 0;
		int pick = 0;

		visited[0] = true;
		for (int i = 1; i < v; i++) {
			if (adjArr[0][i] > 0) {
				pq.add(new int[] { 0, i, adjArr[0][i] });
			}
		}
		pick++;

		while (pick < v) {
			int[] edge = pq.poll();

			int from = edge[0];
			int to = edge[1];
			int weight = edge[2];

			if (visited[to]) continue;

			visited[to] = true;
			ans += weight;
			pick++;

			// 새롭게 연결된 행성에서 다른 행성으로 갈 수 있는 간선 추가
			for (int i = 0; i < v; i++) {
				if (!visited[i] && adjArr[to][i] > 0) {
					pq.add(new int[] { to, i, adjArr[to][i] });
				}
			}
		}
		System.out.println(ans);
	}

}
