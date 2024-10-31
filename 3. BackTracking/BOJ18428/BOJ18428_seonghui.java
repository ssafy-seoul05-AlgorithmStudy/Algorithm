import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// BOJ18428_seonghui
// 메모리 : 13108KB
// 시간 : 116ms

public class BOJ18428_seonghui {
	static int n;
	static String[][] arr;
	static List<int[]> list = new ArrayList<>(); // 장애물 놓을 수 있는 위치 저장
	static boolean flag = false;
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new String[n][n];
		for(int r = 0; r < n; r++) {
			for(int c = 0; c < n; c++) {
				arr[r][c] = sc.next();
				
				// 장애물 놓을 수 있는 전체 빈공간 정보 저장
				if(arr[r][c].equals("X")) {
					list.add(new int[] {r,c});
				}
			}
		}
		
		// 조합으로 빈 공간 3개 뽑기
		comb(0,0);
		
		String ans = flag? "YES" : "NO";
		System.out.println(ans);
	}//main
	
	static void comb(int idx, int sidx) {
		if(sidx == 3) {
			// 3개 선택완료하면 -> 선생님 감시 피할 수 있는지 확인
			chkTeacherSight(); 
			return;
		}
		
		for(int i = idx; i < list.size(); i++) {
			int[] pos = list.get(i);
			arr[pos[0]][pos[1]] = "O"; // 장애물 설치
			comb(i+1, sidx+1);
			arr[pos[0]][pos[1]] = "X"; // 원래로 복구
		}
	}
	
	static void chkTeacherSight() {
		for(int r = 0; r < n; r++) {
			for(int c = 0; c < n; c++) {
				if(arr[r][c].equals("T")) {
					// 학생 감시되는 즉시 바로 종료
					if(find(r,c)) {
						return;
					}
				}
			}
		}
		flag = true; // 모두 감시되지 않으면 flag 변경
	}
	
	static boolean find(int r, int c) {
		for(int k = 0; k < 4; k++) {
			
			// 한 방향으로 쭉 가야해서 갱신하기위해 출발하는 위치 저장
			int nextRow = r;
			int nextCol = c;
			
			// 조건에 걸릴때까지 한 방향으로 계속 감시
			while(true) {
				nextRow += dr[k];
				nextCol += dc[k];
				
				// 복도범위 벗어나거나 장애물 발견 시 종료
				if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n || arr[nextRow][nextCol].equals("O")) break;
				// 학생 발견시 false 반환
				if(arr[nextRow][nextCol].equals("S")) {
					return true;
				}
			}
		}
		return false;
	}
}


