package com.github.wildsource.springbeangenerator.app.strategies;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.Callable;

import javax.lang.model.element.Modifier;

import org.springframework.data.repository.CrudRepository;
import org.springframework.javapoet.JavaFile;
import org.springframework.javapoet.ParameterizedTypeName;
import org.springframework.javapoet.TypeSpec;
import org.springframework.stereotype.Repository;

import com.github.wildsource.springbeangenerator.utils.Capitalizer;

public class RepositoryStrategy implements Callable<Path> {
	private String repositoryName;
	private Class<?> entityClass;

	public RepositoryStrategy(String repositoryName, Class<?> entityClass) {
		this.repositoryName = repositoryName;
		this.entityClass = entityClass;
	}

	@Override
	public Path call() throws Exception {
		TypeSpec repository = TypeSpec	.interfaceBuilder(Capitalizer.capitalizeString(repositoryName) + "Repository")
										.addModifiers(Modifier.PUBLIC)
										.addAnnotation(Repository.class)
										.addSuperinterface(ParameterizedTypeName.get(CrudRepository.class,
												entityClass.getClass(), Long.class))
										.build();

		JavaFile javaFile = JavaFile.builder(repositoryName, repository)
									.build();

		Path path = null;
		try {
			path = javaFile.writeToPath(Path.of("")
											.toAbsolutePath());
			System.out.println(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
