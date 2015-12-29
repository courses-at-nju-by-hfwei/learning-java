package cn.edu.nju.ics.java_practice.lambda.flatmap;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class FlatMapTest {
	private final List<StringList> stringsList = new ArrayList<>();
	
	@Before
	public void setUp() throws Exception {
		stringsList.add(new StringList(Arrays.asList("test11", "test12")));
		stringsList.add(new StringList(Arrays.asList("test21", "test22")));
		stringsList.add(new StringList(Arrays.asList("test11", "test22")));
	}

	@Test
	public void test() {
		Set<String> actualStringUnion = stringsList.parallelStream().map(strings -> strings.strings).flatMap(List::stream).collect(Collectors.toSet());
		Set<String> expectedStringUnion = Stream.of("test11", "test12", "test21", "test22").collect(Collectors.toSet());
		assertEquals("flatMap has obtained the right result.", expectedStringUnion, actualStringUnion);
	}

	protected class StringList {
		private final List<String> strings;
		
		public StringList(List<String> strings) {
			this.strings = strings;
		}
	}
}
