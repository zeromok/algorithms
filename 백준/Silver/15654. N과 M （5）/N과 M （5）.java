import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[] nums;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			nums = new int[N];
			visited = new boolean[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(nums);
			combination(nums, 0, new int[M]);

			bw.flush();
		}
	}

	private static void combination(int[] arr, int depth, int[] curr) {
		if (depth == M) {
			for (int i : curr) {
				System.out.print(i + " ");
			}
			System.out.println();
			return;
		}

		for (int next = 0; next < arr.length; next++) {
			if (!visited[next]) {
				visited[next] = true;
				curr[depth] = arr[next];
				combination(arr, depth + 1, curr);
				visited[next] = false;
			}
		}

	}
}