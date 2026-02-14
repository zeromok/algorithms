import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int A;
	static int B;
	static int minValue = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			dfs(B, 1);

			if (minValue == Integer.MAX_VALUE) {
				bw.write(-1 + "");
			} else {
				bw.write(minValue + "");
			}

			bw.flush();
		}
	}

	private static void dfs(int start, int depth) {
		if (start == A) {
			minValue = Math.min(depth, minValue);
			return;
		}

		if (start < A) {
			return;
		}

		if (start % 2 == 0) {
			dfs(start / 2, depth + 1);
		}

		if (start % 10 == 1) {
			dfs(start / 10, depth + 1);
		}
	}
}