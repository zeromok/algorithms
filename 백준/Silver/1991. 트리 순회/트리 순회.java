import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		char value;
		Node left;
		Node right;

		public Node(char value) {
			this.value = value;
		}
	}

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			Node[] tree = new Node[26];
			for (int i = 0; i < 26; i++) {
				tree[i] = new Node((char)('A' + i));
			}


			int n = Integer.parseInt(br.readLine());
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int idx = st.nextToken().charAt(0) - 'A';
				String left = st.nextToken();
				if (!left.equals(".")) {
					tree[idx].left = tree[left.charAt(0) - 'A'];
				}

				String right = st.nextToken();
				if (!right.equals(".")) {
					tree[idx].right = tree[right.charAt(0) - 'A'];
				}
			}

			preorder(tree[0], bw); // 전위 순회
			bw.newLine();
			inorder(tree[0], bw); // 중위 순회
			bw.newLine();
			postorder(tree[0], bw); // 후위 순회


			bw.flush();
		}
	}

	private static void postorder(Node node, BufferedWriter bw) throws IOException {
		if (node == null) {
			return;
		}

		postorder(node.left, bw);
		postorder(node.right, bw);
		bw.append(node.value);
	}

	private static void inorder(Node node, BufferedWriter bw) throws IOException {
		if (node == null) {
			return;
		}

		inorder(node.left, bw);
		bw.append(node.value);
		inorder(node.right, bw);
	}

	private static void preorder(Node node, BufferedWriter bw) throws IOException {
		if (node == null) {
			return;
		}

		bw.append(node.value);
		preorder(node.left, bw);
		preorder(node.right, bw);
	}
}