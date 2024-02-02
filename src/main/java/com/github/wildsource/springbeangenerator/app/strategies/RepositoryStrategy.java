package com.github.wildsource.springbeangenerator.app.strategies;

import java.io.File;
import java.io.IOException;

import javax.lang.model.element.Modifier;

import org.springframework.data.repository.CrudRepository;
import org.springframework.javapoet.JavaFile;
import org.springframework.javapoet.TypeSpec;
import org.springframework.stereotype.Repository;

public class RepositoryStrategy implements Runnable {

	@Override
	public void run() {
		TypeSpec repository = TypeSpec	.interfaceBuilder("MockRepository")
										.addModifiers(Modifier.PUBLIC)
										.addAnnotation(Repository.class)
										.addSuperinterface(CrudRepository.class)
										.build();

		JavaFile javaFile = JavaFile.builder("featureName", repository)
									.build();

		try {
			javaFile.writeToFile(new File("MockRepository.java"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
