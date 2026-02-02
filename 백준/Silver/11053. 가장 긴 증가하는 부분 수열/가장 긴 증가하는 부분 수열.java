import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] arr;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			dp = new int[N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				dp[i] = 1;
			}

			for (int i = 1; i < N; i++) {
				for (int j = 0; j < i; j++) {
					if (arr[j] < arr[i]) {
						dp[i] = Math.max(dp[i], dp[j] + 1);
					}
				}
			}

			int maxLen = 0;
			for (int length : dp) {
				maxLen = Math.max(maxLen, length);
			}
			bw.write(maxLen + "");

			bw.flush();
		}
	}
}