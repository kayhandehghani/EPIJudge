package epi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinCostToConnectAllNodes {

	public int getMinCost(int n, int[][] edges, int[][] newEdges) {
		UF uf = new UF(n);

		for (int[] edge : edges) {
			uf.union(edge[0], edge[1]);
		}

		Arrays.sort(newEdges, (ec1, ec2) -> ec1[2] - ec2[2]); // sort the costs in ascending order

		int cost = 0;
		for (int[] newEdge : newEdges) {
			if (uf.getComponentId(newEdge[0]) == uf.getComponentId(newEdge[1])) continue;
			uf.union(newEdge[0], newEdge[1]);
			cost += newEdge[2];
			if (uf.size() == 1) return cost;
		}

		return -1;
	}

	public static void main(String[] args) {
		MinCostToConnectAllNodes obj = new MinCostToConnectAllNodes();
		assert(7 == obj.getMinCost(6, new int[][]{{1, 4}, {4, 5}, {2, 3}},
				new int[][] {{1, 2, 5}, {1, 3, 10}, {1, 6, 2}, {5, 6, 5}}));
		assert(18 == obj.getMinCost(7, new int[][]{{1, 4}, {4, 5}, {2, 3}},
				new int[][] {{2, 4, 7}, {1, 2, 3}, {1, 7, 7}, {1, 3, 10}, {1, 6, 9}, {5, 6, 8}}));
		assert(13 == obj.getMinCost(7, new int[][]{{1, 4}, {4, 5}, {2, 3}},
				new int[][] {{2, 4, 7}, {1, 2, 3}, {2, 7, 2}, {1, 7, 7}, {1, 3, 10}, {1, 6, 9}, {5, 6, 8}}));
		System.out.println("Done");

	}

	private class UF {
		private int size;
		private Map<Integer, Node> map;

		public UF(int n) {
			this.size = n;
			this.map = new HashMap<>();
		}

		public int size() {
			return this.size;
		}

		public void union(int id1, int id2) {
			Node p1 = find(id1), p2 = find(id2);

			if (p1 == p2) return;
			if (p1.rank == p2.rank) {
				p2.parent = p1;
				p2.rank = 0;
				p1.rank++;
			} else if (p1.rank > p2.rank) {
				p2.parent = p1;
				p2.rank = 0;
			} else {
				p1.parent = p2;
				p1.rank = 0;
			}

			this.size--;
		}

		private Node find(int id) {
			if (!map.containsKey(id)) {
				map.put(id, new Node(id));
			}

			Node node = map.get(id);

			if (node != node.parent) node.parent = find(node.parent.id);

			return node.parent;
		}

		public int getComponentId(int id) {
			return find(id).id;
		}

		private class Node {
			int id;
			int rank;
			Node parent;

			public Node(int id) {
				this.id = id;
				this.parent = this;
			}
		}
	}
}

