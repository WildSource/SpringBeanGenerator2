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

import com.github.wildsource.springbeangenerator.utils.Capitalizer;

public class ServiceStrategy implements Runnable {

	private String serviceName;

	private String capitalizedName;

	public ServiceStrategy(String serviceName) {
		this.serviceName = serviceName;
		this.capitalizedName = Capitalizer.capitalizeString(serviceName);
	}

	private FieldSpec produceRepositoryField() {
		return FieldSpec.builder(Repository.class, serviceName + "Repository")
						.addAnnotation(Autowired.class)
						.addModifiers(Modifier.PRIVATE)
						.build();
	}

	private MethodSpec produceReadMethod() {
		return MethodSpec	.methodBuilder("read" + capitalizedName + "Entity")
							.addParameter(Long.class, "id")
							.returns(Object.class)
							.addModifiers(Modifier.PRIVATE)
							.build();
	}

	private MethodSpec produceReadAllMethod() {
		return MethodSpec	.methodBuilder("readAll" + capitalizedName + "Entities")
							.returns(ParameterizedTypeName.get(List.class, Object.class))
							.addModifiers(Modifier.PRIVATE)
							.build();
	}

	private MethodSpec produceCreateMethod() {
		return MethodSpec	.methodBuilder("create" + capitalizedName + "Entity")
							.addParameter(Object.class, "mock")
							.returns(void.class)
							.addModifiers(Modifier.PRIVATE)
							.build();
	}

	private MethodSpec produceModifyMethod() {
		return MethodSpec	.methodBuilder("modify" + capitalizedName + "Entity")
							.addParameter(Object.class, "modifiedMock")
							.addParameter(Long.class, "targetMock")
							.returns(void.class)
							.addModifiers(Modifier.PRIVATE)
							.build();
	}

	private MethodSpec produceDeleteMethod() {
		return MethodSpec	.methodBuilder("delete" + capitalizedName + "Entity")
							.addParameter(Long.class, "id")
							.returns(void.class)
							.addModifiers(Modifier.PRIVATE)
							.build();
	}

	private TypeSpec produceService(Iterable<MethodSpec> methods) {
		return TypeSpec	.classBuilder("capitalizedNameService")
						.addModifiers(Modifier.PUBLIC)
						.addAnnotation(Service.class)
						.addField(produceRepositoryField())
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

		JavaFile javaFile = JavaFile.builder(serviceName, produceService(methods))
									.build();

		try {
			javaFile.writeToFile(new File(serviceName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
