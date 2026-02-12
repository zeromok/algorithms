import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[] arr;
	static int[] result;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			arr = new int[N];
			result = new int[M];
			visited = new boolean[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(arr);

			permutation(0);

			bw.flush();
		}
	}

	private static void permutation(int depth) {
		if (depth == M) {
			for (int num : result) {
				System.out.println(num);
			}
			return;
		}

		int prev = -1;

		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;
			if (arr[i] == prev)
				continue;


			visited[i] = true;
			result[depth] = arr[i];
			prev = arr[i];
			permutation(depth + 1);
			visited[i] = false;
		}
	}
}
