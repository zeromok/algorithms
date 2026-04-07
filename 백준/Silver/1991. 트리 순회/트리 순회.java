import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static class Node {
		char value;
		Node left;
		Node right;

		public Node(char value) {
			this.value = value;
		}
	}
	static Node[] tree = new Node[26 + 1];

	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

			int N = Integer.parseInt(br.readLine());

			for (int i = 0; i < N; i++) {
				char[] inputs = br.readLine().replace(" ", "").toCharArray();
				char root = inputs[0], left = inputs[1], right = inputs[2];

				if (tree[root - 'A'] == null) {
					tree[root - 'A'] = new Node(root);
				}

				if (left != '.') {
					tree[left - 'A'] = new Node(left);
					tree[root - 'A'].left = tree[left - 'A'];
				}

				if (right != '.') {
					tree[right - 'A'] = new Node(right);
					tree[root - 'A'].right = tree[right - 'A'];
				}
			}

			preorder(tree[0], bw);
			bw.newLine();

			inorder(tree[0], bw);
			bw.newLine();

			postorder(tree[0], bw);

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