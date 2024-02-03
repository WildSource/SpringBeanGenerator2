package com.github.wildsource.springbeangenerator.app.strategies;

import java.io.IOException;
import java.nio.file.Path;

import javax.lang.model.element.Modifier;

import org.springframework.data.repository.CrudRepository;
import org.springframework.javapoet.JavaFile;
import org.springframework.javapoet.ParameterizedTypeName;
import org.springframework.javapoet.TypeSpec;
import org.springframework.stereotype.Repository;

import com.github.wildsource.springbeangenerator.utils.Capitalizer;

public class RepositoryStrategy implements Runnable {
	private String repositoryName;

	public RepositoryStrategy(String repositoryName) {
		this.repositoryName = repositoryName;
	}

	@Override
	public void run() {
		TypeSpec repository = TypeSpec	.interfaceBuilder(Capitalizer.capitalizeString(repositoryName) + "Repository")
										.addModifiers(Modifier.PUBLIC)
										.addAnnotation(Repository.class)
										.addSuperinterface(ParameterizedTypeName.get(CrudRepository.class, String.class,
												Long.class))
										.build();

		JavaFile javaFile = JavaFile.builder(repositoryName, repository)
									.build();

		try {
			Path path = javaFile.writeToPath(Path	.of("")
													.toAbsolutePath());
			System.out.println(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
