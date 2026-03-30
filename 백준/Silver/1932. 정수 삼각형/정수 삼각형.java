import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int[][] triangle;
	static int[][] dp;


	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n = Integer.parseInt(br.readLine());

			triangle = new int[n][n];
			for (int i = 0; i < n; i++) {
				String[] split = br.readLine().split(" ");
				for (int j = 0; j <= i; j++) {
					triangle[i][j] = Integer.parseInt(split[j]);
				}
			}

			dp = new int[n][n];
			dp[0][0] = triangle[0][0];

			for (int i = 1; i < n; i++) {
				for (int j = 0; j <= i; j++) {
					if (j == 0) {
						dp[i][j] = triangle[i][j] + dp[i - 1][j];
					} else if (j == i) {
						dp[i][j] = triangle[i][j] + dp[i - 1][j - 1];
					} else {
						dp[i][j] = triangle[i][j] + Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
					}
				}
			}

			int result = Integer.MIN_VALUE;;
			for (int i = 0; i < n; i++) {
				result = Math.max(result, dp[n - 1][i]);
			}

			bw.write(result + "");

			bw.flush();
		}
	}
}