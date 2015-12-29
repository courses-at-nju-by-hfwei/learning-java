package cn.edu.nju.ics.java_practice.lambda.exception.ifPresent;

import java.util.Optional;
import java.util.OptionalInt;

/**
 * Throw expection in ifPresent() of {@link Optional}.
 * @author hengxin
 * @date Created on Dec 13, 2015
 */
public class IfPresentThrowException
{
	
	public void printAndsleep(int i) throws InterruptedException
	{
		Thread.sleep(i);
	}
	
	public void test() throws SpecificException
	{
		OptionalInt opt_int = OptionalInt.of(100);
		opt_int.ifPresent(value ->
			{
				try{
					this.printAndsleep(value);
				} catch (InterruptedException ie) {
					throw new SpecificException(ie.getMessage());
				}
			});
	}
	
	private class SpecificException extends RuntimeException
	{
		private static final long serialVersionUID = 1883839476219392351L;

		public SpecificException(String msg)
		{
			super(msg);
		}
	}
}
