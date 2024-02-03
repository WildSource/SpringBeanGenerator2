package com.github.wildsource.springbeangenerator.app.strategies;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.javapoet.FieldSpec;
import org.springframework.javapoet.JavaFile;
import org.springframework.javapoet.MethodSpec;
import org.springframework.javapoet.ParameterSpec;
import org.springframework.javapoet.ParameterizedTypeName;
import org.springframework.javapoet.TypeSpec;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.wildsource.springbeangenerator.utils.Capitalizer;

public class ControllerStrategy implements Runnable {
	private String controllerName;
	private String capitalizedName;

	public ControllerStrategy(String controllerName) {
		this.controllerName = controllerName;
		this.capitalizedName = Capitalizer.capitalizeString(controllerName);
	}

	private FieldSpec produceField() {
		return FieldSpec.builder(Service.class, controllerName + "Service")
						.addAnnotation(Autowired.class)
						.addModifiers(Modifier.PRIVATE)
						.build();
	}

	private TypeSpec produceController(Iterable<MethodSpec> methods) {
		return TypeSpec	.classBuilder(capitalizedName + "Controller")
						.addModifiers(Modifier.PUBLIC)
						.addAnnotation(RestController.class)
						.addAnnotation(RequestMapping.class)
						.addField(produceField())
						.addMethods(methods)
						.build();
	}

	private MethodSpec produceGetMethod() {
		return MethodSpec	.methodBuilder("get" + capitalizedName)
							.addAnnotation(GetMapping.class)
							.addAnnotation(ResponseBody.class)
							.addParameter(ParameterSpec	.builder(Long.class, "id")
														.addAnnotation(PathVariable.class)
														.build())
							.returns(Object.class)
							.build();
	}

	private MethodSpec produceGetAllMethod() {
		return MethodSpec	.methodBuilder("getAll" + capitalizedName)
							.addAnnotation(CrossOrigin.class)
							.addAnnotation(GetMapping.class)
							.addAnnotation(ResponseBody.class)
							.returns(ParameterizedTypeName.get(List.class, Object.class))
							.build();
	}

	private MethodSpec producePostMethod() {
		return MethodSpec	.methodBuilder("post" + capitalizedName)
							.addAnnotation(PostMapping.class)
							.addAnnotation(ResponseBody.class)
							.addParameter(Object.class, "mock")
							.returns(void.class)
							.build();
	}

	private MethodSpec producePutMethod() {
		return MethodSpec	.methodBuilder("put" + capitalizedName)
							.addAnnotation(PutMapping.class)
							.addAnnotation(ResponseBody.class)
							.addParameter(Object.class, "modifiedMock")
							.addParameter(Object.class, "targetMock")
							.returns(void.class)
							.build();
	}

	private MethodSpec produceDeleteMethod() {
		return MethodSpec	.methodBuilder("delete" + capitalizedName)
							.addAnnotation(DeleteMapping.class)
							.addAnnotation(ResponseBody.class)
							.addParameter(ParameterSpec	.builder(Long.class, "id")
														.addAnnotation(PathVariable.class)
														.build())
							.returns(void.class)
							.build();
	}

	@Override
	public void run() {
		List<MethodSpec> methods = new ArrayList<MethodSpec>();

		methods.add(produceGetMethod());
		methods.add(produceGetAllMethod());
		methods.add(producePostMethod());
		methods.add(producePutMethod());
		methods.add(produceDeleteMethod());

		JavaFile javaFile = JavaFile.builder(controllerName, produceController(methods))
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
