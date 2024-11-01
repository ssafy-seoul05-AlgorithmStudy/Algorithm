import java.util.Arrays;
import java.util.Scanner;

public class BOJ1759 {

	static int L, C, mocnt, jacnt;
	static char[] arr;
	static boolean[] visited;
	static char[] selected;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		L = sc.nextInt();
		C = sc.nextInt();
		arr = new char[C];
		visited = new boolean[C];
		selected = new char[L];
		String str = "";

		for (int i = 0; i < C; i++) {
			str += sc.next();
		}

		arr = str.toCharArray();
		Arrays.sort(arr);

		combi(0, 0);
	}

	static void combi(int idx, int depth) {
		if (depth == L) {
			check();
			if (check()) {
				for(int i = 0; i < L; i++) {
					System.out.print(selected[i]);
				}
				System.out.println();
			}
			return;
		}

		for (int i = idx; i < C; i++) {
			if (!visited[i]) {
				visited[i] = true;
				selected[depth] = arr[i];
				combi(i + 1, depth + 1);
				visited[i] = false;
			}
		}
	}

	static boolean check() {
		mocnt = 0;
		jacnt = 0;
		for(int i = 0 ; i < L; i++) {
			if(selected[i]=='a' ||selected[i]=='e' ||selected[i]=='i' ||selected[i]=='o' ||selected[i]=='u') {
				mocnt++;
			} else {
				jacnt++;
			}
		}
		if(mocnt >= 1 && jacnt >= 2) {
			return true;
		} else {
			return false;
		}
	}

}