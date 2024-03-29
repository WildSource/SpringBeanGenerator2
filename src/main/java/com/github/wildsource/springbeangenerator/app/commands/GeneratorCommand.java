package com.github.wildsource.springbeangenerator.app.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

import com.github.wildsource.springbeangenerator.app.Executor;
import com.github.wildsource.springbeangenerator.app.strategies.ControllerStrategy;
import com.github.wildsource.springbeangenerator.app.strategies.EntityStrategy;
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
	public String generateFeature(@Option(required = true) String featureName) {
		generateEntity(featureName);
		generateRepository(featureName);
		generateService(featureName);
		generateController(featureName);
		return "Feature " + featureName + " created";
	}

	@Command(command = "controller", alias = "roller", description = "generates a named controller")
	public String generateController(@Option(required = true) String controllerName) {
		prepareExecutorAndExecute(new ControllerStrategy(controllerName));
		return "generating" + controllerName + "controller";
	}

	@Command(command = "service", alias = "serve", description = "generates a named service")
	public String generateService(@Option(required = true) String serviceName) {
		prepareExecutorAndExecute(new ServiceStrategy(serviceName));
		return "generating" + serviceName + "service";
	}

	@Command(command = "repository", alias = "repo", description = "generates a named repository")
	public String generateRepository(@Option(required = true) String repositoryName) {
		prepareExecutorAndExecute(new RepositoryStrategy(repositoryName));
		return "generating" + repositoryName + "repository";
	}

	@Command(command = "entity", alias = "ent", description = "generates a named table entity")
	public String generateEntity(@Option(required = true) String entityName) {
		prepareExecutorAndExecute(new EntityStrategy(entityName));
		return "generating " + entityName + " entity";
	}

	private void prepareExecutorAndExecute(Runnable strategy) {
		this.executor.setGenerationStrategy(strategy);
		this.executor.execute();
	}
}
