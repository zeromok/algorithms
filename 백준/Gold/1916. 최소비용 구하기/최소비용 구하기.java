import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Edge implements Comparable<Edge> {
		int to;
		int cost;

		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
	static List<Edge>[] graph;
	static int[] dist;

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());

			graph = new List[N + 1];
			for (int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}

			StringTokenizer st;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				graph[a].add(new Edge(b, c));
			}

			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			dijkstra(N, start);

			bw.write(dist[end] + "");

			bw.flush();
		}
	}

	private static void dijkstra(int n, int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();

		dist = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);

		pq.add(new Edge(start, 0));
		dist[start] = 0;

		while (!pq.isEmpty()) {
			Edge curr = pq.poll();

			if (curr.cost > dist[curr.to]) {
				continue;
			}

			for (Edge next : graph[curr.to]) {
				if (dist[next.to] > dist[curr.to] + next.cost) {
					dist[next.to] = dist[curr.to] + next.cost;
					pq.add(new Edge(next.to, dist[next.to]));
				}
			}
		}
	}
}