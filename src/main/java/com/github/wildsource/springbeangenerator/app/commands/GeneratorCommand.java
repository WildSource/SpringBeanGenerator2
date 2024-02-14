package com.github.wildsource.springbeangenerator.app.commands;

import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

import com.github.wildsource.springbeangenerator.app.strategies.ControllerStrategy;
import com.github.wildsource.springbeangenerator.app.strategies.EntityStrategy;
import com.github.wildsource.springbeangenerator.app.strategies.RepositoryStrategy;
import com.github.wildsource.springbeangenerator.app.strategies.ServiceStrategy;
import com.github.wildsource.springbeangenerator.utils.ClassCompiler;

@Command(command = "generate", alias = "gen", description = "generates beans")
public class GeneratorCommand {
	private Executor executor;

	@Autowired
	public GeneratorCommand(Executor executor) {
		this.executor = executor;
	}

	@Command(command = "feature", alias = "feat", description = "generates a named feature with controller, service and repository")
	public String generateFeature(@Option(required = true) String featureName) {
		String entityPath = prepareExecutorAndExecute(new EntityStrategy(featureName)).toString();

		ClassCompiler.compileSourceFile(entityPath);
		String compiledEntityPath = entityPath.replace(".java", ".class");
		URL classesUrl = ClassCompiler.GetCompiledFileURL(compiledEntityPath);
		URLClassLoader classLoader = new URLClassLoader(new URL[] { classesUrl });
		return "Generated feature named " + featureName;
	}

	@Command(command = "controller", alias = "roller", description = "generates a named controller")
	public String generateController(@Option(required = true) String controllerName) {
		prepareExecutorAndExecute(new ControllerStrategy(controllerName, Object.class));
		return "Generated controller named " + controllerName;
	}

	@Command(command = "service", alias = "serve", description = "generates a named service")
	public String generateService(@Option(required = true) String serviceName) {
		prepareExecutorAndExecute(new ServiceStrategy(serviceName, Object.class));
		return "Generated service named " + serviceName;
	}

	@Command(command = "repository", alias = "repo", description = "generates a named repository")
	public String generateRepository(@Option(required = true) String repositoryName) {
		prepareExecutorAndExecute(new RepositoryStrategy(repositoryName, Object.class));
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

}
