import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Solution {

	public static int BFS(int[][] board) {
		long startTime = System.currentTimeMillis();
		long endTime;
		Node newNode = null;
		int nodesExpanded = 0;
		int X = board.length;
		int Y = board[0].length;
		// ax and ay is the position of agent
		int ax = 0;
		int ay = 0;
		// set directions
		int[][] directions = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		Queue<Node> queue = new ArrayDeque();
		// look for the agent
		find: for (ax = 0; ax < X; ax++)
			for (ay = 0; ay < Y; ay++)
				if (board[ax][ay] == 0)
					break find;
		Node start = new Node(board, ax, ay, 0);
		queue.add(start);
		// keep the visited path
		HashSet<String> haveDone = new HashSet();
		haveDone.add(start.boardstring);
		while (!queue.isEmpty()) {
			ArrayList<Node> nextLevelNodes = new ArrayList<>();
			// get current position
			Node node = queue.remove();
			// for (int i = 0; i < 4; i++) {
			// for (int j = 0; j < 4; j++) {
			// System.out.print(node.board[i][j] + " ");
			// }
			// System.out.println();
			// }
			// System.out.println("depth is " + node.depth);
			// System.out.println("nodesExpanded is " + nodesExpanded);
			if (node.board[1][1] == 1 && node.board[2][1] == 2 && node.board[3][1] == 3) {
				System.out.println("depth is " + node.depth);
				System.out.println("nodesExpanded is " + nodesExpanded);
				endTime = System.currentTimeMillis();
				System.out.println("running time  is " + (endTime - startTime) + "ms");
				return node.depth;
			}
			nodesExpanded++;
			// move the agent
			for (int[] direction : directions) {
				int nei_x = direction[0] + node.x_label;
				int nei_y = direction[1] + node.y_label;

				if ((Math.abs(nei_x - node.x_label) + Math.abs(nei_y - node.y_label) != 1) || nei_x < 0 || nei_x >= X
						|| nei_y < 0 || nei_y >= Y)
					continue;

				int[][] newboard = new int[X][Y];
				int t = 0;

				for (int[] row : node.board)
					newboard[t++] = row.clone();

				newboard[node.x_label][node.y_label] = newboard[nei_x][nei_y];
				newboard[nei_x][nei_y] = 0;

				newNode = new Node(newboard, nei_x, nei_y, node.depth + 1);
				// check if the node has been visited
				// if (!haveDone.contains(newNode.boardstring)) {
				nextLevelNodes.add(newNode);
				// haveDone.add(newNode.boardstring);
				// }

			}
			Collections.shuffle(nextLevelNodes);
			for (Node nextLevelnode : nextLevelNodes) {
				if (nextLevelnode != null) {
					queue.add(nextLevelnode);
				}
			}

		}

		return -1;
	}

	public static int DFS(int[][] board) {
		long startTime = System.currentTimeMillis();
		int nodesExpanded = 0;
		int X = board.length;
		int Y = board[0].length;
		int ax = 0;
		int ay = 0;
		int[][] directions = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		Deque<Node> queue = new ArrayDeque();
		find: for (ax = 0; ax < X; ax++)
			for (ay = 0; ay < Y; ay++)
				if (board[ax][ay] == 0)
					break find;

		Node start = new Node(board, ax, ay, 0);
		queue.add(start);

		HashSet<String> haveDone = new HashSet();
		haveDone.add(start.boardstring);
		System.out.println(start.boardstring);

		while (!queue.isEmpty()) {
			ArrayList<Node> nextLevelNodes = new ArrayList<>();
			Node node = queue.removeLast();
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					System.out.print(node.board[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println("depth is " + node.depth);
			System.out.println("nodesExpanded is " + nodesExpanded);

			if (node.board[1][1] == 1 && node.board[2][1] == 2 && node.board[3][1] == 3) {
				System.out.println("depth is " + node.depth);
				System.out.println("nodesExpanded is " + nodesExpanded);
				long endTime = System.currentTimeMillis();
				System.out.println("running time  is " + (endTime - startTime) + "ms");
				return node.depth;
			}
			nodesExpanded++;
			for (int[] direction : directions) {
				int nei_x = direction[0] + node.x_label;
				int nei_y = direction[1] + node.y_label;

				if ((Math.abs(nei_x - node.x_label) + Math.abs(nei_y - node.y_label) != 1) || nei_x < 0 || nei_x >= X
						|| nei_y < 0 || nei_y >= Y)
					continue;

				int[][] newboard = new int[X][Y];
				int t = 0;

				for (int[] row : node.board)
					newboard[t++] = row.clone();

				newboard[node.x_label][node.y_label] = newboard[nei_x][nei_y];
				newboard[nei_x][nei_y] = 0;

				Node newNode = new Node(newboard, nei_x, nei_y, node.depth + 1);
				// if (!haveDone.contains(newNode.boardstring)) {
				nextLevelNodes.add(newNode);
				// haveDone.add(newNode.boardstring);
				// }
			}
			Collections.shuffle(nextLevelNodes);
			for (Node nextLevelnode : nextLevelNodes) {
				if (nextLevelnode != null) {
					queue.add(nextLevelnode);
				}
			}
		}

		return -1;
	}

	public static int aStar(int[][] board) {
		long startTime = System.currentTimeMillis();
		int nodesExpanded = 0;
		int X = board.length;
		int Y = board[0].length;
		int ax = 0;
		int ay = 0;
		int[][] directions = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		PriorityQueue<Node> queue = new PriorityQueue<Node>(
				(a, b) -> (a.heuristic + a.depth) - (b.heuristic + b.depth));

		find: for (ax = 0; ax < X; ax++)
			for (ay = 0; ay < Y; ay++)
				if (board[ax][ay] == 0)
					break find;
		Node start = new Node(board, ax, ay, 0);
		queue.add(start);

		Set<String> haveDone = new HashSet();
		haveDone.add(start.boardstring);

		while (!queue.isEmpty()) {
			ArrayList<Node> nextLevelNodes = new ArrayList<>();
			Node node = queue.remove();
			System.out.println("string is " + node.boardstring);
			System.out.println("depth is " + node.depth);
			System.out.println("heuristic is " + node.heuristic);

			if (node.board[1][1] == 1 && node.board[2][1] == 2 && node.board[3][1] == 3) {
				System.out.println("depth is " + node.depth);
				System.out.println("nodesExpanded is " + nodesExpanded);
				long endTime = System.currentTimeMillis();
				System.out.println("running time  is " + (endTime - startTime) + "ms");
				return node.depth;
			}
			nodesExpanded++;
			for (int[] direction : directions) {
				int nei_x = direction[0] + node.x_label;
				int nei_y = direction[1] + node.y_label;

				if ((Math.abs(nei_x - node.x_label) + Math.abs(nei_y - node.y_label) != 1) || nei_x < 0 || nei_x >= X
						|| nei_y < 0 || nei_y >= Y)
					continue;

				int[][] newboard = new int[X][Y];
				int t = 0;

				for (int[] row : node.board)
					newboard[t++] = row.clone();

				newboard[node.x_label][node.y_label] = newboard[nei_x][nei_y];
				newboard[nei_x][nei_y] = 0;

				Node newNode = new Node(newboard, nei_x, nei_y, node.depth + 1);
				// if (!haveDone.contains(newNode.boardstring)) {
				// haveDone.add(newNode.boardstring);
				nextLevelNodes.add(newNode);
				// }

			}
			for (Node nextLevelnode : nextLevelNodes) {
				if (nextLevelnode != null) {
					queue.add(nextLevelnode);
				}
			}
		}

		return -1;
	}

	public static int IDS(int[][] board) {
		long startTime = System.currentTimeMillis();
		int nodesExpanded = 0;
		int maxDepth = 0;
		int X = board.length;
		int Y = board[0].length;
		int ax = 0;
		int ay = 0;
		int[][] directions = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		Deque<Node> queue = new ArrayDeque();
		Deque<Node> queueIter = new ArrayDeque();

		find: for (ax = 0; ax < X; ax++)
			for (ay = 0; ay < Y; ay++)
				if (board[ax][ay] == 0)
					break find;

		Node start = new Node(board, ax, ay, 0);
		queue.add(start);

		Set<String> haveDone = new HashSet();
		haveDone.add(start.boardstring);

		while (!queue.isEmpty()) {
			ArrayList<Node> nextLevelNodes = new ArrayList<>();
			Node node = queue.removeLast();
			// System.out.println(node.boardstring);
			if (node.board[1][1] == 1 && node.board[2][1] == 2 && node.board[3][1] == 3) {
				System.out.println("depth is " + node.depth);
				System.out.println("nodesExpanded is " + nodesExpanded);
				long endTime = System.currentTimeMillis();
				System.out.println("running time  is " + (endTime - startTime) + "ms");
				return node.depth;
			} else if (node.depth < maxDepth) {
				nodesExpanded++;
				for (int[] direction : directions) {
					int nei_x = direction[0] + node.x_label;
					int nei_y = direction[1] + node.y_label;

					if ((Math.abs(nei_x - node.x_label) + Math.abs(nei_y - node.y_label) != 1) || nei_x < 0
							|| nei_x >= X || nei_y < 0 || nei_y >= Y)
						continue;

					int[][] newboard = new int[X][Y];
					int t = 0;

					for (int[] row : node.board)
						newboard[t++] = row.clone();

					newboard[node.x_label][node.y_label] = newboard[nei_x][nei_y];
					newboard[nei_x][nei_y] = 0;

					Node newNode = new Node(newboard, nei_x, nei_y, node.depth + 1);
					// if (!haveDone.contains(newNode.boardstring)) {
					nextLevelNodes.add(newNode);
					// haveDone.add(newNode.boardstring);
					// }

				}

				for (Node nextLevelnode : nextLevelNodes) {
					if (nextLevelnode != null) {
						queue.add(nextLevelnode);
					}
				}

			}
			if (queue.size() == 0) {
				haveDone.clear();
				queue.add(start);
				haveDone.add(start.boardstring);
				maxDepth++;
			}
		}

		return -1;
	}

}
