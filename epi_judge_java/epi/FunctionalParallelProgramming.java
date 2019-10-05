package epi;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class FunctionalParallelProgramming {

	public static void main(String[] args) {
		ForkJoinPool fkPool = new ForkJoinPool(10);

		TestTaskImpl testTaskImpl = new TestTaskImpl();
		CompletableFuture
		.runAsync(() -> testTaskImpl.getQuote("111"), fkPool);
		CompletableFuture
		.runAsync(() -> testTaskImpl.getQuote("222"));
		CompletableFuture
		.runAsync(() -> testTaskImpl.getQuote("333"), fkPool);

		try {
			fkPool.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.exit(0);
	}


}

interface TestTask {
	String getQuote(String preftix);
}

class TestTaskImpl implements TestTask {

	@Override
	public String getQuote(String preftix) {
		System.out.println("Entering ===> " + preftix + " :: " + Thread.currentThread().getName());
		long time = new Random().nextInt(50) * 100;
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String result = preftix + " " + time + " was chosen";
		System.out.println("Exiting ===> " + preftix + " :: " + Thread.currentThread().getName() + " ::: " + result);
		return result;
	}

}