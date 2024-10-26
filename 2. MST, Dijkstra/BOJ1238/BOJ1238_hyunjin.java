package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 다익스트라 알고리즘 
public class BOJ1238_파티 {
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

	static final int INF = Integer.MAX_VALUE;
	static int N, M, X;
	static List<Edge>[] adjList; // 한 마을에서 연결된 마을
	static int [] dist; // 거리 배열 == 가중치 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 마을의 수
		M = Integer.parseInt(st.nextToken()); // 단방향 도로의 수
		X = Integer.parseInt(st.nextToken()); // 파티를 하는 마을의 번호

		adjList = new ArrayList[N + 1]; // 마을의 번호가 1부터

		for (int i = 1; i < N + 1; i++) {
			adjList[i] = new ArrayList();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			adjList[A].add(new Edge(A, B, W)); // 단방향 도로이므로
		}

		for (int i = 1; i < N + 1; i++) {
			dijkstra(i); // i번 마을 다익스트라 진행
		}

	}

	static void dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N + 1];
		
		dist[start] = 0; // 출발하는 지점의 가중치 = 0으로 두고 비교 시작
		
		pq.add(new Edge(start, X, 0)); // 출발 위치, 도착하려는 지점, 출발점의 가중치 0으로 시작
		
		while(!pq.isEmpty()) { 
			Edge e = pq.poll(); 
			
			if(visited[e.B]) continue; // 이미 방문 했다면 해당 지점까지의 비용을 알고 있다. 
			if(e.B == X) break; // X 마을에 도착함
			
			
		}
		
		

	}
}
