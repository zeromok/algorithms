import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			int T = Integer.parseInt(br.readLine());
			while (T-- > 0) {
				Map<Integer, Integer> map = new HashMap<>();
				PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());
				PriorityQueue<Integer> minQueue = new PriorityQueue<>();

				int k = Integer.parseInt(br.readLine());
				for (int i = 0; i < k; i++) {
					String[] input = br.readLine().split(" ");
					char cmd = input[0].charAt(0);
					int n = Integer.parseInt(input[1]);

					if (cmd == 'I') {
						maxQueue.offer(n);
						minQueue.offer(n);
						map.put(n, map.getOrDefault(n, 0) + 1);
					} else {
						if (n == 1) {
							removeValue(maxQueue, map);
						} else {
							removeValue(minQueue, map);
						}
					}
				}
				cleanQueue(maxQueue, map);
				cleanQueue(minQueue, map);

				if (maxQueue.isEmpty() || minQueue.isEmpty()) {
					bw.write("EMPTY\n");
				} else {
					bw.write(maxQueue.peek() + " " + minQueue.peek() + "\n");
				}
			}

			bw.flush();
		}
	}

	private static void removeValue(PriorityQueue<Integer> queue, Map<Integer, Integer> map) {
		cleanQueue(queue, map);
		if (!queue.isEmpty()) {
			int curr = queue.poll();
			map.put(curr, map.get(curr) - 1);
		}
	}

	private static void cleanQueue(PriorityQueue<Integer> queue, Map<Integer, Integer> map) {
		while (!queue.isEmpty() && map.getOrDefault(queue.peek(), 0) == 0) {
			queue.poll();
		}
	}
}