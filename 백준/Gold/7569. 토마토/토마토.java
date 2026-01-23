import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 위, 아래, 왼쪽, 오른쪽, 앞, 뒤
	static int[][] DIRECTIONS = {{1, 0, 0}, {-1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, 1}, {0, 0, -1}};
	static int[][][] box;
	static int M;
	static int N;
	static int H;
	static int days;
	static int unripeNum;

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			box = new int[H][N][M];
			Queue<int[]> queue = new LinkedList<>();

			for (int h = 0; h < H; h++) {
				for (int n = 0; n < N; n++) {
					st = new StringTokenizer(br.readLine());
					for (int m = 0; m < M; m++) {
						box[h][n][m] = Integer.parseInt(st.nextToken());
						if (box[h][n][m] == 1) {
							queue.offer(new int[] {h, n, m});
						}
						if (box[h][n][m] == 0) {
							unripeNum++;
						}
					}
				}
			}

			if (unripeNum == 0) {
				bw.write(0 + "");
				bw.flush();
				return;
			}

			bw.write(search(queue) + "");
			bw.flush();
		}
	}

	private static int search(Queue<int[]> queue) {
		while (!queue.isEmpty()) {
			int[] curr = queue.poll();

			days = box[curr[0]][curr[1]][curr[2]] - 1;

			for (int[] direction : DIRECTIONS) {
				int nh = direction[0] + curr[0];
				int nx = direction[1] + curr[1];
				int ny = direction[2] + curr[2];
				if (isValid(nh, nx, ny) && box[nh][nx][ny] == 0) {
					box[nh][nx][ny] = box[curr[0]][curr[1]][curr[2]] + 1;
					unripeNum--;
					queue.offer(new int[] {nh, nx, ny});
				}
			}
		}
		return unripeNum == 0 ? days : -1;
	}

	private static boolean isValid(int h, int n, int m) {
		return h >= 0 && h < H && n >= 0 && n < N && m >= 0 && m < M;
	}
}