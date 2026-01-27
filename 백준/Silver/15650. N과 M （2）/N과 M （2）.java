import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] numbers = new int[N];
			for (int i = 0; i < N; i++) {
				numbers[i] = i + 1;
			}

			List<int[]> result = new ArrayList<>();
			combination(numbers, M, 0, new int[M], 0, result);
			for (int[] ints : result) {
				for (int num : ints) {
					bw.write(num + " ");
				}
				bw.write("\n");
			}

			bw.flush();
		}
	}

	private static void combination(int[] arr, int m, int start, int[] curr, int depth, List<int[]> result) {
		if (depth == m) {
			result.add(curr.clone());
			return;
		}

		for (int i = start; i < arr.length; i++) {
			curr[depth] = arr[i];
			combination(arr, m, i + 1, curr, depth + 1, result);
		}
	}
}