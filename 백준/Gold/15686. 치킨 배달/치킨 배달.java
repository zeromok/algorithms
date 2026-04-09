import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int minCityDist = Integer.MAX_VALUE;

	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static List<Point> houses = new ArrayList<>();
	static List<Point> chickens = new ArrayList<>();
	static List<Point> seleted = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int value = Integer.parseInt(st.nextToken());

					if (value == 1) {
						houses.add(new Point(i, j));
					} else if (value == 2) {
						chickens.add(new Point(i, j));
					}
				}
			}

			dfs(0, 0);

			bw.write(minCityDist + "");

			bw.flush();
		}
	}

	private static void dfs(int idx, int count) {
		if (count == M) {
			int totalDist = 0;

			for (Point house : houses) {
				int minDist = Integer.MAX_VALUE;

				for (Point chicken : seleted) {
					int newDist = Math.abs(house.r - chicken.r) + Math.abs(house.c - chicken.c);
					minDist = Math.min(minDist, newDist);
				}

				totalDist += minDist;
			}

			minCityDist = Math.min(minCityDist, totalDist);
			return;
		}

		for (int i = idx; i < chickens.size(); i++) {
			seleted.add(chickens.get(i));
			dfs(i + 1, count + 1);
			seleted.remove(seleted.size() - 1);
		}
	}
}