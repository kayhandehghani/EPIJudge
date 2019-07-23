package epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

public class Anagrams {
	@EpiTest(testDataFile = "anagrams.tsv")

	public static List<List<String>> findAnagrams(List<String> dictionary) {
		Map<String, List<String>> sortedAnagramsMap = new HashMap<>();

		dictionary.forEach(s -> {
			char[] sortedChars = s.toCharArray();
			Arrays.sort(sortedChars);

			String sortedString = new String(sortedChars);

			if (!sortedAnagramsMap.containsKey(sortedString)) {
				sortedAnagramsMap.put(sortedString, new ArrayList<String>());
			}

			sortedAnagramsMap.get(sortedString).add(s);
		});

		return sortedAnagramsMap.values().stream().filter(l -> l.size() > 1).collect(Collectors.toList());
	}

	@EpiTestComparator
	public static BiPredicate<List<List<String>>, List<List<String>>> comp = (expected, result) -> {
		if (result == null) {
			return false;
		}
		for (List<String> l : expected) {
			Collections.sort(l);
		}
		expected.sort(new LexicographicalListComparator<>());
		for (List<String> l : result) {
			Collections.sort(l);
		}
		result.sort(new LexicographicalListComparator<>());
		return expected.equals(result);
	};

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "Anagrams.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
