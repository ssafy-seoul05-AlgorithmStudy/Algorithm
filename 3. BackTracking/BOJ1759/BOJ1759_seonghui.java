import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BOJ1759_seonghui {
	static int l,c;
	static List<String> list;
	static boolean[] selected;
	static StringBuilder sb;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		l = sc.nextInt();
		c = sc.nextInt();
		list = new ArrayList<>();
		selected = new boolean[c];
		sb = new StringBuilder();
		for(int i = 0; i < c; i++) {
			list.add(sc.next());
		}
		
		Collections.sort(list);
		comb(0,0);
		System.out.println(sb.toString());
	}//main
	
	static void comb(int idx, int sidx) {
		if(sidx == l) {
			
			// 자음 및 모음 갯수 체크할 변수
			int a = 0;
			int b = 0;
			
			// 최소 한개의 모음, 최소 두 개의 자음으로 구성됐는지 확인
			for(int i = 0; i < c; i++) {
				if(selected[i]) { // 뽑힌 문자면 -> 자음인지 모음인지 구분
					String elem = list.get(i);
					if("aeiou".contains(elem)) {
						b++;
					}else {
						a++;
					}
					
				}
			}
			
			if(a >= 2 && b >= 1) {
				for(int i = 0; i < c; i++) {
					if(selected[i]) {
						sb.append(list.get(i));
					}
				}
				sb.append("\n");
			}
			return;
		}
		
		for(int i = idx; i < c; i++) {
			if(!selected[i]) {
				selected[i] = true;
				comb(i+1, sidx+1);
				selected[i] = false;
			}
		}
	}
}
