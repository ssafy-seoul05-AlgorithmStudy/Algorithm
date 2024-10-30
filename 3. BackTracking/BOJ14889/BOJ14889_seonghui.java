import java.util.Scanner;

// BOJ14889_seonghui
// 메모리 : 16052KB
// 시간 : 368ms

public class BOJ14889_seonghui {
	static int n;
	static int[][] arr;
	static int min = Integer.MAX_VALUE;
	static boolean[] selected; // 팀원 선택 
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new int[n][n];
		selected = new boolean[n];
		for(int r = 0; r < n; r++) {
			for(int c = 0; c< n; c++) {
				arr[r][c] = sc.nextInt();
			}
		}
		
		
		rec(0,0);
		System.out.println(min);
	}
	
	static void rec(int idx, int sidx) {
		// 팀 반으로 나눠졌을때 계산
		if(sidx == n/2) {
			cal();
			return;
		}
		
		for(int i = idx; i < n; i++) {
			if(!selected[i]) {
				selected[i] = true; // 팀으로 선택
				rec(i+1, sidx+1);
				selected[i] = false; // 팀에서 제외
			}
		}
	}
	
	// 능력치 차이 계산
	static void cal() {
		int teamA = 0;
		int teamB = 0;
		
		// 각 팀 능력치 계산
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(selected[i] && selected[j]) {
					teamA+=arr[i][j];
				}
				
				if(!selected[i] && !selected[j]) {
					teamB+=arr[i][j];
				}
			}
		}
		min = Math.min(min, Math.abs(teamA-teamB));
	}
}
