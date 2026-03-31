import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			int T = Integer.parseInt(br.readLine());

			while (T-- > 0) {
				int n = Integer.parseInt(br.readLine());

				int[][] stickers = new int[2][n];
				int[][] dp = new int[2][n];

				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int i = 0; i < n; i++) {
					stickers[0][i] = Integer.parseInt(st.nextToken());
				}

				st = new StringTokenizer(br.readLine());
				for (int i = 0; i < n; i++) {
					stickers[1][i] = Integer.parseInt(st.nextToken());
				}

				dp[0][0] = stickers[0][0];
				dp[1][0] = stickers[1][0];

				if (n > 1) {
					dp[0][1] = stickers[0][1] + dp[1][0];
					dp[1][1] = stickers[1][1] + dp[0][0];
				}

				for (int i = 2; i < n; i++) {
					dp[0][i] = stickers[0][i] + Math.max(dp[1][i-1], dp[1][i-2]);
					dp[1][i] = stickers[1][i] + Math.max(dp[0][i-1], dp[0][i-2]);
				}

				bw.write(Math.max(dp[0][n - 1], dp[1][n - 1]) + "\n");
			}

			bw.flush();
		}
	}
}