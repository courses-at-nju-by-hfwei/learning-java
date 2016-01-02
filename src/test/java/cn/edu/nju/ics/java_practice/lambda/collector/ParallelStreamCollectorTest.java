package cn.edu.nju.ics.java_practice.lambda.collector;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class ParallelStreamCollectorTest
{

	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void test()
	{
		List<Integer> intList = Stream.of(1, 2, 3).parallel().collect(Collectors.toList());
	}

}
