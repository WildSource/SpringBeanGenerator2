package com.github.wildsource.springbeangenerator.app.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.command.annotation.Command;

import com.github.wildsource.springbeangenerator.app.Executor;
import com.github.wildsource.springbeangenerator.app.strategies.ControllerStrategy;
import com.github.wildsource.springbeangenerator.app.strategies.ModuleStrategy;
import com.github.wildsource.springbeangenerator.app.strategies.RepositoryStrategy;
import com.github.wildsource.springbeangenerator.app.strategies.ServiceStrategy;

@Command(command = "generate")
public class GeneratorCommand {
	private Executor executor;

	@Autowired
	public GeneratorCommand(Executor executor) {
		this.executor = executor;
	}

	@Command(command = "module")
	public String generateModule() {
		prepareExecutorAndExecute(new ModuleStrategy());
		return "Module created";
	}

	@Command(command = "controller")
	public String generateController() {
		prepareExecutorAndExecute(new ControllerStrategy());
		return "creating controller";
	}

	@Command(command = "service")
	public String generateService() {
		prepareExecutorAndExecute(new ServiceStrategy());
		return "generating service";
	}

	@Command(command = "repository")
	public String generateRepository() {
		prepareExecutorAndExecute(new RepositoryStrategy());
		return "generating repository";
	}

	private void prepareExecutorAndExecute(Runnable strategy) {
		this.executor.setGenerationStrategy(strategy);
		this.executor.execute();
	}
}
