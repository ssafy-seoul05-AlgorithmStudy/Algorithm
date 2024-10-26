package DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BOJ1043_거짓말 {
	static int[] p; // 대표를 저장하는 배열

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 사람의 수
		int M = sc.nextInt(); // 파티의 수

		p = new int[N + 1]; // 사람 번호 1부터 시작

		// 초기 대표 자기 자신으로 만들기
		for (int i = 1; i <= N; i++) {
			p[i] = i;
		}

		int knowTruePersonCnt = sc.nextInt(); // 진실을 아는 사람의 수
		int[] truePersonNum = new int[N]; // 진실을 아는 사람의 번호 배열

		if (knowTruePersonCnt == 0) { // 진실을 아는 사람이 없다면
			// 모든 파티에서 거짓말 가능
			System.out.println(M);
			return;
		} else {
			// 진실을 아는 사람이 있으면, 그 사람의 번호 저장
			for (int i = 0; i < knowTruePersonCnt; i++) {
				truePersonNum[i] = sc.nextInt();
			}
		}

		// 파티의 수 만큼 리스트 생성 => 각 리스트에 참여한 사람 추가
		List<Integer>[] partyList = new ArrayList[M];
		for (int i = 0; i < M; i++) {
			partyList[i] = new ArrayList<>();
			int partyPeople = sc.nextInt();
			for (int j = 0; j < partyPeople; j++) {
				partyList[i].add(sc.nextInt()); // i번째 list에 파티 참여 인원 번호 추가
			}
		}
		

		System.out.println(Arrays.toString(partyList));

	}
}