package com.akopiants.rest.demonstration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.concurrent.CompletableFuture;
import com.akopiants.rest.ScriptServiceImpl;
/* run the class and notice result.After this comment @Async in service
 * and compare the result
 */
@Component
public class AppRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final ScriptServiceImpl service;
   
    public AppRunner(ScriptServiceImpl service) {
		super();
		this.service = service;
	}

	@Override
    public void run(String... args) throws Exception {
        // Start the clock
        long start = System.currentTimeMillis();

        // Kick of multiple, asynchronous lookups
        CompletableFuture<String> page1 = service.callAsync("print('1')");
        CompletableFuture<String> page2 = service.callAsync("print('2')");
        CompletableFuture<String> page3 = service.callAsync("print('3')");
        CompletableFuture<String> page4 = service.callAsync("print('4')");

        // Wait until they are all done
        CompletableFuture.allOf(page1,page2,page3,page4).join();

        // Print results, including elapsed time
        logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
        logger.info("--> " + page1.get());
        logger.info("--> " + page2.get());
        logger.info("--> " + page3.get());
        logger.info("--> " + page4.get());

    }

}
