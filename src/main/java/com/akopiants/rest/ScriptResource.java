package com.akopiants.rest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
// @RequestMapping(path="script")
public class ScriptResource {
	@Autowired
	private ScriptServiceImpl scriptService;

	@PostMapping(path = "script")
	public ResponseEntity<String> getScriptOutputNon_blocking(@RequestBody String script) throws InterruptedException, ExecutionException {
		CompletableFuture<String> asyncResult = scriptService.callAsync(script);
		String out = asyncResult.get();
		if (out.startsWith("-")) {
			if (out.equals("-scriptException")) {
				return new ResponseEntity<String>(out, HttpStatus.BAD_REQUEST);// out=='-scriptException'
			}
		} 
		return new ResponseEntity<String>(out, HttpStatus.OK);
	}
		

	@PostMapping(path = "script/{functionName}")
	public String getFunctionReturn(@PathVariable String functionName, @RequestBody String script) {
		String ret = scriptService.getFunctionReturn(functionName, script);
		return ret;
	}
	

}
