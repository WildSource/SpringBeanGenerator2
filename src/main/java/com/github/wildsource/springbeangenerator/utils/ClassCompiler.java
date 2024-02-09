package com.github.wildsource.springbeangenerator.utils;

import java.io.File;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class ClassCompiler {
	private static JavaCompiler compiler;
	private static DiagnosticCollector<JavaFileObject> diagnostics;
	private static StandardJavaFileManager fileManager;

	public ClassCompiler() {
		compiler = ToolProvider.getSystemJavaCompiler();
		diagnostics = new DiagnosticCollector<JavaFileObject>();
		fileManager = compiler.getStandardFileManager(diagnostics, null, null);
	}

	public static Boolean compileSourceFile(String sourceCodeFilePath) {
		Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(
				new File(sourceCodeFilePath));
		JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null,
				compilationUnits);
		return task.call();
	}
}
