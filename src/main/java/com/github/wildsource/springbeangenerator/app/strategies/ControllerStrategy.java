package com.github.wildsource.springbeangenerator.app.strategies;

import java.io.File;
import java.io.IOException;

import javax.lang.model.element.Modifier;

import org.springframework.javapoet.FieldSpec;
import org.springframework.javapoet.JavaFile;
import org.springframework.javapoet.TypeSpec;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

public class ControllerStrategy implements Runnable {

	@Override
	public void run() {
		FieldSpec service = FieldSpec	.builder(Service.class, "service")
										.addModifiers(Modifier.PRIVATE)
										.build();

		TypeSpec controller = TypeSpec	.classBuilder("MockController")
										.addModifiers(Modifier.PUBLIC)
										.addAnnotation(Controller.class)
										.addField(service)
										.build();

		JavaFile javaFile = JavaFile.builder("featureName", controller)
									.build();

		try {
			javaFile.writeToFile(new File("MockFeature"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
