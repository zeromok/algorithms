import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[][] paper;
	static boolean[][] visited;
	static int maxSum;
	static int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			paper = new int[N][M];
			visited = new boolean[N][M];
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				for (int m = 0; m < M; m++) {
					paper[n][m] = Integer.parseInt(st.nextToken());
				}
			}

			dfs();

			bw.write(maxSum + "");

			bw.flush();
		}
	}

	private static void dfs() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				helper(i, j, 1, paper[i][j]);
				visited[i][j] = false;
				checkT(i, j);
			}
		}
	}

	private static void checkT(int x, int y) {
		int depth = 0;
		int min = Integer.MAX_VALUE;
		int sum = paper[x][y];

		for (int[] direction : DIRECTIONS) {
			int nx = x + direction[0];
			int ny = y + direction[1];

			if (rangeChk(nx, ny)) {
				depth++;
				min = Math.min(min, paper[nx][ny]);
				sum += paper[nx][ny];
			}
		}

		if (depth == 3) {
			maxSum = Math.max(maxSum, sum);
		} else if (depth == 4) {
			maxSum = Math.max(maxSum, sum - min);
		}
	}

	private static void helper(int x, int y, int depth, int sum) {
		if (depth == 4) {
			maxSum = Math.max(maxSum, sum);
			return;
		}

		for (int[] direction : DIRECTIONS) {
			int nx = x + direction[0];
			int ny = y + direction[1];

			if (rangeChk(nx, ny) && !visited[nx][ny]) {
				visited[nx][ny] = true;
				helper(nx, ny, depth + 1, sum + paper[nx][ny]);
				visited[nx][ny] = false;
			}
		}
	}

	private static boolean rangeChk(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < M);
	}
}