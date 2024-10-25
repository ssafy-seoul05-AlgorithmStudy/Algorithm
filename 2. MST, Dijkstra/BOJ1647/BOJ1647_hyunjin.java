package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1647_도시분할계획 {
	public static class Edge implements Comparable<Edge> {
		int A, B, W;

		public Edge(int a, int b, int w) {
			super();
			A = a;
			B = b;
			W = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.W - o.W;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 집의 개수
		int M = Integer.parseInt(st.nextToken()); // 길의 개수

		List<Edge>[] adjList = new ArrayList[N + 1];

		for (int i = 1; i < N + 1; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			adjList[A].add(new Edge(A, B, W));
			adjList[B].add(new Edge(B, A, W));
		}

		boolean[] visited = new boolean[N + 1];

		// 프림 알고리즘 활용
		PriorityQueue<Edge> pq = new PriorityQueue();

		int minSum = 0;
		int pick = 1;
		int maxW = 0;

		pq.addAll(adjList[1]);
		visited[1] = true;

		while (pick != N) {
			Edge e = pq.poll();

			if (visited[e.B])
				continue;

			minSum += e.W;
			pick++;
			visited[e.B] = true;
			maxW = Math.max(maxW, e.W);

			pq.addAll(adjList[e.B]);
		}

		int ans = minSum - maxW;

//		System.out.println(minSum);
//		System.out.println(maxW);
		System.out.println(ans);
	} // main
}
