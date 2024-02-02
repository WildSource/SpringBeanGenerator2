package com.github.wildsource.springbeangenerator.app.strategies;

import java.io.File;
import java.io.IOException;

import javax.lang.model.element.Modifier;

import org.springframework.javapoet.JavaFile;
import org.springframework.javapoet.MethodSpec;
import org.springframework.javapoet.TypeSpec;

public class RepositoryStrategy implements Runnable {

	@Override
	public void run() {
		MethodSpec main = MethodSpec.methodBuilder("main")
									.addModifiers(Modifier.PUBLIC, Modifier.STATIC)
									.returns(void.class)
									.addParameter(String[].class, "args")
									.addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
									.build();

		TypeSpec helloWorld = TypeSpec	.classBuilder("HelloWorld")
										.addModifiers(Modifier.PUBLIC, Modifier.FINAL)
										.addMethod(main)
										.build();

		JavaFile javaFile = JavaFile.builder("com.example.helloworld", helloWorld)
									.build();

		try {
			javaFile.writeToFile(new File("HelloWorld.java"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
