import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BOJ1987_seonghui {
	static int row,col;
	static char[][] board;
	static boolean[][] visited;
	static Set<Character> chk = new HashSet<>();
	static int max = Integer.MIN_VALUE;
	static int cnt;
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		row = sc.nextInt();
		col = sc.nextInt();
		board = new char[row][col];
		visited = new boolean[row][col];
		
		for(int i = 0; i < row; i++) {
			String s = sc.next();
			for(int j = 0; j < col; j++) {
				board[i][j] = s.charAt(j);
			}
		}
		dfs(0,0);
		System.out.println(max);
	}
	
	static void dfs(int r, int c) {
		if(chk.add(board[r][c])) { // set추가 성공하면
			cnt++;
			max = Math.max(max, cnt);
			
			for(int k = 0; k < 4; k++) {
				int nextRow = r + dr[k];
				int nextCol = c + dc[k];
				
				if(nextRow < 0 || nextRow >= row || nextCol < 0 || nextCol >= col || visited[nextRow][nextCol]) continue;
				dfs(nextRow, nextCol);
				
			}
			chk.remove(board[r][c]);
			cnt--;
		}
	}
}


