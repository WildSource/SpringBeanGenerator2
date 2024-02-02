package com.github.wildsource.springbeangenerator.app;

public class Executor {
	private Generator generator;

	public Executor(Generator generator) {
		super();
		this.generator = generator;
	}

	public Generator getGenerator() {
		return generator;
	}

	public void setGenerator(Generator generator) {
		this.generator = generator;
	}
}
