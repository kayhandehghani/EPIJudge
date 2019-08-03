package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class Fibonacci {

	@EpiTest(testDataFile = "fibonacci.tsv")

	public static int fibonacci(int n) {
		return fibonacciIterativeDP(n);
	}

	// O(n) time and O(1) space
	public static int fibonacciIterativeDP(int n) {
		if (n <= 1) {
			return n;
		}

		int nMinus1 = 1;
		int nMinus2 = 0;
		int result = 1;

		for (int i = 2; i <= n; i++) {
			result = nMinus1 + nMinus2;
			nMinus2 = nMinus1;
			nMinus1 = result;
		}

		return result;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
				.runFromAnnotations(args, "Fibonacci.java",
						new Object() {}.getClass().getEnclosingClass())
				.ordinal());
	}
}
