package cn.edu.nju.ics.java_practice.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Test the control flow under different cases of exceptions
 * with Java Streams API.
 * @see  <a href="http://stackoverflow.com/q/34564025/1833118">Catching exceptions out of `stream()` or `parallelStream()` loses correct values@stackoverflow</a>
 * @author hengxin
 * @date Created on Jan 2, 2016
 */
public class ExceptionWithStreamControlFlowTest {

	@Test
	public void testCaughtRuntimeExceptionOutOfStream() {
		List<String> strList = Stream.of("1", "2", "illegal_3", "4", "illegal_5", "6").collect(Collectors.toList());

		List<Integer> intList = new ArrayList<>();
		
		try{
			intList = strList.stream()	// same with "parallelStream()"
					.map(Integer::parseInt)
					.collect(Collectors.toList());
		} catch (NumberFormatException nfe) {
			System.err.println(nfe.getMessage());
		}
		
		assertEquals("Lose correct values.", Collections.<Integer>emptyList(), intList);

//		// fails
//		List<Integer> expectedIntList = Stream.of(1, 2).collect(Collectors.toList());
//		assertEquals("The first two elements have been parsed successfully.", expectedIntList, intList);
	}

	@Test
	public void testCaughtRuntimeExceptionInStream() {
		List<String> strList = Stream.of("1", "2", "illegal_3", "4", "illegal_5", "6").collect(Collectors.toList());

		List<Integer> intList = strList.stream()	// same with "parallelStream()"
				.map(str -> {
					try{
						return Integer.parseInt(str);
					} catch (NumberFormatException nfe) {
						System.err.println(nfe.getMessage());
						return -1;
					} })
				.collect(Collectors.toList());
		
		List<Integer> expectedIntList = Stream.of(1, 2, -1, 4, -1, 6).collect(Collectors.toList());
		assertEquals("The first two elements have been parsed successfully.", expectedIntList, intList);
	}
}
