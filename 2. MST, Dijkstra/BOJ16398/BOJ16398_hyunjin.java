package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ16398_행성연결
// 메모리 : 157064KB
// 시간 : 664ms

public class BOJ16398_행성연결 {
	public static class Edge implements Comparable<Edge> {
		int Node, W;

		public Edge(int node, int w) {
			super();
			Node = node;
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

		int N = Integer.parseInt(st.nextToken()); // 행성의 수
		int[][] adjArr = new int[N][N];

		// 행성 인접 행렬 입력 받기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				adjArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 방문 체크
		boolean[] visited = new boolean[N]; // 방문한 정점인지 체크

		PriorityQueue<Edge> pq = new PriorityQueue<>();

		long ans = 0;
		int pick = 0;

		pq.add(new Edge(0, 0)); // 첫 시작 Node와 가중치 0 pq에 넣기

		// 모든 정점을 다 방문할 때까지 반복
		while (pick != N) {
			Edge e = pq.poll();
			
//			System.out.println(e.Node+" 노드 ");
			
			// 이미 방문한 노드라면 pass
			if (visited[e.Node])
				continue;

			// 방문하지 않은 노드라면 가중치 더하기
			ans += e.W;
			visited[e.Node] = true;
			pick++;

			// 해당 노드와 연결된 노드 우선순위 큐에 넣기
			for (int nextNode = 0; nextNode < N; nextNode++) {
				if (!visited[nextNode]) {
					pq.add(new Edge(nextNode, adjArr[e.Node][nextNode]));
					
				}
			}
		} // while

		System.out.println(ans);
	}
}
