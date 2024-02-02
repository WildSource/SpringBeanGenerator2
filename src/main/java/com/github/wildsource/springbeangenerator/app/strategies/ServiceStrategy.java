package com.github.wildsource.springbeangenerator.app.strategies;

import java.io.File;
import java.io.IOException;

import javax.lang.model.element.Modifier;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.javapoet.FieldSpec;
import org.springframework.javapoet.JavaFile;
import org.springframework.javapoet.TypeSpec;
import org.springframework.stereotype.Service;

public class ServiceStrategy implements Runnable {

	@Override
	public void run() {
		FieldSpec repository = FieldSpec.builder(Repository.class, "repository")
										.addModifiers(Modifier.PRIVATE)
										.build();

		TypeSpec service = TypeSpec	.classBuilder("MockRepository")
									.addModifiers(Modifier.PUBLIC)
									.addAnnotation(Service.class)
									.addField(repository)
									.build();

		JavaFile javaFile = JavaFile.builder("featureName", service)
									.build();

		try {
			javaFile.writeToFile(new File("MockService.java"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
