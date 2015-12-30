package cn.edu.nju.ics.java_practice.lambda.optional;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author hengxin
 * @date Created on Dec 30, 2015
 */
public class OptionalMapTest
{
	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void test()
	{
		List<String> stringSet = Arrays.asList("s", "ss", "sss");
		
		List<String> actual_trans = stringSet.stream()
				.map(this::transformIfLengthGTOne)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());

		List<String> expected_trans = Arrays.asList("TRANSFORMED", "TRANSFORMED");
		
		assertArrayEquals("Transformed well.", expected_trans.toArray(), actual_trans.toArray());
	}

	public Optional<String> transformIfLengthGTOne(String str)
	{
		if (str.length() > 1)
			return Optional.of("TRANSFORMED");
		return Optional.empty();
	}
}
