package epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BinarySearch {

	public static int bsearch(int t, List<Integer> A) {

		if (A == null) {
			return -1;
		}

		int high = A.size() - 1;
		int mid = 0;
		int low = 0;

		while (low <= high) {
			mid = (low + high) >>> 1; // to avoid overflow bug
			int curr = A.get(mid);
			if (t == curr) {
				return mid;
			} else if (t < curr) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		return -1;
	}

	public static boolean searchStudent(List<Student> students, Student target, Comparator<Student> gpaComparator) {
		return Collections.binarySearch(students, target, gpaComparator) >= 0;
	}

	public static void main(String[] args) {

		List<Integer> list = Arrays.asList(-1, 0, 2, 5, 7, 7);

		System.out.println(bsearch(4, list));
		System.out.println(bsearch(-1, list));
		System.out.println(bsearch(0, list));
		System.out.println(bsearch(5, list));
		System.out.println(bsearch(7, list));
		System.out.println(bsearch(9, list));
		
		List<Student> studentsList = new ArrayList<>();
		studentsList.add(new Student("ad", 1.1));
		studentsList.add(new Student("ab", 1.1));
		studentsList.add(new Student("aa", 1.1));
		studentsList.add(new Student("aa", 1));
		
		Comparator<Student> comparator = (s1, s2) -> (s1.gpa == s2.gpa ? s2.name.compareTo(s1.name) : Double.compare(s2.gpa, s1.gpa));
		
		System.out.println(searchStudent(studentsList, new Student("ab", 1.2), comparator));
		System.out.println(searchStudent(studentsList, new Student("ab", 1.2), comparator));
		System.out.println(searchStudent(studentsList, new Student("ab", 1.1), comparator));
		System.out.println(searchStudent(studentsList, new Student("ad", 1.1), comparator));
		System.out.println(searchStudent(studentsList, new Student("ac", 1.1), comparator));
		System.out.println(searchStudent(studentsList, new Student("aa", 1), comparator));
		

	}

}

class Student {
	public String name;
	public double gpa;

	Student(String name, double gpa) {
		this.name = name;
		this.gpa = gpa;
	}
}