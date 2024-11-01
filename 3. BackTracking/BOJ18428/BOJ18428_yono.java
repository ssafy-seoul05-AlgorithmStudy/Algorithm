import java.util.Arrays;
import java.util.Scanner;

public class BOJ18428 {

	static int N;
	static String[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static boolean answer;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		// initiate var
		N = sc.nextInt();
		map = new String[N][N];

		// input array
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = sc.next();
			}
		}

		// making object
		object(0);
		
		if(answer) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

	static void object(int count) {
		if (count == 3) {
			check();
			if (check()) {
			}
			return;
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c].equals("X")) {
					map[r][c] = "O";
					object(count + 1);
					map[r][c] = "X";
				}
			}
		}

	}

	static boolean check() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c].equals("T")) {
					int cr = r;
					int cc = c;
					for (int d = 0; d < 4; d++) {
						int nr = cr + dr[d];
						int nc = cc + dc[d];
						while (nr >= 0 && nc >= 0 && nr < N && nc < N && !map[nr][nc].equals("O")) {
							if (map[nr][nc].equals("S")) {
								return false;
							}
							nr = nr + dr[d];
							nc = nc + dc[d];
						}
					}
				}
			}
		}
		answer = true;
		return true;
	}
}