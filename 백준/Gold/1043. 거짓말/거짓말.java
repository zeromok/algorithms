import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] parent;


	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			parent = new int[N + 1];
			for (int i = 1; i <= N; i++) parent[i] = i;

			st = new StringTokenizer(br.readLine());
			int trueCnt = Integer.parseInt(st.nextToken());
			int[] trueArr = new int[trueCnt];
			for (int i = 0; i < trueCnt; i++) {
				trueArr[i] = Integer.parseInt(st.nextToken());
			}

			int[][] parties = new int[M][];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int cnt = Integer.parseInt(st.nextToken());
				parties[i] = new int[cnt];
				for (int j = 0; j < cnt; j++) {
					parties[i][j] = Integer.parseInt(st.nextToken());
				}
				for (int j = 1; j < cnt; j++) {
					union(parties[i][0], parties[i][j]);
				}
			}

			int answer = 0;
			for (int i = 0; i < M; i++) {
				boolean isTruth = false;
				for (int j = 0; j < parties[i].length; j++) {
					for (int k = 0; k < trueCnt; k++) {
						if (find(parties[i][j]) == find(trueArr[k])) {
							isTruth = true;
							break;
						}
					}
					if (isTruth) break;
				}
				if (!isTruth) answer++;
			}

			bw.write(answer + "");

			bw.flush();
		}
	}

	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA != rootB) {
			parent[rootB] = rootA;
		}
	}

	private static int find(int x) {
		if (parent[x] == x) return x;
		return find(parent[x]);
	}
}