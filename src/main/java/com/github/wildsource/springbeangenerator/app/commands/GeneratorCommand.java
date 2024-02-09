package com.github.wildsource.springbeangenerator.app.commands;

import java.nio.file.Path;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

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
		Class<?> entity = prepareExecutorAndExecute(new EntityStrategy(featureName)).getClass();
		Class<?> repository = prepareExecutorAndExecute(new RepositoryStrategy(featureName, entity)).getClass();
		Class<?> service = prepareExecutorAndExecute(new ServiceStrategy(featureName, repository)).getClass();
		prepareExecutorAndExecute(new ControllerStrategy(featureName, service));
		return "Generated feature named " + featureName;
	}

	@Command(command = "controller", alias = "roller", description = "generates a named controller")
	public String generateController(@Option(required = true) String controllerName, Class<?> serviceClass) {
		prepareExecutorAndExecute(new ControllerStrategy(controllerName, classIsPresent(serviceClass)));
		return "Generated controller named " + controllerName;
	}

	@Command(command = "service", alias = "serve", description = "generates a named service")
	public String generateService(@Option(required = true) String serviceName, Class<?> repositoryClass) {
		prepareExecutorAndExecute(new ServiceStrategy(serviceName, classIsPresent(repositoryClass)));
		return "Generated service named " + serviceName;
	}

	@Command(command = "repository", alias = "repo", description = "generates a named repository")
	public String generateRepository(@Option(required = true) String repositoryName, Class<?> entityClass) {
		prepareExecutorAndExecute(new RepositoryStrategy(repositoryName, classIsPresent(entityClass)));
		return "generating repository named " + repositoryName;
	}

	@Command(command = "entity", alias = "ent", description = "generates a named table entity")
	public String generateEntity(@Option(required = true) String entityName) {
		prepareExecutorAndExecute(new EntityStrategy(entityName));
		return "generating entity named " + entityName;
	}

	private Path prepareExecutorAndExecute(Callable<Path> strategy) {
		this.executor.addThreadToPool(strategy);
		return this.executor.execute();
	}

	private Class<?> classIsPresent(Class<?> maybeClass) {
		return maybeClass == null ? Object.class : maybeClass;
	}
}
