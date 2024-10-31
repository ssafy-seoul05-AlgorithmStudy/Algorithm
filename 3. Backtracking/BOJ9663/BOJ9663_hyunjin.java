package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ9663_N-Queen
// 메모리 : 12160KB
// 시간 : 6452ms
public class BOJ9663_hyunjin {
	static int N, count;
	static int[] board; // 각 열마다 퀸이 위치한 행의 정보를 저장하는 배열

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		board = new int[N];

		nQueen(0);
		System.out.println(count);

	}

	static void nQueen(int depth) {
		// 퀸이 갈 수 있는 모든 행을 다 돌았다면 count++
		if (depth == N) {
			count++;
			return;
		}

		for (int i = 0; i < N; i++) {
			// depth번째 열에서 퀸을 배치할 행 = i번째 행
			// ex) 0번째 열에서 2번째 행에 퀸 위치
			// -
			// -
			// O
			// -
			board[depth] = i;

			// 해당 위치에 queen을 배치할 수 있는지 확인
			if (posibility(depth)) {
				// 해당 위치에 가능하다면, 그 열에는 배치시키고,
				// 다음 열로 이동하여 배치
				nQueen(depth + 1);
			}

		}
	}

	// 해당 위치가 다른 퀸으로부터 위협 받는지 검사하는 조건문
	static boolean posibility(int col) {
		for (int i = 0; i < col; i++) {
			// col 열에 배치된 퀸의 행의 위치 == i열의 퀸 행의 위치 => 같은 행에 있다.
			if (board[col] == board[i]) {
				return false;
			}
			
			// 대각선에 위치한 경우
			else if(Math.abs(col-i) == Math.abs(board[col] - board[i])) {
				return false;
			}
		}
		
		
		return true;
	}
}
