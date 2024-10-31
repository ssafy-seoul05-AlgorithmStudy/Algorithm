package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ9663_N-Queen
// 메모리 : 12160KB
// 시간 : 6452ms
public class BOJ9663_hyunjin {
	static int N, count;
	static int[] board;

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
			// depth번째 행에서 갈 수 있는 위치가 i개수
			// ex) 1행에서 퀸이 갈 수 있는 위치 i개
			board[depth] = i;

			// posibility() 해당 열에서 i번째 행에 놓을 수 있는지 검사
			if (posibility(depth)) {
				nQueen(depth + 1);
			}

		}
	}

	// 해당 위치가 다른 퀸으로부터 위협 받는지 검사하는 조건문
	static boolean posibility(int col) {
		for (int i = 0; i < col; i++) {
			// 해당 열의 행과 i열의 행이 일치하는 경우 == 같은 행에 위치함
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