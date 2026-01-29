import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			combination(1, 0, new int[M]);

			bw.flush();
		}
	}

	private static void combination(int start, int depth, int[] curr) {
		if (depth == M) {
			for (int i : curr) {
				System.out.print(i + " ");
			}
			System.out.println();
			return;
		}

		for (int next = start; next <= N; next++) {
			curr[depth] = next;
			combination(next, depth + 1, curr);
		}

	}
}