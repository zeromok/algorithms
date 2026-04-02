import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[] maxDP;
	static int[] minDP;

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			int N = Integer.parseInt(br.readLine());

			maxDP = new int[3];
			minDP = new int[3];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 3; i++) {
				int value = Integer.parseInt(st.nextToken());
				maxDP[i] = minDP[i] = value;
			}

			for (int i = 1; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());

				int max0 = a + Math.max(maxDP[0], maxDP[1]);
				int max1 = b + Math.max(maxDP[0], Math.max(maxDP[1], maxDP[2]));
				int max2 = c + Math.max(maxDP[1], maxDP[2]);

				maxDP[0] = max0;
				maxDP[1] = max1;
				maxDP[2] = max2;

				int min0 = a + Math.min(minDP[0], minDP[1]);
				int min1 = b + Math.min(minDP[0], Math.min(minDP[1], minDP[2]));
				int min2 = c + Math.min(minDP[1], minDP[2]);

				minDP[0] = min0;
				minDP[1] = min1;
				minDP[2] = min2;
			}

			int resultMax = Math.max(maxDP[0], Math.max(maxDP[1], maxDP[2]));
			int resultMin = Math.min(minDP[0], Math.min(minDP[1], minDP[2]));

			bw.write(resultMax + " " + resultMin);

			bw.flush();
		}
	}
}