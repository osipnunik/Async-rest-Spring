package com.akopiants.rest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
public class ScriptServiceImpl implements ScriptService {
	@Async
	public CompletableFuture<String> callAsync(String script){
		return CompletableFuture.completedFuture(getScriptOutput(script));
	}
	
	public String getScriptOutput(String script) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		// IMPORTANT: Save the old System.out!
		PrintStream old = System.out;
		// Tell Java to use your special stream
		System.setOut(ps);
		// Print some output: goes to your special stream
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		try {
			engine.eval(script);
		} catch (ScriptException e) {
			e.printStackTrace();
			return "-scriptException";
		}
		// Put things back
		System.out.flush();
		System.setOut(old);
		return baos.toString();
	}

	@Override
	public String getFunctionReturn(String methodName, String script) {
		Object result = null;
		try {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			engine.eval(script);
			Invocable invocable = (Invocable) engine;
			result = invocable.invokeFunction(methodName, "parameterNameOfFunction");

		} catch (Exception e) {
			e.printStackTrace();
		}
		String s = validate(result);
		return s;
	}

	private String validate(Object result) {
		String s = null;
		try {
			s = result.toString();
		} catch (ClassCastException e) {
			e.printStackTrace();
			return "type not possible to cast.The type is :" + result.getClass();
		} catch (NullPointerException e) {
			e.printStackTrace();
			return "-NullPointerException";
		}
		return s;
	}

	
}
