package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1753_최단경로 {

	public static class Node implements Comparable<Node> {
		int V, W;

		public Node(int v, int w) {
			super();
			this.V = v;
			this.W = w;
		}

		@Override
		public int compareTo(Node o) {
			return this.W - o.W;
		}

	}

	static int V, E, K;
	static final int INF = Integer.MAX_VALUE;
	static List<Node>[] adjList;
	static int[] dist;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken()); // 정점의 개수
		E = Integer.parseInt(st.nextToken()); // 간선의 개수

		K = Integer.parseInt(br.readLine()); // 시작 정점의 번호

		adjList = new ArrayList[V + 1]; // 정점의 번호가 1번부터 시작
		for (int i = 1; i < V + 1; i++) {
			adjList[i] = new ArrayList<>();
		}

		// 거리를 저장하는 배열
		dist = new int[V + 1];
		Arrays.fill(dist, INF); // 모든 배열 무한으로 초기화

		// 인접 리스트 입력 받기
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			int A = Integer.parseInt(st.nextToken()); // 출발 정점
			int B = Integer.parseInt(st.nextToken()); // 도착 정점
			int W = Integer.parseInt(st.nextToken()); // 간선 가중치

			adjList[A].add(new Node(B, W));
		}

		dijkstra(K); // i번째 정점까지 도착

		for (int i = 1; i < V + 1; i++) {
			if (dist[i] == INF)
				System.out.println("INF");
			else
				System.out.println(dist[i]);
		}

	} // main

	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[V + 1];

		dist[start] = 0; // 시작 지점은 자기 자신 == 거리가 0

		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node currN = pq.poll();

			if (visited[currN.V])
				continue; // 이미 방문했다면,최소 비용이 정해짐
			visited[currN.V] = true;

			// 다음으로 갈 수 있는 node 확인
			// 최단 거리 갱신하면서 이동 
			// 최단 거리를 갱신했다면 pq에 다시 넣어주기 
			for (Node node : adjList[currN.V]) {
				if (dist[node.V] > dist[currN.V] + node.W) {
					dist[node.V] = dist[currN.V] + node.W;

					pq.add(new Node(node.V, dist[node.V]));
				}
			}

		}

	}
}
