package epi;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.function.IntConsumer;

public class SemaphoreTests {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		ZeroEvenOdd zeo = new ZeroEvenOdd(10);
		IntConsumer printInt = (a) -> { System.out.print(a); };
		Runnable zeroRunnable = () -> {try {
			zeo.zero(printInt);
		} catch (InterruptedException e) {
		}};
		Runnable evenRunnable = () -> {try {
			zeo.even(printInt);
		} catch (InterruptedException e) {
		}};
		Runnable oddRunnabele = () -> {try {
			zeo.odd(printInt);
		} catch (InterruptedException e) {
		}};
		executorService.submit(evenRunnable);
		executorService.submit(oddRunnabele);
		executorService.submit(zeroRunnable);

		try {
			executorService.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			executorService.shutdownNow();
		}
		System.exit(0);
	}

}

class ZeroEvenOdd {
	private int n;
	private Semaphore zeroSem = new Semaphore(1);
	private Semaphore evenSem = new Semaphore(0);
	private Semaphore oddSem = new Semaphore(0);

	public ZeroEvenOdd(int n) {
		this.n = n;
	}

	// printNumber.accept(x) outputs "x", where x is an integer.
	public void zero(IntConsumer printNumber) throws InterruptedException {
		boolean lastEven = true;

		for (int i = 0; i < n; i++) {
			zeroSem.acquire();
			printNumber.accept(0);

			if (lastEven) {
				oddSem.release();
			} else {
				evenSem.release();
			}

			lastEven = !lastEven;
		}

	}

	public void even(IntConsumer printNumber) throws InterruptedException {
		for (int i = 2; i <= n; i+= 2) {
			evenSem.acquire();
			printNumber.accept(i);
			zeroSem.release();
		}
	}

	public void odd(IntConsumer printNumber) throws InterruptedException {
		for (int i = 1; i <= n; i+= 2) {
			oddSem.acquire();
			printNumber.accept(i);
			zeroSem.release();
		}
	}

}



