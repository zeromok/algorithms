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

			 N = Integer.parseInt(br.readLine());
			grid = new int[N + 1][N + 1];

			for (int r = 1; r <= N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 1; c <= N; c++) {
					grid[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			bfs(1, 2, 0);

			bw.write(answer + "");

			bw.flush();
		}
	}

	private static void bfs(int r, int c, int dir) {
		if (r == N && c == N) {
			answer++;
			return;
		}

		if (dir == 0 || dir == 2) {
			if (inBounds(r, c + 1) && canMove(r, c+1)) {
				bfs(r, c + 1, 0);
			}
		}

		if (dir == 1 || dir == 2) {
			if (inBounds(r + 1, c) && canMove(r + 1, c)) {
				bfs(r + 1, c, 1);
			}
		}

		if (inBounds(r + 1, c + 1)) {
			if (isDiagonal(r, c)) {
				bfs(r + 1, c + 1, 2);
			}
		}
	}

	private static boolean isDiagonal(int r, int c) {
		return grid[r][c + 1] == 0 && grid[r + 1][c] == 0 && grid[r + 1][c + 1] == 0;
	}

	private static boolean canMove(int r, int c) {
		return grid[r][c] == 0;
	}

	private static boolean inBounds(int r, int c) {
		return r >= 1 && r <= N && c >= 1 && c <= N;
	}
}