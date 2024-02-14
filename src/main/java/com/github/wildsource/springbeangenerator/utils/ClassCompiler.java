package com.github.wildsource.springbeangenerator.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class ClassCompiler {
	private static final JavaCompiler COMPILER = ToolProvider.getSystemJavaCompiler();
	private static final DiagnosticCollector<JavaFileObject> DIAGNOSTICS = new DiagnosticCollector<JavaFileObject>();
	private static final StandardJavaFileManager FILEMANAGER = COMPILER.getStandardFileManager(DIAGNOSTICS, null, null);

	public static Boolean compileSourceFile(String sourceCodeFilePath) {
		Iterable<? extends JavaFileObject> compilationUnits = FILEMANAGER.getJavaFileObjects(
				new File(sourceCodeFilePath));
		JavaCompiler.CompilationTask task = COMPILER.getTask(null, FILEMANAGER, DIAGNOSTICS, null, null,
				compilationUnits);
		return task.call();
	}

	public static URL GetCompiledFileURL(String path) {
		URL classesUrl = null;
		try {
			classesUrl = new File(path)	.toURI()
										.toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return classesUrl;
	}
}
