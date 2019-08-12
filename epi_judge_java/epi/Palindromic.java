package epi;

import java.util.HashMap;
import java.util.Map;

public class Palindromic {

	public static boolean isPalindromic(String s) {
		if (s == null || s.isEmpty()) {
			return true;
		}

		for (int i = 0; i < s.length() / 2; i++) {
			char c = s.charAt(i);
			if (s.charAt(s.length() - 1 - i) != c) {
				return false;
			}
		}

		return true;
	}

	// Using DP with time complexity of O(n2) and space complexity of O(n2)
	public static String longestPalindrome(String s) {
		String result = "";

		if (s == null || s.isEmpty()) {
			return result;
		}

		Map<IndexPair, Boolean> isPalindromicMap = new HashMap<>();

		for (int i = 1; i <= 2; i++) {
			for (int j = 0; i + j <= s.length(); j++) {
				boolean isPalindromic = isPalindromic(s.substring(j, j + i));
				isPalindromicMap.put(new IndexPair(j, j + i), isPalindromic);

				if (isPalindromic && result.length() < s.substring(j, j + i).length()) {
					result = s.substring(j, j + i);
				}
			}
		}

		for (int i = 3; i <= s.length(); i++) {
			for (int j = 0; i + j <= s.length(); j++) {
				boolean isPalindromic = isPalindromicMap.get(new IndexPair(j + 1, j + i - 1)) && s.charAt(j) == s.charAt(i + j - 1);
				isPalindromicMap.put(new IndexPair(j, j + i), isPalindromic);

				if (isPalindromic && result.length() < s.substring(j, j + i).length()) {
					result = s.substring(j, j + i);
				}
			}
		}

		return result;
	}

	public static void main(String[] args) {
		// testIsPalindromic();
		testLongestPalindrome();
	}

	public static void testLongestPalindrome() {
		testLongestPalindromeHelper("bab", "bab");
		testLongestPalindromeHelper("babxx", "bab");
		testLongestPalindromeHelper("babad", "bab");
		testLongestPalindromeHelper("cbbd", "bb");
		testLongestPalindromeHelper("bbbabbbad", "bbbabbb");
		System.out.println("Passed all tests!");
	}

	public static void testIsPalindromic() {
		testIsPalindromicHelper("bab", true);
		testIsPalindromicHelper("baba", false);
		testIsPalindromicHelper("babab", true);
		testIsPalindromicHelper("", true);
		testIsPalindromicHelper(null, true);
		testIsPalindromicHelper("aaaaaaaaaa", true);
		testIsPalindromicHelper("baaaaaaaaaab", true);
		testIsPalindromicHelper("baaaaaaaaaabx", false);
		System.out.println("Passed all tests!");
	}

	public static void testLongestPalindromeHelper(String input, String expected) {
		String result = longestPalindrome(input);
		System.out.println(String.format("Input: %s, expected: %s, result: %s", input, expected, result));
		assert (result.equals(expected));
	}

	public static void testIsPalindromicHelper(String s, boolean expected) {
		boolean result = isPalindromic(s);
		System.out.println(String.format("Input: %s, expected: %b, result: %b", s, expected, result));
		assert (expected == result);
	}

}

class IndexPair {
	int start;
	int end;

	public IndexPair(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + start;
		result = prime * result + end;
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
		IndexPair other = (IndexPair) obj;
		if (start != other.start)
			return false;
		if (end != other.end)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IndexPair [start=" + start + ", end=" + end + "]";
	}



}
