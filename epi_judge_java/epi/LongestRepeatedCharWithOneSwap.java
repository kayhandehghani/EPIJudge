package epi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LongestRepeatedCharWithOneSwap {

	public static void main(String[] args) {
		runTest("baaabaaaaaaabaab", 11);
		runTest("bbababaaaa", 6);
		runTest("aaabbaa", 4);
		runTest("aabaaba", 5);
		runTest("aaaaba", 5);
		runTest("aaaaaa", 6);
		runTest("a", 1);
		runTest("caaaaa", 5);
		runTest("ccaaaa", 4);
		runTest("ababa", 3);
	}

	public static void runTest(String text, int expected) {
		int result = maxRepOpt1(text);
		assert(expected == result);
		System.out.println("Passed all tests");
	}

	public static int maxRepOpt1(String text) {
		if (text == null || text.isEmpty()) {
			return 0;
		}

		if (text.length() == 1) {
			return 1;
		}

		Map<Character, CharRunList> charRunMap = new HashMap<>();

		int startIndex = 0, i = 0;
		for (i = 0; i < text.length() - 1; i++) {
			char current = text.charAt(startIndex);
			char next = text.charAt(i + 1);
			if (current == next) {
				continue;
			}
			addRunToMap(charRunMap, current, startIndex, i);
			startIndex = i + 1;
		}

		addRunToMap(charRunMap, text.charAt(i), startIndex, i);

		System.out.println(charRunMap);
		return findMaxRun(charRunMap);
	}

	public static int findMaxRun(Map<Character, CharRunList> charRunMap) {
		int max = 0;

		for (char c : charRunMap.keySet()) {
			CharRunList runList = charRunMap.get(c);
			List<CharRun> listOfRuns = runList.list;
			max = Math.max(max, runList.max);

			for (int i = 1; i < listOfRuns.size(); i++) {
				CharRun curr = listOfRuns.get(i);
				CharRun prev = listOfRuns.get(i - 1);

				if (curr.isNeighborWith(prev)) {
					Set<Integer> startIndexes = new HashSet<Integer>(runList.startIndexes);
					startIndexes.remove(curr.start);
					startIndexes.remove(prev.start);
					if (!startIndexes.isEmpty() ) {
						max = Math.max(max, curr.length() + prev.length() + 1);
					} else {
						max = Math.max(max, curr.length() + prev.length());
					}
				} else {
					max = Math.max(max, Math.max(curr.length(), prev.length()) + 1);
				}
			}
		}

		return max;
	}

	public static void addRunToMap(Map<Character, CharRunList> charRunMap, char c, int start, int end) {
		if (!charRunMap.containsKey(c)) {
			charRunMap.put(c, new CharRunList());
		}
		charRunMap.get(c).add(new CharRun(start, end));
	}

}

class CharRunList {
	List<CharRun> list;
	int max = 0;
	Set<Integer> startIndexes;

	public CharRunList() {
		list = new ArrayList<>();
		startIndexes = new HashSet<>();
	}

	public void add(CharRun run) {
		this.list.add(run);
		this.max = Math.max(this.max, run.length());
		startIndexes.add(run.start);
	}

	@Override
	public String toString() {
		return String.format("[list: %s, startIndexes: %s, max: %d]", this.list, this.startIndexes, this.max);
	}
}

class CharRun {
	int start;
	int end;

	public CharRun(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public int length() {
		return this.end - this.start + 1;
	}

	public boolean isNeighborWith(CharRun other) {
		return this.end == other.start - 2 || this.start == other.end + 2;
	}

	@Override
	public String toString() {
		return String.format("[start: %d, end: %d]", this.start, this.end);
	}
}
