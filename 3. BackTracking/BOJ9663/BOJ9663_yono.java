import java.util.Scanner;

public class BOJ9663 {

	static int[] map;
	static int N, ans;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		map = new int[N];
		
		dfs(0);
		System.out.println(ans);

	}

	static void dfs(int row) {

		if (row == N) {
			ans++;
			return;
		}

		for (int i = 0; i < N; i++) {
			map[row] = i;
			if (possible(row)) {
				dfs(row + 1);
			}
		}

	}

	static boolean possible(int row) {
		for (int i = 0; i < row; i++) {
			if (map[row] == map[i]) {
				return false;
			}

			if (Math.abs(row - i) == Math.abs(map[row] - map[i])) {
				return false;
			}
		}
		return true;
	}

}