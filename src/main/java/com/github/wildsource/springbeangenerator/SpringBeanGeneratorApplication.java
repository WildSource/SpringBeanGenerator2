package com.github.wildsource.springbeangenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.shell.command.annotation.EnableCommand;

import com.github.wildsource.springbeangenerator.app.commands.GeneratorCommand;
import com.github.wildsource.springbeangenerator.app.commands.WipeCommand;

@EnableCommand(value = { GeneratorCommand.class, WipeCommand.class })
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringBeanGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBeanGeneratorApplication.class, args);
	}

}
