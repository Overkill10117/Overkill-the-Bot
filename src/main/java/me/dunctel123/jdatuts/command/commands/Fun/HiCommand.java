package me.dunctel123.jdatuts.command.commands.Fun;

import me.dunctel123.jdatuts.command.ICommand;
import me.dunctel123.jdatuts.command.CommandContext;

public class HiCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        ctx.getChannel().sendMessageFormat("%s hi",
                ctx.getMessage().getMentionedMembers().get(0)).queue();
    }

    @Override
    public String getName() {
        return "hi";
    }

    @Override
    public String getHelp() {
        return "hi";
    }
}
