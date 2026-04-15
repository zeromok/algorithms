import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static final int HORIZONTAL = 0, VERTICAL = 1, DIAGONAL = 2;
	static int N;
	static int[][] grid;
	static int[][][] dp;


	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			N = Integer.parseInt(br.readLine());

			grid = new int[N + 1][N + 1];
			dp = new int[N + 1][N + 1][3];

			for (int r = 1; r <= N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 1; c <= N; c++) {
					grid[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			dp[1][2][HORIZONTAL] = 1;

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

			int answer = dp[N][N][HORIZONTAL] + dp[N][N][VERTICAL] + dp[N][N][DIAGONAL];
			bw.write(answer + "");

			bw.flush();
		}
	}

	private static void move(int r, int c, int dir) {
		int count = dp[r][c][dir];

		// 가로 이동
		if (dir == HORIZONTAL || dir == DIAGONAL) {
			if (canMove(r, c + 1)) {
				dp[r][c + 1][HORIZONTAL] += count;
			}
		}

		// 세로 이동
		if (dir == VERTICAL || dir == DIAGONAL) {
			if (canMove(r + 1, c)) {
				dp[r + 1][c][VERTICAL] += count;
			}
		}

		// 대각선 이동
		if (canMoveDiagonal(r, c)) {
			dp[r + 1][c + 1][DIAGONAL] += count;
		}
	}

	private static boolean canMoveDiagonal(int r, int c) {
		return inBounds(r + 1, c + 1)
			&& grid[r][c + 1] == 0
			&& grid[r + 1][c] == 0
			&& grid[r + 1][c + 1] == 0;
	}

	private static boolean canMove(int r, int c) {
		return inBounds(r, c) && grid[r][c] == 0;
	}

	private static boolean inBounds(int r, int c) {
		return r >= 1 && r <= N && c >= 1 && c <= N;
	}
}