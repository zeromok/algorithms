import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			long A = Long.parseLong(st.nextToken());
			long B = Long.parseLong(st.nextToken());
			long C = Long.parseLong(st.nextToken());

			long result = pow(A, B, C);
			bw.write(result + "");

			bw.flush();
		}
	}

	private static long pow(long a, long b, long c) {
		if (b == 1) {
			return a % c;
		}

		long half = pow(a, b / 2, c);
		if (b % 2 == 0) {
			return (half * half % c);
		} else if (b % 2 == 1) {
			return ((half * half) % c * (a % c) % c);
		}

		return -1;
	}
}