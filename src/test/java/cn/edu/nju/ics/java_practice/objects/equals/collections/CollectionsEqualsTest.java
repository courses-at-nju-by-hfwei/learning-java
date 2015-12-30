package cn.edu.nju.ics.java_practice.objects.equals.collections;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * Test various methods of equal-testing between collections.
 * @author hengxin
 * @date Created on Dec 30, 2015
 */
public class CollectionsEqualsTest
{

	@Before
	public void setUp() throws Exception
	{
	}

	/**
	 * Test {@link CollectionUtils#isEqualCollection(java.util.Collection, java.util.Collection)}
	 * of org.apache.commons.commons-collections4 on {@link List}.
	 */
	@Test
	public void testIsEqualCollectionOnListOfString()
	{
		List<String> stringList = Arrays.asList("test1", "test2");
		List<String> stringListEqualWithSameOrder = Arrays.asList("test1", "test2");
		List<String> stringListEqualIgnoreOrder = Arrays.asList("test2", "test1");
		List<String> stringListNotEqualAtAll = Arrays.asList("test", "test1");
		
		assertTrue("stringList and stringListEqualWithSameOrder are equal.", CollectionUtils.isEqualCollection(stringList, stringListEqualWithSameOrder));
		assertTrue("stringList and stringListEqualIgnoreOrder are equal.", CollectionUtils.isEqualCollection(stringList, stringListEqualIgnoreOrder));
		assertFalse("stringList and stringListNotEqualAtAll are not equal.", CollectionUtils.isEqualCollection(stringList, stringListNotEqualAtAll));
	}

	/**
	 * <b>Note:</n> {@link CollectionUtils#isEqualCollection(java.util.Collection, java.util.Collection)}
	 * requires the element class in {@link List} override hashCode() and equals(); it uses {@link Map} internally.
	 */
	@Test
	public void testIsEqualCollectionOnListOfPeople()
	{
		
	}
}
