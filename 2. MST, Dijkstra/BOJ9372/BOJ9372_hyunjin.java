package MST;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BOJ9372_상근이의여행 {

	public static class Edge {
		int A, B;

		public Edge(int a, int b) {
			super();
			A = a;
			B = b;
		}
	}

	// 프림 알고리즘
	// 모든 국가를 방문하기까지 필요한 간선의 개수 = 타야할 비행기 수
	// 모든 국가를 방문했다면 stop = 모든 정점을 다 뽑으면 stop

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 국가의 수
			int M = sc.nextInt(); // 비행기 수

			List<Edge>[] adjList = new ArrayList[N + 1]; // 국가의 번호가 1부터 시작

			for (int i = 1; i < N + 1; i++) {
				adjList[i] = new ArrayList<>();
			}

			// M개의 비행기가 연결되어 있는 국가 연결하기
			for (int i = 0; i < M; i++) {
				int A = sc.nextInt();
				int B = sc.nextInt();

				adjList[A].add(new Edge(A, B));
				adjList[B].add(new Edge(B, A));
			} // 어떤 국가들끼리 연결되었는지 저장

			boolean[] visited = new boolean[N + 1]; // 방문한 국가 체크하기

			Queue<Edge> pq = new LinkedList<>();
			visited[1] = true;

			int ans = 0;
			int pick = 1;

			pq.addAll(adjList[1]);

			while (pick != N) {
				Edge e = pq.poll();
				if (visited[e.B])
					continue;

				ans++;
				visited[e.B] = true;
				pick++;

				pq.addAll(adjList[e.B]);

			}

			System.out.println(ans);

		} // tc
	}

}
