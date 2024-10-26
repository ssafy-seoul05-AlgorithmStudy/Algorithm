package MST;

// 메모리 : 13244KB
// 시간 : 104ms
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

		p = new int[N + 1]; // 사람 번호 1부터 시작

		// 1. makeSet : 자기 자신을 대표로 만들기
		for (int i = 1; i < N + 1; i++) {
			p[i] = i;
		}

		// 2. 연결된 노드인지 확인
		for (int i = 0; i < M; i++) {
			List<Integer> peopleList = partyList[i];
			int firstPerson = peopleList.get(0);
			for (int j = 1; j < peopleList.size(); j++) {
				union(firstPerson, peopleList.get(j));
			}
		}

//		System.out.println(Arrays.toString(partyList));
//		System.out.println(Arrays.toString(p));

		// 3. 진실을 아는 사람과 모르는 사람 구분하기
		boolean[] knowTruth = new boolean[N + 1];
		for (int i = 0; i < knowTruePersonCnt; i++) {
			knowTruth[findSet(truePersonNum[i])] = true;
		}
		
		System.out.println(Arrays.toString(knowTruth));
		
		// 4. 진실을 말할 수 있는 파티수 세기 
		int lieCount = 0;
		for (int i = 0; i < M; i++) {
		    List<Integer> party = partyList[i];
		    boolean canLie = true;
		    for (int person : party) {
		        if (knowTruth[findSet(person)]) {
		            canLie = false;
		            break;
		        }
		    }
		    if (canLie) lieCount++;
		}

		System.out.println(lieCount);

	}

	static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);

		if (px != py) {
			p[px] = py;
		}
	}

	static int findSet(int x) {
		if (x != p[x])
			p[x] = findSet(p[x]);
		return p[x];
	}
}