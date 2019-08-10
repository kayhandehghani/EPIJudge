package epi;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
public class GraphClone {

	public static class GraphVertex {
		public int label;
		public List<GraphVertex> edges;

		public GraphVertex(int label) {
			this.label = label;
			edges = new ArrayList<>();
		}
	}

	public static GraphVertex cloneGraph(GraphVertex graph) {
		if (graph == null) {
			return null;
		}

		Map<GraphVertex, GraphVertex> originalCloneMap = new HashMap<>();
		Queue<GraphVertex> queue = new LinkedList<>();

		queue.add(graph);
		originalCloneMap.put(graph, new GraphVertex(graph.label));

		while (!queue.isEmpty()) {
			GraphVertex currentOriginal = queue.poll();

			for (GraphVertex e : currentOriginal.edges) {
				if (!originalCloneMap.containsKey(e)) {
					originalCloneMap.put(e, new GraphVertex(e.label));
					queue.add(e);
				}
				originalCloneMap.get(currentOriginal).edges.add(originalCloneMap.get(e));
			}
		}

		return originalCloneMap.get(graph);
	}
	private static List<Integer> copyLabels(List<GraphVertex> edges) {
		List<Integer> labels = new ArrayList<>();
		for (GraphVertex e : edges) {
			labels.add(e.label);
		}
		return labels;
	}

	private static void checkGraph(GraphVertex node, List<GraphVertex> graph)
			throws TestFailure {
		if (node == null) {
			throw new TestFailure("Graph was not copied");
		}

		Set<GraphVertex> vertexSet = new HashSet<>();
		Queue<GraphVertex> q = new ArrayDeque<>();
		q.add(node);
		vertexSet.add(node);
		while (!q.isEmpty()) {
			GraphVertex vertex = q.remove();
			if (vertex.label >= graph.size()) {
				throw new TestFailure("Invalid vertex label");
			}
			List<Integer> label1 = copyLabels(vertex.edges),
					label2 = copyLabels(graph.get(vertex.label).edges);
			Collections.sort(label1);
			Collections.sort(label2);
			if (!label1.equals(label2)) {
				throw new TestFailure("Edges mismatch");
			}
			for (GraphVertex e : vertex.edges) {
				if (!vertexSet.contains(e)) {
					vertexSet.add(e);
					q.add(e);
				}
			}
		}
	}

	@EpiUserType(ctorParams = {int.class, int.class})
	public static class Edge {
		public int from;
		public int to;

		public Edge(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}

	@EpiTest(testDataFile = "graph_clone.tsv")
	public static void cloneGraphTest(int k, List<Edge> edges)
			throws TestFailure {
		if (k <= 0) {
			throw new RuntimeException("Invalid k value");
		}
		List<GraphVertex> graph = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			graph.add(new GraphVertex(i));
		}
		for (Edge e : edges) {
			if (e.from < 0 || e.from >= k || e.to < 0 || e.to >= k) {
				throw new RuntimeException("Invalid vertex index");
			}
			graph.get(e.from).edges.add(graph.get(e.to));
		}
		GraphVertex result = cloneGraph(graph.get(0));
		checkGraph(result, graph);
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
				.runFromAnnotations(args, "GraphClone.java",
						new Object() {}.getClass().getEnclosingClass())
				.ordinal());
	}
}
