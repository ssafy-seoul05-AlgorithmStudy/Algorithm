import java.util.Scanner;

public class BOJ14889 {

	static int N, min;
	static int[][] map;
	static boolean[] visited;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// initiate var
		N = sc.nextInt();
		min = 987654321;

		map = new int[N][N];
		visited = new boolean[N];

		// input array
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = sc.nextInt();
			}
		}

		combi(0, 0);
		System.out.println(min);

	}

	static void combi(int idx, int count) {
		if (count == N / 2) {
			diff();
			return;
		}

		for (int i = idx; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				combi(i, count + 1);
				visited[i] = false;
			}
		}

	}

	static void diff() {
		int start = 0;
		int link = 0;
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(visited[r]==true && visited[c]==true) {
					start += map[r][c];
				}
				
				if(visited[r]==false && visited[c]==false) {
					link += map[r][c];
				}
			}
		}
		
		int gap = Math.abs(start - link);
		min = Math.min(min, gap);
	}

}