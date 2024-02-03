package com.github.wildsource.springbeangenerator.app;

import org.springframework.stereotype.Component;

@Component
public class Executor {
	private Thread runnerThread;

	public Executor() {
		this.runnerThread = null;
	}

	public void setGenerationStrategy(Runnable strategy) {
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
