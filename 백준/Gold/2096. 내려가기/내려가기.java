import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			int N = Integer.parseInt(br.readLine());

			int[] maxDP = new int[3];
			int[] minDP = new int[3];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 3; i++) {
				int val = Integer.parseInt(st.nextToken());
				maxDP[i] = minDP[i] = val;
			}

			for (int i = 1; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());

				int nextMax0 = a + Math.max(maxDP[0], maxDP[1]);
				int nextMax1 = b + Math.max(maxDP[0], Math.max(maxDP[1], maxDP[2]));
				int nextMax2 = c + Math.max(maxDP[1], maxDP[2]);

				maxDP[0] = nextMax0;
				maxDP[1] = nextMax1;
				maxDP[2] = nextMax2;

				int nextMin0 = a + Math.min(minDP[0], minDP[1]);
				int nextMin1 = b + Math.min(minDP[0], Math.min(minDP[1], minDP[2]));
				int nextMin2 = c + Math.min(minDP[1], minDP[2]);

				minDP[0] = nextMin0;
				minDP[1] = nextMin1;
				minDP[2] = nextMin2;
			}

			int maxResult = Math.max(maxDP[0], Math.max(maxDP[1], maxDP[2]));
			int minResult = Math.min(minDP[0], Math.min(minDP[1], minDP[2]));

			bw.write(maxResult + " " + minResult);

			bw.flush();
		}
	}
}