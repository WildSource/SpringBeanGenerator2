package com.github.wildsource.springbeangenerator.app.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.command.annotation.Command;

import com.github.wildsource.springbeangenerator.app.Executor;
import com.github.wildsource.springbeangenerator.app.strategies.ControllerStrategy;
import com.github.wildsource.springbeangenerator.app.strategies.RepositoryStrategy;
import com.github.wildsource.springbeangenerator.app.strategies.ServiceStrategy;

@Command(command = "generate", alias = "gen", description = "generates beans")
public class GeneratorCommand {
	private Executor executor;

	@Autowired
	public GeneratorCommand(Executor executor) {
		this.executor = executor;
	}

	@Command(command = "feature", alias = "feat", description = "generates a named feature with controller, service and repository")
	public String generateFeature() {
		generateRepository();
		generateService();
		generateController();
		return "Feature created";
	}

	@Command(command = "controller", alias = "roller", description = "generates a named controller")
	public String generateController() {
		prepareExecutorAndExecute(new ControllerStrategy());
		return "creating controller";
	}

	@Command(command = "service", alias = "serve", description = "generates a named service")
	public String generateService() {
		prepareExecutorAndExecute(new ServiceStrategy());
		return "generating service";
	}

	@Command(command = "repository", alias = "repo", description = "generates a named repository")
	public String generateRepository() {
		prepareExecutorAndExecute(new RepositoryStrategy());
		return "generating repository";
	}

	private void prepareExecutorAndExecute(Runnable strategy) {
		this.executor.setGenerationStrategy(strategy);
		this.executor.execute();
	}
}
