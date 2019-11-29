package epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SubstringWithDistinctChar {

	public Set<String> getSubstringsWithUniqChars(String input, int size) {
		Set<String> result = new HashSet<>();
		if (input == null || input.isEmpty()) return result;

		for (int i = 0; i < input.length(); i++) {
			List<Character> charSet = new ArrayList<>();
			int j = 0;

			while (i + j < input.length() && j - i <= size) {
				char currentChar = input.charAt(i + j);
				j++;
				if (charSet.size() == size || charSet.contains(currentChar)) break;
				charSet.add(currentChar);
			}

			if (charSet.size() != size) continue;
			String currentSubstr = charSet.stream().map(String::valueOf).collect(Collectors.joining());
			result.add(currentSubstr);
		}

		return result;
	}

	public static void main(String[] args) {
		SubstringWithDistinctChar sol = new SubstringWithDistinctChar();
		assert(new HashSet<String>(Arrays.asList("abc", "bca", "cab"))
				.equals(sol.getSubstringsWithUniqChars("abcabc", 3)));
		assert(new HashSet<String>(Arrays.asList("bac", "cab"))
				.equals(sol.getSubstringsWithUniqChars("abacab", 3)));
		assert(new HashSet<String>(Arrays.asList("wagl", "aglk", "glkn", "lkna", "knag",
				"gawu", "awun", "wuna", "unag", "nagw", "agwk", "kwag"))
				.equals(sol.getSubstringsWithUniqChars("awaglknagawunagwkwagl", 4)));


		System.out.println("Done");

	}

}
