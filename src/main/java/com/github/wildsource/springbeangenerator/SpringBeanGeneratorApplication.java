package com.github.wildsource.springbeangenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.EnableCommand;

import com.github.wildsource.springbeangenerator.app.commands.GeneratorCommand;

@EnableCommand(GeneratorCommand.class)
@SpringBootApplication
public class SpringBeanGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBeanGeneratorApplication.class, args);
	}

}
