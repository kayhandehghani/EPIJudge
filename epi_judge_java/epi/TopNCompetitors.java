package epi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TopNCompetitors {


	public static String[] getTopCompetitors(String[] reviews, String[] competitors) {
		Map<String, Integer> wordCount = new HashMap<>();

		for (String review : reviews) {
			for (String word : review.split("\\s+")) {
				if (!wordCount.containsKey(word)) wordCount.put(word, 0);
				wordCount.put(word, wordCount.get(word) + 1);
			}
		}

		String[] competitorsCopy = Arrays.copyOf(competitors, competitors.length);

		Arrays.sort(competitorsCopy, (c1, c2) -> {
			return wordCount.get(c2) - wordCount.get(c1);
		});

		return competitorsCopy;
	}

	public static void main(String[] args) {
		String[] reviews = {
				"Amazon is better than Google",
				"Google is the worst",
				"Microsoft beats Amazon",
				"BestBuy and Amazon and Microsoft are the best"
		};

		String[] competitors = {
				"Amazon",
				"Google",
				"BestBuy",
				"Microsoft"
		};

		String[] result = getTopCompetitors(reviews, competitors);

		assert(result[0].equals("Amazon"));
		assert(result[1].equals("Google") || result[1].equals("Microsoft"));
		assert(result[2].equals("Google") || result[2].equals("Microsoft"));
		assert(result[3].equals("BestBuy"));

		System.out.print("Done");

	}

}
