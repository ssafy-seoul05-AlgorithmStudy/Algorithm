import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BOJ10026_seonghui {
   static int n;
   static char[][] grid;
   static boolean[][] visited1;
   static boolean[][] visited2;
   static int cnt1, cnt2;
   
   static int[] dr = {-1,1,0,0};
   static int[] dc = {0,0,-1,1};
   
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      n = sc.nextInt();
      grid = new char[n][n];
      visited1 = new boolean[n][n];
      visited2 = new boolean[n][n];
      
      for(int r = 0; r < n; r++) {
         String s = sc.next();
         for(int c = 0; c < n; c++) {
            grid[r][c] = s.charAt(c);
         }
      }
      
      // case1
      for(int r = 0; r < n; r++) {
         for(int c = 0; c < n; c++) {
        	if(!visited1[r][c]) {
        		bfs(r,c,grid[r][c],false);
        		cnt1++;
        	}
         }
      }
      
      // case2
      for(int r = 0; r < n; r++) {
         for(int c = 0; c < n; c++) {
        	 if(!visited2[r][c]) {
         		bfs(r,c,grid[r][c],true);
         		cnt2++;
         	}
         }
      }
      System.out.println(cnt1 + " " + cnt2);
   }
   
   static void bfs(int r, int c, char color, boolean chk) {
      Queue<int[]> q = new ArrayDeque<>();
      q.add(new int[] {r,c});
      if(chk) { // 적록색약이면
    	  visited2[r][c] = true;
      }
      else {
    	  visited1[r][c] = true;
      }
      
      while(!q.isEmpty()) {
         int[] tmp = q.poll();
         int curRow = tmp[0];
         int curCol = tmp[1];
         
         for(int k = 0; k < 4; k++) {
            int nextRow = curRow + dr[k];
            int nextCol = curCol + dc[k];
            
            if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
            
            
            if(chk) {
            	if(!visited2[nextRow][nextCol]) {
                	if((color == 'R' || color == 'G') && (grid[nextRow][nextCol] == 'R' || grid[nextRow][nextCol] == 'G')) {
                        q.add(new int[] {nextRow, nextCol});
                        visited2[nextRow][nextCol] = true;
                     }
                	else if(color == 'B' && grid[nextRow][nextCol] == color) {
                        q.add(new int[] {nextRow, nextCol});
                        visited2[nextRow][nextCol] = true;
                     }
                }
            	
            } else {
            	if(!visited1[nextRow][nextCol] && grid[nextRow][nextCol] == color) {
                    q.add(new int[] {nextRow, nextCol});
                    visited1[nextRow][nextCol] = true;
                 }
            }
         }
      }
   }
}
