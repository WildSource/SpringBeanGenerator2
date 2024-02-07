package com.github.wildsource.springbeangenerator.app.strategies;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.lang.model.element.Modifier;

import org.springframework.javapoet.FieldSpec;
import org.springframework.javapoet.JavaFile;
import org.springframework.javapoet.MethodSpec;
import org.springframework.javapoet.ParameterSpec;
import org.springframework.javapoet.TypeSpec;

import com.github.wildsource.springbeangenerator.utils.Capitalizer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class EntityStrategy implements Callable<Path> {
	private String entityName;
	private String capitalizedName;

	public EntityStrategy(String entityName) {
		this.entityName = entityName;
		this.capitalizedName = Capitalizer.capitalizeString(entityName);
	}

	private FieldSpec produceIdField() {
		return FieldSpec.builder(Long.class, "id")
						.addModifiers(Modifier.PRIVATE)
						.addAnnotation(Id.class)
						.addAnnotation(GeneratedValue.class)
						.build();
	}

	private MethodSpec produceDefaultConstructor() {
		return MethodSpec	.constructorBuilder()
							.addModifiers(Modifier.PUBLIC)
							.build();
	}

	private MethodSpec produceGetter() {
		return MethodSpec	.methodBuilder("get" + capitalizedName)
							.addModifiers(Modifier.PUBLIC)
							.returns(Object.class)
							.addStatement("return this.id")
							.build();
	}

	private MethodSpec produceSetter() {
		return MethodSpec	.methodBuilder("set" + capitalizedName)
							.addModifiers(Modifier.PUBLIC)
							.returns(void.class)
							.addParameter(ParameterSpec	.builder(Long.class, entityName)
														.build())
							.addStatement("this.id = " + entityName)
							.build();
	}

	private TypeSpec produceEntity(List<MethodSpec> methods) {
		return TypeSpec	.classBuilder(capitalizedName)
						.addModifiers(Modifier.PUBLIC)
						.addAnnotation(Entity.class)
						.addField(produceIdField())
						.addMethods(methods)
						.build();
	}

	@Override
	public Path call() throws Exception {
		List<MethodSpec> methods = new ArrayList<MethodSpec>();

		methods.add(produceDefaultConstructor());
		methods.add(produceGetter());
		methods.add(produceSetter());

		JavaFile javaFile = JavaFile.builder(entityName, produceEntity(methods))
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
