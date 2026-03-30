import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static long A;
	static long B;
	static long C;

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			A = Long.parseLong(st.nextToken());
			B = Long.parseLong(st.nextToken());
			C = Long.parseLong(st.nextToken());

			long result = pow(B);

			bw.write(result + "");

			bw.flush();
		}
	}

	private static long pow(long b) {
		if (b == 1) {
			return A % C;
		}

		long half = pow(b / 2);
		if (b % 2 == 0) {
			return (half * half) % C;
		} else if (b % 2 == 1) {
			return ((half * half % C) * (A % C)) % C;
		}

		return -1;
	}
}