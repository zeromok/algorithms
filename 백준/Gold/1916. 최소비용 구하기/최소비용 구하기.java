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
	static final int INF = Integer.MAX_VALUE;
	static List<Edge>[] graph;
	static int[] dist;

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

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {


			// 입력 처리
			int n = Integer.parseInt(br.readLine());
			int m = Integer.parseInt(br.readLine());

			graph = new ArrayList[n + 1];
			for (int i = 1; i <= n; i++) {
				graph[i] = new ArrayList<>();
			}

			StringTokenizer st;
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				graph[u].add(new Edge(v, w));
			}

			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			// 다익스트라
			int result = dijkstra(n, start, end);
			bw.write(result + "");

			bw.flush();
		}
	}

	private static int dijkstra(int n, int start, int end) {
		dist = new int[n + 1];
		Arrays.fill(dist, INF);

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		dist[start] = 0;
		pq.offer(new Edge(start, 0));

		while (!pq.isEmpty()) {
			Edge curr = pq.poll();

			if (curr.cost > dist[curr.to]) continue;

			for (Edge next : graph[curr.to]) {
				int newDist = dist[curr.to] + next.cost;
				if (dist[next.to] > newDist) {
					dist[next.to] = newDist;
					pq.offer(new Edge(next.to, dist[next.to]));
				}
			}
		}
		return dist[end];
	}
}