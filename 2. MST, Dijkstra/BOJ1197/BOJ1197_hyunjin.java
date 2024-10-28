package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ1197_최소 스패닝 트리 
// 메모리 : 56556KB
// 시간 : 504ms 
public class BOJ1197_최소스패닝트리 {
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
			return Integer.compare(this.W, o.W);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int V = Integer.parseInt(st.nextToken()); // 정점의 개수
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수

		List<Edge>[] adjList = new ArrayList[V + 1]; // 인접 리스트 : 정점의 번호가 1부터
		for (int i = 1; i < V + 1; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			adjList[A].add(new Edge(A, B, W));
			adjList[B].add(new Edge(B, A, W));
		} // 입력 받음

		// 프림 알고리즘
		boolean[] visited = new boolean[V + 1];

		PriorityQueue<Edge> pq = new PriorityQueue<>();

		// 우선 순위 큐에 처음 위치 넣기
		pq.addAll(adjList[1]);
		visited[1] = true;

		long ans = 0;
		int pick = 1;

		while (pick != V ) { // 모든 정점을 방문 할 때까지
			Edge e = pq.poll();

//			System.out.println(e.B + " 노드 ");
//			System.out.println(e.W + " 가중치 ");

			if (visited[e.B])
				continue; // 이미 방문한 정점 패쓰

			ans += e.W;
			visited[e.B] = true;
			pick++;

			pq.addAll(adjList[e.B]);
		}

		System.out.println(ans);
	} // main
}
