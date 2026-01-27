import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int M;
	static int N;
	static int H;
	static int[][][] box;
	static boolean[][][] visited;
	static int[][] DIRECTIONS = {{-1, 0, 0}, {1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}};
	static int unripeCnt;
	static int minDays;

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			box = new int[H][N][M];
			visited = new boolean[H][N][M];

			Queue<int[]> queue = new LinkedList<>();
			for (int h = 0; h < H; h++) {
				for (int n = 0; n < N; n++) {
					st = new StringTokenizer(br.readLine());
					for (int m = 0; m < M; m++) {
						box[h][n][m] = Integer.parseInt(st.nextToken());
						if (box[h][n][m] == 0) {
							unripeCnt++;
						}
						if (box[h][n][m] == 1) {
							queue.offer(new int[] {h, n, m});
						}
					}
				}
			}

			bfs(queue);

			int result = (unripeCnt == 0) ? minDays : -1;
			bw.write(result + "");
			bw.flush();
		}
	}

	private static void bfs(Queue<int[]> queue) {
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();

			minDays = box[curr[0]][curr[1]][curr[2]] - 1;

			for (int[] direction : DIRECTIONS) {
				int nh = curr[0] + direction[0];
				int nx = curr[1] + direction[1];
				int ny = curr[2] + direction[2];

				if (rangeChk(nh, nx, ny) && !visited[nh][nx][ny] && box[nh][nx][ny] == 0) {
					queue.offer(new int[] {nh, nx, ny});
					box[nh][nx][ny] = box[curr[0]][curr[1]][curr[2]] + 1;
					visited[nh][nx][ny] = true;
					unripeCnt--;
				}
			}
		}
	}

	private static boolean rangeChk(int z, int x, int y) {
		return (z >= 0 && z < H && x >= 0 && x < N && y >= 0 && y < M);
	}
}