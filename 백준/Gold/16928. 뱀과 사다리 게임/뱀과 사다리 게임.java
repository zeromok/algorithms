import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dist = new int[101];
	static int ladderCnt;
	static int snakeCnt;
	static Map<Integer, Integer> ladderMap;
	static Map<Integer, Integer> snakeMap;

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			ladderCnt = Integer.parseInt(st.nextToken());
			snakeCnt = Integer.parseInt(st.nextToken());

			ladderMap = new HashMap<>();
			snakeMap = new HashMap<>();
			for (int i = 0; i < ladderCnt; i++) {
				st = new StringTokenizer(br.readLine());
				ladderMap.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			for (int i = 0; i < snakeCnt; i++) {
				st = new StringTokenizer(br.readLine());
				snakeMap.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			int result = shotDist();
			bw.write(result + "");

			bw.flush();
		}
	}

	private static int shotDist() {
		Arrays.fill(dist, -1);

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(1);
		dist[1] = 0;

		while (!queue.isEmpty()) {
			int curr = queue.poll();

			if (curr == 100) {
				return dist[curr];
			}

			for (int dice = 1; dice <= 6; dice++) {
				int next = curr + dice;
				if (next > 100) {
					continue;
				}

				int finalPos = next;
				if (ladderMap.containsKey(next)) {
					finalPos = ladderMap.get(next);
				} else if (snakeMap.containsKey(next)) {
					finalPos = snakeMap.get(next);
				}
				
				if (dist[finalPos] != -1)
					continue;

				dist[finalPos] = dist[curr] + 1;
				queue.offer(finalPos);
			}
		}
		return dist[100];
	}
}