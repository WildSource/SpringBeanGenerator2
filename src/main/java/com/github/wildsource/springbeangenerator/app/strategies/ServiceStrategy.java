package com.github.wildsource.springbeangenerator.app.strategies;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.javapoet.FieldSpec;
import org.springframework.javapoet.JavaFile;
import org.springframework.javapoet.MethodSpec;
import org.springframework.javapoet.ParameterizedTypeName;
import org.springframework.javapoet.TypeSpec;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public class ServiceStrategy implements Runnable {

	private FieldSpec produceField() {
		return FieldSpec.builder(Repository.class, "repository")
						.addAnnotation(Autowired.class)
						.addModifiers(Modifier.PRIVATE)
						.build();
	}

	private MethodSpec produceReadMethod() {
		return MethodSpec	.methodBuilder("readMockEntity")
							.addParameter(Long.class, "id")
							.returns(Object.class)
							.addModifiers(Modifier.PRIVATE)
							.build();
	}

	private MethodSpec produceReadAllMethod() {
		return MethodSpec	.methodBuilder("readAllMockEntities")
							.returns(ParameterizedTypeName.get(List.class, Object.class))
							.addModifiers(Modifier.PRIVATE)
							.build();
	}

	private MethodSpec produceCreateMethod() {
		return MethodSpec	.methodBuilder("createMockEntity")
							.addParameter(Object.class, "mock")
							.returns(void.class)
							.addModifiers(Modifier.PRIVATE)
							.build();
	}

	private MethodSpec produceModifyMethod() {
		return MethodSpec	.methodBuilder("modifyMockEntity")
							.addParameter(Object.class, "modifiedMock")
							.addParameter(Long.class, "targetMock")
							.returns(void.class)
							.addModifiers(Modifier.PRIVATE)
							.build();
	}

	private MethodSpec produceDeleteMethod() {
		return MethodSpec	.methodBuilder("deleteMockEntity")
							.addParameter(Long.class, "id")
							.returns(void.class)
							.addModifiers(Modifier.PRIVATE)
							.build();
	}

	private TypeSpec produceService(Iterable<MethodSpec> methods) {
		return TypeSpec	.classBuilder("MockService")
						.addModifiers(Modifier.PUBLIC)
						.addAnnotation(Service.class)
						.addField(produceField())
						.addMethods(methods)
						.build();
	}

	@Override
	public void run() {
		List<MethodSpec> methods = new ArrayList<MethodSpec>();

		methods.add(produceReadMethod());
		methods.add(produceReadAllMethod());
		methods.add(produceCreateMethod());
		methods.add(produceModifyMethod());
		methods.add(produceDeleteMethod());

		JavaFile javaFile = JavaFile.builder("featureName", produceService(methods))
									.build();

		try {
			javaFile.writeToFile(new File("MockFeature"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
