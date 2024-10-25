import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ1774_yoonji {
	
	// 좌표 클래스
	static class Node {
		int x;
		int y;
		
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	// 점1, 점2와 그 사이 거리 클래스
	static class Edge implements Comparable<Edge> {
		int A;
		int B;
		double W;

		public Edge(int a, int b, double w) {
			super();
			A = a;
			B = b;
			W = w;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.W, o.W);
		}

	}
	
	static int[] p;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 우주신들의 개수(1~N+1)
		int M = sc.nextInt(); // 연결된 좌표
		
		Node[] godPos = new Node[N+1];
		p = new int[N+1]; // 부모 신 저장할 배열
		
		for(int i=1; i<N+1; i++) {
			godPos[i] = new Node(sc.nextInt(), sc.nextInt());
			
			p[i] = i; // 자기 자신을 부모로 갖는다
		} // 신들의 좌표 입력 완료
		
		for(int i=0; i<M; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			
			int px = find_set(A);
			int py = find_set(B);
			
			union_set(px,py);
			
		} // 이미 연결되어 있는 신들 부모 갱신
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		for(int i=1; i<N; i++) {
			for(int j=i+1; j<N+1; j++) {
				// i번째 신과 j번째 신의 부모 찾기
				int pa = find_set(i);
				int pb = find_set(j);
				
				// 부모가 다르면 연결이 안되어 있으므로 큐에 넣어준다
				if(pa != pb) {
					// x의 거리
					double x = Math.pow(godPos[i].x-godPos[j].x, 2);
					// y의 거리
					double y = Math.pow(godPos[i].y-godPos[j].y, 2);
					// i번째 신과 j번째 신의 거리
					double weight = Math.sqrt(x+y);
					pq.add(new Edge(i, j, weight));
				}
			}
		}
		

		double ans = 0;

		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			
			int px = find_set(e.A);
			int py = find_set(e.B);
			
			// 부모가 같다는 것은 연결이 되어있는 것이므로 넘어간다
			if (px == py)
				continue;
			
			// 연결이 안되어 있는 최소거리이므로 더해준다
			ans += e.W;
			// 뽑은 것들로 부모 갱신
			union_set(px, py);

		}
		
		System.out.printf("%.2f", ans);
	} // main

	private static int find_set(int x) {
		
		if(p[x] != x)
			p[x] = find_set(p[x]);
		
		return p[x];
	}

	private static void union_set(int x, int y) {
		
		p[y] = x;
	}
}
