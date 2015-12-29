package cn.edu.nju.ics.java_practice.lambda;

import java.util.Arrays;

import org.junit.Test;

import junit.framework.TestCase;

public class AllMatchTest extends TestCase
{

	protected void setUp() throws Exception
	{
		super.setUp();
	}

	@Test
	public void testAllTrueBooleanArray()
	{
		Boolean[] flags = { true, true, true };
		assertTrue("The array flags contains all true's.", Arrays.stream(flags).allMatch(flag -> flag));
		Arrays.stream(flags).forEach(System.out::println);
		
		Boolean[] nonAllTrueFlags = { true, false, true };
		assertFalse("The array nonAllTrueFlags contains false.", Arrays.stream(nonAllTrueFlags).allMatch(flag -> flag));
		Arrays.stream(nonAllTrueFlags).forEach(System.out::println);

//		nonAllTrueFlags = Arrays.stream(flags).map(flag -> false).toArray(Boolean[]::new);
		Arrays.stream(nonAllTrueFlags).forEach(flag -> flag = false);
		// forEach above does not change the underlying nonAllTrueFlags
		Arrays.stream(nonAllTrueFlags).forEach(System.out::println);
	}
}
