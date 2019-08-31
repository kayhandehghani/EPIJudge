package epi;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

public class SearchMaze {
	@EpiUserType(ctorParams = { int.class, int.class })

	public static class Coordinate {
		public int x, y;

		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}

			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			Coordinate that = (Coordinate) o;
			if (x != that.x || y != that.y) {
				return false;
			}
			return true;
		}
	}

	public enum Color {
		WHITE, BLACK
	}

	private static int[][] SHIFTS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	public static List<Coordinate> searchMaze(List<List<Color>> maze, Coordinate s, Coordinate e) {
		//		return searchMazeDFS(maze, s, e);
		System.out.println(-13 % 10);
		return searchMazeBFS(maze, s, e);
	}

	public static List<Coordinate> searchMazeBFS(List<List<Color>> maze, Coordinate s, Coordinate e) {
		List<Coordinate> path = new LinkedList<>();
		Queue<Coordinate> queue = new LinkedList<>();

		queue.add(s);

		while (!queue.isEmpty()) {
			Coordinate current = queue.poll();

			path.add(current);

			if (current.equals(e)) {
				return path;
			}


			for (int[] shift : SHIFTS) {
				Coordinate neighbor = new Coordinate(current.x + shift[0], current.y + shift[1]);
				if (isValidNeighbor(maze, neighbor)) {
					maze.get(current.x).set(current.y, Color.BLACK);
					queue.add(neighbor);
				}
			}
		}

		return path;
	}

	public static List<Coordinate> searchMazeDFS(List<List<Color>> maze, Coordinate s, Coordinate e) {
		List<Coordinate> path = new ArrayList<>();

		path.add(s);
		maze.get(s.x).set(s.y, Color.BLACK);

		if (!isReachable(maze, s, e, path)) {
			path.remove(path.size() - 1);
		}

		return path;
	}

	public static boolean isReachable(List<List<Color>> maze, Coordinate s, Coordinate e, List<Coordinate> path) {
		if (s.equals(e)) {
			return true;
		}

		for (int[] shift : SHIFTS) {
			Coordinate next = new Coordinate(s.x + shift[0], s.y + shift[1]);
			if (isValidNeighbor(maze, next)) {
				path.add(next);
				maze.get(next.x).set(next.y, Color.BLACK);

				if (isReachable(maze, next, e, path)) {
					return true;
				}

				path.remove(path.size() - 1);
			}
		}

		return false;
	}

	public static boolean isValidNeighbor(List<List<Color>> maze, Coordinate c) {
		return c.x >= 0 && c.x < maze.size() &&
				c.y >= 0 && c.y < maze.get(c.x).size()
				&& maze.get(c.x).get(c.y) == Color.WHITE;
	}

	public static boolean pathElementIsFeasible(List<List<Integer>> maze, Coordinate prev, Coordinate cur) {
		if (!(0 <= cur.x && cur.x < maze.size() && 0 <= cur.y && cur.y < maze.get(cur.x).size()
				&& maze.get(cur.x).get(cur.y) == 0)) {
			return false;
		}
		return cur.x == prev.x + 1 && cur.y == prev.y || cur.x == prev.x - 1 && cur.y == prev.y
				|| cur.x == prev.x && cur.y == prev.y + 1 || cur.x == prev.x && cur.y == prev.y - 1;
	}

	@EpiTest(testDataFile = "search_maze.tsv")
	public static boolean searchMazeWrapper(List<List<Integer>> maze, Coordinate s, Coordinate e) throws TestFailure {
		List<List<Color>> colored = new ArrayList<>();
		for (List<Integer> col : maze) {
			List<Color> tmp = new ArrayList<>();
			for (Integer i : col) {
				tmp.add(i == 0 ? Color.WHITE : Color.BLACK);
			}
			colored.add(tmp);
		}
		List<Coordinate> path = searchMaze(colored, s, e);
		if (path.isEmpty()) {
			return s.equals(e);
		}

		if (!path.get(0).equals(s) || !path.get(path.size() - 1).equals(e)) {
			throw new TestFailure("Path doesn't lay between start and end points");
		}

		for (int i = 1; i < path.size(); i++) {
			if (!pathElementIsFeasible(maze, path.get(i - 1), path.get(i))) {
				throw new TestFailure("Path contains invalid segments");
			}
		}

		return true;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "SearchMaze.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
