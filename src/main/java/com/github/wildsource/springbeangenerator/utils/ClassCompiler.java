package com.github.wildsource.springbeangenerator.utils;

import java.io.File;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class ClassCompiler {
	private JavaCompiler compiler;
	private DiagnosticCollector<JavaFileObject> diagnostics;
	private StandardJavaFileManager fileManager;

	public ClassCompiler() {
		this.compiler = ToolProvider.getSystemJavaCompiler();
		this.diagnostics = new DiagnosticCollector<JavaFileObject>();
		fileManager = compiler.getStandardFileManager(diagnostics, null, null);
	}

	public Boolean compileSourceFile(String sourceCodeFilePath) {
		Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(
				new File(sourceCodeFilePath));
		JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null,
				compilationUnits);
		return task.call();
	}
}
