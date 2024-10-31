package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// BOJ14889_스타트와 링크
// 재귀 2번 돌리기 / for문 돌면서 값 비교
// 메모리 : 28548KB / 36228KB
// 시간 : 288ms / 312ms

public class BOJ14889_hyunjin {
	static int N, M, combCnt, teamAbility;
	static int[] arr, result, result2;
	static boolean[] isChecked, isChecked2;
	static int[][] ability;
	static List<int[]> team;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 전체 인원수
		M = N / 2; // 한 팀의 인원

		arr = new int[N]; // 조합을 위한 배열
		for (int i = 0; i < N; i++) {
			arr[i] = i + 1;
		}

		ability = new int[N][N]; // 능력치 배열
		team = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ability[i][j] = sc.nextInt();
			}
		}

		isChecked = new boolean[N];
		result = new int[M];

		isChecked2 = new boolean[M];
		result2 = new int[2];

		// 1. 조합으로 팀 나누기
		comb(0, 0);

		// 2. 각 팀별로 능력치 계산하기

		int ans = Integer.MAX_VALUE;
//		for (int i = 0; i < team.size() / 2; i++) {
////			System.out.println(Arrays.toString(team.get(i)));
//			teamAbility = 0;
//			comb2(0, 0, team.get(i));
//			int sum1 = teamAbility;
////			System.out.println("teamAbility1 : " + sum1);
////			System.out.println(Arrays.toString(team.get(team.size()-1-i)));
//			teamAbility = 0;
//			comb2(0, 0, team.get(team.size() - 1 - i));
//			int sum2 = teamAbility;
////			System.out.println("teamAbility2 : " + sum2);
////			System.out.println("---------------");
//
//			ans = Math.min(ans, Math.abs(sum1 - sum2));
//
//		}

		List<Integer> scoreArr = new ArrayList<>();
		for (int[] intArr : team) {
			System.out.println(Arrays.toString(intArr));
			int t = 0;
			
			for(int i :intArr) {
				for(int j : intArr) {
					if(i==j) continue;
					t+=ability[i-1][j-1];
				}
			}
			
			System.out.println(t);
			scoreArr.add(t);
		}
		System.out.println(scoreArr);
		
		
		// 3. 능력치의 차이 최소값 구하기
		for(int i=0; i<scoreArr.size()/2; i++){
			ans = Math.min(ans, Math.abs(scoreArr.get(i) - scoreArr.get(scoreArr.size()-1-i)));
		}
		System.out.println(ans);

	}

	static void comb(int idx, int cnt) {
		if (cnt == M) {
			combCnt++;
			int[] newArr = new int[M];
			for (int i = 0; i < M; i++) {
				newArr[i] = result[i];
			}
			team.add(newArr);
			return;
		}

		for (int i = idx; i < N; i++) {
			if (isChecked[i])
				continue;

			result[cnt] = arr[i];
			isChecked[i] = true;
			comb(i, cnt + 1);
			isChecked[i] = false;
		}
	}

	static void comb2(int idx, int cnt, int[] intArr) {
		if (cnt == 2) {
			int a = result2[0];
			int b = result2[1];
//			System.out.println("a : " + a + " b : " + b);

			teamAbility += (ability[a - 1][b - 1] + ability[b - 1][a - 1]);
//			for(int a : result2) {
//				System.out.print(a + " ");
//			}
			return;
		}

		for (int i = idx; i < M; i++) {
			if (isChecked2[i])
				continue;

			result2[cnt] = intArr[i];
			isChecked2[i] = true;
			comb2(i, cnt + 1, intArr);
			isChecked2[i] = false;
		}
	}
}
