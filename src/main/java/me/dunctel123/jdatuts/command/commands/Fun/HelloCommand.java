package me.dunctel123.jdatuts.command.commands.Fun;

import me.dunctel123.jdatuts.command.CommandContext;
import me.dunctel123.jdatuts.command.ICommand;

public class HelloCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        ctx.getChannel().sendMessage("Hello").queue();
    }

    @Override
    public String getName() {
        return "hello";
    }

    @Override
    public String getHelp() {
        return "Says Hello";
    }
}
