package com.github.wildsource.springbeangenerator.factories;

import com.github.wildsource.springbeangenerator.app.strategies.ControllerStrategy;
import com.github.wildsource.springbeangenerator.app.strategies.ModuleStrategy;
import com.github.wildsource.springbeangenerator.app.strategies.RepositoryStrategy;
import com.github.wildsource.springbeangenerator.app.strategies.ServiceStrategy;

public class GeneratorFactory {
	public static Thread moduleFactory() {
		return new Thread(new ModuleStrategy());
	}

	public static Thread controllerFactory() {
		return new Thread(new ControllerStrategy());
	}

	public static Thread serviceFactory() {
		return new Thread(new ServiceStrategy());
	}

	public static Thread repositoryThread() {
		return new Thread(new RepositoryStrategy());
	}
}
