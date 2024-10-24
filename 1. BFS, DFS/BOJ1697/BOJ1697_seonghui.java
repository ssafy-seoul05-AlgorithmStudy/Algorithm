import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BOJ1697_seonghui {
	static int n, k;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		visited = new boolean[100000+1];
		
		// 이동방식 : x-1, x+1, x*2
		int res = bfs(n, 0); // 시작값, 0초
		System.out.println(res);
	}
	
	static int bfs(int n, int sec) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[]{n, sec});
		visited[n] = true;
		
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			int curr_x = tmp[0];
			int curr_sec = tmp[1];
			
			if(curr_x == k) return curr_sec;
			
			int next_sec = curr_sec+1;
			
			if(curr_x-1 >= 0 && !visited[curr_x-1]) {
				q.add(new int[] {curr_x-1, next_sec});
				visited[curr_x-1] = true;
			}
			if(curr_x+1 <= 100000 && !visited[curr_x+1]) {
				q.add(new int[] {curr_x+1,next_sec});
				visited[curr_x+1] = true;
			}
			if(curr_x*2 <= 100000 && !visited[curr_x*2]) {
				q.add(new int[] {curr_x*2, next_sec});
				visited[curr_x*2] = true;
			}
		}
		
		return 0;
	}
}
