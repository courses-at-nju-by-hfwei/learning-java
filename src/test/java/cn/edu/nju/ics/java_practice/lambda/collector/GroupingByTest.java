package cn.edu.nju.ics.java_practice.lambda.collector;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

/**
 * Test the {@link Collectors#groupingBy} collectors.
 * @author hengxin
 * @date Created on Dec 31, 2015
 */
public class GroupingByTest
{
	List<String> words = Stream.of("s", "ss", "tt", "sss", "ttt").collect(Collectors.toList());
	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void testGroupingWordsByLength()
	{
		Map<Integer, List<String>> actualLength2StrListMap = words.parallelStream()
				.collect(Collectors.groupingBy(String::length, Collectors.toList()));	
		// here groupingByConcurrent is better for performance; however, the order should be ignore for equality-test; for example: Collectors.toSet() 

		Map<Integer, List<String>> actualLength2StrListMap2 = words.parallelStream()
				.collect(Collectors.groupingBy(String::length));
		
		// expected: 1 => { "s" }; 2 => { "ss", "tt" }; 3 => { "sss", "ttt" }
		Map<Integer, List<String>> expectedLength2StrListMap = new HashMap<>();
		expectedLength2StrListMap.put(1, Stream.of("s").collect(Collectors.toList()));
		expectedLength2StrListMap.put(2, Stream.of("ss", "tt").collect(Collectors.toList()));
		expectedLength2StrListMap.put(3, Stream.of("sss", "ttt").collect(Collectors.toList()));
		
		assertEquals("Grouping by length works.", expectedLength2StrListMap, actualLength2StrListMap);
		assertEquals("Grouping by length works.", expectedLength2StrListMap, actualLength2StrListMap2);
	}

}
