package epi;

import java.util.Arrays;
import java.util.List;

public class BinarySearch {

	public static int bsearch (int t, List<Integer> A) {
		
		if (A == null) {
			return -1;
		}
		
		int high = A.size() - 1;
		int mid = 0;
		int low = 0;
		
		while (low <= high) {
			mid = (low + high) / 2;
			int curr = A.get(mid);
			if (t == curr) {
				return mid;
			} else if (t < curr) {
				high = mid -1;
			} else {
				low = mid + 1;
			}
		}
		
		return -1;
	}
	
	public static void main (String[] args) {
		List<Integer> list = Arrays.asList(-1, 0, 2, 5, 7, 7);
		System.out.println(bsearch(4, list));
		System.out.println(bsearch(-1, list));
		System.out.println(bsearch(0, list));
		System.out.println(bsearch(5, list));
		System.out.println(bsearch(7, list));
		System.out.println(bsearch(9, list));
	}

}
