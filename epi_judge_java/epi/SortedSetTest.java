package epi;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

public class SortedSetTest {

	public static void main(String[] args) {
		SortedSet<Integer> ss = new TreeSet<>(Arrays.asList(4, 3, -1, 3, 1, 5, 6, 8, 12, 66, 8, -1, 3));

		System.out.println(ss);
		System.out.println(ss.tailSet(8));
		System.out.println(ss.headSet(8));
		System.out.println("aa".compareTo("ab"));
	}

}
