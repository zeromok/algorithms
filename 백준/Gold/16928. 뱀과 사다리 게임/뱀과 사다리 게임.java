import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] board = new int[101];

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			for (int i = 0; i < N + M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				board[from] = to;
			}

			bw.write(bfs() + "");

			bw.flush();
		}
	}

	private static int bfs() {
		Queue<Integer> queue = new LinkedList<>();
		int dist[] = new int[101];
		Arrays.fill(dist, -1);

		queue.offer(1);
		dist[1] = 0;

		while (!queue.isEmpty()) {
			int curr = queue.poll();

			if (curr == 100) {
				return dist[curr];
			}

			for (int i = 1; i <= 6; i++) {
				int next = curr + i;

				if (next > 100) {
					continue;
				}

				if (board[next] != 0) {
					next = board[next];
				}

				if (dist[next] == -1) {
					dist[next] = dist[curr] + 1;
					queue.offer(next);
				}
			}
		}
		return -1;
	}
}