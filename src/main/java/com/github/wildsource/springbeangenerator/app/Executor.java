package com.github.wildsource.springbeangenerator.app;

import java.util.concurrent.Callable;

import org.springframework.stereotype.Component;

@Component
public class Executor {
	private Thread runnerThread;

	public Executor() {
		this.runnerThread = null;
	}

	public <T> void setGenerationStrategy(Callable<T> strategy) {
		// TODO implement threadpool and stuff to make callables run
		this.runnerThread = new Thread(strategy);
	}

	public void execute() {
		this.runnerThread.start();
		try {
			this.runnerThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
