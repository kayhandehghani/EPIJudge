package epi;

import java.util.HashMap;
import java.util.Map;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class NumberOfTraversalsMatrix {
	@EpiTest(testDataFile = "number_of_traversals_matrix.tsv")

	public static int numberOfWays(int n, int m) {
		return numberOfWays(n, m, new HashMap<Cell, Integer>());
	}

	private static int numberOfWays(int n, int m, Map<Cell, Integer> cellCount) {
		if (n <= 0 || m <= 0) {
			return 0;
		} else if (n == 1 && m == 1) {
			cellCount.put(new Cell(0, 0), 1);
			return 1;
		} else if (cellCount.containsKey(new Cell(n, m))) {
			return cellCount.get(new Cell(n, m));
		} else {
			int fromLeft =  numberOfWays(n - 1, m, cellCount);
			int fromTop =  numberOfWays(n, m - 1, cellCount);
			cellCount.put(new Cell(n - 1, m), fromLeft);
			cellCount.put(new Cell(n, m - 1), fromTop);
			return fromLeft + fromTop;
		}
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "NumberOfTraversalsMatrix.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}

}

class Cell {
	int n;
	int m;

	public Cell(int n, int m) {
		this.n = n;
		this.m = m;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + m;
		result = prime * result + n;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (m != other.m)
			return false;
		if (n != other.n)
			return false;
		return true;
	}
}
