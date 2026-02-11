import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<List<Integer>> graph;
	static int[] parent;
	static boolean[] visited;


	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			int N = Integer.parseInt(br.readLine());

			graph = new ArrayList<>();
			parent = new int[N + 1];
			visited = new boolean[N + 1];

			for (int i = 0; i <= N; i++) {
				graph.add(new ArrayList<>());
			}

			for (int i = 0; i < N - 1; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				graph.get(x).add(y);
				graph.get(y).add(x);
			}

			dfs(1);

			for (int i = 2; i <= N; i++) {
				bw.write(parent[i] + "\n");
			}

			bw.flush();
		}
	}

	private static void dfs(int start) {
		visited[start] = true;

		for (Integer next : graph.get(start)) {
			if (!visited[next]) {
				parent[next] = start;
				dfs(next);
			}
		}
	}
}