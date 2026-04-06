import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n = Integer.parseInt(br.readLine());
			dp = new int[n][n];
			dp[0][0] = Integer.parseInt(br.readLine());

			for (int i = 1; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j <= i; j++) {
					int value = Integer.parseInt(st.nextToken());
					if (j == 0) {
						dp[i][j] = value + dp[i - 1][0];
					} else if (i == j) {
						dp[i][j] = value + dp[i - 1][j - 1];
					} else {
						dp[i][j] = value + Math.max(dp[i - 1][j], dp[i - 1][j - 1]);
					}
				}
			}

			int max = Integer.MIN_VALUE;
			for (int i = 0; i < n; i++) {
				if (dp[n - 1][i] > max) {
					max = dp[n - 1][i];
				}
			}
			bw.write(max + "");

			bw.flush();
		}
	}
}