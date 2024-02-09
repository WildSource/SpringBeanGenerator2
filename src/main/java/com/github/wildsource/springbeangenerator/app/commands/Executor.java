package com.github.wildsource.springbeangenerator.app.commands;

import java.nio.file.Path;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.stereotype.Component;

@Component
public class Executor {
	private ExecutorService executorPool;
	private Future<Path> futureThread;

	public Executor() {
		this.executorPool = Executors.newCachedThreadPool();
		this.futureThread = null;
	}

	public void addThreadToPool(Callable<Path> strategy) {
		futureThread = executorPool.submit(strategy);
		executorPool.shutdown();
	}

	public Path execute() {
		Path path = null;
		try {
			path = futureThread.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return path;
	}
}
