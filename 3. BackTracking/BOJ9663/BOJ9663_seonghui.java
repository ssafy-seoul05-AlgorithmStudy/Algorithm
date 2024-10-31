import java.util.Scanner;

public class BOJ9663_seonghui {
	static int n, ans;
	static int[] arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		ans = 0;
		arr = new int[n]; // 퀸의 위치 저장 배열, i번째열에 놓일 행위치
		
		putQueen(0); // 첫번재 열부터 퀸 두기
		System.out.println(ans);
	}// main
	
	static void putQueen(int col) {
		if(col == n) {
			ans++;
			return;
		}
		
		for(int i = 0; i < n; i++) {
			arr[col] = i;
			if(canPutChk(col)) {
				putQueen(col+1);
			}
		}
		
	}
	static boolean canPutChk(int col) {
		for(int i = 0; i < col; i++) {
			// 같은 행에있는지 확인
			if(arr[col] == arr[i]) {
				return false;
			}
			// 대각선에 있는지 확인(두 퀸의 행의 차이와 열의 차이가 같은지 판단)
			if(Math.abs(arr[col] - arr[i]) == Math.abs(col - i)) {
				return false;
			}
		}
		return true;
	}
}
