package cn.edu.nju.ics.java_practice.concurrency.java5;

import static java.util.stream.Collectors.toList;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;

/**
 * Test {@link Executors}
 * @author hengxin
 * @date Created on Jan 7, 2016
 */
public class ExecutorTest {

	private static final ExecutorService exec = Executors.newCachedThreadPool();
	private static final int WAITING_TIME = 10000;	// in ms
	
	@Before
	public void setUp() throws Exception {}

	
	/**
	 * <p>Q: Does {@link ExecutorService#submit(Callable)} block the caller thread?
	 * <p>A: No.
	 * @throws InterruptedException
	 */
	@Test
	public void testSubmitCallableBlockingMechanism() throws InterruptedException {
		Thread.currentThread().setName("Caller");
		
		long startTime = System.currentTimeMillis();
		exec.submit(new Task(10));
		long endTime = System.currentTimeMillis();
		
		assertFalse(String.format("The thread [%s] should have been blocked.", Thread.currentThread().getName()), 
				endTime - startTime >= WAITING_TIME);
	}
	
	/**
	 * <p>Q: Does {@link ExecutorService#invokeAll(java.util.Collection)} block the caller thread?
	 * <p>A: Yes.
	 * @throws InterruptedException
	 */
	@Test
	public void testInvokeAllBlockingMechanism() throws InterruptedException {
		Thread.currentThread().setName("Caller");
		
		List<Task> tasks = IntStream.range(0, 5)
				.mapToObj(Task::new)
				.collect(toList());
		
		long startTime = System.currentTimeMillis();
		exec.invokeAll(tasks);
		long endTime = System.currentTimeMillis();
		
		assertTrue(String.format("The thread [%s] has been blocked.", Thread.currentThread().getName()), 
				endTime - startTime >= WAITING_TIME);
	}

	private static class Task implements Callable<Boolean> {

		private final int taskId;
		
		public Task(final int id) {
			this.taskId = id;
		}
		
		@Override
		public Boolean call() throws Exception {
			System.out.println("Thread " + this.taskId);
			Thread.sleep(WAITING_TIME);
			return true;
		}
		
	}
}
