import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] grid;
	static int[][][] dp;

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

			dp = new int[N + 1][N + 1][3];
			dp[1][2][0] = 1;

			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
					for (int dir = 0; dir < 3; dir++) {
						if (dp[r][c][dir] == 0) {
							continue;
						}
						move(r, c, dir);
					}
				}
			}

			bw.write(dp[N][N][0] + dp[N][N][1] + dp[N][N][2] + "");

			bw.flush();
		}
	}

	private static void move(int r, int c, int dir) {
		int count = dp[r][c][dir];

		if (dir == 0 || dir == 2) {
			if (canMove(r, c + 1)) {
				dp[r][c + 1][0] += count;
			}
		}

		if (dir == 1 || dir == 2) {
			if (canMove(r + 1, c)) {
				dp[r+1][c][1] += count;
			}
		}

		if (isDiagonal(r, c)) {
			dp[r + 1][c + 1][2] += count;
		}
	}

	private static boolean isDiagonal(int r, int c) {
		return inBounds(r + 1, c + 1) && grid[r][c + 1] == 0 && grid[r + 1][c] == 0 && grid[r + 1][c + 1] == 0;
	}

	private static boolean canMove(int r, int c) {
		return inBounds(r, c) && grid[r][c] == 0;
	}

	private static boolean inBounds(int r, int c) {
		return r >= 1 && r <= N && c >= 1 && c <= N;
	}
}