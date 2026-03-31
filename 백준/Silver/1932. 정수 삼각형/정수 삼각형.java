import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n = Integer.parseInt(br.readLine());

			int[][] dp = new int[n][n];
			dp[0][0] = Integer.parseInt(br.readLine());

			for (int i = 1; i < n; i++) {
				String[] split = br.readLine().split(" ");
				for (int j = 0; j <= i; j++) {
					if (j == 0) {
						dp[i][j] = Integer.parseInt(split[j]) + dp[i - 1][j];
					} else if (j == i) {
						dp[i][j] = Integer.parseInt(split[j]) + dp[i - 1][j - 1];
					} else {
						dp[i][j] = Integer.parseInt(split[j]) + Math.max(dp[i - 1][j], dp[i - 1][j - 1]);
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