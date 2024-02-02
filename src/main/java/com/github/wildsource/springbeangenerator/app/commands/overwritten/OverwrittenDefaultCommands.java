package com.github.wildsource.springbeangenerator.app.commands.overwritten;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.commands.Quit;

@ShellComponent
public class OverwrittenDefaultCommands implements Quit.Command {
	@ShellMethod(value = "Exit the shell.", key = { "quit", "exit", "terminate" })
	public void quit() {
		System.exit(0);
	}
}
