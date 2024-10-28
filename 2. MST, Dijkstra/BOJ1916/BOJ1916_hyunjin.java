package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1916_최소비용구하기 {
	public static class Node implements Comparable<Node> {
		int V, W;

		public Node(int v, int w) {
			super();
			V = v;
			W = w;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.W - o.W;
		}

	}

	static int N, M, start, end;
	static List<Node>[] adjList;
	static int[] dist;
	static int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		adjList = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			adjList[i] = new ArrayList<>();
		}

		// 거리 배열 초기화
		dist = new int[N + 1];
		Arrays.fill(dist, INF);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			adjList[A].add(new Node(B, W));
		}

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		dijkstra(start);
	}

	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N + 1];

		dist[start] = 0;

		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node currN = pq.poll();

			if(currN.V == end) {
				System.out.println(dist[currN.V]);
				return;
			}
			
			if (visited[currN.V])
				continue;
			visited[currN.V] = true;

			for (Node node : adjList[currN.V]) {
				if (dist[node.V] > dist[currN.V] + node.W) {
					dist[node.V] = dist[currN.V] + node.W;

					pq.add(new Node(node.V, dist[node.V]));
				}
			}
		}
	}
}