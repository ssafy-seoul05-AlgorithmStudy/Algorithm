package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 프림 알고리즘
// 모든 노드 방문 => 최소 비용 구하기
// 메모리 : 24016KB
// 시간 : 212ms

public class BOJ1368_물대기 {

	public static class Node implements Comparable<Node> {
		int A, B, W;

		public Node() {

		}

		public Node(int a, int b, int w) {
			super();
			A = a;
			B = b;
			W = w;
		}

		@Override
		public int compareTo(Node o) {
			return this.W - o.W;
		}
	}

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine()); // 논의 수
		int[] costArr = new int[N + 1]; // 각 논에 우물을 파기 위한 비용

		for (int i = 1; i < N + 1; i++) {
			costArr[i] = Integer.parseInt(br.readLine());
		}

		// 논들 사이 물을 끌어오는데 드는 비용
		int[][] adjArr = new int[N + 1][N + 1];
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N + 1; j++) {
				adjArr[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력 완료

		// 특정 논에 물을 대기 위해서는..
		// 해당 논에 직접 물을 대는 비용 v.s. 주변 논에서 물을 대는데 드는 비용 비교

		// 모든 논에 물을 댔는지 확인
		boolean[] visited = new boolean[N + 1];

		int ans = 0; // 정답 = 드는 최소 비용
		int pick = 0; // 모든 논을 픽 했는지 == 모든 논에 물을 댔는가

		// 우선 순위 큐에 넣어서 비용이 최소인 논부터 빼오기
		PriorityQueue<Node> pq = new PriorityQueue<>();

		// 본인 논에 우물 파는 비용 우선순위 큐에 넣기
		for (int i = 1; i < N + 1; i++) {
			pq.add(new Node(0, i, costArr[i]));
		}

		while (pick != N) {
			Node n = pq.poll();

			if (visited[n.B])
				continue; // 이미 물을 댄 논은 패쓰

			// 다른 논에서 물을 끌어오는것 보다, 본인 논에 우물을 파는 비용이 더 적은 경우
			if (n.W > costArr[n.B]) {
				ans += costArr[n.B];
			}
			// 다른 논에서 물 끌어오는게 더 비용이 적게 드는 경우
			else {
				ans += n.W;
			}

//			System.out.println(ans);

			visited[n.B] = true; // 이 논에는 물을 댔음
			pick++; // 물을 댄 논 수 ++

			// 해당 논과 연결되어 있는 논을 pq에 넣기
			for (int i = 1; i < N + 1; i++) {
				if (!visited[i] && adjArr[n.B][i] != 0) {
					pq.add(new Node(n.B, i, adjArr[n.B][i]));
				}
			}
		}

		System.out.println(ans);

	} // main
}
