package com.github.wildsource.springbeangenerator.app;

import com.github.wildsource.springbeangenerator.app.strategies.Strategy;

public class Generator {
	private Strategy strategy;

	public Generator(Strategy strategy) {
		super();
		this.strategy = strategy;
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

}
