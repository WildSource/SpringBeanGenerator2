package com.github.wildsource.springbeangenerator.app.commands;

import java.io.File;

import org.springframework.shell.command.annotation.Command;

@Command(command = "wipe")
public class WipeCommand {
	@Command(command = "log")
	public String wipeLog() {
		File logFile = new File("spring-shell.log");
		logFile.deleteOnExit();
		return "Log file will be wiped out on exit.";
	}
}
