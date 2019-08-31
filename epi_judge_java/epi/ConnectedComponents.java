package epi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ConnectedComponents {

	public static int countComponents(int n, int[][] edges) {
		Map<Integer, Set<Integer>> componentMap = new HashMap<>();

		for (int[] e : edges) {
			if (!componentMap.containsKey(e[0]) && !componentMap.containsKey(e[1])) {
				Set<Integer> newSet = new HashSet<>(Arrays.asList(e[0], e[1]));
				componentMap.put(e[0], newSet);
				componentMap.put(e[1], newSet);
			} else {
				if (!componentMap.containsKey(e[0])) {
					Set<Integer> neighborSet = componentMap.get(e[1]);
					neighborSet.add(e[0]);
					componentMap.put(e[0], neighborSet);
				} else {
					Set<Integer> neighborSet = componentMap.get(e[0]);
					neighborSet.add(e[1]);
					componentMap.put(e[1], neighborSet);
				}
			}
		}

		for (int i = 0; i < n; i++) {
			if (!componentMap.containsKey(i)) {
				componentMap.put(i, new HashSet<>());
			}
		}

		System.out.println(componentMap);

		return (int) componentMap.values().stream().filter(s -> !s.isEmpty()).distinct().count() +
				(int) componentMap.values().stream().filter(s -> s.isEmpty()).count();
	}

	public static void main(String[] args) {
		testArray(5, new int[][] {{0, 1}, {1, 2}, {3, 4}}, 2);
		testArray(3, new int[][] {{1, 0}, {0, 2}}, 1);
		testArray(5, new int[][] {{1, 0}, {0, 2}}, 3);
		testArray(6, new int[][] {}, 6);
		testArray(6, new int[][] {{0, 5}}, 5);
		testArray(6, new int[][] {{0, 1}, {0, 2}, {0, 3}, {0, 4}, {2, 5}}, 1);
		System.out.println("\n\nPassed all the tests!");
	}

	public static void testArray(int vertices, int[][] edges, int expected) {
		System.out.println(String.format("Vertices %d, edges %s", vertices, Arrays.deepToString(edges)));
		int result = countComponents(vertices, edges);
		System.out.println(String.format("\tExpected %d, returned %d", expected, result));
		assert (expected == result);
	}

}
