import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Root {
		int value;
		String cmd;

		public Root(int value, String cmd) {
			this.value = value;
			this.cmd = cmd;
		}
	}

	static int A;
	static int B;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			int T = Integer.parseInt(br.readLine());
			while (T-- > 0) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				A = Integer.parseInt(st.nextToken());
				B = Integer.parseInt(st.nextToken());

				visited = new boolean[10_000];
				bw.write(bfs() + "\n");
			}

			bw.flush();
		}
	}

	private static String bfs() {
		Queue<Root> queue = new LinkedList<>();
		queue.offer(new Root(A, ""));
		visited[A] = true;

		while (!queue.isEmpty()) {
			Root curr = queue.poll();

			if (curr.value == B) {
				return curr.cmd;
			}

			int D = (curr.value * 2) % 10_000;
			int S = (curr.value == 0) ? 9999 : curr.value - 1;
			int L = leftRotation(curr.value);
			int R = rightRotation(curr.value);
			if (!visited[D]) {
				visited[D] = true;
				queue.offer(new Root(D, curr.cmd + "D"));
			}
			if (!visited[S]) {
				visited[S] = true;
				queue.offer(new Root(S, curr.cmd + "S"));
			}
			if (!visited[L]) {
				visited[L] = true;
				queue.offer(new Root(L, curr.cmd + "L"));
			}
			if (!visited[R]) {
				visited[R] = true;
				queue.offer(new Root(R, curr.cmd + "R"));
			}
		}
		return "";
	}

	private static int leftRotation(int n) {
		return (n % 1000) * 10 + (n / 1000);
	}

	private static int rightRotation(int n) {
		return (n % 10) * 1000 + (n / 10);
	}
}