package com.akopiants.rest;

import java.util.concurrent.ExecutionException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;



public class ScriptResourceTest {
	
	private static ScriptServiceImpl scriptService;
			
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		scriptService = new ScriptServiceImpl();
	}

	@Test
	public void test() throws InterruptedException, ExecutionException {
		String exp = scriptService.callAsync("print('B')").get();
		String act = "B";
		Assert.assertEquals(exp.substring(0, exp.length()-2),act);
	}

	

}
