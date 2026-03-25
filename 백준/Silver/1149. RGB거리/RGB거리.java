import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int[][] cost;

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			int N = Integer.parseInt(br.readLine());
			cost = new int[N][3];

			for (int i = 0; i < N; i++) {
				String[] split = br.readLine().split(" ");
				for (int j = 0; j < 3; j++) {
					cost[i][j] = Integer.parseInt(split[j]);
				}
			}

			for (int i = 1; i < N; i++) {
				cost[i][0] += Math.min(cost[i - 1][1], cost[i - 1][2]);
				cost[i][1] += Math.min(cost[i - 1][0], cost[i - 1][2]);
				cost[i][2] += Math.min(cost[i - 1][0], cost[i - 1][1]);
			}

			bw.write(Math.min(cost[N-1][0], Math.min(cost[N-1][1], cost[N-1][2])) + "");

			bw.flush();
		}
	}
}