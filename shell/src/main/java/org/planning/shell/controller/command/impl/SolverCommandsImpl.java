package org.planning.shell.controller.command.impl;

import org.planning.shell.controller.command.SolverCommands;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;

/**
 * @author pascalstammer
 */
public class SolverCommandsImpl extends AbstractCommands implements SolverCommands {

    @CliCommand(value = "validate", help = "translate text from one language to another")
    public void validate(@CliOption(key = {"", "file"}) String file) {

    }
}
