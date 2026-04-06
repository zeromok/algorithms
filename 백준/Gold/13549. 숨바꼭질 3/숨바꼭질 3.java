import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {


	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			int result = bfs(N, K);
			bw.write(result + "");

			bw.flush();
		}
	}

	private static int bfs(int n, int k) {
		int MAX = 100_000;
		int[] time = new int[MAX + 1];
		Arrays.fill(time, -1);

		if (n >= k) {
			return n - k;
		}

		Deque<Integer> deque = new ArrayDeque<>();
		deque.add(n);
		time[n] = 0;

		while (!deque.isEmpty()) {
			Integer curr = deque.pollFirst();

			if (curr == k) {
				return time[curr];
			}

			int teleport = curr * 2;
			if (teleport <= MAX && time[teleport] == -1) {
				time[teleport] = time[curr];
				deque.addFirst(teleport);
			}

			int walkBack = curr - 1;
			if (walkBack >= 0 && time[walkBack] == -1) {
				time[walkBack] = time[curr] + 1;
				deque.addLast(walkBack);
			}

			int walkFront = curr + 1;
			if (walkFront <= MAX && time[walkFront] == -1) {
				time[walkFront] = time[curr] + 1;
				deque.addLast(walkFront);
			}
		}

		return -1;
	}
}
