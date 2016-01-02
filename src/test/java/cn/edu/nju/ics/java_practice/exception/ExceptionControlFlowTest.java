package cn.edu.nju.ics.java_practice.exception;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ExceptionControlFlowTest {
	
	private static final Logger LOGGER = Logger.getLogger(ExceptionControlFlowTest.class.getName());

	@Before
	public void setUp() throws Exception {}

	@Test
	public void testCaughtRuntimeException() {
		int count = 100;
		try{
			count = Integer.parseInt("illegal_count_0");
		} catch (NumberFormatException nfe) {
			System.err.println(nfe.getMessage());
		}
		
		System.out.println("This statement has been executed.");
		assertEquals("count is still 100.", 100, count);
	}

	@Test
	@Ignore
	public void testUncaughtRuntimeException() {
		int count = 100;
		count = Integer.parseInt("illegal_count_0");
		
		System.out.println("This statement is unreachable.");
		assertEquals("count is still 100.", 100, count);
	}
	
	@Test
	public void testCaughtRuntimeExceptionOutOfIteration() {
		List<String> strList = Stream.of("1", "2", "illegal_3", "4", "illegal_5", "6").collect(Collectors.toList());

		List<Integer> intList = new ArrayList<>();
		
		try{
			for (String str : strList) {
				intList.add(Integer.parseInt(str));
			}
		} catch (NumberFormatException nfe) {
			System.err.println(nfe.getMessage());
		}
		
		List<Integer> expectedIntList = Stream.of(1, 2).collect(Collectors.toList());
		assertEquals("The first two elements have been parsed successfully.", expectedIntList, intList);
	}

	@Test
	public void testCaughtRuntimeExceptionInIteration() {
		List<String> strList = Stream.of("1", "2", "illegal_3", "4", "illegal_5", "6").collect(Collectors.toList());

		List<Integer> intList = new ArrayList<>();
		
		for (String str : strList) {
			try{
				intList.add(Integer.parseInt(str));
			} catch (NumberFormatException nfe) {
				LOGGER.severe(nfe.getMessage());
			}
		}
		
		LOGGER.info("This statement has been executed.");
		List<Integer> expectedIntList = Stream.of(1, 2, 4, 6).collect(Collectors.toList());
		assertEquals("The first two elements have been parsed successfully.", expectedIntList, intList);
	}
}
