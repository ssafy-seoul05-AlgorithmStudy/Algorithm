import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BOJ5427_seonghui {
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static int w, h;
	static char[][] map;
	static boolean[][] visited;
	static boolean escape;
	static int second;
	
	static Queue<int[]> fq = new ArrayDeque<>();
	static Queue<int[]> sq = new ArrayDeque<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		for(int tc = 0; tc < t; tc++) {
			w = sc.nextInt();
			h = sc.nextInt();
			map = new char[h][w];
			visited = new boolean[h][w];
			escape = false;
			second = 0;
			
			fq.clear();
			sq.clear();
			
			for(int r = 0; r < h; r++) {
				String s = sc.next();
				for(int c = 0; c < w; c++) {
					map[r][c] = s.charAt(c);
					// 상근, 불 같이 움직임
					// 불이 먼저 옮겨지고 -> 상근이가 가능한 이동 탐색
					if(map[r][c] == '*') {
						fq.add(new int[] {r,c}); // 현재 불 갯수만큼 추가됨
					}
					if(map[r][c] == '@') {
						sq.add(new int[] {r,c,second});
						visited[r][c] = true;
					}
				}
			}
			bfs();
			System.out.println(escape? second : "IMPOSSIBLE");
		}
	}
	
	static void bfs() {
		while(!sq.isEmpty()) {
			int fq_size = fq.size(); // 현재 턴에 추가된 불 수 만큼만 반복되도록 사이즈를 변수에 저장
			for(int i = 0; i < fq_size; i++) { // 각 불들이 독립적으로 탐색
				
				int[] tmp = fq.poll();
				
				for(int k = 0; k < 4; k++) {
					int nextRow = tmp[0] + dr[k];
					int nextCol = tmp[1] + dc[k];
					
					if(nextRow < 0 || nextRow >= h || nextCol < 0 || nextCol >= w) continue;
					if(map[nextRow][nextCol] == '.' || map[nextRow][nextCol] == '@') { // 빈공간이거나 처음 상근이의 위치면
						fq.add(new int[] {nextRow, nextCol});
						map[nextRow][nextCol] = '*'; // 불로 변경
					}
				}
			}
			
			int sq_size = sq.size();
			for(int i = 0; i < sq_size; i++) {
				
				int[] tmp = sq.poll();
				
				for(int k = 0; k < 4; k++) {
					int nextRow = tmp[0] + dr[k];
					int nextCol = tmp[1] + dc[k];
					int nextSec = tmp[2] + 1;
					
					if(nextRow < 0 || nextRow >= h || nextCol < 0 || nextCol >= w) {
						// 경계밖을 나갔으므로 탈출성공
						escape = true;
						second = nextSec;
						return;
					}
					
					if(map[nextRow][nextCol] == '.' && !visited[nextRow][nextCol]) {
						sq.add(new int[] {nextRow, nextCol, nextSec});
						visited[nextRow][nextCol] = true;
					}
				}
			}
		}
	}
}


