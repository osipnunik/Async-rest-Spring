package com.akopiants.rest;

import java.util.concurrent.CompletableFuture;

public interface ScriptService {
	String getScriptOutput(String script);
	CompletableFuture<String> callAsync(String script); 
	String getFunctionReturn(String methodName, String script);
}
