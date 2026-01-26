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
	static int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int maxSum;

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 행
			M = Integer.parseInt(st.nextToken()); // 열
			paper = new int[N][M];
			visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					paper[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					visited[i][j] = true;
					dfs(i, j, 1, paper[i][j]);
					visited[i][j] = false;
					checkT(i, j);
				}
			}
			bw.write(maxSum + "");

			bw.flush();
		}
	}

	private static void checkT(int x, int y) {
		int sum = paper[x][y];
		int count = 0;
		int min = Integer.MAX_VALUE;

		for (int[] direction : DIRECTIONS) {
			int nx = x + direction[0];
			int ny = y + direction[1];

			if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
				count++;
				int value = paper[nx][ny];
				sum += value;
				min = Math.min(min, value);
			}
		}

		if (count == 4) {
			maxSum = Math.max(maxSum, sum - min);
		} else if (count == 3) {
			maxSum = Math.max(maxSum, sum);
		}
	}

	private static void dfs(int x, int y, int depth, int sum) {
		if (depth == 4) {
			maxSum = Math.max(maxSum, sum);
			return;
		}

		for (int[] direction : DIRECTIONS) {
			int nx = x + direction[0];
			int ny = y + direction[1];

			if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {
				visited[nx][ny] = true;
				dfs(nx, ny, depth + 1, sum + paper[nx][ny]);
				visited[nx][ny] = false;
			}
		}
	}
}