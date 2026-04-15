import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] grid;
	static int answer = 0;


	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			N = Integer.parseInt(br.readLine().trim());
			grid = new int[N + 1][N + 1];

			for (int r = 1; r <= N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 1; c <= N; c++) {
					grid[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			// 시작: (1,1)-(1,2) 가로 → 꼬리 (1,2), 방향 가로(0)
			dfs(1, 2, 0);
			System.out.println(answer);

			bw.flush();
		}
	}

	private static void dfs(int r, int c, int dir) {
		if (r == N && c == N) {
			answer++;
			return;
		}

		// 가로 이동
		if (dir == 0 || dir == 2) {
			if (r >= 1 && r <= N && c + 1 >= 1 && c + 1 <= N && grid[r][c + 1] == 0) {
				dfs(r, c + 1, 0);
			}
		}

		// 세로 이동
		if (dir == 1 || dir == 2) {
			if (r + 1 >= 1 && r + 1 <= N && c >= 1 && c <= N && grid[r + 1][c] == 0) {
				dfs(r + 1, c, 1);
			}
		}

		// 대각선 이동
		if (r + 1 >= 1 && r + 1 <= N && c + 1 >= 1 && c + 1 <= N) {
			if (grid[r + 1][c + 1] == 0 && grid[r][c + 1] == 0 && grid[r + 1][c] == 0) {
				dfs(r + 1, c + 1, 2);
			}
		}
	}
}