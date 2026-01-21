import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int N;
	static char[][] grid;
	static boolean[][] generalVisited;
	static boolean[][] redGreenVisited;
	static int generalCnt;
	static int redGreenCnt;

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			N = Integer.parseInt(br.readLine());
			grid = new char[N][N];
			generalVisited = new boolean[N][N];
			redGreenVisited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				char[] line = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					grid[i][j] = line[j];
				}
			}

			dfs();

			bw.write(generalCnt + " " + redGreenCnt);
			bw.flush();
		}
	}

	private static void dfs() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!generalVisited[i][j]) {
					generalCnt++;
					generalHelper(i, j);
				}
				if (!redGreenVisited[i][j]) {
					redGreenCnt++;
					redGreenHelper(i, j);
				}
			}
		}
	}

	private static void redGreenHelper(int x, int y) {
		redGreenVisited[x][y] = true;

		for (int[] direction : DIRECTIONS) {
			int nx = x + direction[0];
			int ny = y + direction[1];

			if (nx >= 0 && nx < N && ny >= 0 && ny < N && !redGreenVisited[nx][ny]) {
				if (isSameColor(grid[x][y], grid[nx][ny])) {
					redGreenHelper(nx, ny);
				}
			}
		}
	}

	private static void generalHelper(int x, int y) {
		generalVisited[x][y] = true;

		for (int[] direction : DIRECTIONS) {
			int nx = x + direction[0];
			int ny = y + direction[1];

			if (nx >= 0 && nx < N && ny >= 0 && ny < N && !generalVisited[nx][ny]) {
				if (grid[x][y] == grid[nx][ny]) {
					generalHelper(nx, ny);
				}
			}
		}
	}

	private static boolean isSameColor(char curr, char next) {
		if (curr == next)
			return true;
		if ((curr == 'R' || curr == 'G') && (next == 'R' || next == 'G')) {
			return true;
		}
		return false;
	}
}