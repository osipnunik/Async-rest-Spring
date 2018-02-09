package com.akopiants.rest;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ScriptResourceFunctionReturnTest {
	@Mock
	private ScriptServiceImpl scriptService;
	@InjectMocks
	static
	ScriptResource sut;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sut = new ScriptResource();
	}

	@Test
	public void test() {
		//prepare
		String script = "var fun1 = function(name) {print('Hi there from Javascript, ' + name) ;"
				+ "return \"greeting from javascript\";};";
				
		when(scriptService.getFunctionReturn("fun1", script)).thenReturn("greeting from javascript");
		//System.out.println(scriptService.getFunctionReturn("fun1", script));
		//testing
		String out = sut.getFunctionReturn("fun1", script);
		//validate
		verify(scriptService).getFunctionReturn("fun1", script);
	}

}
